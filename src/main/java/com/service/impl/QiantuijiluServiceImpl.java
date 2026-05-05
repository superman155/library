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


import com.dao.QiantuijiluDao;
import com.entity.QiantuijiluEntity;
import com.service.QiantuijiluService;
import com.entity.vo.QiantuijiluVO;
import com.entity.view.QiantuijiluView;

@Service("qiantuijiluService")
public class QiantuijiluServiceImpl extends ServiceImpl<QiantuijiluDao, QiantuijiluEntity> implements QiantuijiluService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QiantuijiluEntity> page = this.selectPage(
                new Query<QiantuijiluEntity>(params).getPage(),
                new EntityWrapper<QiantuijiluEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<QiantuijiluEntity> wrapper) {
		  Page<QiantuijiluView> page =new Query<QiantuijiluView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<QiantuijiluVO> selectListVO(Wrapper<QiantuijiluEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public QiantuijiluVO selectVO(Wrapper<QiantuijiluEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<QiantuijiluView> selectListView(Wrapper<QiantuijiluEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public QiantuijiluView selectView(Wrapper<QiantuijiluEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<QiantuijiluEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<QiantuijiluEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<QiantuijiluEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
