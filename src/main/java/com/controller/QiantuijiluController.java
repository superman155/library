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

import com.entity.QiantuijiluEntity;
import com.entity.view.QiantuijiluView;
import com.entity.ZuoweiyuyueEntity;

import com.service.QiantuijiluService;
import com.service.ZuoweiyuyueService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 签退记录
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
@RestController
@RequestMapping("/qiantuijilu")
public class QiantuijiluController {
    @Autowired
    private QiantuijiluService qiantuijiluService;
    @Autowired
    private ZuoweiyuyueService zuoweiyuyueService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QiantuijiluEntity qiantuijilu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			qiantuijilu.setXuehao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("gongzuorenyuan")) {
			qiantuijilu.setYuangonggonghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<QiantuijiluEntity> ew = new EntityWrapper<QiantuijiluEntity>();


        //查询结果
		PageUtils page = qiantuijiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiantuijilu), params), params));
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
    public R list(@RequestParam Map<String, Object> params,QiantuijiluEntity qiantuijilu,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date qiantuishijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date qiantuishijianend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<QiantuijiluEntity> ew = new EntityWrapper<QiantuijiluEntity>();
        if(qiantuishijianstart!=null) ew.ge("qiantuishijian", qiantuishijianstart);
        if(qiantuishijianend!=null) ew.le("qiantuishijian", qiantuishijianend);

        //查询结果
		PageUtils page = qiantuijiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiantuijilu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QiantuijiluEntity qiantuijilu){
       	EntityWrapper<QiantuijiluEntity> ew = new EntityWrapper<QiantuijiluEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qiantuijilu, "qiantuijilu"));
        return R.ok().put("data", qiantuijiluService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QiantuijiluEntity qiantuijilu){
        EntityWrapper< QiantuijiluEntity> ew = new EntityWrapper< QiantuijiluEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qiantuijilu, "qiantuijilu"));
		QiantuijiluView qiantuijiluView =  qiantuijiluService.selectView(ew);
		return R.ok("查询签退记录成功").put("data", qiantuijiluView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QiantuijiluEntity qiantuijilu = qiantuijiluService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qiantuijilu,deSens);
        return R.ok().put("data", qiantuijilu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QiantuijiluEntity qiantuijilu = qiantuijiluService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qiantuijilu,deSens);
        return R.ok().put("data", qiantuijilu);
    }




    /**
     * 后台保存 — 签退后自动释放座位
     */
    @RequestMapping("/save")
    @SysLog("新增签退记录")
    public R save(@RequestBody QiantuijiluEntity qiantuijilu, HttpServletRequest request){
        qiantuijilu.setQiantuishijian(new Date());
        qiantuijiluService.insert(qiantuijilu);
        // 签退后自动释放座位
        releaseSeat(qiantuijilu);
        return R.ok().put("data",qiantuijilu.getId());
    }

    /**
     * 前台保存 — 用户签退后自动释放座位
     */
    @SysLog("新增签退记录")
    @RequestMapping("/add")
    public R add(@RequestBody QiantuijiluEntity qiantuijilu, HttpServletRequest request){
        qiantuijilu.setQiantuishijian(new Date());
        qiantuijiluService.insert(qiantuijilu);
        // 签退后自动释放座位
        releaseSeat(qiantuijilu);
        return R.ok().put("data",qiantuijilu.getId());
    }

    /**
     * 释放座位 — 根据签退记录找到对应预约，更新状态为已签退，释放座位
     */
    private void releaseSeat(QiantuijiluEntity qiantuijilu) {
        if(qiantuijilu.getCrossrefid() != null) {
            ZuoweiyuyueEntity zuoweiyuyue = zuoweiyuyueService.selectById(qiantuijilu.getCrossrefid());
            if(zuoweiyuyue != null) {
                zuoweiyuyue.setReservationstate("已签退");
                zuoweiyuyue.setQiandaozhuangtai("已签退");
                zuoweiyuyueService.updateById(zuoweiyuyue);
            }
        }
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改签退记录")
    public R update(@RequestBody QiantuijiluEntity qiantuijilu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qiantuijilu);
        //全部更新
        qiantuijiluService.updateById(qiantuijilu);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除签退记录")
    public R delete(@RequestBody Long[] ids){
        qiantuijiluService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }







    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, @RequestParam(required = false, defaultValue = "总和") String func, HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_qiantuijilu_" + xColumnName + "_" + yColumnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        //构建查询统计条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("method", func);
        params.put("order", request.getParameter("order"));
        params.put("orderType", request.getParameter("orderType"));
        EntityWrapper<QiantuijiluEntity> ew = new EntityWrapper<QiantuijiluEntity>();
        if(StringUtils.isNotBlank(conditionColumn)&&StringUtils.isNotBlank(conditionValue))
        {
            String[] conditionColumns = conditionColumn.split(";");
            String[] conditionValues = conditionValue.split(";");

            for (int i = 0; i < conditionColumns.length; i++) {
                String column = conditionColumns[i];
                String value = conditionValues[i];

                // 处理范围查询：如果列名包含逗号，表示是范围查询
                if (column.contains(",")) {
                    String[] rangeColumns = column.split(",");
                    String[] rangeValues = value.split(",");

                    if (rangeColumns.length == 2 && rangeValues.length == 2) {
                        // 第一个列名使用 >= 条件
                        ew.ge(rangeColumns[0], rangeValues[0]);
                        // 第二个列名使用 <= 条件
                        ew.le(rangeColumns[1], rangeValues[1]);
                    }
                } else {
                    // 普通等值查询
                    ew.eq(column, value);
                }
            }
        }
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            ew.eq("xuehao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("gongzuorenyuan")) {
            ew.eq("yuangonggonghao", (String)request.getSession().getAttribute("username"));
        }
        //获取结果
        List<Map<String, Object>> result = qiantuijiluService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, HttpServletRequest request)  throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_qiantuijilu_" + xColumnName + "_" + String.join("_", yColumnNameMul.split(",")) + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("order", request.getParameter("order"));
        params.put("orderType", request.getParameter("orderType"));
        //构建查询统计条件
        EntityWrapper<QiantuijiluEntity> ew = new EntityWrapper<QiantuijiluEntity>();
        if(StringUtils.isNotBlank(conditionColumn)&&StringUtils.isNotBlank(conditionValue))
        {
            String[] conditionColumns = conditionColumn.split(";");
            String[] conditionValues = conditionValue.split(";");

            for (int i = 0; i < conditionColumns.length; i++) {
                String column = conditionColumns[i];
                String value = conditionValues[i];

                // 处理范围查询：如果列名包含逗号，表示是范围查询
                if (column.contains(",")) {
                    String[] rangeColumns = column.split(",");
                    String[] rangeValues = value.split(",");

                    if (rangeColumns.length == 2 && rangeValues.length == 2) {
                        // 第一个列名使用 >= 条件
                        ew.ge(rangeColumns[0], rangeValues[0]);
                        // 第二个列名使用 <= 条件
                        ew.le(rangeColumns[1], rangeValues[1]);
                    }
                } else {
                    // 普通等值查询
                    ew.eq(column, value);
                }
            }
        }
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            ew.eq("xuehao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("gongzuorenyuan")) {
            ew.eq("yuangonggonghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = qiantuijiluService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, @RequestParam(required = false, defaultValue = "总和") String func, HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_qiantuijilu_" + xColumnName + "_" + yColumnName + "_"+timeStatType+".json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        params.put("method", func);
        //构建查询统计条件
        EntityWrapper<QiantuijiluEntity> ew = new EntityWrapper<QiantuijiluEntity>();
        String order = request.getParameter("order");
        if (StringUtils.isNotBlank(order)) {
            String orderType = request.getParameter("orderType");
            if (null != orderType) {
                ew.orderBy(true, orderType.equals("x") ? xColumnName : yColumnName, order.equals("desc"));
            }
        }
        if(StringUtils.isNotBlank(conditionColumn)&&StringUtils.isNotBlank(conditionValue))
        {
            String[] conditionColumns = conditionColumn.split(";");
            String[] conditionValues = conditionValue.split(";");

            for (int i = 0; i < conditionColumns.length; i++) {
                String column = conditionColumns[i];
                String value = conditionValues[i];

                // 处理范围查询：如果列名包含逗号，表示是范围查询
                if (column.contains(",")) {
                    String[] rangeColumns = column.split(",");
                    String[] rangeValues = value.split(",");

                    if (rangeColumns.length == 2 && rangeValues.length == 2) {
                        // 第一个列名使用 >= 条件
                        ew.ge(rangeColumns[0], rangeValues[0]);
                        // 第二个列名使用 <= 条件
                        ew.le(rangeColumns[1], rangeValues[1]);
                    }
                } else {
                    // 普通等值查询
                    ew.eq(column, value);
                }
            }
        }
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            ew.eq("xuehao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("gongzuorenyuan")) {
            ew.eq("yuangonggonghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = qiantuijiluService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType, @RequestParam String yColumnNameMul, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_qiantuijilu_" + xColumnName + "_" + String.join("_", yColumnNameMul.split(",")) + ".json");
        if (java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        //构建查询统计条件
        EntityWrapper<QiantuijiluEntity> ew = new EntityWrapper<QiantuijiluEntity>();
        String order = request.getParameter("order");
        if (StringUtils.isNotBlank(order)) {
            String orderType = request.getParameter("orderType");
            if (null != orderType) {
                ew.orderBy(true, orderType.equals("x") ? Arrays.asList(xColumnName) : Arrays.asList(yColumnNames), order.equals("desc"));
            }
        }
        if(StringUtils.isNotBlank(conditionColumn)&&StringUtils.isNotBlank(conditionValue))
        {
            String[] conditionColumns = conditionColumn.split(";");
            String[] conditionValues = conditionValue.split(";");

            for (int i = 0; i < conditionColumns.length; i++) {
                String column = conditionColumns[i];
                String value = conditionValues[i];

                // 处理范围查询：如果列名包含逗号，表示是范围查询
                if (column.contains(",")) {
                    String[] rangeColumns = column.split(",");
                    String[] rangeValues = value.split(",");

                    if (rangeColumns.length == 2 && rangeValues.length == 2) {
                        // 第一个列名使用 >= 条件
                        ew.ge(rangeColumns[0], rangeValues[0]);
                        // 第二个列名使用 <= 条件
                        ew.le(rangeColumns[1], rangeValues[1]);
                    }
                } else {
                    // 普通等值查询
                    ew.eq(column, value);
                }
            }
        }
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            ew.eq("xuehao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("gongzuorenyuan")) {
            ew.eq("yuangonggonghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = qiantuijiluService.selectTimeStatValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("group_qiantuijilu_" + columnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)){
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        //构建查询统计条件
        EntityWrapper<QiantuijiluEntity> ew = new EntityWrapper<QiantuijiluEntity>();
        if(StringUtils.isNotBlank(conditionColumn)&&StringUtils.isNotBlank(conditionValue))
        {
            String[] conditionColumns = conditionColumn.split(";");
            String[] conditionValues = conditionValue.split(";");

            for (int i = 0; i < conditionColumns.length; i++) {
                String column = conditionColumns[i];
                String value = conditionValues[i];

                // 处理范围查询：如果列名包含逗号，表示是范围查询
                if (column.contains(",")) {
                    String[] rangeColumns = column.split(",");
                    String[] rangeValues = value.split(",");

                    if (rangeColumns.length == 2 && rangeValues.length == 2) {
                        // 第一个列名使用 >= 条件
                        ew.ge(rangeColumns[0], rangeValues[0]);
                        // 第二个列名使用 <= 条件
                        ew.le(rangeColumns[1], rangeValues[1]);
                    }
                } else {
                    // 普通等值查询
                    ew.eq(column, value);
                }
            }
        }
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            ew.eq("xuehao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("gongzuorenyuan")) {
            ew.eq("yuangonggonghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = qiantuijiluService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }




    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,QiantuijiluEntity qiantuijilu, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            qiantuijilu.setXuehao((String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("gongzuorenyuan")) {
            qiantuijilu.setYuangonggonghao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<QiantuijiluEntity> ew = new EntityWrapper<QiantuijiluEntity>();
        int count = qiantuijiluService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiantuijilu), params), params));
        return R.ok().put("data", count);
    }


}
