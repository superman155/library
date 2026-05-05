package com.dao;

import com.entity.DiscussyuelanshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussyuelanshiVO;
import com.entity.view.DiscussyuelanshiView;


/**
 * 阅览室评论
 * 
 * @author 
 * @email 
 * @date 2026-04-30 12:26:30
 */
public interface DiscussyuelanshiDao extends BaseMapper<DiscussyuelanshiEntity> {
	
	List<DiscussyuelanshiVO> selectListVO(@Param("ew") Wrapper<DiscussyuelanshiEntity> wrapper);
	
	DiscussyuelanshiVO selectVO(@Param("ew") Wrapper<DiscussyuelanshiEntity> wrapper);
	
	List<DiscussyuelanshiView> selectListView(@Param("ew") Wrapper<DiscussyuelanshiEntity> wrapper);

	List<DiscussyuelanshiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussyuelanshiEntity> wrapper);

	
	DiscussyuelanshiView selectView(@Param("ew") Wrapper<DiscussyuelanshiEntity> wrapper);
	

}
