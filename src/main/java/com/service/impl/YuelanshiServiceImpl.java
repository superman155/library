package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.YuelanshiDao;
import com.entity.YuelanshiEntity;
import com.service.YuelanshiService;
import com.entity.vo.YuelanshiVO;
import com.entity.view.YuelanshiView;

@Service("yuelanshiService")
public class YuelanshiServiceImpl extends ServiceImpl<YuelanshiDao, YuelanshiEntity> implements YuelanshiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YuelanshiEntity> page = this.selectPage(
                new Query<YuelanshiEntity>(params).getPage(),
                new EntityWrapper<YuelanshiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YuelanshiEntity> wrapper) {
		  Page<YuelanshiView> page =new Query<YuelanshiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<YuelanshiVO> selectListVO(Wrapper<YuelanshiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YuelanshiVO selectVO(Wrapper<YuelanshiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<YuelanshiView> selectListView(Wrapper<YuelanshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YuelanshiView selectView(Wrapper<YuelanshiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<YuelanshiEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<YuelanshiEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<YuelanshiEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
