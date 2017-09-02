package com.aegis.acm.service;

import com.aegis.acm.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    void save(Customer customer);

    List<Customer> findAll();

    Customer findByCid(Integer cid);

    void addActivity(Integer cid, Integer aid);

    Page<Customer> findByPage(Pageable pageable);
}
