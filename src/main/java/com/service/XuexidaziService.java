package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.XuexidaziEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.XuexidaziVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.XuexidaziView;


/**
 * 学习搭子
 *
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
public interface XuexidaziService extends IService<XuexidaziEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XuexidaziVO> selectListVO(Wrapper<XuexidaziEntity> wrapper);
   	
   	XuexidaziVO selectVO(@Param("ew") Wrapper<XuexidaziEntity> wrapper);
   	
   	List<XuexidaziView> selectListView(Wrapper<XuexidaziEntity> wrapper);
   	
   	XuexidaziView selectView(@Param("ew") Wrapper<XuexidaziEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XuexidaziEntity> wrapper);

   	

}

