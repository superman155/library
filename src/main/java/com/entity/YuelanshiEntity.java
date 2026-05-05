package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 阅览室
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-30 12:26:28
 */
@TableName("yuelanshi")
@Data
public class YuelanshiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public YuelanshiEntity() {
		
	}
	
	public YuelanshiEntity(T t) {
		try {
			BeanUtils.copyProperties(t, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 编号
	 */
	private String refno;
	
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
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
