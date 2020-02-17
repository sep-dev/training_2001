package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StatusRepository{
	@Select("SELECT distinct(client) FROM client")
	public List<Client> findAll();

	@Select("SELECT distinct(status_number) FROM client")
	public List<Client> findByStatus_number();
}