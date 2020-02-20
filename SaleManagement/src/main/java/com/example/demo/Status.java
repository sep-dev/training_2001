package com.example.demo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="status_master")
public class Status implements Serializable{
    @Id
    //Testadd↓
    @Column(name="STATUS_NUMBERS")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    //Testadd↑
    private String status_numbers;
    private String status;

    public String getStatus_numbers() {
        return status_numbers;
    }
    public void setStatus_numbers(String status_numbers) {
        this.status_numbers = status_numbers;
    }
    public String getStatus() {
    	return status;
    }
    public void setStatus(String status) {
    	this.status = status;
    }
    @OneToMany(mappedBy = "status_number")
    private List<Client> clientList;
}
