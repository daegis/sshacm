package com.aegis.acm.web.action;

import com.aegis.acm.dao.CustomerDao;
import com.aegis.acm.domain.Activity;
import com.aegis.acm.domain.Customer;
import com.aegis.acm.service.ActivityService;
import com.aegis.acm.service.CustomerService;
import com.aegis.acm.web.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class CustomerAction extends BaseAction<Customer> {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ActivityService activityService;

    private String keyword;

    @Action("customerAction_findByPage")
    public void findByPage() {
        Sort sort = new Sort(Sort.Direction.DESC, "cid");
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new LinkedList<>();
                if (keyword != null && !keyword.equals("")) {
                    String keywordLike = "%" + keyword + "%";
                    Predicate nickname = cb.like(root.get("nickname").as(String.class), keywordLike);
                    predicateList.add(nickname);
                    if (keyword.matches("[0-9]+")) {
                        Predicate mobile = cb.like(root.get("mobile").as(String.class), keywordLike);
                        predicateList.add(mobile);
                    }
                    Predicate name = cb.like(root.get("name").as(String.class), keywordLike);
                    predicateList.add(name);
                    Predicate address = cb.like(root.get("address").as(String.class), keywordLike);
                    predicateList.add(address);
                }
                if (predicateList.isEmpty()) {
                    return null;
                }
                Predicate[] predicates = new Predicate[predicateList.size()];
                predicates = predicateList.toArray(predicates);
                Predicate predicate = cb.or(predicates);
                return predicate;
            }
        };
        Pageable pageable = new PageRequest(page - 1, limit, sort);
        Page<Customer> customerPage = customerService.findByPage(specification, pageable);
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

    @Action(value = "customerAction_associateActivity", results =
    @Result(name = "toAssociation", type = "dispatcher", location = "/jsp/customer_association.jsp"))
    public String associateActivity() {
        Customer customer = customerService.findByCid(model.getCid());
        List<Activity> activityList = activityService.findForCustomer(model.getCid());
        ActionContext.getContext().getValueStack().set("customer", customer);
        ActionContext.getContext().getValueStack().set("activities", activityList);
        return "toAssociation";
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
