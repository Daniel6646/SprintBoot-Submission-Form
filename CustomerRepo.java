package com.example.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Customers;

public interface CustomerRepo extends JpaRepository<Customers, Integer>{

}
