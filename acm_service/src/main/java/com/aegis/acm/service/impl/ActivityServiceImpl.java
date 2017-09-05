package com.aegis.acm.service.impl;

import com.aegis.acm.dao.ActivityDao;
import com.aegis.acm.domain.Activity;
import com.aegis.acm.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Override
    public void save(Activity activity) {
        if (activity.getActivityDate() == null) {
            activity.setActivityDate(new Date());
        }
        activityDao.save(activity);
    }

    @Override
    public List<Activity> findAll() {
        return activityDao.findAll();
    }

    @Override
    public Activity findByAid(Integer aid) {
        return activityDao.findOne(aid);
    }

    @Override
    public Page<Activity> findByPage(Pageable pageable) {
        return activityDao.findAll(pageable);
    }

    @Override
    public List<Activity> findForCustomer(Integer cid) {
        return activityDao.findForCustomer(cid);
    }
}
