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
import com.utils.UserBasedCollaborativeFiltering;
import com.algorithm.recommend.RecommendAlgorithmFactory;

import com.entity.YuelanshiEntity;
import com.entity.view.YuelanshiView;

import com.service.YuelanshiService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 阅览室
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-30 12:26:28
 */
@RestController
@RequestMapping("/yuelanshi")
public class YuelanshiController {
    @Autowired
    private YuelanshiService yuelanshiService;

    @Autowired
    private StoreupService storeupService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YuelanshiEntity yuelanshi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("gongzuorenyuan")) {
			yuelanshi.setYuangonggonghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<YuelanshiEntity> ew = new EntityWrapper<YuelanshiEntity>();


        //查询结果
		PageUtils page = yuelanshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuelanshi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,YuelanshiEntity yuelanshi,
                @RequestParam(required = false) Double columnsstart,
                @RequestParam(required = false) Double columnsend,
                @RequestParam(required = false) Double rowsstart,
                @RequestParam(required = false) Double rowsend,
                @RequestParam(required = false) Double totalsstart,
                @RequestParam(required = false) Double totalsend,
                @RequestParam(required = false) Double thumbsupnumstart,
                @RequestParam(required = false) Double thumbsupnumend,
                @RequestParam(required = false) Double crazilynumstart,
                @RequestParam(required = false) Double crazilynumend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date clicktimestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date clicktimeend,
                @RequestParam(required = false) Double clicknumstart,
                @RequestParam(required = false) Double clicknumend,
                @RequestParam(required = false) Double discussnumstart,
                @RequestParam(required = false) Double discussnumend,
                @RequestParam(required = false) Double storeupnumstart,
                @RequestParam(required = false) Double storeupnumend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YuelanshiEntity> ew = new EntityWrapper<YuelanshiEntity>();
        if(columnsstart!=null) ew.ge("columns", columnsstart);
        if(columnsend!=null) ew.le("columns", columnsend);
        if(rowsstart!=null) ew.ge("rows", rowsstart);
        if(rowsend!=null) ew.le("rows", rowsend);
        if(totalsstart!=null) ew.ge("totals", totalsstart);
        if(totalsend!=null) ew.le("totals", totalsend);
        if(thumbsupnumstart!=null) ew.ge("thumbsupnum", thumbsupnumstart);
        if(thumbsupnumend!=null) ew.le("thumbsupnum", thumbsupnumend);
        if(crazilynumstart!=null) ew.ge("crazilynum", crazilynumstart);
        if(crazilynumend!=null) ew.le("crazilynum", crazilynumend);
        if(clicktimestart!=null) ew.ge("clicktime", clicktimestart);
        if(clicktimeend!=null) ew.le("clicktime", clicktimeend);
        if(clicknumstart!=null) ew.ge("clicknum", clicknumstart);
        if(clicknumend!=null) ew.le("clicknum", clicknumend);
        if(discussnumstart!=null) ew.ge("discussnum", discussnumstart);
        if(discussnumend!=null) ew.le("discussnum", discussnumend);
        if(storeupnumstart!=null) ew.ge("storeupnum", storeupnumstart);
        if(storeupnumend!=null) ew.le("storeupnum", storeupnumend);

        //查询结果
		PageUtils page = yuelanshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuelanshi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YuelanshiEntity yuelanshi){
       	EntityWrapper<YuelanshiEntity> ew = new EntityWrapper<YuelanshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yuelanshi, "yuelanshi"));
        return R.ok().put("data", yuelanshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YuelanshiEntity yuelanshi){
        EntityWrapper< YuelanshiEntity> ew = new EntityWrapper< YuelanshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yuelanshi, "yuelanshi"));
		YuelanshiView yuelanshiView =  yuelanshiService.selectView(ew);
		return R.ok("查询阅览室成功").put("data", yuelanshiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YuelanshiEntity yuelanshi = yuelanshiService.selectById(id);
        if(null==yuelanshi.getClicknum()){
            yuelanshi.setClicknum(0);
        }
		yuelanshi.setClicknum(yuelanshi.getClicknum()+1);
		yuelanshiService.updateById(yuelanshi);
        yuelanshi = yuelanshiService.selectView(new EntityWrapper<YuelanshiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yuelanshi,deSens);
        return R.ok().put("data", yuelanshi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YuelanshiEntity yuelanshi = yuelanshiService.selectById(id);
        if(null==yuelanshi.getClicknum()){
            yuelanshi.setClicknum(0);
        }
		yuelanshi.setClicknum(yuelanshi.getClicknum()+1);
		yuelanshiService.updateById(yuelanshi);
        yuelanshi = yuelanshiService.selectView(new EntityWrapper<YuelanshiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yuelanshi,deSens);
        return R.ok().put("data", yuelanshi);
    }



    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        YuelanshiEntity yuelanshi = yuelanshiService.selectById(id);
        if(type.equals("1")) {
        	yuelanshi.setThumbsupnum(yuelanshi.getThumbsupnum()+1);
        } else {
        	yuelanshi.setCrazilynum(yuelanshi.getCrazilynum()+1);
        }
        yuelanshiService.updateById(yuelanshi);
        return R.ok("投票成功");
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增阅览室")
    public R save(@RequestBody YuelanshiEntity yuelanshi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(yuelanshiService.selectCount(new EntityWrapper<YuelanshiEntity>().eq("refno", yuelanshi.getRefno()))>0) {
            return R.error("编号已存在");
        }
        //ValidatorUtils.validateEntity(yuelanshi);
        yuelanshiService.insert(yuelanshi);
        return R.ok().put("data",yuelanshi.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增阅览室")
    @RequestMapping("/add")
    public R add(@RequestBody YuelanshiEntity yuelanshi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(yuelanshiService.selectCount(new EntityWrapper<YuelanshiEntity>().eq("refno", yuelanshi.getRefno()))>0) {
            return R.error("编号已存在");
        }
        //ValidatorUtils.validateEntity(yuelanshi);
        yuelanshiService.insert(yuelanshi);
        return R.ok().put("data",yuelanshi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改阅览室")
    public R update(@RequestBody YuelanshiEntity yuelanshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yuelanshi);
        //验证字段唯一性，否则返回错误信息
        if(yuelanshiService.selectCount(new EntityWrapper<YuelanshiEntity>().ne("id", yuelanshi.getId()).eq("refno", yuelanshi.getRefno()))>0) {
            return R.error("编号已存在");
        }
        //全部更新
        yuelanshiService.updateById(yuelanshi);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除阅览室")
    public R delete(@RequestBody Long[] ids){
        yuelanshiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,YuelanshiEntity yuelanshi, HttpServletRequest request,String pre){
        EntityWrapper<YuelanshiEntity> ew = new EntityWrapper<YuelanshiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicknum");
        params.put("order", "desc");

		PageUtils page = yuelanshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuelanshi), params), params));
        return R.ok().put("data", page);
    }



    /**
     * 协同算法（基于用户的协同算法：购买、收藏、关注）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,YuelanshiEntity yuelanshi, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        Integer limit = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        //用户行为数据
        List<RecommendAlgorithmFactory.UserBehavior> userBehaviors = new ArrayList<>();
        // 查询收藏/关注数据
        userBehaviors.addAll(storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1 ).eq("tablename", "yuelanshi")).stream().map(storeup -> {
            return new RecommendAlgorithmFactory.UserBehavior(storeup.getUserid(), storeup.getRefid());
        }).collect(Collectors.toList()));
        // 查询点赞数据
        userBehaviors.addAll(storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 21).eq("tablename", "yuelanshi")).stream().map(storeup -> {
            return new RecommendAlgorithmFactory.UserBehavior(storeup.getUserid(), storeup.getRefid());
        }).collect(Collectors.toList()));
        // 根据物品推荐用户
        List<Long> recommendations = new ArrayList<>();
        // 用协 算法推荐物品id
        RecommendAlgorithmFactory.UserItemMatrixRecommender recommender = RecommendAlgorithmFactory.buildUserItemMatrix(userBehaviors);
        // 输出矩阵
        recommender.printCollectionMatrix();
        // 输出相似度矩阵
        recommender.printSimilarityMatrix();
        // 输出指定用户的余弦相似度详情
        recommender.printDetailedCosineSimilarity(Long.parseLong(userId));
        // 获取推荐结果（带得分）
        List<Map<String, Object>> itemIds = recommender.recommendItemsWithDetails(Long.parseLong(userId), limit);
        recommendations = recommender.recommendItems(Long.parseLong(userId), limit);
        // 输出推荐结果
        System.out.println("Recommendations for " + userId + ":");
        recommendations.forEach(recommendation -> System.out.println(recommendation));
        EntityWrapper<YuelanshiEntity> ew = new EntityWrapper<YuelanshiEntity>();

        if(recommendations!=null && recommendations.size()>0) {
            ew.in("id", recommendations);
            ew.last("order by FIELD(id, "+String.join(",", recommendations.stream().map(String::valueOf).collect(Collectors.toList()))+")");
        }
        // 根据协同结果查询结果并返回
        PageUtils page = yuelanshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuelanshi), params), params));
        List<YuelanshiEntity> pageList = (List<YuelanshiEntity>)page.getList();
        if(recommendations!=null && recommendations.size()>0 && pageList.size()<limit) {
            int toAddNum = limit-pageList.size();
            ew = new EntityWrapper<YuelanshiEntity>();
            ew.notIn("id", recommendations);
            ew.orderBy("id", false);
            ew.last("limit "+toAddNum);
            pageList.addAll(yuelanshiService.selectList(ew));
        } else if(pageList.size()>limit) {
            pageList = pageList.subList(0, limit);
        }
        page.setList(pageList);
        return R.ok().put("data", page);
    }



    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, @RequestParam(required = false, defaultValue = "总和") String func, HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_yuelanshi_" + xColumnName + "_" + yColumnName + "_timeType.json");
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
        EntityWrapper<YuelanshiEntity> ew = new EntityWrapper<YuelanshiEntity>();
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
        if(tableName.equals("gongzuorenyuan")) {
            ew.eq("yuangonggonghao", (String)request.getSession().getAttribute("username"));
        }
        //获取结果
        List<Map<String, Object>> result = yuelanshiService.selectValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_yuelanshi_" + xColumnName + "_" + String.join("_", yColumnNameMul.split(",")) + "_timeType.json");
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
        EntityWrapper<YuelanshiEntity> ew = new EntityWrapper<YuelanshiEntity>();
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
        if(tableName.equals("gongzuorenyuan")) {
            ew.eq("yuangonggonghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = yuelanshiService.selectValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_yuelanshi_" + xColumnName + "_" + yColumnName + "_"+timeStatType+".json");
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
        EntityWrapper<YuelanshiEntity> ew = new EntityWrapper<YuelanshiEntity>();
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
        if(tableName.equals("gongzuorenyuan")) {
            ew.eq("yuangonggonghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = yuelanshiService.selectTimeStatValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_yuelanshi_" + xColumnName + "_" + String.join("_", yColumnNameMul.split(",")) + ".json");
        if (java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        //构建查询统计条件
        EntityWrapper<YuelanshiEntity> ew = new EntityWrapper<YuelanshiEntity>();
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
        if(tableName.equals("gongzuorenyuan")) {
            ew.eq("yuangonggonghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = yuelanshiService.selectTimeStatValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("group_yuelanshi_" + columnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)){
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        //构建查询统计条件
        EntityWrapper<YuelanshiEntity> ew = new EntityWrapper<YuelanshiEntity>();
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
        if(tableName.equals("gongzuorenyuan")) {
            ew.eq("yuangonggonghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = yuelanshiService.selectGroup(params, ew);
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
    public R count(@RequestParam Map<String, Object> params,YuelanshiEntity yuelanshi, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("gongzuorenyuan")) {
            yuelanshi.setYuangonggonghao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<YuelanshiEntity> ew = new EntityWrapper<YuelanshiEntity>();
        int count = yuelanshiService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuelanshi), params), params));
        return R.ok().put("data", count);
    }


}
