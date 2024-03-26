package com.example.boot13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot13.entity.Dept;

@Repository
public interface DeptRepository extends JpaRepository<Dept,Integer>{
	
}
