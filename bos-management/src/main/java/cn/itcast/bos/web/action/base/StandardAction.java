package cn.itcast.bos.web.action.base;

import cn.itcast.bos.domain.base.Standard;
import cn.itcast.bos.service.base.StandardService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class StandardAction extends ActionSupport implements ModelDriven<Standard>{
	
	@Resource
	private StandardService service;
	//当前页数
	private Integer page;
	//每页显示的数量
	private Integer rows;
	
	private Standard model = new Standard();
	@Override
	public Standard getModel() {
		return model;
	}
					
	@Action(value="standard_save",results={@Result(name="success",type="redirect",location="/pages/base/standard.html")})
	public String save(){
		System.out.println("hello 注解struts"+model);
		service.save(model);
		return SUCCESS;
	}
	@Action(value="standard_pageQuery",results={@Result(name="success",type="json")})
	public String pageQuery(){
		Page<Standard> pageData = service.pageQuery(new PageRequest(page-1, rows));
		
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("total",pageData.getNumberOfElements());
		hashMap.put("rows", pageData.getContent());
		ServletActionContext.getContext().getValueStack().push(hashMap);
		return SUCCESS;
	}
	@Action(value="standard_findAll",results={@Result(name="success",type="json")})
	public String findAll(){
		List<Standard> list = service.findAll();
		ServletActionContext.getContext().getValueStack().push(list);
		return SUCCESS;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
}
