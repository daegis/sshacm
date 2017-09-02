package com.aegis.acm.dao;

import com.aegis.acm.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityDao extends JpaRepository<Activity, Integer> {
}
