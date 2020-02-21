package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DisplayRepository extends JpaRepository<Client, String>{
	@Query(value="SELECT * "
			+ "FROM client "
			+ "LEFT OUTER JOIN status_master "
			+ "ON client.status_number = status_master.status_numbers"
			,nativeQuery=true
			)
	public Page<Client> findAll(Pageable pageable);
	public Page<Client> findBySubjectContainingAndClient(Pageable pageable, String subject, String client);
}