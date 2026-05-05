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

import com.entity.DiscussyuelanshiEntity;
import com.entity.view.DiscussyuelanshiView;

import com.service.DiscussyuelanshiService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 阅览室评论
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-30 12:26:30
 */
@RestController
@RequestMapping("/discussyuelanshi")
public class DiscussyuelanshiController {
    @Autowired
    private DiscussyuelanshiService discussyuelanshiService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussyuelanshiEntity discussyuelanshi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussyuelanshiEntity> ew = new EntityWrapper<DiscussyuelanshiEntity>();


        //查询结果
		PageUtils page = discussyuelanshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyuelanshi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscussyuelanshiEntity discussyuelanshi,
                @RequestParam(required = false) Double thumbsupnumstart,
                @RequestParam(required = false) Double thumbsupnumend,
                @RequestParam(required = false) Double crazilynumstart,
                @RequestParam(required = false) Double crazilynumend,
                @RequestParam(required = false) Double istopstart,
                @RequestParam(required = false) Double istopend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussyuelanshiEntity> ew = new EntityWrapper<DiscussyuelanshiEntity>();
        if(thumbsupnumstart!=null) ew.ge("thumbsupnum", thumbsupnumstart);
        if(thumbsupnumend!=null) ew.le("thumbsupnum", thumbsupnumend);
        if(crazilynumstart!=null) ew.ge("crazilynum", crazilynumstart);
        if(crazilynumend!=null) ew.le("crazilynum", crazilynumend);
        if(istopstart!=null) ew.ge("istop", istopstart);
        if(istopend!=null) ew.le("istop", istopend);

        //查询结果
		PageUtils page = discussyuelanshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyuelanshi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussyuelanshiEntity discussyuelanshi){
       	EntityWrapper<DiscussyuelanshiEntity> ew = new EntityWrapper<DiscussyuelanshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussyuelanshi, "discussyuelanshi"));
        return R.ok().put("data", discussyuelanshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussyuelanshiEntity discussyuelanshi){
        EntityWrapper< DiscussyuelanshiEntity> ew = new EntityWrapper< DiscussyuelanshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussyuelanshi, "discussyuelanshi"));
		DiscussyuelanshiView discussyuelanshiView =  discussyuelanshiService.selectView(ew);
		return R.ok("查询阅览室评论成功").put("data", discussyuelanshiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussyuelanshiEntity discussyuelanshi = discussyuelanshiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussyuelanshi,deSens);
        return R.ok().put("data", discussyuelanshi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussyuelanshiEntity discussyuelanshi = discussyuelanshiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussyuelanshi,deSens);
        return R.ok().put("data", discussyuelanshi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增阅览室评论")
    public R save(@RequestBody DiscussyuelanshiEntity discussyuelanshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyuelanshi);
        discussyuelanshiService.insert(discussyuelanshi);
        return R.ok().put("data",discussyuelanshi.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增阅览室评论")
    @RequestMapping("/add")
    public R add(@RequestBody DiscussyuelanshiEntity discussyuelanshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyuelanshi);
        discussyuelanshiService.insert(discussyuelanshi);
        return R.ok().put("data",discussyuelanshi.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussyuelanshiEntity discussyuelanshi = discussyuelanshiService.selectOne(new EntityWrapper<DiscussyuelanshiEntity>().eq("", username));
        return R.ok().put("data", discussyuelanshi);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussyuelanshiEntity discussyuelanshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyuelanshi);
        //全部更新
        discussyuelanshiService.updateById(discussyuelanshi);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除阅览室评论")
    public R delete(@RequestBody Long[] ids){
        discussyuelanshiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussyuelanshiEntity discussyuelanshi, HttpServletRequest request,String pre){
        EntityWrapper<DiscussyuelanshiEntity> ew = new EntityWrapper<DiscussyuelanshiEntity>();
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
		params.put("sort", "clicktime");
        params.put("order", "desc");

		PageUtils page = discussyuelanshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyuelanshi), params), params));
        return R.ok().put("data", page);
    }









}
