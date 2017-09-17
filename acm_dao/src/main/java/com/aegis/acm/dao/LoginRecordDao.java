package com.aegis.acm.dao;

import com.aegis.acm.domain.LoginRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRecordDao extends JpaRepository<LoginRecord, Integer> {
}
