package cn.itcast.bos.service.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.base.StandardRepository;
import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.service.base.StandardService;

@Service
@Transactional
public class StandardServiceImpl implements StandardService {

	@Resource
	private StandardRepository repostitory;
	@Override
	public void save(Standard model) {
		repostitory.save(model);
	}

	@Override
	public Page<Standard> pageQuery(Pageable pageable){
		Page<Standard> pageData = repostitory.findAll(pageable);
		return pageData;
	}
	
	@Override
	public List<Standard> findAll(){
		List<Standard> list = repostitory.findAll();
		return list;
	}
}
