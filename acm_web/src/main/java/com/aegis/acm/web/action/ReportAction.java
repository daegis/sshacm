package com.aegis.acm.web.action;

import com.aegis.acm.domain.Activity;
import com.aegis.acm.service.ActivityService;
import com.aegis.acm.web.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
}
