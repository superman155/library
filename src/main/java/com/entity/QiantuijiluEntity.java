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
 * 签退记录
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
@TableName("qiantuijilu")
@Data
public class QiantuijiluEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public QiantuijiluEntity() {
		
	}
	
	public QiantuijiluEntity(T t) {
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
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
