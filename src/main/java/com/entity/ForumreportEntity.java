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
 * 论坛举报
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
@TableName("forumreport")
@Data
public class ForumreportEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ForumreportEntity() {
		
	}
	
	public ForumreportEntity(T t) {
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
	 * 论坛id
	 */
	private Long forumid;
	
	/**
	 * 帖子标题
	 */
	private String title;
	
	/**
	 * 举报用户id
	 */
	private Long userid;
	
	/**
	 * 举报用户名
	 */
	private String username;
	
	/**
	 * 被举报用户id
	 */
	private Long reporteduserid;
	
	/**
	 * 被举报用户名
	 */
	private String reportedusername;
	
	/**
	 * 举报原因
	 */
	private String reason;
	
	/**
	 * 图片补充
	 */
	private String picture;
	
	/**
	 * 处理建议
	 */
	private String handleadvise;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 举报类型
	 */
	private String reporttype;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

}
