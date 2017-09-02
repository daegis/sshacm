package com.aegis.acm.web.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
@Results({
        @Result(name = "error", type = "redirect", location = "")
})
public class BaseAction<G> extends ActionSupport implements ModelDriven<G> {

    protected G model;
    protected Integer page; // current page
    protected Integer limit; // page size


    public BaseAction() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] types = genericSuperclass.getActualTypeArguments();
        Class<G> clazz = (Class<G>) types[0];
        try {
            model = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doAjaxResponse(Object containerOrStringMessage, final String... excludes) {
        try {
            String jsonString = JSON.toJSONString(containerOrStringMessage, new PropertyFilter() {
                @Override
                public boolean apply(Object object, String name, Object value) {
                    if (excludes.length == 0) {
                        return true;
                    }
                    List<String> list = Arrays.asList(excludes);
                    return !list.contains(name);
                }
            }, SerializerFeature.DisableCircularReferenceDetect);
            if (jsonString.charAt(0) == '"') {
                jsonString = jsonString.substring(1, jsonString.length() - 1);
            }
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doAjaxResponseIncludingFields(Object container, final String... includes) {
        try {
            String jsonString = JSON.toJSONString(container, new PropertyFilter() {
                @Override
                public boolean apply(Object object, String name, Object value) {
                    if (includes.length == 0) {
                        return false;
                    }
                    List<String> list = Arrays.asList(includes);
                    return list.contains(name);
                }
            }, SerializerFeature.DisableCircularReferenceDetect);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doAjaxResponseResultMap(boolean result, String message) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("success", result);
        map.put("message", message);
        doAjaxResponse(map);
    }

    @Override
    public G getModel() {
        return model;
    }

    public void setModel(G model) {
        this.model = model;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
