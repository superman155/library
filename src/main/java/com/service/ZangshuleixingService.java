package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZangshuleixingEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZangshuleixingVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZangshuleixingView;


/**
 * 藏书类型
 *
 * @author 
 * @email 
 * @date 2026-04-30 12:26:28
 */
public interface ZangshuleixingService extends IService<ZangshuleixingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZangshuleixingVO> selectListVO(Wrapper<ZangshuleixingEntity> wrapper);
   	
   	ZangshuleixingVO selectVO(@Param("ew") Wrapper<ZangshuleixingEntity> wrapper);
   	
   	List<ZangshuleixingView> selectListView(Wrapper<ZangshuleixingEntity> wrapper);
   	
   	ZangshuleixingView selectView(@Param("ew") Wrapper<ZangshuleixingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZangshuleixingEntity> wrapper);

   	

}

