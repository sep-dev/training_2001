package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DisplayRepository extends JpaRepository<Client, String>{
	@Query(value="SELECT c "
			+ "FROM Client c "
			+ "LEFT OUTER JOIN Status s "
			+ "ON c.status_number = s.status_numbers"
			)
	public Page<Client> findAll(Pageable pageable);
	public Page<Client> findBySubjectContainingAndClient(Pageable pageable, String subject, String client);
}