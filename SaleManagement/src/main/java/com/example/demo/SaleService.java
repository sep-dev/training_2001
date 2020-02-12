package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class SaleService {
	private final SaleRepository saleRepository;
	private final DisplayRepository displayRepository;

	public  Optional<Login> findOne(String mail_address) {
		return saleRepository.findById(mail_address);
	}
	public List<Client> selectAll() {
		return displayRepository.findAll();
	}

	public void create(SaleRequest saleRequest) {

		Client customer = new Client();
		customer.setClient(saleRequest.getClient());
		customer.setOrder_date(saleRequest.getOrder_date().replace("/",""));
		customer.setS_number(saleRequest.getS_number());
		customer.setSubject(saleRequest.getSubject());
		customer.setQuantity(saleRequest.getQuantity());
		customer.setDelivery_date(saleRequest.getDelivery_date().replace("/",""));
		customer.setDue_date(saleRequest.getDue_date().replace("/",""));
		customer.setBilling_date(saleRequest.getBilling_date().replace("/",""));
		customer.setEstimated_amount(saleRequest.getEstimated_amount());
		customer.setOrder_amount(saleRequest.getOrder_amount());
		customer.setStatus_number(saleRequest.getStatus_number());
		customer.setRemarks(saleRequest.getRemarks());
		customer.setId("1");
		customer.setDelete_flg("0");

		displayRepository.save(customer);
	}

	public Client getOne(String no) {
		return displayRepository.getOne(no);
	}

	public void delete(SaleRequest saleRequest) {

		Client customer = new Client();
		customer.setNo(saleRequest.getNo());
		customer.setClient(saleRequest.getClient());
		customer.setOrder_date(saleRequest.getOrder_date().replace("/",""));
		customer.setS_number(saleRequest.getS_number());
		customer.setSubject(saleRequest.getSubject());
		customer.setQuantity(saleRequest.getQuantity());
		customer.setDelivery_date(saleRequest.getDelivery_date().replace("/",""));
		customer.setDue_date(saleRequest.getDue_date().replace("/",""));
		customer.setBilling_date(saleRequest.getBilling_date().replace("/",""));
		customer.setEstimated_amount(saleRequest.getEstimated_amount());
		customer.setOrder_amount(saleRequest.getOrder_amount());
		customer.setStatus_number(saleRequest.getStatus_number());
		customer.setRemarks(saleRequest.getRemarks());
		customer.setId("1");
		customer.setDelete_flg("1");

		displayRepository.save(customer);
	}
}
