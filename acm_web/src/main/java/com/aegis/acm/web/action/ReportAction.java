package com.aegis.acm.web.action;

import com.aegis.acm.domain.Activity;
import com.aegis.acm.service.ActivityService;
import com.aegis.acm.web.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@SuppressWarnings("Duplicates")
public class ReportAction extends BaseAction<Activity> {

    @Autowired
    private ActivityService activityService;

    @Action("reportAction_reportInsurance")
    public void reportInsurance() throws IOException {
        String filePath = ServletActionContext.getServletContext().getRealPath(File.separator)
                + File.separator + "template" + File.separator + "insurance.xls";
        FileInputStream in = new FileInputStream(filePath);
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader("content-disposition", "attachment;fileName=baoxian.xls");
        activityService.reportInsurance(model.getAid(), in, outputStream);
    }

    @Action(value = "reportAction_reportAnnouncement", results =
    @Result(name = "toAnnouncement", type = "dispatcher", location = "/jsp/announcement.jsp"))
    public String reportAnnouncement() {
        Activity activity = activityService.findByAid(model.getAid());
        ActionContext.getContext().getValueStack().set("activity", activity);
        return "toAnnouncement";
    }

    @Action("reportAction_reportNormalExcel")
    public void reportNormalExcel() throws Exception {
        String filePath = ServletActionContext.getServletContext().getRealPath(File.separator)
                + File.separator + "template" + File.separator + "newbook.xls";
        FileInputStream in = new FileInputStream(filePath);
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader("content-disposition", "attachment;fileName=mingdan.xls");
        activityService.reportNormalExcel(model.getAid(), in, outputStream);
    }
}
