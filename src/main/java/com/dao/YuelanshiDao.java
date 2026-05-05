package com.dao;

import com.entity.YuelanshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.YuelanshiVO;
import com.entity.view.YuelanshiView;


/**
 * 阅览室
 * 
 * @author 
 * @email 
 * @date 2026-04-30 12:26:28
 */
public interface YuelanshiDao extends BaseMapper<YuelanshiEntity> {
	
	List<YuelanshiVO> selectListVO(@Param("ew") Wrapper<YuelanshiEntity> wrapper);
	
	YuelanshiVO selectVO(@Param("ew") Wrapper<YuelanshiEntity> wrapper);
	
	List<YuelanshiView> selectListView(@Param("ew") Wrapper<YuelanshiEntity> wrapper);

	List<YuelanshiView> selectListView(Pagination page,@Param("ew") Wrapper<YuelanshiEntity> wrapper);

	
	YuelanshiView selectView(@Param("ew") Wrapper<YuelanshiEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YuelanshiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YuelanshiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YuelanshiEntity> wrapper);



}
