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
 * 用户
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-30 12:26:28
 */
@TableName("yonghu")
@Data
public class YonghuEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public YonghuEntity() {
		
	}
	
	public YonghuEntity(T t) {
		try {
			BeanUtils.copyProperties(t, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 主键id
	 */
    @TableId
	private Long id;
	/**
	 * 学号
	 */
	private String xuehao;
	
	/**
	 * 密码
	 */
	private String mima;
	
	/**
	 * 姓名
	 */
	private String xingming;
	
	/**
	 * 性别
	 */
	private String xingbie;
	
	/**
	 * 头像
	 */
	private String touxiang;
	
	/**
	 * 手机号码
	 */
	private String shoujihaoma;
	
	/**
	 * 学院
	 */
	private String xueyuan;
	
	/**
	 * 班级
	 */
	private String banji;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
