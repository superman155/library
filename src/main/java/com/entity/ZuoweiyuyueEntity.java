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
 * 座位预约
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-30 12:26:28
 */
@TableName("zuoweiyuyue")
@Data
public class ZuoweiyuyueEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ZuoweiyuyueEntity() {
		
	}
	
	public ZuoweiyuyueEntity(T t) {
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
	 * 座位号
	 */
	private String seatnum;
	
	/**
	 * 预约日期
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
	private Date reservationdate;
	
	/**
	 * 时间段
	 */
	private String timeslot;
	
	/**
	 * 座位状态
	 */
	private String reservationstate;
	
	/**
	 * 签到状态
	 */
	private String qiandaozhuangtai;
	
	/**
	 * 预约备注
	 */
	private String yuyuebeizhu;
	
	/**
	 * 学号
	 */
	private String xuehao;
	
	/**
	 * 姓名
	 */
	private String xingming;
	
	/**
	 * 学院
	 */
	private String xueyuan;
	
	/**
	 * 班级
	 */
	private String banji;
	
	/**
	 * 员工工号
	 */
	private String yuangonggonghao;
	
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
