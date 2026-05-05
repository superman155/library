package com.entity.vo;

import com.entity.QiantuijiluEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 签退记录
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
public class QiantuijiluVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 名称
	 */
	
	private String mingcheng;
		
	/**
	 * 藏书类型
	 */
	
	private String zangshuleixing;
		
	/**
	 * 楼层
	 */
	
	private String louceng;
		
	/**
	 * 座位号
	 */
	
	private String seatnum;
		
	/**
	 * 签退时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date qiantuishijian;
		
	/**
	 * 签退备注
	 */
	
	private String qiantuibeizhu;
		
	/**
	 * 学号
	 */
	
	private String xuehao;
		
	/**
	 * 姓名
	 */
	
	private String xingming;
		
	/**
	 * 头像
	 */
	
	private String touxiang;
		
	/**
	 * 员工工号
	 */
	
	private String yuangonggonghao;
		
	/**
	 * 跨表用户id
	 */
	
	private Long crossuserid;
		
	/**
	 * 跨表主键id
	 */
	
	private Long crossrefid;
				
	
	/**
	 * 设置：名称
	 */
	 
	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
	}
	
	/**
	 * 获取：名称
	 */
	public String getMingcheng() {
		return mingcheng;
	}
				
	
	/**
	 * 设置：藏书类型
	 */
	 
	public void setZangshuleixing(String zangshuleixing) {
		this.zangshuleixing = zangshuleixing;
	}
	
	/**
	 * 获取：藏书类型
	 */
	public String getZangshuleixing() {
		return zangshuleixing;
	}
				
	
	/**
	 * 设置：楼层
	 */
	 
	public void setLouceng(String louceng) {
		this.louceng = louceng;
	}
	
	/**
	 * 获取：楼层
	 */
	public String getLouceng() {
		return louceng;
	}
				
	
	/**
	 * 设置：座位号
	 */
	 
	public void setSeatnum(String seatnum) {
		this.seatnum = seatnum;
	}
	
	/**
	 * 获取：座位号
	 */
	public String getSeatnum() {
		return seatnum;
	}
				
	
	/**
	 * 设置：签退时间
	 */
	 
	public void setQiantuishijian(Date qiantuishijian) {
		this.qiantuishijian = qiantuishijian;
	}
	
	/**
	 * 获取：签退时间
	 */
	public Date getQiantuishijian() {
		return qiantuishijian;
	}
				
	
	/**
	 * 设置：签退备注
	 */
	 
	public void setQiantuibeizhu(String qiantuibeizhu) {
		this.qiantuibeizhu = qiantuibeizhu;
	}
	
	/**
	 * 获取：签退备注
	 */
	public String getQiantuibeizhu() {
		return qiantuibeizhu;
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
	 * 设置：头像
	 */
	 
	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}
	
	/**
	 * 获取：头像
	 */
	public String getTouxiang() {
		return touxiang;
	}
				
	
	/**
	 * 设置：员工工号
	 */
	 
	public void setYuangonggonghao(String yuangonggonghao) {
		this.yuangonggonghao = yuangonggonghao;
	}
	
	/**
	 * 获取：员工工号
	 */
	public String getYuangonggonghao() {
		return yuangonggonghao;
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
