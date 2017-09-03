<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title">
    <legend>当前活动: <s:property value="activity.activityName"/></legend>
</fieldset>
<div class="layui-form">
    <table class="layui-table">
        <colgroup>
            <col width="250">
            <col width="150">
            <col width="120">
            <col width="120">
            <col width="200">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>活动名称</th>
            <th>开始时间</th>
            <th>天数</th>
            <th>当前人数</th>
            <th>价格</th>
            <th>使用车辆</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><s:property value="activity.activityName"/></td>
            <td><s:property value="activity.activityTime"/></td>
            <td><s:property value="activity.dayCount"/></td>
            <td><s:property value="activity.currentCustomerCount"/></td>
            <td><s:property value="activity.activityPrice"/></td>
            <td><s:property value="activity.activityBus"/></td>
        </tr>
        </tbody>
    </table>
</div>
<fieldset class="layui-elem-field layui-field-title">
    <legend>待添加人员名单</legend>
</fieldset>
<form class="layui-form" action="" id="mainForm">
    <div class="layui-container" style="width: 1080px">
        <div class="layui-row">
            <div class="layui-col-md3" style="margin-bottom: 20px">
                <input type="checkbox" name="" title="赵治博(六个字的昵称)">
            </div>
            <div class="layui-col-md3" style="margin-bottom: 20px">
                <input type="checkbox" name="" title="赵治博(六个字的昵称)">
            </div>
            <div class="layui-col-md3" style="margin-bottom: 20px">
                <input type="checkbox" name="" title="赵治博(六个字的昵称)">
            </div>
            <div class="layui-col-md3" style="margin-bottom: 20px">
                <input type="checkbox" name="" title="赵治博(六个字的昵称)">
            </div>
            <div class="layui-col-md3" style="margin-bottom: 20px">
                <input type="checkbox" name="" title="赵治博(六个字的昵称)">
            </div>
            <div class="layui-col-md3" style="margin-bottom: 20px">
                <input type="checkbox" name="" title="赵治博(六个字的昵称)">
            </div>
            <div class="layui-col-md3" style="margin-bottom: 20px">
                <input type="checkbox" name="" title="赵治博(六个字的昵称)">
            </div>
            <div class="layui-col-md3" style="margin-bottom: 20px">
                <input type="checkbox" name="" title="赵治博(六个字的昵称)">
            </div>
            <div class="layui-col-md3" style="margin-bottom: 20px">
                <input type="checkbox" name="" title="赵治博(六个字的昵称)">
            </div>
            <div class="layui-col-md3" style="margin-bottom: 20px">
                <input type="checkbox" name="" title="赵治博(六个字的昵称)">
            </div>
        </div>
    </div>
</form>
<script>
    layui.use(['form', 'jquery', 'layer', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer;
        var $ = layui.jquery;
        var laydate = layui.laydate;

        form.render();
    })
</script>
</body>
</html>
