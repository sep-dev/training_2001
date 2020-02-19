package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends JpaRepository<Client, String>{

	@Query(value="SELECT c "
			+ "FROM Client c "
			+ "WHERE c.client =  :searchClient "
			+ "AND c.status_number =  :searchStatus  "
			+ "AND c.delete_flg = 0 "
			+ "AND c.subject like %:searchSomething%"
			)
	public Page<Client> findAll(
			Pageable pageable,
			@Param("searchClient") String searchClient,
			@Param("searchStatus") String searchStatus,
			@Param("searchSomething") String searchSomething);

	@Query(value="SELECT c "
			+ "FROM Client c "
			+ "WHERE c.status_number =  :searchStatus "
			+ "AND c.delete_flg = 0 "
			+ "AND c.subject like %:searchSomething%"
			)
	public Page<Client> findIntAll(
			Pageable pageable,
			@Param("searchStatus") String searchStatus,
			@Param("searchSomething") String searchSomething);

	@Query(value="SELECT c "
			+ "FROM Client c "
			+ "WHERE c.client =  :searchClient "
			+ "AND c.delete_flg = 0 "
			+ "AND c.subject like %:searchSomething%"
			)
	public Page<Client> findAll(
			Pageable pageable,
			@Param("searchClient") String searchClient,
			@Param("searchSomething") String searchSomething);

	@Query(value="SELECT c "
			+ "FROM Client c "
			+ "WHERE c.delete_flg = 0 "
			+ "AND c.subject like %:searchSomething%"
			)
	public Page<Client> findAll(
			Pageable pageable,
			@Param("searchSomething") String searchSomething);

}