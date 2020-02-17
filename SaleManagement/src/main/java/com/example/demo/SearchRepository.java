package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends JpaRepository<Client, String>{
/*	@Select("SELECT * "
			+ "FROM client "
			+ "WHERE client =  #{searchClient}  "
			+ "AND status_number = #{searchStatus} "
			+ "AND subject like % #{searchSomething} %")
	public Page<Client> findAll2(
			Pageable pageable,
			@Param("searchClient") String searchClient,
			@Param("searchStatus") String searchStatus,
			@Param("searchSomething") String searchSomething);
*/

	@Query(value="SELECT * "
			+ "FROM client "
			+ "WHERE client =  :searchClient "
			+ "AND status_number =  :searchStatus  "
			+ "AND subject like %:searchSomething%"
			, nativeQuery = true)
	public Page<Client> findAll(
			Pageable pageable,
			@Param("searchClient") String searchClient,
			@Param("searchStatus") String searchStatus,
			@Param("searchSomething") String searchSomething);

	@Query(value="SELECT * "
			+ "FROM client "
			+ "WHERE status_number =  :searchStatus "
			+ "AND subject like %:searchSomething%"
			, nativeQuery = true)
	public Page<Client> findAll(
			Pageable pageable,
			@Param("searchStatus") int searchStatus,
			@Param("searchSomething") String searchSomething);

	@Query(value="SELECT * "
			+ "FROM client "
			+ "WHERE client =  :searchClient "
			+ "AND subject like %:searchSomething%"
			, nativeQuery = true)
	public Page<Client> findAll(
			Pageable pageable,
			@Param("searchClient") String searchClient,
			@Param("searchSomething") String searchSomething);

	@Query(value="SELECT * "
			+ "FROM client "
			+ "WHERE  subject like %:searchSomething%"
			, nativeQuery = true)
	public Page<Client> findAll(
			Pageable pageable,
			@Param("searchSomething") String searchSomething);

}