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
    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "activity_date")
    private Date activityDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "activity_price")
    private Integer activityPrice;

    @Column(name = "activity_bus")
    private String activityBus;

    @Column(name = "day_count")
    private String dayCount;

    @Column(name = "in_use")
    private String inUse;

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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getInUse() {
        return inUse;
    }

    public void setInUse(String inUse) {
        this.inUse = inUse;
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
