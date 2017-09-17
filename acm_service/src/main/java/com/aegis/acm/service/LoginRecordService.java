package com.aegis.acm.service;

import com.aegis.acm.domain.LoginRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoginRecordService {
    void save(LoginRecord loginRecord);

    Page<LoginRecord> findTop100(Pageable pageable);
}
