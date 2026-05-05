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

import com.entity.ZuoweiyuyueEntity;
import com.entity.view.ZuoweiyuyueView;

import com.service.ZuoweiyuyueService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 座位预约
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-30 12:26:28
 */
@RestController
@RequestMapping("/zuoweiyuyue")
public class ZuoweiyuyueController {
    @Autowired
    private ZuoweiyuyueService zuoweiyuyueService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZuoweiyuyueEntity zuoweiyuyue,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdatestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdateend,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			zuoweiyuyue.setXuehao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("gongzuorenyuan")) {
			zuoweiyuyue.setYuangonggonghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
        if(reservationdatestart!=null) ew.ge("reservationdate", reservationdatestart);
        if(reservationdateend!=null) ew.le("reservationdate", reservationdateend);


        //查询结果
		PageUtils page = zuoweiyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zuoweiyuyue), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ZuoweiyuyueEntity zuoweiyuyue,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdatestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date reservationdateend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
        if(reservationdatestart!=null) ew.ge("reservationdate", reservationdatestart);
        if(reservationdateend!=null) ew.le("reservationdate", reservationdateend);

        //查询结果
		PageUtils page = zuoweiyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zuoweiyuyue), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZuoweiyuyueEntity zuoweiyuyue){
       	EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zuoweiyuyue, "zuoweiyuyue"));
        return R.ok().put("data", zuoweiyuyueService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZuoweiyuyueEntity zuoweiyuyue){
        EntityWrapper< ZuoweiyuyueEntity> ew = new EntityWrapper< ZuoweiyuyueEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zuoweiyuyue, "zuoweiyuyue"));
		ZuoweiyuyueView zuoweiyuyueView =  zuoweiyuyueService.selectView(ew);
		return R.ok("查询座位预约成功").put("data", zuoweiyuyueView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZuoweiyuyueEntity zuoweiyuyue = zuoweiyuyueService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zuoweiyuyue,deSens);
        return R.ok().put("data", zuoweiyuyue);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZuoweiyuyueEntity zuoweiyuyue = zuoweiyuyueService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zuoweiyuyue,deSens);
        return R.ok().put("data", zuoweiyuyue);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增座位预约")
    public R save(@RequestBody ZuoweiyuyueEntity zuoweiyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zuoweiyuyue);
        zuoweiyuyueService.insert(zuoweiyuyue);
        return R.ok().put("data",zuoweiyuyue.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增座位预约")
    @RequestMapping("/add")
    public R add(@RequestBody ZuoweiyuyueEntity zuoweiyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zuoweiyuyue);
        zuoweiyuyueService.insert(zuoweiyuyue);
        return R.ok().put("data",zuoweiyuyue.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改座位预约")
    public R update(@RequestBody ZuoweiyuyueEntity zuoweiyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zuoweiyuyue);
        //全部更新
        zuoweiyuyueService.updateById(zuoweiyuyue);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    @SysLog("审核座位预约")
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ZuoweiyuyueEntity> list = new ArrayList<ZuoweiyuyueEntity>();
        for(Long id : ids) {
            ZuoweiyuyueEntity zuoweiyuyue = zuoweiyuyueService.selectById(id);
            zuoweiyuyue.setSfsh(sfsh);
            zuoweiyuyue.setShhf(shhf);
            list.add(zuoweiyuyue);
        }
        zuoweiyuyueService.updateBatchById(list);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除座位预约")
    public R delete(@RequestBody Long[] ids){
        zuoweiyuyueService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }







    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, @RequestParam(required = false, defaultValue = "总和") String func, HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_zuoweiyuyue_" + xColumnName + "_" + yColumnName + "_timeType.json");
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
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
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
        List<Map<String, Object>> result = zuoweiyuyueService.selectValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_zuoweiyuyue_" + xColumnName + "_" + String.join("_", yColumnNameMul.split(",")) + "_timeType.json");
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
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
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
            List<Map<String, Object>> result = zuoweiyuyueService.selectValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_zuoweiyuyue_" + xColumnName + "_" + yColumnName + "_"+timeStatType+".json");
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
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
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
        List<Map<String, Object>> result = zuoweiyuyueService.selectTimeStatValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_zuoweiyuyue_" + xColumnName + "_" + String.join("_", yColumnNameMul.split(",")) + ".json");
        if (java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        //构建查询统计条件
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
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
            List<Map<String, Object>> result = zuoweiyuyueService.selectTimeStatValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("group_zuoweiyuyue_" + columnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)){
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        //构建查询统计条件
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
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
        List<Map<String, Object>> result = zuoweiyuyueService.selectGroup(params, ew);
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
    public R count(@RequestParam Map<String, Object> params,ZuoweiyuyueEntity zuoweiyuyue, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            zuoweiyuyue.setXuehao((String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("gongzuorenyuan")) {
            zuoweiyuyue.setYuangonggonghao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<ZuoweiyuyueEntity> ew = new EntityWrapper<ZuoweiyuyueEntity>();
        int count = zuoweiyuyueService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zuoweiyuyue), params), params));
        return R.ok().put("data", count);
    }


}
