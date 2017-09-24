package com.aegis.acm.service;

import com.aegis.acm.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CustomerService {
    void save(Customer customer);

    List<Customer> findAll();

    Customer findByCid(Integer cid);

    void addActivity(Integer cid, Integer aid);

    Page<Customer> findByPage(Pageable pageable);

    Page<Customer> findByPage(Specification<Customer> specification, Pageable pageable);

    List<Customer> findByNotInActivity(String aid, String keyword, Integer page, Integer limit);

    int findByNotInActivityCount(String aid, String keyword);

    Customer findByIdNum(String idNum);
}
