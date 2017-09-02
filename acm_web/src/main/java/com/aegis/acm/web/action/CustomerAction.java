package com.aegis.acm.web.action;

import com.aegis.acm.dao.CustomerDao;
import com.aegis.acm.domain.Customer;
import com.aegis.acm.service.CustomerService;
import com.aegis.acm.web.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomerAction extends BaseAction<Customer> {

    @Autowired
    private CustomerService customerService;

    @Action("customerAction_findByPage")
    public void findByPage() {
        Pageable pageable = new PageRequest(page - 1, limit);
        Page<Customer> customerPage = customerService.findByPage(pageable);
        long totalElements = customerPage.getTotalElements();
        List<Customer> content = customerPage.getContent();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", totalElements);
        map.put("data", content);
        doAjaxResponse(map, "caList");
    }

    @Action("customerAction_save")
    public void save() {
        try {
            model.setFirstAdd(new Date());
            customerService.save(model);
            doAjaxResponseResultMap(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            doAjaxResponseResultMap(false, "保存失败, 错误信息:" + e.getMessage() + ", 请截图联系管理员.");
        }
    }

    @Action(value = "customerAction_update", results =
    @Result(name = "toUpdate", type = "dispatcher", location = "/jsp/customer_update.jsp"))
    public String update() {
        model = customerService.findByCid(model.getCid());
        ActionContext.getContext().getValueStack().set("customer", model);
        return "toUpdate";
    }
}
