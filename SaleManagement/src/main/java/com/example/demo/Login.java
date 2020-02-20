package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Data;

@Data
@Entity
@Table(name="login")
@Where(clause="delete_flg = 0")
public class Login {
    @Id
    @Column(name="MAIL_ADDRESS")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String mail_address;
    private String password;
    private String delete_flg;
}