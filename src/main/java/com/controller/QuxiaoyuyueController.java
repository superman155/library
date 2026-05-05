package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import com.utils.*;
import com.service.*;
import com.entity.*;
import com.entity.view.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;
import com.annotation.SysLog;

import com.entity.QuxiaoyuyueEntity;
import com.entity.view.QuxiaoyuyueView;

import com.service.QuxiaoyuyueService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 取消预约
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
@RestController
@RequestMapping("/quxiaoyuyue")
public class QuxiaoyuyueController {
    @Autowired
    private QuxiaoyuyueService quxiaoyuyueService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QuxiaoyuyueEntity quxiaoyuyue,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdatestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdateend,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			quxiaoyuyue.setXuehao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("gongzuorenyuan")) {
			quxiaoyuyue.setYuangonggonghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<QuxiaoyuyueEntity> ew = new EntityWrapper<QuxiaoyuyueEntity>();
        if(reservationdatestart!=null) ew.ge("reservationdate", reservationdatestart);
        if(reservationdateend!=null) ew.le("reservationdate", reservationdateend);


        //查询结果
		PageUtils page = quxiaoyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, quxiaoyuyue), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,QuxiaoyuyueEntity quxiaoyuyue,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdatestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdateend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date quxiaoshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date quxiaoshijianend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<QuxiaoyuyueEntity> ew = new EntityWrapper<QuxiaoyuyueEntity>();
        if(reservationdatestart!=null) ew.ge("reservationdate", reservationdatestart);
        if(reservationdateend!=null) ew.le("reservationdate", reservationdateend);
        if(quxiaoshijianstart!=null) ew.ge("quxiaoshijian", quxiaoshijianstart);
        if(quxiaoshijianend!=null) ew.le("quxiaoshijian", quxiaoshijianend);

        //查询结果
		PageUtils page = quxiaoyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, quxiaoyuyue), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QuxiaoyuyueEntity quxiaoyuyue){
       	EntityWrapper<QuxiaoyuyueEntity> ew = new EntityWrapper<QuxiaoyuyueEntity>();
      	ew.allEq(MPUtil.allEQMapPre( quxiaoyuyue, "quxiaoyuyue"));
        return R.ok().put("data", quxiaoyuyueService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QuxiaoyuyueEntity quxiaoyuyue){
        EntityWrapper< QuxiaoyuyueEntity> ew = new EntityWrapper< QuxiaoyuyueEntity>();
 		ew.allEq(MPUtil.allEQMapPre( quxiaoyuyue, "quxiaoyuyue"));
		QuxiaoyuyueView quxiaoyuyueView =  quxiaoyuyueService.selectView(ew);
		return R.ok("查询取消预约成功").put("data", quxiaoyuyueView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QuxiaoyuyueEntity quxiaoyuyue = quxiaoyuyueService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(quxiaoyuyue,deSens);
        return R.ok().put("data", quxiaoyuyue);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QuxiaoyuyueEntity quxiaoyuyue = quxiaoyuyueService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(quxiaoyuyue,deSens);
        return R.ok().put("data", quxiaoyuyue);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增取消预约")
    public R save(@RequestBody QuxiaoyuyueEntity quxiaoyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(quxiaoyuyue);
        quxiaoyuyueService.insert(quxiaoyuyue);
        return R.ok().put("data",quxiaoyuyue.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增取消预约")
    @RequestMapping("/add")
    public R add(@RequestBody QuxiaoyuyueEntity quxiaoyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(quxiaoyuyue);
        quxiaoyuyueService.insert(quxiaoyuyue);
        return R.ok().put("data",quxiaoyuyue.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改取消预约")
    public R update(@RequestBody QuxiaoyuyueEntity quxiaoyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(quxiaoyuyue);
        //全部更新
        quxiaoyuyueService.updateById(quxiaoyuyue);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除取消预约")
    public R delete(@RequestBody Long[] ids){
        quxiaoyuyueService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
