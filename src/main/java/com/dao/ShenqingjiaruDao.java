package com.dao;

import com.entity.ShenqingjiaruEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ShenqingjiaruVO;
import com.entity.view.ShenqingjiaruView;


/**
 * 申请加入
 * 
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
public interface ShenqingjiaruDao extends BaseMapper<ShenqingjiaruEntity> {
	
	List<ShenqingjiaruVO> selectListVO(@Param("ew") Wrapper<ShenqingjiaruEntity> wrapper);
	
	ShenqingjiaruVO selectVO(@Param("ew") Wrapper<ShenqingjiaruEntity> wrapper);
	
	List<ShenqingjiaruView> selectListView(@Param("ew") Wrapper<ShenqingjiaruEntity> wrapper);

	List<ShenqingjiaruView> selectListView(Pagination page,@Param("ew") Wrapper<ShenqingjiaruEntity> wrapper);

	
	ShenqingjiaruView selectView(@Param("ew") Wrapper<ShenqingjiaruEntity> wrapper);
	

}
