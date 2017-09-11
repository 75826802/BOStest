package cn.itcast.bos.dao.base;

import cn.itcast.bos.domain.base.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ============================================================
 * <p>
 * 版 权 ： 传智播客 版权所有 (c) 2017
 * 作 者 : 焦海峰
 * 版 本 ： 1.0
 * 创建日期 ： 2017年08月06日  11点11分
 * 描 述 ：
 * ============================================================
 **/
public interface CourierRepository extends JpaRepository<Courier,Integer>,JpaSpecificationExecutor<Courier> {
}
