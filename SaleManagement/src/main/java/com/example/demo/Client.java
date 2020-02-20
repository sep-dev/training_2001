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

import lombok.Data;


@Data
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
