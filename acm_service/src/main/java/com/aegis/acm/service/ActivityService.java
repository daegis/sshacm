package com.aegis.acm.service;

import com.aegis.acm.domain.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.ServletOutputStream;
import java.io.FileInputStream;
import java.util.List;

public interface ActivityService {
    void save(Activity activity);

    List<Activity> findAll();

    Activity findByAid(Integer i);

    Page<Activity> findByPage(Pageable pageable);

    List<Activity> findForCustomer(Integer cid);

    void reportInsurance(Integer aid, FileInputStream in, ServletOutputStream outputStream);

    void reportNormalExcel(Integer aid, FileInputStream in, ServletOutputStream outputStream);
}
