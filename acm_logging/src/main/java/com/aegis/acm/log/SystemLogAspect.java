package com.aegis.acm.log;

import com.aegis.acm.commons.SystemLog;
import com.aegis.acm.dao.SystemRecordDao;
import com.aegis.acm.domain.SystemRecord;
import com.aegis.acm.domain.User;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class SystemLogAspect {

    @Autowired
    private SystemRecordDao systemRecordDao;

    @Pointcut("@annotation(com.aegis.acm.commons.SystemLog)")
    public void actionAspect() {

    }

    @After("actionAspect()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            SystemRecord record = new SystemRecord();
            record.setOperationDate(new Date());
            record.setOperationDescription(getAspectDescription(joinPoint));
            Object[] args = joinPoint.getArgs();
            record.setOperationParams(Arrays.toString(args));
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            String operator;
            if (user != null) {
                operator = user.getUid() + "-" + user.getUsername();
            } else {
                operator = "未登录";
            }
            record.setOperator(operator);
            saveRecord(record);
        } catch (Exception ignored) {
        }
    }

    private static String getAspectDescription(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    @Transactional
    void saveRecord(SystemRecord record) {
        systemRecordDao.save(record);
    }

}
