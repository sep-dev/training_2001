package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;



@Entity
@Table(name="client")
@Where(clause="delete_flg = 0")
public class Client {
    @Id
    @Column(name="NO")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String no;
    private String client;
    private String order_date;
    private String s_number;
    private String subject;
    private String quantity;
    private String delivery_date;
    private String due_date;
    private String billing_date;
    private String estimated_amount;
    private String order_amount;
    private String status_number;
    private String id;
    private String delete_flg;
    private String remarks;


    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public String getClient() {
        return client;
    }
    public void setClient(String client) {
        this.client = client;
    }
    public String getOrder_date() {
        return order_date;
    }
    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
    public String getS_number() {
        return s_number;
    }
    public void setS_number(String s_number) {
        this.s_number = s_number;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getDelivery_date() {
        return delivery_date;
    }
    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }
    public String getDue_date() {
        return due_date;
    }
    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
    public String getBilling_date() {
        return billing_date;
    }
    public void setBilling_date(String billing_date) {
        this.billing_date = billing_date;
    }
    public String getEstimated_amount() {
        return estimated_amount;
    }
    public void setEstimated_amount(String estimated_amount) {
        this.estimated_amount = estimated_amount;
    }
    public String getOrder_amount() {
        return order_amount;
    }
    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }
    public String getStatus_number() {
        return status_number;
    }
    public void setStatus_number(String status_number) {
        this.status_number = status_number;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDelete_flg() {
        return delete_flg;
    }
    public void setDelete_flg(String delete_flg) {
        this.delete_flg = delete_flg;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_number", referencedColumnName = "status_numbers", insertable = false, updatable = false)
    private Status status;
    public String getStatus() {
        return status.getStatus();
    }
    public void setStatus(String status) {
        this.status.setStatus(status);
    }
}
