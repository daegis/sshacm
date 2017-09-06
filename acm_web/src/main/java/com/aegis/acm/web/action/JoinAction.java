package com.aegis.acm.web.action;

import com.aegis.acm.domain.Activity;
import com.aegis.acm.domain.Customer;
import com.aegis.acm.domain.JoinCA;
import com.aegis.acm.service.JoinCAService;
import com.aegis.acm.web.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

public class JoinAction extends BaseAction<JoinCA> {

    @Autowired
    private JoinCAService joinCAService;

    @Action(value = "joinAction_update", results =
    @Result(name = "toUpdate", type = "dispatcher", location = "/jsp/join_ca_edit.jsp"))
    public String update() {
        JoinCA joinCA = joinCAService.findByJid(model.getJid());
        ActionContext.getContext().getValueStack().set("joinca", joinCA);
        return "toUpdate";
    }

    @Action("joinAction_save")
    public void save() {
        try {
            joinCAService.save(model);
            doAjaxResponseResultMap(true, "");
        } catch (Exception e) {
            e.printStackTrace();
            doAjaxResponseResultMap(false, "操作失败,原因" + e.getMessage());
        }
    }

    @Action("joinAction_doAssociation")
    public void doAssociation() {
        try {
            joinCAService.doAssociation(model);
            doAjaxResponseResultMap(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            doAjaxResponseResultMap(false, "操作失败,原因" + e.getMessage());
        }
    }

    @Action("joinAction_deleteFromActivity")
    public void deleteFromActivity() {
        try {
            joinCAService.joinAction_deleteFromActivity(model);
            doAjaxResponseResultMap(true, "移除成功");
        } catch (Exception e) {
            e.printStackTrace();
            doAjaxResponseResultMap(true, "移除失败, 原因: " + e.getMessage());
        }
    }
}
