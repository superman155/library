package com.dao;

import com.entity.ZangshuleixingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ZangshuleixingVO;
import com.entity.view.ZangshuleixingView;


/**
 * 藏书类型
 * 
 * @author 
 * @email 
 * @date 2026-04-30 12:26:28
 */
public interface ZangshuleixingDao extends BaseMapper<ZangshuleixingEntity> {
	
	List<ZangshuleixingVO> selectListVO(@Param("ew") Wrapper<ZangshuleixingEntity> wrapper);
	
	ZangshuleixingVO selectVO(@Param("ew") Wrapper<ZangshuleixingEntity> wrapper);
	
	List<ZangshuleixingView> selectListView(@Param("ew") Wrapper<ZangshuleixingEntity> wrapper);

	List<ZangshuleixingView> selectListView(Pagination page,@Param("ew") Wrapper<ZangshuleixingEntity> wrapper);

	
	ZangshuleixingView selectView(@Param("ew") Wrapper<ZangshuleixingEntity> wrapper);
	

}
