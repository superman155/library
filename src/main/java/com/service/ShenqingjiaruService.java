package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ShenqingjiaruEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ShenqingjiaruVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ShenqingjiaruView;


/**
 * 申请加入
 *
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
public interface ShenqingjiaruService extends IService<ShenqingjiaruEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ShenqingjiaruVO> selectListVO(Wrapper<ShenqingjiaruEntity> wrapper);
   	
   	ShenqingjiaruVO selectVO(@Param("ew") Wrapper<ShenqingjiaruEntity> wrapper);
   	
   	List<ShenqingjiaruView> selectListView(Wrapper<ShenqingjiaruEntity> wrapper);
   	
   	ShenqingjiaruView selectView(@Param("ew") Wrapper<ShenqingjiaruEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ShenqingjiaruEntity> wrapper);

   	

}

