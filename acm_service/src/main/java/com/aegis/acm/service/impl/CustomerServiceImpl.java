package com.aegis.acm.service.impl;

import com.aegis.acm.dao.ActivityDao;
import com.aegis.acm.dao.CustomerDao;
import com.aegis.acm.dao.JoinCADao;
import com.aegis.acm.domain.Activity;
import com.aegis.acm.domain.Customer;
import com.aegis.acm.domain.JoinCA;
import com.aegis.acm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private JoinCADao joinCADao;

    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public Customer findByCid(Integer cid) {
        return customerDao.findOne(cid);
    }

    @Override
    public void addActivity(Integer cid, Integer aid) {
        Customer customer = customerDao.findOne(cid);
        Activity activity = activityDao.findOne(aid);
        JoinCA ca = new JoinCA();
        ca.setDiscount(200);
        ca.setJoinDate(new Date());
        ca.setPrepay(800);
        ca.setCustomer(customer);
        ca.setActivity(activity);
        customer.getCaList().add(ca);
        activity.getCaList().add(ca);
        joinCADao.save(ca);
    }

    @Override
    public Page<Customer> findByPage(Pageable pageable) {
        return customerDao.findAll(pageable);
    }

    @Override
    public Page<Customer> findByPage(Specification<Customer> specification, Pageable pageable) {
        return customerDao.findAll(specification, pageable);
    }

    @Override
    public List<Customer> findByNotInActivity(String aid, String keyword, Integer page, Integer limit) {
        return customerDao.findByNotInActivity(aid, keyword, keyword, keyword, keyword, page, limit);
    }

    @Override
    public int findByNotInActivityCount(String aid, String keyword) {
        return customerDao.findByNotInActivityCount(aid, keyword, keyword, keyword, keyword);
    }

    @Override
    public Customer findByIdNum(String idNum) {
        if (idNum != null && !"".equals(idNum)) {
            List<Customer> customerList = customerDao.findByIdNumber(idNum);
            if (customerList == null || customerList.size() == 0) {
                return null;
            }
            return customerList.get(0);
        }
        return null;
    }
}
