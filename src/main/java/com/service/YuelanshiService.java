package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YuelanshiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.YuelanshiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.YuelanshiView;


/**
 * 阅览室
 *
 * @author 
 * @email 
 * @date 2026-04-30 12:26:28
 */
public interface YuelanshiService extends IService<YuelanshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YuelanshiVO> selectListVO(Wrapper<YuelanshiEntity> wrapper);
   	
   	YuelanshiVO selectVO(@Param("ew") Wrapper<YuelanshiEntity> wrapper);
   	
   	List<YuelanshiView> selectListView(Wrapper<YuelanshiEntity> wrapper);
   	
   	YuelanshiView selectView(@Param("ew") Wrapper<YuelanshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YuelanshiEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<YuelanshiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<YuelanshiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<YuelanshiEntity> wrapper);



}

