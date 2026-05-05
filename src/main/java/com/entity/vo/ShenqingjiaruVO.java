package com.entity.vo;

import com.entity.ShenqingjiaruEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 申请加入
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
public class ShenqingjiaruVO  implements Serializable {
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
	 * 加入原因
	 */
	
	private String jiaruyuanyin;
		
	/**
	 * 申请时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date shenqingshijian;
		
	/**
	 * 学号
	 */
	
	private String xuehao;
		
	/**
	 * 姓名
	 */
	
	private String xingming;
		
	/**
	 * 跨表用户id
	 */
	
	private Long crossuserid;
		
	/**
	 * 跨表主键id
	 */
	
	private Long crossrefid;
				
	
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
	 * 设置：加入原因
	 */
	 
	public void setJiaruyuanyin(String jiaruyuanyin) {
		this.jiaruyuanyin = jiaruyuanyin;
	}
	
	/**
	 * 获取：加入原因
	 */
	public String getJiaruyuanyin() {
		return jiaruyuanyin;
	}
				
	
	/**
	 * 设置：申请时间
	 */
	 
	public void setShenqingshijian(Date shenqingshijian) {
		this.shenqingshijian = shenqingshijian;
	}
	
	/**
	 * 获取：申请时间
	 */
	public Date getShenqingshijian() {
		return shenqingshijian;
	}
				
	
	/**
	 * 设置：学号
	 */
	 
	public void setXuehao(String xuehao) {
		this.xuehao = xuehao;
	}
	
	/**
	 * 获取：学号
	 */
	public String getXuehao() {
		return xuehao;
	}
				
	
	/**
	 * 设置：姓名
	 */
	 
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	
	/**
	 * 获取：姓名
	 */
	public String getXingming() {
		return xingming;
	}
				
	
	/**
	 * 设置：跨表用户id
	 */
	 
	public void setCrossuserid(Long crossuserid) {
		this.crossuserid = crossuserid;
	}
	
	/**
	 * 获取：跨表用户id
	 */
	public Long getCrossuserid() {
		return crossuserid;
	}
				
	
	/**
	 * 设置：跨表主键id
	 */
	 
	public void setCrossrefid(Long crossrefid) {
		this.crossrefid = crossrefid;
	}
	
	/**
	 * 获取：跨表主键id
	 */
	public Long getCrossrefid() {
		return crossrefid;
	}
			
}
