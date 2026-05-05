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


import com.dao.XuexidaziDao;
import com.entity.XuexidaziEntity;
import com.service.XuexidaziService;
import com.entity.vo.XuexidaziVO;
import com.entity.view.XuexidaziView;

@Service("xuexidaziService")
public class XuexidaziServiceImpl extends ServiceImpl<XuexidaziDao, XuexidaziEntity> implements XuexidaziService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XuexidaziEntity> page = this.selectPage(
                new Query<XuexidaziEntity>(params).getPage(),
                new EntityWrapper<XuexidaziEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<XuexidaziEntity> wrapper) {
		  Page<XuexidaziView> page =new Query<XuexidaziView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<XuexidaziVO> selectListVO(Wrapper<XuexidaziEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public XuexidaziVO selectVO(Wrapper<XuexidaziEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<XuexidaziView> selectListView(Wrapper<XuexidaziEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XuexidaziView selectView(Wrapper<XuexidaziEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
