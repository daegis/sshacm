package com.aegis.acm.domain;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "joinCA")
public class JoinCA {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jid;

    private Date joinDate;
    private Integer discount;
    private Integer prepay;
    private Integer isLeader;
    private String payMethod; // 缴费方式
    private String jnote; // 活动备注

    @ManyToOne
    @JoinColumn(name = "aid", referencedColumnName = "aid")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "cid", referencedColumnName = "cid")
    private Customer customer;

    public String getJoinTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(this.joinDate);
    }

    public Integer getRestPay() {
        try {
            return activity.getActivityPrice() - discount - prepay;
        } catch (Exception e) {
            return null;
        }
    }

    public Integer getJid() {
        return jid;
    }

    public void setJid(Integer jid) {
        this.jid = jid;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getPrepay() {
        return prepay;
    }

    public void setPrepay(Integer prepay) {
        this.prepay = prepay;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(Integer isLeader) {
        this.isLeader = isLeader;
    }

    @Override
    public String toString() {
        return "JoinCA{" +
                "jid=" + jid +
                ", joinDate=" + joinDate +
                ", discount=" + discount +
                ", prepay=" + prepay +
                ", activity=" + activity +
                ", customer=" + customer +
                '}';
    }
}
