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
}
