package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.QiantuijiluEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.QiantuijiluVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.QiantuijiluView;


/**
 * 签退记录
 *
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
public interface QiantuijiluService extends IService<QiantuijiluEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QiantuijiluVO> selectListVO(Wrapper<QiantuijiluEntity> wrapper);
   	
   	QiantuijiluVO selectVO(@Param("ew") Wrapper<QiantuijiluEntity> wrapper);
   	
   	List<QiantuijiluView> selectListView(Wrapper<QiantuijiluEntity> wrapper);
   	
   	QiantuijiluView selectView(@Param("ew") Wrapper<QiantuijiluEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QiantuijiluEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<QiantuijiluEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<QiantuijiluEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<QiantuijiluEntity> wrapper);



}

