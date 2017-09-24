package com.aegis.acm.dao;

import com.aegis.acm.domain.Customer;
import com.aegis.acm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {
    @Query(value = "SELECT * FROM customers c WHERE c.cid NOT IN ( SELECT cid FROM joinca j WHERE j.aid = ?) AND (c. NAME LIKE ? OR c.nickname LIKE ? OR c.address LIKE ? OR c.mobile LIKE ?) ORDER BY c.cid DESC LIMIT ?, ?", nativeQuery = true)
    List<Customer> findByNotInActivity(String aid, String keyword1, String keyword2, String keyword3, String keyword4, Integer page, Integer limit);

    @Query(value = "SELECT count(*) FROM customers c WHERE c.cid NOT IN ( SELECT cid FROM joinca j WHERE j.aid = ?) AND (c. NAME LIKE ? OR c.nickname LIKE ? OR c.address LIKE ? OR c.mobile LIKE ?)", nativeQuery = true)
    int findByNotInActivityCount(String aid, String keyword, String keyword1, String keyword2, String keyword3);

    List<Customer> findByIdNumber(String idNum);
}
