package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisplayRepository extends JpaRepository<Client, String>{
	public Page<Client> findAll(Pageable pageable);
	public Page<Client> findBySubjectContaining(Pageable pageable, String subject);
}