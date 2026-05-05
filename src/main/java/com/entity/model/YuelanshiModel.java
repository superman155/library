package com.entity.model;

import com.entity.YuelanshiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 阅览室
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2026-04-30 12:26:28
 */
public class YuelanshiModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 名称
	 */
	
	private String mingcheng;
		
	/**
	 * 图片
	 */
	
	private String tupian;
		
	/**
	 * 藏书类型
	 */
	
	private String zangshuleixing;
		
	/**
	 * 楼层
	 */
	
	private String louceng;
		
	/**
	 * 分区
	 */
	
	private String fenqu;
		
	/**
	 * 列数
	 */
	
	private Integer columns;
		
	/**
	 * 行数
	 */
	
	private Integer rows;
		
	/**
	 * 总数
	 */
	
	private Integer totals;
		
	/**
	 * 开放时间
	 */
	
	private String opentime;
		
	/**
	 * 阅览室地址
	 */
	
	private String yuelanshidizhi;
		
	/**
	 * 阅览室详情
	 */
	
	private String yuelanshixiangqing;
		
	/**
	 * 员工工号
	 */
	
	private String yuangonggonghao;
		
	/**
	 * 工作员
	 */
	
	private String yuangongxingming;
		
	/**
	 * 赞
	 */
	
	private Integer thumbsupnum;
		
	/**
	 * 踩
	 */
	
	private Integer crazilynum;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 点击次数
	 */
	
	private Integer clicknum;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
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
	 * 设置：图片
	 */
	 
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	
	/**
	 * 获取：图片
	 */
	public String getTupian() {
		return tupian;
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
	 * 设置：分区
	 */
	 
	public void setFenqu(String fenqu) {
		this.fenqu = fenqu;
	}
	
	/**
	 * 获取：分区
	 */
	public String getFenqu() {
		return fenqu;
	}
				
	
	/**
	 * 设置：列数
	 */
	 
	public void setColumns(Integer columns) {
		this.columns = columns;
	}
	
	/**
	 * 获取：列数
	 */
	public Integer getColumns() {
		return columns;
	}
				
	
	/**
	 * 设置：行数
	 */
	 
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	/**
	 * 获取：行数
	 */
	public Integer getRows() {
		return rows;
	}
				
	
	/**
	 * 设置：总数
	 */
	 
	public void setTotals(Integer totals) {
		this.totals = totals;
	}
	
	/**
	 * 获取：总数
	 */
	public Integer getTotals() {
		return totals;
	}
				
	
	/**
	 * 设置：开放时间
	 */
	 
	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}
	
	/**
	 * 获取：开放时间
	 */
	public String getOpentime() {
		return opentime;
	}
				
	
	/**
	 * 设置：阅览室地址
	 */
	 
	public void setYuelanshidizhi(String yuelanshidizhi) {
		this.yuelanshidizhi = yuelanshidizhi;
	}
	
	/**
	 * 获取：阅览室地址
	 */
	public String getYuelanshidizhi() {
		return yuelanshidizhi;
	}
				
	
	/**
	 * 设置：阅览室详情
	 */
	 
	public void setYuelanshixiangqing(String yuelanshixiangqing) {
		this.yuelanshixiangqing = yuelanshixiangqing;
	}
	
	/**
	 * 获取：阅览室详情
	 */
	public String getYuelanshixiangqing() {
		return yuelanshixiangqing;
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
	 * 设置：工作员
	 */
	 
	public void setYuangongxingming(String yuangongxingming) {
		this.yuangongxingming = yuangongxingming;
	}
	
	/**
	 * 获取：工作员
	 */
	public String getYuangongxingming() {
		return yuangongxingming;
	}
				
	
	/**
	 * 设置：赞
	 */
	 
	public void setThumbsupnum(Integer thumbsupnum) {
		this.thumbsupnum = thumbsupnum;
	}
	
	/**
	 * 获取：赞
	 */
	public Integer getThumbsupnum() {
		return thumbsupnum;
	}
				
	
	/**
	 * 设置：踩
	 */
	 
	public void setCrazilynum(Integer crazilynum) {
		this.crazilynum = crazilynum;
	}
	
	/**
	 * 获取：踩
	 */
	public Integer getCrazilynum() {
		return crazilynum;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：点击次数
	 */
	 
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	
	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
	}
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
