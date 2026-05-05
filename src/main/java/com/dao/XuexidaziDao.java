package com.dao;

import com.entity.XuexidaziEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.XuexidaziVO;
import com.entity.view.XuexidaziView;


/**
 * 学习搭子
 * 
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
public interface XuexidaziDao extends BaseMapper<XuexidaziEntity> {
	
	List<XuexidaziVO> selectListVO(@Param("ew") Wrapper<XuexidaziEntity> wrapper);
	
	XuexidaziVO selectVO(@Param("ew") Wrapper<XuexidaziEntity> wrapper);
	
	List<XuexidaziView> selectListView(@Param("ew") Wrapper<XuexidaziEntity> wrapper);

	List<XuexidaziView> selectListView(Pagination page,@Param("ew") Wrapper<XuexidaziEntity> wrapper);

	
	XuexidaziView selectView(@Param("ew") Wrapper<XuexidaziEntity> wrapper);
	

}
