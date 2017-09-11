package cn.itcast.bos.service.base;

import cn.itcast.bos.domain.base.Courier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

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
public interface CourierService {
    void save(Courier courier);

    Page<Courier> pageQuery(PageRequest pageRequest);
    Page<Courier> findPageData(Specification<Courier> specification,PageRequest pageRequest);
}
