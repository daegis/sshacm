//package com.aegis.acm.test;
//
//import com.aegis.acm.domain.Activity;
//import com.aegis.acm.domain.Customer;
//import com.aegis.acm.domain.JoinCA;
//import com.aegis.acm.service.ActivityService;
//import com.aegis.acm.service.CustomerService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//import java.util.List;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
//public class ServiceTest {
//
//    @Autowired
//    private ActivityService activityService;
//
//    @Autowired
//    private CustomerService customerService;
//
//    @Test
//    public void test01() {
//        Activity activity = new Activity();
//        activity.setActivityName("测试活动3");
//        activity.setActivityDate(new Date());
//        activity.setActivityBus("测试车辆3");
//        activity.setActivityPrice(1350);
//        activityService.save(activity);
//    }
//
//    @Test
//    public void test02() {
//        List<Activity> activityList = activityService.findAll();
//        for (Activity activity : activityList) {
//            System.out.println(activity);
//        }
//    }
//
//    @Test
//    public void test03() {
//        Customer customer = new Customer();
//        customer.setNickname("黄蓉");
//        customer.setName("黄蓉");
//        customer.setFirstAdd(new Date());
//        customer.setAddress("云南昆明");
//        customer.setMobile("17778787732");
//        customer.setIdNumber("320721199998987777");
//        customerService.save(customer);
//    }
//
//    @Test
//    public void test04() {
//        List<Customer> customerList = customerService.findAll();
//        for (Customer customer : customerList) {
//            System.out.println(customer);
//        }
//    }
//
//    // 为人员设置活动
//    // 为cid为1的人员添加aid为1的活动
//    @Test
//    public void test05() {
//        customerService.addActivity(4, 2);
//    }
//
//
//    //为活动添加人员
//}
