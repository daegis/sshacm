package com.aegis.acm.web.action;

import com.aegis.acm.domain.Activity;
import com.aegis.acm.domain.JoinCA;
import com.aegis.acm.service.ActivityService;
import com.aegis.acm.web.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ActivityAction extends BaseAction<Activity> {

    @Autowired
    private ActivityService activityService;

    @Action("activityAction_findByPage")
    public void findByPage() {
        Sort sort = new Sort(Sort.Direction.DESC, "aid");
        Pageable pageable = new PageRequest(page - 1, limit, sort);
        Page<Activity> page = activityService.findByPage(pageable);
        long totalElements = page.getTotalElements();
        List<Activity> content = page.getContent();
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", totalElements);
        map.put("data", content);
        doAjaxResponse(map, "caList");
    }

    @Action("activityAction_save")
    public void save() {
        try {
            activityService.save(model);
            doAjaxResponseResultMap(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            doAjaxResponseResultMap(true, "添加失败, 原因: " + e.getMessage());
        }
    }

    @Action(value = "activityAction_update", results =
    @Result(name = "toUpdate", type = "dispatcher", location = "/jsp/activity/activity_update.jsp"))
    public String update() {
        model = activityService.findByAid(model.getAid());
        ActionContext.getContext().getValueStack().set("activity", model);
        return "toUpdate";
    }

    @Action(value = "activityAction_detail", results =
    @Result(name = "toDetail", type = "dispatcher", location = "/jsp/activity/activity_detail.jsp"))
    public String detail() {
        model = activityService.findByAid(model.getAid());
        ActionContext.getContext().getValueStack().set("activity", model);
        return "toDetail";
    }

    @Action("activityAction_findForDetail")
    public void findForDetail() {
        try {
            model = activityService.findByAid(model.getAid());
            List<JoinCA> caList = model.getCaList();
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("code", 0);
            map.put("msg", "");
            map.put("count", caList.size());
            map.put("data", caList);
            doAjaxResponse(map, "caList", "activity", "customer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Action(value = "activityAction_associate", results =
    @Result(name = "toAssociation", type = "dispatcher", location = "/jsp/join/join_ca_add.jsp"))
    public String forAssociation() {
        model = activityService.findByAid(model.getAid());
        ActionContext.getContext().getValueStack().set("activity", model);
        return "toAssociation";
    }

    @Action(value = "activityAction_report", results =
    @Result(name = "toReport", type = "dispatcher", location = "/jsp/reporter.jsp"))
    public String forReport() {
        List<Activity> activityList = activityService.findAll();
        ActionContext.getContext().getValueStack().set("activity", activityList);
        return "toReport";
    }
}
