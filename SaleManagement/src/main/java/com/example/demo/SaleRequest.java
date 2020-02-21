package com.example.demo;

import lombok.Data;

@Data
public class SaleRequest {
	private String mail_address;
	private String password;
	private String delete_flg;
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
	private String remarks;
	private String id;
	private String status;
	private String status_numbers;
	private String delete_flgs;

	String searchSubject;
	String searchClient;
	String searchStatus;
}