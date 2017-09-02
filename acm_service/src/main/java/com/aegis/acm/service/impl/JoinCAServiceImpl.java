package com.aegis.acm.service.impl;

import com.aegis.acm.dao.JoinCADao;
import com.aegis.acm.domain.JoinCA;
import com.aegis.acm.service.JoinCAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JoinCAServiceImpl implements JoinCAService {

    @Autowired
    private JoinCADao joinCADao;

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
}
