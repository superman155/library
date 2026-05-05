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

import com.entity.ZangshuleixingEntity;
import com.entity.view.ZangshuleixingView;

import com.service.ZangshuleixingService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 藏书类型
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-30 12:26:28
 */
@RestController
@RequestMapping("/zangshuleixing")
public class ZangshuleixingController {
    @Autowired
    private ZangshuleixingService zangshuleixingService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZangshuleixingEntity zangshuleixing,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZangshuleixingEntity> ew = new EntityWrapper<ZangshuleixingEntity>();


        //查询结果
		PageUtils page = zangshuleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zangshuleixing), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ZangshuleixingEntity zangshuleixing,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZangshuleixingEntity> ew = new EntityWrapper<ZangshuleixingEntity>();

        //查询结果
		PageUtils page = zangshuleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zangshuleixing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZangshuleixingEntity zangshuleixing){
       	EntityWrapper<ZangshuleixingEntity> ew = new EntityWrapper<ZangshuleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zangshuleixing, "zangshuleixing"));
        return R.ok().put("data", zangshuleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZangshuleixingEntity zangshuleixing){
        EntityWrapper< ZangshuleixingEntity> ew = new EntityWrapper< ZangshuleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zangshuleixing, "zangshuleixing"));
		ZangshuleixingView zangshuleixingView =  zangshuleixingService.selectView(ew);
		return R.ok("查询藏书类型成功").put("data", zangshuleixingView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZangshuleixingEntity zangshuleixing = zangshuleixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zangshuleixing,deSens);
        return R.ok().put("data", zangshuleixing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZangshuleixingEntity zangshuleixing = zangshuleixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zangshuleixing,deSens);
        return R.ok().put("data", zangshuleixing);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增藏书类型")
    public R save(@RequestBody ZangshuleixingEntity zangshuleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zangshuleixing);
        zangshuleixingService.insert(zangshuleixing);
        return R.ok().put("data",zangshuleixing.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增藏书类型")
    @RequestMapping("/add")
    public R add(@RequestBody ZangshuleixingEntity zangshuleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zangshuleixing);
        zangshuleixingService.insert(zangshuleixing);
        return R.ok().put("data",zangshuleixing.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改藏书类型")
    public R update(@RequestBody ZangshuleixingEntity zangshuleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zangshuleixing);
        //全部更新
        zangshuleixingService.updateById(zangshuleixing);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除藏书类型")
    public R delete(@RequestBody Long[] ids){
        zangshuleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
