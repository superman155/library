package com.dao;

import com.entity.QiandaodengjiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.QiandaodengjiVO;
import com.entity.view.QiandaodengjiView;


/**
 * 签到登记
 * 
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
public interface QiandaodengjiDao extends BaseMapper<QiandaodengjiEntity> {
	
	List<QiandaodengjiVO> selectListVO(@Param("ew") Wrapper<QiandaodengjiEntity> wrapper);
	
	QiandaodengjiVO selectVO(@Param("ew") Wrapper<QiandaodengjiEntity> wrapper);
	
	List<QiandaodengjiView> selectListView(@Param("ew") Wrapper<QiandaodengjiEntity> wrapper);

	List<QiandaodengjiView> selectListView(Pagination page,@Param("ew") Wrapper<QiandaodengjiEntity> wrapper);

	
	QiandaodengjiView selectView(@Param("ew") Wrapper<QiandaodengjiEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<QiandaodengjiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<QiandaodengjiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<QiandaodengjiEntity> wrapper);



}
