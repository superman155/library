package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussyuelanshiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussyuelanshiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussyuelanshiView;


/**
 * 阅览室评论
 *
 * @author 
 * @email 
 * @date 2026-04-30 12:26:30
 */
public interface DiscussyuelanshiService extends IService<DiscussyuelanshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussyuelanshiVO> selectListVO(Wrapper<DiscussyuelanshiEntity> wrapper);
   	
   	DiscussyuelanshiVO selectVO(@Param("ew") Wrapper<DiscussyuelanshiEntity> wrapper);
   	
   	List<DiscussyuelanshiView> selectListView(Wrapper<DiscussyuelanshiEntity> wrapper);
   	
   	DiscussyuelanshiView selectView(@Param("ew") Wrapper<DiscussyuelanshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussyuelanshiEntity> wrapper);

   	

}

