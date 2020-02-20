package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class SaleService {
	private final SaleRepository saleRepository;
	private final DisplayRepository displayRepository;
	private final StatusRepository statusRepository;
	private final SearchRepository searchRepository;
	private final StatusNumberRepository statusNumberRepository;

	/**
	 * ログイン情報取得
	 *
	 * @param mail_address
	 * @return
	 */
	public  Optional<Login> findOne(String mail_address) {
		return saleRepository.findById(mail_address);
	}

	/**
	 * テーブル取得
	 *
	 * @return
	 */
	public Page<Client> selectAll(Pageable pageable) {
		return displayRepository.findAll(pageable);
	}

	/**
	 * ユーザー情報登録
	 *
	 * @param saleRequest
	 */
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

	/**
	 * 該当Noのデータ1件取得
	 *
	 * @param no
	 * @return
	 */
	public Client getOne(String no) {
		return displayRepository.getOne(no);
	}

	/**
	 * 該当ユーザーデータの削除
	 *
	 * @param saleRequest
	 */
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

	/**
	 * 該当ユーザーデータの編集
	 *
	 * @param saleRequest
	 */
	public void edit(SaleRequest saleRequest) {

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
		customer.setDelete_flg("0");

        displayRepository.save(customer);
	}

	/**
	 * 部分検索時のテーブル取得
	 *
	 * @param saleRequest
	 * @return
	 */
	public List<Client> statusAll() {
	    return statusRepository.findByClient();
	}
	public List<Status> statusNumberAll() {
	    return statusRepository.findAll();
	}
	public Page<Client> searchAll(Pageable pageable, SaleRequest saleRequest) {
		if(saleRequest.getSearchClient()==""&&saleRequest.getSearchStatus()==""){
			return searchRepository.findAll(pageable, saleRequest.getSearchSomething());
		}else if(saleRequest.getSearchClient()=="") {
			return searchRepository.findIntAll(pageable, saleRequest.getSearchStatus(), saleRequest.getSearchSomething());
		}else if(saleRequest.getSearchStatus()=="") {
			return searchRepository.findAll(pageable, saleRequest.getSearchClient(), saleRequest.getSearchSomething());
		}else {
			return searchRepository.findAll(pageable, saleRequest.getSearchClient(), saleRequest.getSearchStatus(), saleRequest.getSearchSomething());
		}
	}

	public void status(SaleRequest saleRequest) {

		Status status = new Status();
		if(saleRequest.getStatus_numbers()=="") {
			status.setStatus(saleRequest.getStatus());
		}else {
			status.setStatus(saleRequest.getStatus());
			status.setStatus_numbers(saleRequest.getStatus_numbers());
		}

		statusNumberRepository.save(status);
	}
}