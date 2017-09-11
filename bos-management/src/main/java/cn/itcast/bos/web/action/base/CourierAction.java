package cn.itcast.bos.web.action.base;

import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.service.base.CourierService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ============================================================
 * <p>
 * 版 权 ： 传智播客 版权所有 (c) 2017
 * 作 者 : 焦海峰
 * 版 本 ： 1.0
 * 创建日期 ： 2017年08月06日  10点54分
 * 描 述 ：快递员的动作类
 * ============================================================
 **/
@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class CourierAction extends ActionSupport implements ModelDriven<Courier> {

    @Resource
    private CourierService service;
    private Courier model = new Courier();
    private Integer page;
    private Integer rows;

    @Override
    public Courier getModel() {
        return model;
    }

    @Action(value = "courier_save", results = {@Result(type = "redirect", location = "/pages/base/courier.html")})
    public String courier_save() {
        System.out.println("成功保存了");
        service.save(model);
        return SUCCESS;
    }

    @Action(value = "courier_pageQuery", results = {@Result(type = "json")})
    public String pageQuery() {
//        System.out.println("分页查询");
        //封装查询条件
        Specification<Courier> specification = new Specification<Courier>() {
            @Override
            public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
           /*     courierNum:1
                standard.name:2
                company:3
                type:4
                page:1
                rows:30*/
                List<Predicate> list = new ArrayList<>();
                if (StringUtils.isNotBlank(model.getCourierNum())) {
                    Predicate p1 = cb.equal(root.get("courierNum").as(String.class), model.getCourierNum());
                    list.add(p1);
                }
                if (StringUtils.isNotBlank(model.getCompany())) {
                    Predicate p2 = cb.like(root.get("company").as(String.class), "%" + model.getCompany() + "%");
                    list.add(p2);
                }
                if (StringUtils.isNotBlank(model.getType())) {
                    Predicate p3 = cb.like(root.get("type").as(String.class), model.getType());
                    list.add(p3);
                }
                Join<Object, Object> standardRoot = root.join("standard", JoinType.INNER);
                if (model.getStandard() != null && StringUtils.isNotBlank(model.getStandard().getName())) {
                    Predicate p4 = cb.like(standardRoot.get("name").as(String.class), "%"+model.getStandard().getName()+"%");
                    list.add(p4);
                }
                return cb.and(list.toArray(new Predicate[0]));
            }
        };
        Page<Courier> pageData = service.findPageData(specification, new PageRequest(page - 1, rows));

        HashMap<String, Object> map = new HashMap<>();
        map.put("total", pageData.getTotalElements());
        map.put("rows", pageData.getContent());
        ServletActionContext.getContext().getValueStack().push(map);
        return SUCCESS;
    }


    public void setPage(Integer page) {
        this.page = page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
