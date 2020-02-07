package com.example.demo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class SaleService {
	private final SaleRepository saleRepository;

	public  Optional<Login> findOne(String mail_address) {
		return saleRepository.findById(mail_address);
	}
}
