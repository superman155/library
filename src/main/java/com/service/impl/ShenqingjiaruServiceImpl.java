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


import com.dao.ShenqingjiaruDao;
import com.entity.ShenqingjiaruEntity;
import com.service.ShenqingjiaruService;
import com.entity.vo.ShenqingjiaruVO;
import com.entity.view.ShenqingjiaruView;

@Service("shenqingjiaruService")
public class ShenqingjiaruServiceImpl extends ServiceImpl<ShenqingjiaruDao, ShenqingjiaruEntity> implements ShenqingjiaruService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShenqingjiaruEntity> page = this.selectPage(
                new Query<ShenqingjiaruEntity>(params).getPage(),
                new EntityWrapper<ShenqingjiaruEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ShenqingjiaruEntity> wrapper) {
		  Page<ShenqingjiaruView> page =new Query<ShenqingjiaruView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ShenqingjiaruVO> selectListVO(Wrapper<ShenqingjiaruEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ShenqingjiaruVO selectVO(Wrapper<ShenqingjiaruEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ShenqingjiaruView> selectListView(Wrapper<ShenqingjiaruEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ShenqingjiaruView selectView(Wrapper<ShenqingjiaruEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
