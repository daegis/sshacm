package com.aegis.acm.service.impl;

import com.aegis.acm.dao.ActivityDao;
import com.aegis.acm.dao.CustomerDao;
import com.aegis.acm.dao.JoinCADao;
import com.aegis.acm.domain.Activity;
import com.aegis.acm.domain.Customer;
import com.aegis.acm.domain.JoinCA;
import com.aegis.acm.service.ActivityService;
import com.aegis.acm.service.CustomerService;
import com.aegis.acm.service.JoinCAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class JoinCAServiceImpl implements JoinCAService {

    @Autowired
    private JoinCADao joinCADao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private CustomerDao customerDao;

    @Override
    public JoinCA findByJid(Integer jid) {
        return joinCADao.findOne(jid);
    }

    @Override
    public void save(JoinCA model) {
        JoinCA joinCA = joinCADao.findOne(model.getJid());
        joinCA.setPrepay(model.getPrepay());
        joinCA.setJoinDate(model.getJoinDate());
        joinCA.setDiscount(model.getDiscount());
        joinCA.setJnote(model.getJnote());
        joinCA.setPayMethod(model.getPayMethod());
    }

    @Override
    public void doAssociation(JoinCA model) {
        Customer customer = customerDao.findOne(model.getCustomer().getCid());
        Activity activity = activityDao.findOne(model.getActivity().getAid());
        model.setCustomer(customer);
        model.setActivity(activity);
        customer.getCaList().add(model);
        activity.getCaList().add(model);
        if (model.getJoinDate() == null) {
            model.setJoinDate(new Date());
        }
        if (model.getDiscount() == null) {
            model.setDiscount(0);
        }
        if (model.getPrepay() == null) {
            model.setPrepay(0);
        }
        joinCADao.save(model);
    }

    @Override
    public void joinAction_deleteFromActivity(JoinCA model) {
        joinCADao.delete(model);
    }

    @Override
    public void addBusSeat(JoinCA model) {
        JoinCA one = joinCADao.findOne(model.getJid());
        one.setBusSeat(model.getBusSeat());
    }
}
