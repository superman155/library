package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.QiandaodengjiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.QiandaodengjiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.QiandaodengjiView;


/**
 * 签到登记
 *
 * @author 
 * @email 
 * @date 2026-04-30 12:26:29
 */
public interface QiandaodengjiService extends IService<QiandaodengjiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QiandaodengjiVO> selectListVO(Wrapper<QiandaodengjiEntity> wrapper);
   	
   	QiandaodengjiVO selectVO(@Param("ew") Wrapper<QiandaodengjiEntity> wrapper);
   	
   	List<QiandaodengjiView> selectListView(Wrapper<QiandaodengjiEntity> wrapper);
   	
   	QiandaodengjiView selectView(@Param("ew") Wrapper<QiandaodengjiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QiandaodengjiEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<QiandaodengjiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<QiandaodengjiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<QiandaodengjiEntity> wrapper);



}

