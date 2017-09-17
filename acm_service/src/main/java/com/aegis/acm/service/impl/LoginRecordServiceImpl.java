package com.aegis.acm.service.impl;

import com.aegis.acm.dao.LoginRecordDao;
import com.aegis.acm.domain.LoginRecord;
import com.aegis.acm.service.LoginRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginRecordServiceImpl implements LoginRecordService {
    @Autowired
    private LoginRecordDao loginRecordDao;

    @Override
    public void save(LoginRecord loginRecord) {
        loginRecordDao.save(loginRecord);
    }

    @Override
    public Page<LoginRecord> findTop100(Pageable pageable) {
        return loginRecordDao.findAll(pageable);
    }
}
