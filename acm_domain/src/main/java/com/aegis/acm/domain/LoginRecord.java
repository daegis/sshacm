package com.aegis.acm.domain;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class LoginRecord {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lid;

    @Column(name = "login_ip")
    private String loginIP;

    @Column(name = "login_date")
    private Date loginDate;

    @Column(name = "login_username")
    private String loginUsername;

    @Column(name = "login_password")
    private String loginPassword;

    @Column(name = "login_status")
    private String loginStatus;

    @Override
    public String toString() {
        return "LoginRecord{" +
                "lid=" + lid +
                ", loginIP='" + loginIP + '\'' +
                ", loginDate=" + loginDate +
                ", loginUsername='" + loginUsername + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", loginStatus='" + loginStatus + '\'' +
                '}';
    }

    public String getLoginLocation() throws IOException {
        return "pending";
    }

    public String getLoginTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.loginDate);
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }
}
