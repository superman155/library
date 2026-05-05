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
 * 签到登记
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
@TableName("qiandaodengji")
@Data
public class QiandaodengjiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public QiandaodengjiEntity() {
		
	}
	
	public QiandaodengjiEntity(T t) {
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
	 * 签到时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date qiandaoshijian;
	
	/**
	 * 签到备注
	 */
	private String qiandaobeizhu;
	
	/**
	 * 学号
	 */
	private String xuehao;
	
	/**
	 * 姓名
	 */
	private String xingming;
	
	/**
	 * 人脸识别
	 */
	private String touxiang;
	
	/**
	 * 手机号码
	 */
	private String shoujihaoma;
	
	/**
	 * 员工工号
	 */
	private String yuangonggonghao;
	
	/**
	 * 关联预约ID
	 */
	private Long yuyueid;
	
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
