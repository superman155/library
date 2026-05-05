package com.entity.vo;

import com.entity.XuexidaziEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 学习搭子
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
public class XuexidaziVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 封面
	 */
	
	private String fengmian;
		
	/**
	 * 学习风格
	 */
	
	private String xuexifengge;
		
	/**
	 * 监督机制
	 */
	
	private String jiandujizhi;
		
	/**
	 * 搭子类型
	 */
	
	private String dazileixing;
		
	/**
	 * 简介
	 */
	
	private String jianjie;
		
	/**
	 * 内容
	 */
	
	private String neirong;
		
	/**
	 * 发布时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date fabushijian;
		
	/**
	 * 用户id
	 */
	
	private Long userid;
				
	
	/**
	 * 设置：封面
	 */
	 
	public void setFengmian(String fengmian) {
		this.fengmian = fengmian;
	}
	
	/**
	 * 获取：封面
	 */
	public String getFengmian() {
		return fengmian;
	}
				
	
	/**
	 * 设置：学习风格
	 */
	 
	public void setXuexifengge(String xuexifengge) {
		this.xuexifengge = xuexifengge;
	}
	
	/**
	 * 获取：学习风格
	 */
	public String getXuexifengge() {
		return xuexifengge;
	}
				
	
	/**
	 * 设置：监督机制
	 */
	 
	public void setJiandujizhi(String jiandujizhi) {
		this.jiandujizhi = jiandujizhi;
	}
	
	/**
	 * 获取：监督机制
	 */
	public String getJiandujizhi() {
		return jiandujizhi;
	}
				
	
	/**
	 * 设置：搭子类型
	 */
	 
	public void setDazileixing(String dazileixing) {
		this.dazileixing = dazileixing;
	}
	
	/**
	 * 获取：搭子类型
	 */
	public String getDazileixing() {
		return dazileixing;
	}
				
	
	/**
	 * 设置：简介
	 */
	 
	public void setJianjie(String jianjie) {
		this.jianjie = jianjie;
	}
	
	/**
	 * 获取：简介
	 */
	public String getJianjie() {
		return jianjie;
	}
				
	
	/**
	 * 设置：内容
	 */
	 
	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}
	
	/**
	 * 获取：内容
	 */
	public String getNeirong() {
		return neirong;
	}
				
	
	/**
	 * 设置：发布时间
	 */
	 
	public void setFabushijian(Date fabushijian) {
		this.fabushijian = fabushijian;
	}
	
	/**
	 * 获取：发布时间
	 */
	public Date getFabushijian() {
		return fabushijian;
	}
				
	
	/**
	 * 设置：用户id
	 */
	 
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	/**
	 * 获取：用户id
	 */
	public Long getUserid() {
		return userid;
	}
			
}
