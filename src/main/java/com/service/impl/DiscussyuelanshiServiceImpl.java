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


import com.dao.DiscussyuelanshiDao;
import com.entity.DiscussyuelanshiEntity;
import com.service.DiscussyuelanshiService;
import com.entity.vo.DiscussyuelanshiVO;
import com.entity.view.DiscussyuelanshiView;

@Service("discussyuelanshiService")
public class DiscussyuelanshiServiceImpl extends ServiceImpl<DiscussyuelanshiDao, DiscussyuelanshiEntity> implements DiscussyuelanshiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussyuelanshiEntity> page = this.selectPage(
                new Query<DiscussyuelanshiEntity>(params).getPage(),
                new EntityWrapper<DiscussyuelanshiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussyuelanshiEntity> wrapper) {
		  Page<DiscussyuelanshiView> page =new Query<DiscussyuelanshiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussyuelanshiVO> selectListVO(Wrapper<DiscussyuelanshiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussyuelanshiVO selectVO(Wrapper<DiscussyuelanshiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussyuelanshiView> selectListView(Wrapper<DiscussyuelanshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussyuelanshiView selectView(Wrapper<DiscussyuelanshiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
