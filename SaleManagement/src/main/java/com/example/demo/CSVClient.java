package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonPropertyOrder({"No", "顧客", "受注日", "S番号","件名","数量","納入指定日","納入日","請求日","見積金額","受注金額","ステータス"})
@Data
public class CSVClient {
    @JsonProperty("No")
    private String no;
    @JsonProperty("顧客")
    private String client;
    @JsonProperty("受注日")
    private String order_date;
    @JsonProperty("S番号")
    private String s_number;
    @JsonProperty("件名")
    private String subject;
    @JsonProperty("数量")
    private String quantity;
    @JsonProperty("納入指定日")
    private String delivery_date;
    @JsonProperty("納入日")
    private String due_date;
    @JsonProperty("請求日")
    private String billing_date;
    @JsonProperty("見積金額")
    private String estimated_amount;
    @JsonProperty("受注金額")
    private String order_amount;
    @JsonProperty("ステータス")
    private String status;

    public CSVClient() {}

    public CSVClient(String no, String client, String order_date, String s_number, String subject, String quantity, String delivery_date, String due_date, String billing_date, String estimated_amount, String order_amount, String status) {
        this.no = no;
        this.client = client;
        this.order_date = order_date;
        this.s_number = s_number;
        this.subject = subject;
        this.quantity = quantity;
        this.delivery_date = delivery_date;
        this.due_date = due_date;
        this.billing_date = billing_date;
        this.estimated_amount = estimated_amount;
        this.order_amount = order_amount;
        this.status = status;
    }
}