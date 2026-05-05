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

import com.entity.XuexidaziEntity;
import com.entity.view.XuexidaziView;

import com.service.XuexidaziService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 学习搭子
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
@RestController
@RequestMapping("/xuexidazi")
public class XuexidaziController {
    @Autowired
    private XuexidaziService xuexidaziService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XuexidaziEntity xuexidazi,
		HttpServletRequest request){
        if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
            xuexidazi.setUserid((Long)request.getSession().getAttribute("userId"));
        }
        //设置查询条件
        EntityWrapper<XuexidaziEntity> ew = new EntityWrapper<XuexidaziEntity>();


        //查询结果
		PageUtils page = xuexidaziService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuexidazi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,XuexidaziEntity xuexidazi,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fabushijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fabushijianend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<XuexidaziEntity> ew = new EntityWrapper<XuexidaziEntity>();
        if(fabushijianstart!=null) ew.ge("fabushijian", fabushijianstart);
        if(fabushijianend!=null) ew.le("fabushijian", fabushijianend);

        //查询结果
		PageUtils page = xuexidaziService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuexidazi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XuexidaziEntity xuexidazi){
       	EntityWrapper<XuexidaziEntity> ew = new EntityWrapper<XuexidaziEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xuexidazi, "xuexidazi"));
        return R.ok().put("data", xuexidaziService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XuexidaziEntity xuexidazi){
        EntityWrapper< XuexidaziEntity> ew = new EntityWrapper< XuexidaziEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xuexidazi, "xuexidazi"));
		XuexidaziView xuexidaziView =  xuexidaziService.selectView(ew);
		return R.ok("查询学习搭子成功").put("data", xuexidaziView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XuexidaziEntity xuexidazi = xuexidaziService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xuexidazi,deSens);
        return R.ok().put("data", xuexidazi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XuexidaziEntity xuexidazi = xuexidaziService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xuexidazi,deSens);
        return R.ok().put("data", xuexidazi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增学习搭子")
    public R save(@RequestBody XuexidaziEntity xuexidazi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuexidazi);
        if(xuexidazi.getUserid()==null){
            xuexidazi.setUserid((Long) request.getSession().getAttribute("userId"));
        }
        xuexidaziService.insert(xuexidazi);
        return R.ok().put("data",xuexidazi.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增学习搭子")
    @RequestMapping("/add")
    public R add(@RequestBody XuexidaziEntity xuexidazi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuexidazi);
        xuexidaziService.insert(xuexidazi);
        return R.ok().put("data",xuexidazi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改学习搭子")
    public R update(@RequestBody XuexidaziEntity xuexidazi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuexidazi);
        //全部更新
        xuexidaziService.updateById(xuexidazi);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除学习搭子")
    public R delete(@RequestBody Long[] ids){
        xuexidaziService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
