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
    private String newPassword1;
    private String newPassword2;

    @Autowired
    private UserService userService;

    @Action("userAction_login")
    public void login() {
        String sessionCaptcha = (String) ServletActionContext.getRequest().getSession().getAttribute("captcha");
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
                return;
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
