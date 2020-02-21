package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StatusRepository{
	@Select("SELECT distinct client FROM client")
	public List<Client> findByClient();

	@Select("SELECT distinct status, status_numbers FROM status_master WHERE delete_flgs = 0")
	public List<Status> findAll();
}