package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.Basic;

@Mapper
public interface BasicMapper {
	List<Basic> findByDate(@Param("date1") String date1,@Param("date2") String date2);
}
