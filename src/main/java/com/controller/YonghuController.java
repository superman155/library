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
import com.service.TokenService;
import com.entity.TokenEntity;
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

import com.entity.YonghuEntity;
import com.entity.view.YonghuView;

import com.service.YonghuService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 用户
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-30 12:26:28
 */
@RestController
@RequestMapping("/yonghu")
public class YonghuController {
    @Autowired
    private YonghuService yonghuService;







	@Autowired
	private TokenService tokenService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("xuehao", username));
        if(u==null || !u.getMima().equals(password)) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 获取登录token
        String token = tokenService.generateToken(u.getId(), username,"yonghu",  "用户" );
        //返回token
		return R.ok().put("token", token);
	}

    /**
     * 人脸登录
     */
    @IgnoreAuth
    @RequestMapping(value = "/faceLogin")
    public R faceLogin(String face, HttpServletRequest request) {
        //获取所有用户信息
        List<YonghuEntity> uList = yonghuService.selectList(new EntityWrapper<YonghuEntity>());
        R result = null;
        YonghuEntity user = null;
        for(YonghuEntity u : uList) {
            Object tx = CommonUtil.getPropertyByFieldNames(u, "touxiang", "headportrait");
            if (null != tx && tx.toString().length()>0) {
                // 循环将集合中的用户头像跟前端提供的头像照片做比较
                result = CommonUtil.matchFace(face, tx.toString().substring(7));
                // 当照片相似度大于等于80时，则认为人脸匹配成功
                if (result.get("code").toString().equals("0")&&new BigDecimal(result.get("score").toString()).compareTo(new BigDecimal(80)) == 1) {
                    user = u;
                    break;
                }
            }
        }
        //当照片未匹配到相似度高于80的用户时，认为人脸识别失败，并返回提示
        if(user==null) {
            return R.error("人脸识别失败");
        }
        // 获取登录token
		String token = tokenService.generateToken(user.getId(), user.getXuehao(),"yonghu", "用户");
        //返回token及登录账号
        return R.ok().put("token", token).put("username", user.getXuehao());
    }


	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody YonghuEntity yonghu){
    	//ValidatorUtils.validateEntity(yonghu);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("xuehao", yonghu.getXuehao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同学号，否则返回错误信息
        if(yonghuService.selectCount(new EntityWrapper<YonghuEntity>().eq("xuehao", yonghu.getXuehao()))>0) {
            return R.error("学号已存在");
        }
		Long uId = new Date().getTime();
		yonghu.setId(uId);
        //保存用户
        yonghuService.insert(yonghu);
        return R.ok();
    }



	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}

	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        YonghuEntity u = yonghuService.selectById(id);
        return R.ok().put("data", u);
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("xuehao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456
        u.setMima("123456");
        yonghuService.updateById(u);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 获取账号列表
     */
    @RequestMapping("/accountList")
    public R getAccountList(@RequestParam Map<String, Object> params,YonghuEntity yonghu){
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();
        Wrapper<YonghuEntity> wrapper =MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yonghu), params), params);
        List<Map> list = yonghuService.selectList(wrapper).stream().map(v -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", v.getId());
            map.put("account", v.getXuehao());
            return map;
        }).collect(Collectors.toList());
        return R.ok().put("data", list);
    }






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YonghuEntity yonghu,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();


        //查询结果
		PageUtils page = yonghuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yonghu), params), params));
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
    public R list(@RequestParam Map<String, Object> params,YonghuEntity yonghu,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();

        //查询结果
		PageUtils page = yonghuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yonghu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YonghuEntity yonghu){
       	EntityWrapper<YonghuEntity> ew = new EntityWrapper<YonghuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yonghu, "yonghu"));
        return R.ok().put("data", yonghuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YonghuEntity yonghu){
        EntityWrapper< YonghuEntity> ew = new EntityWrapper< YonghuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yonghu, "yonghu"));
		YonghuView yonghuView =  yonghuService.selectView(ew);
		return R.ok("查询用户成功").put("data", yonghuView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YonghuEntity yonghu = yonghuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yonghu,deSens);
        return R.ok().put("data", yonghu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YonghuEntity yonghu = yonghuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yonghu,deSens);
        return R.ok().put("data", yonghu);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增用户")
    public R save(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(yonghuService.selectCount(new EntityWrapper<YonghuEntity>().eq("xuehao", yonghu.getXuehao()))>0) {
            return R.error("学号已存在");
        }
        //ValidatorUtils.validateEntity(yonghu);
        //验证账号唯一性，否则返回错误信息
        YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("xuehao", yonghu.getXuehao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	yonghu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		yonghu.setId(new Date().getTime());
        yonghuService.insert(yonghu);
        return R.ok().put("data",yonghu.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增用户")
    @RequestMapping("/add")
    public R add(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(yonghuService.selectCount(new EntityWrapper<YonghuEntity>().eq("xuehao", yonghu.getXuehao()))>0) {
            return R.error("学号已存在");
        }
        //ValidatorUtils.validateEntity(yonghu);
        //验证账号唯一性，否则返回错误信息
        YonghuEntity u = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("xuehao", yonghu.getXuehao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	yonghu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		yonghu.setId(new Date().getTime());
        yonghuService.insert(yonghu);
        return R.ok().put("data",yonghu.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改用户")
    public R update(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yonghu);
        //验证字段唯一性，否则返回错误信息
        if(yonghuService.selectCount(new EntityWrapper<YonghuEntity>().ne("id", yonghu.getId()).eq("xuehao", yonghu.getXuehao()))>0) {
            return R.error("学号已存在");
        }
        //全部更新
        yonghuService.updateById(yonghu);
        if(null!=yonghu.getXuehao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(yonghu.getXuehao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", yonghu.getId()));
        }
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除用户")
    public R delete(@RequestBody Long[] ids){
        yonghuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
