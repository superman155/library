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
 * 学习搭子
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
@TableName("xuexidazi")
@Data
public class XuexidaziEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public XuexidaziEntity() {
		
	}
	
	public XuexidaziEntity(T t) {
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
	 * 标题
	 */
	private String biaoti;
	
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
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
	private Date fabushijian;
	
	/**
	 * 用户id
	 */
	private Long userid;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
