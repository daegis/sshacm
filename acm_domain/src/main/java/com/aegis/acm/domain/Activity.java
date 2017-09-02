package com.aegis.acm.domain;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aid;
    private String activityName;
    private Date activityDate;
    private Integer activityPrice;
    private String activityBus;
    private String dayCount;

    @OneToMany(mappedBy = "activity", fetch = FetchType.EAGER)
    private List<JoinCA> caList;

    public Integer getCurrentCustomerCount() {
        return caList.size();
    }

    public String getActivityTime() {
        return (new SimpleDateFormat("yyyy-MM-dd")).format(activityDate);
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDayCount() {
        return dayCount;
    }

    public void setDayCount(String dayCount) {
        this.dayCount = dayCount;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public Integer getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(Integer activityPrice) {
        this.activityPrice = activityPrice;
    }

    public String getActivityBus() {
        return activityBus;
    }

    public void setActivityBus(String activityBus) {
        this.activityBus = activityBus;
    }

    public List<JoinCA> getCaList() {
        return caList;
    }

    public void setCaList(List<JoinCA> caList) {
        this.caList = caList;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "aid=" + aid +
                ", activityName='" + activityName + '\'' +
                ", activityDate='" + activityDate + '\'' +
                ", activityPrice=" + activityPrice +
                ", activityBus='" + activityBus + '\'' +
                '}';
    }
}
