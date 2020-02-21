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
	public Page<Client> findClientAll(Pageable pageable) {
		return displayRepository.findAll(pageable);
	}

	/**
	 * ユーザー情報登録
	 *
	 * @param saleRequest
	 */
	public void insert(SaleRequest saleRequest) {

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
	public Client getOneClient(String no) {
		return displayRepository.getOne(no);
	}


	public Status getOneStatus(String status_numbers) {
		return statusNumberRepository.getOne(status_numbers);
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
	public List<Client> findClientList() {
	    return statusRepository.findByClient();
	}
	public List<Status> findStatusAndStatusNumber() {
	    return statusRepository.findAll();
	}
	public Page<Client> search(Pageable pageable, SaleRequest saleRequest) {

		//顧客名・ステータスに値がない場合
		if(saleRequest.getSearchClient()==""&&saleRequest.getSearchStatus()==""){
			return searchRepository.findAll(pageable, saleRequest.getSearchSubject());

		//顧客名に値がなく、ステータスに値がある場合
		}else if(saleRequest.getSearchClient()=="") {
			return searchRepository.findIntAll(pageable, saleRequest.getSearchStatus(), saleRequest.getSearchSubject());

		//顧客名に値があり、ステータスに値がない場合
		}else if(saleRequest.getSearchStatus()=="") {
			return searchRepository.findAll(pageable, saleRequest.getSearchClient(), saleRequest.getSearchSubject());

		//顧客名・ステータスに値がある場合
		}else {
			return searchRepository.findAll(pageable, saleRequest.getSearchClient(), saleRequest.getSearchStatus(), saleRequest.getSearchSubject());
		}
	}

	/**
	 * ステータス更新・新規登録
	 *
	 * @param saleRequest
	 */
	public void status(SaleRequest saleRequest) {

		Status status = new Status();

		//新規登録
		if(saleRequest.getStatus_numbers()=="") {
			status.setStatus(saleRequest.getStatus());
			status.setDelete_flgs("0");

		//更新
		}else {
			status.setStatus(saleRequest.getStatus());
			status.setStatus_numbers(saleRequest.getStatus_numbers());
			status.setDelete_flgs("0");
		}

		statusNumberRepository.save(status);
	}

	/**
	 * ステータス論理削除
	 *
	 * @param saleRequest
	 */
	public void statusdelete(SaleRequest saleRequest) {

		Status status = new Status();
		status.setStatus(saleRequest.getStatus());
		status.setStatus_numbers(saleRequest.getStatus_numbers());
		status.setDelete_flgs("1");

		statusNumberRepository.save(status);
	}

}