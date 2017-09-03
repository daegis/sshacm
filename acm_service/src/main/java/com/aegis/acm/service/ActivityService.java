package com.aegis.acm.service;

import com.aegis.acm.domain.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ActivityService {
    void save(Activity activity);

    List<Activity> findAll();

    Activity findByAid(Integer i);

    Page<Activity> findByPage(Pageable pageable);

    List<Activity> findForCustomer(Integer cid);
}
