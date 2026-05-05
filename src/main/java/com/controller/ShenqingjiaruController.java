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

import com.entity.ShenqingjiaruEntity;
import com.entity.view.ShenqingjiaruView;

import com.service.ShenqingjiaruService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 申请加入
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
@RestController
@RequestMapping("/shenqingjiaru")
public class ShenqingjiaruController {
    @Autowired
    private ShenqingjiaruService shenqingjiaruService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShenqingjiaruEntity shenqingjiaru,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			shenqingjiaru.setXuehao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<ShenqingjiaruEntity> ew = new EntityWrapper<ShenqingjiaruEntity>();


        //查询结果
		PageUtils page = shenqingjiaruService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shenqingjiaru), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ShenqingjiaruEntity shenqingjiaru,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date shenqingshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date shenqingshijianend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ShenqingjiaruEntity> ew = new EntityWrapper<ShenqingjiaruEntity>();
        if(shenqingshijianstart!=null) ew.ge("shenqingshijian", shenqingshijianstart);
        if(shenqingshijianend!=null) ew.le("shenqingshijian", shenqingshijianend);

        //查询结果
		PageUtils page = shenqingjiaruService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shenqingjiaru), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShenqingjiaruEntity shenqingjiaru){
       	EntityWrapper<ShenqingjiaruEntity> ew = new EntityWrapper<ShenqingjiaruEntity>();
      	ew.allEq(MPUtil.allEQMapPre( shenqingjiaru, "shenqingjiaru"));
        return R.ok().put("data", shenqingjiaruService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShenqingjiaruEntity shenqingjiaru){
        EntityWrapper< ShenqingjiaruEntity> ew = new EntityWrapper< ShenqingjiaruEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shenqingjiaru, "shenqingjiaru"));
		ShenqingjiaruView shenqingjiaruView =  shenqingjiaruService.selectView(ew);
		return R.ok("查询申请加入成功").put("data", shenqingjiaruView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShenqingjiaruEntity shenqingjiaru = shenqingjiaruService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(shenqingjiaru,deSens);
        return R.ok().put("data", shenqingjiaru);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShenqingjiaruEntity shenqingjiaru = shenqingjiaruService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(shenqingjiaru,deSens);
        return R.ok().put("data", shenqingjiaru);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增申请加入")
    public R save(@RequestBody ShenqingjiaruEntity shenqingjiaru, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shenqingjiaru);
        shenqingjiaruService.insert(shenqingjiaru);
        return R.ok().put("data",shenqingjiaru.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增申请加入")
    @RequestMapping("/add")
    public R add(@RequestBody ShenqingjiaruEntity shenqingjiaru, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shenqingjiaru);
        shenqingjiaruService.insert(shenqingjiaru);
        return R.ok().put("data",shenqingjiaru.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改申请加入")
    public R update(@RequestBody ShenqingjiaruEntity shenqingjiaru, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shenqingjiaru);
        //全部更新
        shenqingjiaruService.updateById(shenqingjiaru);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除申请加入")
    public R delete(@RequestBody Long[] ids){
        shenqingjiaruService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
