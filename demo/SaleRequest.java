package com.example.demo;

import lombok.Data;

@Data
public class SaleRequest {
	private String mail_address;
	private String password;
	private String delete_flg;
}