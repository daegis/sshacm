package com.aegis.acm.service.impl;

import com.aegis.acm.dao.ActivityDao;
import com.aegis.acm.domain.Activity;
import com.aegis.acm.domain.Customer;
import com.aegis.acm.domain.JoinCA;
import com.aegis.acm.service.ActivityService;
import com.aegis.acm.utils.IDNumberUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author XIAN_Yingda
 */
@SuppressWarnings({"SpringAutowiredFieldsWarningInspection", "Duplicates"})
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
        if (activity.getEndDate() == null) {
            activity.setEndDate(new Date());
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

    @Override
    public void reportInsurance(Integer aid, FileInputStream in, ServletOutputStream outputStream) {
        Activity activity = activityDao.findOne(aid);
        List<JoinCA> caList = activity.getCaList();
        try {
            HSSFWorkbook book = new HSSFWorkbook(in);
            HSSFSheet sheet = book.getSheetAt(0);
            sheet.setForceFormulaRecalculation(true);
            int rowIndex = 15;
            HSSFRow row;
            for (JoinCA joinCA : caList) {
                Customer customer = joinCA.getCustomer();
                row = sheet.getRow(rowIndex);
                row.getCell(2).setCellValue(customer.getName());
                row.getCell(3).setCellValue("身份证");
                row.getCell(4).setCellValue(customer.getIdNumber());
                row.getCell(7).setCellValue(customer.getMobile());
                rowIndex++;
            }
            book.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reportNormalExcel(Integer aid, FileInputStream in, ServletOutputStream outputStream) {
        Activity activity = activityDao.findOne(aid);
        List<JoinCA> caList = activity.getCaList();
        try {
            HSSFWorkbook book = new HSSFWorkbook(in);
            HSSFSheet sheet = book.getSheetAt(0);
            sheet.setForceFormulaRecalculation(true);
            int rowIndex = 1;
            HSSFRow row;
            for (JoinCA joinCA : caList) {
                Customer customer = joinCA.getCustomer();
                row = sheet.createRow(rowIndex);
                row.createCell(0).setCellValue(Long.parseLong(joinCA.getBusSeat()));
                row.createCell(1).setCellValue(customer.getName());
                row.createCell(2).setCellValue(customer.getMobile());
                row.createCell(3).setCellValue(customer.getAge());
                row.createCell(4).setCellValue(IDNumberUtil.getGender(customer.getIdNumber()));
                row.createCell(5).setCellValue(customer.getIdNumber());
                rowIndex++;
            }
            book.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reportPaymentExcel(Integer aid, FileInputStream in, ServletOutputStream outputStream) {
        Activity activity = activityDao.findOne(aid);
        List<JoinCA> caList = activity.getCaList();
        try {
            HSSFWorkbook book = new HSSFWorkbook(in);
            HSSFSheet sheet = book.getSheetAt(0);
            sheet.setForceFormulaRecalculation(true);
            int rowIndex = 1;
            HSSFRow row;
            for (JoinCA joinCA : caList) {
                Customer customer = joinCA.getCustomer();
                row = sheet.createRow(rowIndex);
                row.createCell(0).setCellValue(customer.getNickname());
                row.createCell(1).setCellValue(customer.getName());
                row.createCell(2).setCellValue(activity.getActivityPrice());
                row.createCell(3).setCellValue(joinCA.getDiscount());
                row.createCell(4).setCellValue(joinCA.getPrepay());
                row.createCell(5).setCellValue(joinCA.getRestPay());
                row.createCell(8).setCellValue(joinCA.getPayMethod());
                row.createCell(9).setCellValue(customer.getMobile());
                row.createCell(10).setCellValue(customer.getIdNumber());
                rowIndex++;
            }
            book.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
