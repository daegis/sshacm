package com.aegis.acm.dao;

import com.aegis.acm.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityDao extends JpaRepository<Activity, Integer> {
    @Query("select a from Activity a where a.aid not in (select j.activity.aid from JoinCA j where j.customer.cid = ?) order by a.aid desc")
    List<Activity> findForCustomer(Integer cid);
}
