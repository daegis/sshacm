package com.aegis.acm.web.action;

import com.aegis.acm.domain.User;
import com.aegis.acm.web.base.BaseAction;
import org.apache.struts2.convention.annotation.Action;

public class TestAction extends BaseAction<User> {

    @Action("test01")
    public void testSomething() {
        doAjaxResponse("来自项目的中文乱码测试");
    }
}
