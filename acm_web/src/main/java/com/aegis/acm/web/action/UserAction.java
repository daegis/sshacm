package com.aegis.acm.web.action;

import com.aegis.acm.domain.User;
import com.aegis.acm.service.UserService;
import com.aegis.acm.web.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public class UserAction extends BaseAction<User> {

    private String captcha;
    private String newPassword1;
    private String newPassword2;

    @Autowired
    private UserService userService;


    @Action("userAction_login")
    public void login() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String sessionCaptcha = (String) request.getSession().getAttribute("captcha");
        if (sessionCaptcha == null || !sessionCaptcha.toLowerCase().equals(captcha.toLowerCase())) {
            doAjaxResponseResultMap(false, "验证码不正确, 请注意所有的圈都是数字0. 同时, 验证码不区分大小写");
        } else {
            request.getSession().removeAttribute("captcha");
            UsernamePasswordToken token = new UsernamePasswordToken(model.getUsername(), model.getPassword());
            Subject subject = SecurityUtils.getSubject();
            try {
                subject.login(token);
                doAjaxResponseResultMap(true, "success");
            } catch (Exception e) {
                doAjaxResponseResultMap(false, "用户名或密码错误");
            }
        }
    }

    @Action(value = "userAction_logout", results =
    @Result(name = "logout", type = "redirect", location = "/"))
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "logout";
    }

    @Action("userAction_changePassword")
    public void changePassword() {
        try {
            if (newPassword1 != null && newPassword2 != null && newPassword1.equals(newPassword2)) {
                User user = (User) SecurityUtils.getSubject().getPrincipal();
                User dbuser = userService.findByUsernameAndPassword(user.getUsername(), model.getPassword());
                if (dbuser == null) {
                    doAjaxResponseResultMap(false, "旧密码不正确,无法修改密码");
                    return;
                }
                dbuser.setPassword(newPassword2);
                userService.save(dbuser);
                SecurityUtils.getSubject().logout();
                doAjaxResponseResultMap(true, "修改成功, 请重新登录");
            } else {
                doAjaxResponseResultMap(false, "两次输入的新密码不一致, 请重新输入");
            }
        } catch (Exception e) {
            e.printStackTrace();
            doAjaxResponseResultMap(false, "修改失败, 请重试");
        }
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
