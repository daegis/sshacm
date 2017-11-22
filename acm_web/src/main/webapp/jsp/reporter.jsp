<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
    <legend>生成报表</legend>
</fieldset>
<div class="layui-form">
    <table class="layui-table">
        <colgroup>
            <col width="300">
            <col width="120">
            <col width="150">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>活动名称</th>
            <th colspan="3">报表操作</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="activity">
            <tr>
                <td><s:property value="activityName"/></td>
                <td colspan="3">
                    <a href="${pageContext.request.contextPath}/reportAction_reportInsurance.action?aid=<s:property value="aid"/>"
                       class="layui-btn layui-btn-small layui-btn-normal insurance">保险单</a>
                    <a href="${pageContext.request.contextPath}/reportAction_reportAnnouncement.action?aid=<s:property value="aid"/>"
                       class="layui-btn layui-btn-small" target="_blank">免责声明</a>
                    <a href="${pageContext.request.contextPath}/reportAction_reportNormalExcel.action?aid=<s:property value="aid"/>"
                       class="layui-btn layui-btn-small layui-btn-danger">常规人员登记表</a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
<script>
    layui.use('jquery', function () {
        var $ = layui.jquery;
        $(".insurance").click(function () {
            layer.alert('生成的表单根据已经填写的人员身份信息自动生成, 生成之前请检查活动中的人员的年龄是否能正确显示, 能正常显示年龄则表示身份证号码是一个合法的身份证号码');
        })
    })
</script>
</body>
</html>
