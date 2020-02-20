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

import lombok.Data;


@Data
@Entity
@Table(name="status_master")
public class Status implements Serializable{
    @Id
    @Column(name="STATUS_NUMBERS")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String status_numbers;
    private String status;

    @OneToMany(mappedBy = "status_number")
    private List<Client> clientList;
}
