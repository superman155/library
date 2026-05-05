package com.schedule;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.QiandaodengjiEntity;
import com.entity.YonghuEntity;
import com.entity.ZuoweiyuyueEntity;
import com.service.QiandaodengjiService;
import com.service.YonghuService;
import com.service.ZuoweiyuyueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 预约超时自动处理定时任务
 * 
 * 功能：
 * 1. 检查已审核通过但超过30分钟未签到的预约
 * 2. 自动释放座位（更新预约状态为"已超时"）
 * 3. 记录违约次数，累计3次禁止预约
 * 4. 更新对应的签到审核记录状态
 */
@Component
@EnableScheduling
public class ReservationTimeoutTask {

    @Autowired
    private ZuoweiyuyueService zuoweiyuyueService;
    @Autowired
    private QiandaodengjiService qiandaodengjiService;
    @Autowired
    private YonghuService yonghuService;

    /**
     * 每分钟检查一次超时未签到的预约
     */
    @Scheduled(fixedRate = 60000) // 每60秒执行一次
    @Transactional
    public void checkTimeoutReservations() {
        // 查询所有已审核通过(sfsh=是)且签到状态为"未签到"的预约
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<>();
        ew.eq("sfsh", "是");
        ew.eq("qiandaozhuangtai", "未签到");
        ew.eq("reservationstate", "已确认");

        List<ZuoweiyuyueEntity> reservations = zuoweiyuyueService.selectList(ew);

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (ZuoweiyuyueEntity reservation : reservations) {
            try {
                // 构建预约的起始时间
                // reservationdate 是日期，timeslot 是时间段（如 "08:00-12:00"）
                Date startTime = parseStartTime(reservation.getReservationdate(), reservation.getTimeslot());
                if (startTime == null) {
                    continue;
                }

                // 计算超时截止时间（起始时间 + 30分钟）
                Calendar cal = Calendar.getInstance();
                cal.setTime(startTime);
                cal.add(Calendar.MINUTE, 30);
                Date deadline = cal.getTime();

                // 如果当前时间超过了截止时间，处理超时
                if (now.after(deadline)) {
                    handleTimeout(reservation);
                }
            } catch (Exception e) {
                // 单条记录处理异常不影响其他记录
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理超时预约
     */
    private void handleTimeout(ZuoweiyuyueEntity reservation) {
        // 1. 更新预约状态为"已超时"，释放座位
        reservation.setReservationstate("已超时");
        reservation.setQiandaozhuangtai("超时未到");
        reservation.setShhf("预约起始时间后30分钟未到场，座位自动释放");
        zuoweiyuyueService.updateById(reservation);

        // 2. 更新对应的签到审核记录为"否"（拒绝）
        EntityWrapper<QiandaodengjiEntity> qew = new EntityWrapper<>();
        qew.eq("yuyueid", reservation.getId());
        qew.eq("sfsh", "待审核");
        List<QiandaodengjiEntity> qiandaoList = qiandaodengjiService.selectList(qew);
        for (QiandaodengjiEntity qiandao : qiandaoList) {
            qiandao.setSfsh("否");
            qiandao.setShhf("超时未到场，自动拒绝");
            qiandao.setQiandaoshijian(new Date());
            qiandaodengjiService.updateById(qiandao);
        }

        // 3. 记录违约次数
        if (reservation.getXuehao() != null) {
            EntityWrapper<YonghuEntity> yew = new EntityWrapper<>();
            yew.eq("xuehao", reservation.getXuehao());
            YonghuEntity yonghu = yonghuService.selectOne(yew);
            if (yonghu != null) {
                int violationCount = (yonghu.getWeiyuecishu() == null ? 0 : yonghu.getWeiyuecishu()) + 1;
                yonghu.setWeiyuecishu(violationCount);

                // 累计3次违约，禁止预约
                if (violationCount >= 3) {
                    yonghu.setYuyuejinzhi(1);
                }
                yonghuService.updateById(yonghu);
            }
        }
    }

    /**
     * 解析预约的起始时间
     * @param date 预约日期
     * @param timeslot 时间段，格式如 "08:00-12:00" 或 "时间段1"
     * @return 起始时间的Date对象
     */
    private Date parseStartTime(Date date, String timeslot) {
        if (date == null || timeslot == null || timeslot.isEmpty()) {
            return null;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(date);

            // 尝试解析时间段格式 "HH:mm-HH:mm"
            if (timeslot.contains(":")) {
                String startTimeStr = timeslot.split("-")[0].trim();
                SimpleDateFormat fullSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                return fullSdf.parse(dateStr + " " + startTimeStr);
            }

            // 如果是"时间段X"这种格式，尝试用预设时间映射
            // 默认按上午8:00、下午14:00、晚上18:00处理
            SimpleDateFormat fullSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            if (timeslot.contains("1") || timeslot.contains("上午") || timeslot.contains("morning")) {
                return fullSdf.parse(dateStr + " 08:00");
            } else if (timeslot.contains("2") || timeslot.contains("下午") || timeslot.contains("afternoon")) {
                return fullSdf.parse(dateStr + " 14:00");
            } else if (timeslot.contains("3") || timeslot.contains("晚上") || timeslot.contains("evening")) {
                return fullSdf.parse(dateStr + " 18:00");
            }

            // 默认按上午8:00处理
            return fullSdf.parse(dateStr + " 08:00");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
