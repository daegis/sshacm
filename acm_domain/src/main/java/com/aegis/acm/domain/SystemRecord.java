package com.aegis.acm.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SystemRecord {

    @Id
    @Column(name = "srid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer srid;

    @Column(name = "operation_date")
    private Date operationDate;

    @Column(name = "operation_desc")
    private String operationDescription;

    @Column(name = "operation_params")
    private String operationParams;

    @Column(name = "operator")
    private String operator;

    @Override
    public String toString() {
        return "SystemRecord{" +
                "srid=" + srid +
                ", operationDate=" + operationDate +
                ", operationDescription='" + operationDescription + '\'' +
                ", operationParams='" + operationParams + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }

    public Integer getSrid() {
        return srid;
    }

    public void setSrid(Integer srid) {
        this.srid = srid;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public String getOperationParams() {
        return operationParams;
    }

    public void setOperationParams(String operationParams) {
        this.operationParams = operationParams;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
