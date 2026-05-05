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
 * 工作人员
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-30 12:26:28
 */
@TableName("gongzuorenyuan")
@Data
public class GongzuorenyuanEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public GongzuorenyuanEntity() {
		
	}
	
	public GongzuorenyuanEntity(T t) {
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
	 * 员工工号
	 */
	private String yuangonggonghao;
	
	/**
	 * 密码
	 */
	private String mima;
	
	/**
	 * 员工姓名
	 */
	private String yuangongxingming;
	
	/**
	 * 性别
	 */
	private String xingbie;
	
	/**
	 * 头像
	 */
	private String touxiang;
	
	/**
	 * 联系方式
	 */
	private String lianxifangshi;
	
	/**
	 * 是否审核
	 */
	private String sfsh;
	
	/**
	 * 审核回复
	 */
	private String shhf;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
