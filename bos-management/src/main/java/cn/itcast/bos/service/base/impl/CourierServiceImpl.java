package cn.itcast.bos.service.base.impl;

import cn.itcast.bos.dao.base.CourierRepository;
import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.service.base.CourierService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * ============================================================
 * <p>
 * 版 权 ： 传智播客 版权所有 (c) 2017
 * 作 者 : 焦海峰
 * 版 本 ： 1.0
 * 创建日期 ： 2017年08月06日  11点10分
 * 描 述 ：
 * ============================================================
 **/
@Service
@Transactional
public class CourierServiceImpl implements CourierService {

    @Resource
    private CourierRepository repository;

    @Override
    public void save(Courier courier){
        repository.save(courier);
    }

    @Override
    public Page<Courier> pageQuery(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    @Override
    public Page<Courier> findPageData(Specification<Courier> specification, PageRequest pageRequest) {

        return repository.findAll(specification,pageRequest);
    }


}
