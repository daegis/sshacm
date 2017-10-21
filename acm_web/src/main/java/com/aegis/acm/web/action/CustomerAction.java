package com.aegis.acm.web.action;

import com.aegis.acm.domain.Activity;
import com.aegis.acm.domain.Customer;
import com.aegis.acm.service.ActivityService;
import com.aegis.acm.service.CustomerService;
import com.aegis.acm.utils.IDNumberUtil;
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
    private String aid;

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
                        Predicate id = cb.like(root.get("idNumber").as(String.class), keywordLike);
                        predicateList.add(id);
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
            String idNumber = model.getIdNumber();
            if (idNumber == null || "".equals(idNumber)) {
                customerService.save(model);
                doAjaxResponseResultMap(true, "操作成功");
                return;
            }
            boolean isValid = IDNumberUtil.checkID(idNumber);
            if (!isValid) {
                doAjaxResponseResultMap(false, "身份证号码校验不通过! 请注意: 一个非法的身份证号码录入是" +
                        "没有意义的, 本系统的校验方式和公安局的相同, 请仔细核查后重新进行输入. 暂时无法获取正确身份证号" +
                        "的情况下, 请将身份证号留空.另外: 身份证最后一位X是大写字母.");
                return;
            }
            Customer customer = customerService.findByIdNum(idNumber);
            if (customer != null && !Objects.equals(customer.getCid(), model.getCid())) {
                doAjaxResponseResultMap(false, "身份证号码与现有记录重复! 请注意: 没有两个人的身份证号是" +
                        "相同的, 请到相应功能页面搜索关键字对现有人员进行查找.");
                return;
            }
            customerService.save(model);
            doAjaxResponseResultMap(true, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            doAjaxResponseResultMap(false, "操作失败, 错误信息:" + e.getMessage() + ", 请截图联系管理员.");
        }
    }

    @Action(value = "customerAction_update", results =
    @Result(name = "toUpdate", type = "dispatcher", location = "/jsp/customer/customer_update.jsp"))
    public String update() {
        model = customerService.findByCid(model.getCid());
        ActionContext.getContext().getValueStack().set("customer", model);
        return "toUpdate";
    }

    @Action(value = "customerAction_associateActivity", results =
    @Result(name = "toAssociation", type = "dispatcher", location = "/jsp/customer/customer_association.jsp"))
    public String associateActivity() {
        Customer customer = customerService.findByCid(model.getCid());
        List<Activity> activityList = activityService.findForCustomer(model.getCid());
        ActionContext.getContext().getValueStack().set("customer", customer);
        ActionContext.getContext().getValueStack().set("activities", activityList);
        return "toAssociation";
    }

    @Action("customerAction_findByNotInActivity")
    public void findByNotInActivity() {
        try {
            if (keyword == null) {
                keyword = "";
            }
            page = (page - 1) * limit;
            keyword = "%" + keyword + "%";
            List<Customer> customerList = customerService.findByNotInActivity(aid, keyword, page, limit);
            int count = customerService.findByNotInActivityCount(aid, keyword);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("code", 0);
            map.put("msg", "");
            map.put("count", count);
            map.put("data", customerList);
            doAjaxResponse(map, "caList");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }
}
