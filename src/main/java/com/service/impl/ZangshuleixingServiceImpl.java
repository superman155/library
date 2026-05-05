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


import com.dao.ZangshuleixingDao;
import com.entity.ZangshuleixingEntity;
import com.service.ZangshuleixingService;
import com.entity.vo.ZangshuleixingVO;
import com.entity.view.ZangshuleixingView;

@Service("zangshuleixingService")
public class ZangshuleixingServiceImpl extends ServiceImpl<ZangshuleixingDao, ZangshuleixingEntity> implements ZangshuleixingService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZangshuleixingEntity> page = this.selectPage(
                new Query<ZangshuleixingEntity>(params).getPage(),
                new EntityWrapper<ZangshuleixingEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ZangshuleixingEntity> wrapper) {
		  Page<ZangshuleixingView> page =new Query<ZangshuleixingView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ZangshuleixingVO> selectListVO(Wrapper<ZangshuleixingEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ZangshuleixingVO selectVO(Wrapper<ZangshuleixingEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ZangshuleixingView> selectListView(Wrapper<ZangshuleixingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZangshuleixingView selectView(Wrapper<ZangshuleixingEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
