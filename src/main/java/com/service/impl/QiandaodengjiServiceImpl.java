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


import com.dao.QiandaodengjiDao;
import com.entity.QiandaodengjiEntity;
import com.service.QiandaodengjiService;
import com.entity.vo.QiandaodengjiVO;
import com.entity.view.QiandaodengjiView;

@Service("qiandaodengjiService")
public class QiandaodengjiServiceImpl extends ServiceImpl<QiandaodengjiDao, QiandaodengjiEntity> implements QiandaodengjiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QiandaodengjiEntity> page = this.selectPage(
                new Query<QiandaodengjiEntity>(params).getPage(),
                new EntityWrapper<QiandaodengjiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<QiandaodengjiEntity> wrapper) {
		  Page<QiandaodengjiView> page =new Query<QiandaodengjiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<QiandaodengjiVO> selectListVO(Wrapper<QiandaodengjiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public QiandaodengjiVO selectVO(Wrapper<QiandaodengjiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<QiandaodengjiView> selectListView(Wrapper<QiandaodengjiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public QiandaodengjiView selectView(Wrapper<QiandaodengjiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<QiandaodengjiEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<QiandaodengjiEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<QiandaodengjiEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
