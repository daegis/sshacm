package com.aegis.acm.dao;

import com.aegis.acm.domain.SystemRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemRecordDao extends JpaRepository<SystemRecord, Integer> {
}
