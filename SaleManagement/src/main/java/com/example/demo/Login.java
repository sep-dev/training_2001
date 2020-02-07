package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;


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


    public String getMail_address() {
        return mail_address;
    }
    public void setMail_address(String mail_address) {
        this.mail_address = mail_address;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDelete_flg() {
        return delete_flg;
    }
    public void setDelete_flg(String delete_flg) {
        this.delete_flg = delete_flg;
    }
}