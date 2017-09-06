package com.aegis.acm.web.action;

import com.aegis.acm.domain.User;
import com.aegis.acm.service.UserService;
import com.aegis.acm.web.base.BaseAction;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAction extends BaseAction<User> {

    private String captcha;

    @Autowired
    private UserService userService;

    @Action("userAction_login")
    public void login() {
        String sessionCaptcha = (String) ServletActionContext.getRequest().getSession().getAttribute("captcha");
        System.out.println(sessionCaptcha);
        System.out.println(captcha);
        if (sessionCaptcha == null || !sessionCaptcha.equals(captcha)) {
            doAjaxResponseResultMap(false, "验证码不正确, 请注意所有的圈都是数字0");
            return;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(model.getUsername(), model.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            doAjaxResponseResultMap(true, "success");
        } catch (Exception e) {
            e.printStackTrace();
            doAjaxResponseResultMap(false, "用户名或密码错误");
        }
    }

    @Action(value = "userAction_logout", results =
    @Result(name = "logout", type = "redirect", location = "/"))
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "logout";
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
