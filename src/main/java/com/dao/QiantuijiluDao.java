package com.dao;

import com.entity.QiantuijiluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.QiantuijiluVO;
import com.entity.view.QiantuijiluView;


/**
 * 签退记录
 * 
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
public interface QiantuijiluDao extends BaseMapper<QiantuijiluEntity> {
	
	List<QiantuijiluVO> selectListVO(@Param("ew") Wrapper<QiantuijiluEntity> wrapper);
	
	QiantuijiluVO selectVO(@Param("ew") Wrapper<QiantuijiluEntity> wrapper);
	
	List<QiantuijiluView> selectListView(@Param("ew") Wrapper<QiantuijiluEntity> wrapper);

	List<QiantuijiluView> selectListView(Pagination page,@Param("ew") Wrapper<QiantuijiluEntity> wrapper);

	
	QiantuijiluView selectView(@Param("ew") Wrapper<QiantuijiluEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<QiantuijiluEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<QiantuijiluEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<QiantuijiluEntity> wrapper);



}
