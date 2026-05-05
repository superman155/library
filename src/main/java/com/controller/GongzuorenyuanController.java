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

import com.entity.GongzuorenyuanEntity;
import com.entity.view.GongzuorenyuanView;

import com.service.GongzuorenyuanService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 工作人员
 * 后端接口
 * @author 
 * @email 
 * @date 2026-04-30 12:26:28
 */
@RestController
@RequestMapping("/gongzuorenyuan")
public class GongzuorenyuanController {
    @Autowired
    private GongzuorenyuanService gongzuorenyuanService;







	@Autowired
	private TokenService tokenService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        GongzuorenyuanEntity u = gongzuorenyuanService.selectOne(new EntityWrapper<GongzuorenyuanEntity>().eq("yuangonggonghao", username));
        if(u==null || !u.getMima().equals(password)) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 如果用户是待审核状态，返回请联系管理员审核提示
        if(!"是".equals(u.getSfsh())) return R.error("账号已锁定，请联系管理员审核。");
        // 获取登录token
        String token = tokenService.generateToken(u.getId(), username,"gongzuorenyuan",  "工作人员" );
        //返回token
		return R.ok().put("token", token);
	}



	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody GongzuorenyuanEntity gongzuorenyuan){
    	//ValidatorUtils.validateEntity(gongzuorenyuan);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	GongzuorenyuanEntity u = gongzuorenyuanService.selectOne(new EntityWrapper<GongzuorenyuanEntity>().eq("yuangonggonghao", gongzuorenyuan.getYuangonggonghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同员工工号，否则返回错误信息
        if(gongzuorenyuanService.selectCount(new EntityWrapper<GongzuorenyuanEntity>().eq("yuangonggonghao", gongzuorenyuan.getYuangonggonghao()))>0) {
            return R.error("员工工号已存在");
        }
		Long uId = new Date().getTime();
		gongzuorenyuan.setId(uId);
        //保存用户
        gongzuorenyuanService.insert(gongzuorenyuan);
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
        GongzuorenyuanEntity u = gongzuorenyuanService.selectById(id);
        return R.ok().put("data", u);
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        GongzuorenyuanEntity u = gongzuorenyuanService.selectOne(new EntityWrapper<GongzuorenyuanEntity>().eq("yuangonggonghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456
        u.setMima("123456");
        gongzuorenyuanService.updateById(u);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 获取账号列表
     */
    @RequestMapping("/accountList")
    public R getAccountList(@RequestParam Map<String, Object> params,GongzuorenyuanEntity gongzuorenyuan){
        EntityWrapper<GongzuorenyuanEntity> ew = new EntityWrapper<GongzuorenyuanEntity>();
        Wrapper<GongzuorenyuanEntity> wrapper =MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gongzuorenyuan), params), params);
        List<Map> list = gongzuorenyuanService.selectList(wrapper).stream().map(v -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", v.getId());
            map.put("account", v.getYuangonggonghao());
            return map;
        }).collect(Collectors.toList());
        return R.ok().put("data", list);
    }






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,GongzuorenyuanEntity gongzuorenyuan,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<GongzuorenyuanEntity> ew = new EntityWrapper<GongzuorenyuanEntity>();


        //查询结果
		PageUtils page = gongzuorenyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gongzuorenyuan), params), params));
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
    public R list(@RequestParam Map<String, Object> params,GongzuorenyuanEntity gongzuorenyuan,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<GongzuorenyuanEntity> ew = new EntityWrapper<GongzuorenyuanEntity>();

        //查询结果
		PageUtils page = gongzuorenyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gongzuorenyuan), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( GongzuorenyuanEntity gongzuorenyuan){
       	EntityWrapper<GongzuorenyuanEntity> ew = new EntityWrapper<GongzuorenyuanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( gongzuorenyuan, "gongzuorenyuan"));
        return R.ok().put("data", gongzuorenyuanService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(GongzuorenyuanEntity gongzuorenyuan){
        EntityWrapper< GongzuorenyuanEntity> ew = new EntityWrapper< GongzuorenyuanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( gongzuorenyuan, "gongzuorenyuan"));
		GongzuorenyuanView gongzuorenyuanView =  gongzuorenyuanService.selectView(ew);
		return R.ok("查询工作人员成功").put("data", gongzuorenyuanView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        GongzuorenyuanEntity gongzuorenyuan = gongzuorenyuanService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(gongzuorenyuan,deSens);
        return R.ok().put("data", gongzuorenyuan);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        GongzuorenyuanEntity gongzuorenyuan = gongzuorenyuanService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(gongzuorenyuan,deSens);
        return R.ok().put("data", gongzuorenyuan);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增工作人员")
    public R save(@RequestBody GongzuorenyuanEntity gongzuorenyuan, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(gongzuorenyuanService.selectCount(new EntityWrapper<GongzuorenyuanEntity>().eq("yuangonggonghao", gongzuorenyuan.getYuangonggonghao()))>0) {
            return R.error("员工工号已存在");
        }
        //ValidatorUtils.validateEntity(gongzuorenyuan);
        //验证账号唯一性，否则返回错误信息
        GongzuorenyuanEntity u = gongzuorenyuanService.selectOne(new EntityWrapper<GongzuorenyuanEntity>().eq("yuangonggonghao", gongzuorenyuan.getYuangonggonghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	gongzuorenyuan.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		gongzuorenyuan.setId(new Date().getTime());
        gongzuorenyuanService.insert(gongzuorenyuan);
        return R.ok().put("data",gongzuorenyuan.getId());
    }

    /**
     * 前台保存
     */
    @SysLog("新增工作人员")
    @RequestMapping("/add")
    public R add(@RequestBody GongzuorenyuanEntity gongzuorenyuan, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(gongzuorenyuanService.selectCount(new EntityWrapper<GongzuorenyuanEntity>().eq("yuangonggonghao", gongzuorenyuan.getYuangonggonghao()))>0) {
            return R.error("员工工号已存在");
        }
        //ValidatorUtils.validateEntity(gongzuorenyuan);
        //验证账号唯一性，否则返回错误信息
        GongzuorenyuanEntity u = gongzuorenyuanService.selectOne(new EntityWrapper<GongzuorenyuanEntity>().eq("yuangonggonghao", gongzuorenyuan.getYuangonggonghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	gongzuorenyuan.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		gongzuorenyuan.setId(new Date().getTime());
        gongzuorenyuanService.insert(gongzuorenyuan);
        return R.ok().put("data",gongzuorenyuan.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改工作人员")
    public R update(@RequestBody GongzuorenyuanEntity gongzuorenyuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(gongzuorenyuan);
        //验证字段唯一性，否则返回错误信息
        if(gongzuorenyuanService.selectCount(new EntityWrapper<GongzuorenyuanEntity>().ne("id", gongzuorenyuan.getId()).eq("yuangonggonghao", gongzuorenyuan.getYuangonggonghao()))>0) {
            return R.error("员工工号已存在");
        }
        //全部更新
        gongzuorenyuanService.updateById(gongzuorenyuan);
        if(null!=gongzuorenyuan.getYuangonggonghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(gongzuorenyuan.getYuangonggonghao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", gongzuorenyuan.getId()));
        }
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    @SysLog("审核工作人员")
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<GongzuorenyuanEntity> list = new ArrayList<GongzuorenyuanEntity>();
        for(Long id : ids) {
            GongzuorenyuanEntity gongzuorenyuan = gongzuorenyuanService.selectById(id);
            gongzuorenyuan.setSfsh(sfsh);
            gongzuorenyuan.setShhf(shhf);
            list.add(gongzuorenyuan);
        }
        gongzuorenyuanService.updateBatchById(list);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除工作人员")
    public R delete(@RequestBody Long[] ids){
        gongzuorenyuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
