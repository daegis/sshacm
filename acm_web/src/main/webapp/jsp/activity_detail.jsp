<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>活动详情</title>
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

<div class="layui-collapse">
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">
            当前参加人员名单
        </h2>
        <div class="layui-colla-content layui-show">
            <table class="layui-table" lay-even="" lay-skin="row">
                <colgroup>
                    <col width="120">
                    <col width="120">
                    <col width="150">
                    <col width="100">
                    <col width="100">
                    <col width="150">
                    <col width="100">
                    <col>
                </colgroup>
                <thead>
                <tr>
                    <th>昵称</th>
                    <th>姓名</th>
                    <th>报名日期</th>
                    <th>折扣</th>
                    <th>预付款</th>
                    <th>付款方式</th>
                    <th>余款</th>
                    <th>活动备注</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="activity.caList">
                    <tr>
                        <td><s:property value="customer.nickname"/></td>
                        <td><s:property value="customer.name"/></td>
                        <td><s:property value="joinTime"/></td>
                        <td><s:property value="discount"/></td>
                        <td><s:property value="prepay"/></td>
                        <td>支付宝</td>
                        <td><s:property value="restPay"/></td>
                        <td>备注信息~~~~~~~~</td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">李清照</h2>
        <div class="layui-colla-content">内容区域</div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">鲁迅</h2>
        <div class="layui-colla-content">内容区域</div>
    </div>
</div>


<script>
    layui.use('element', function () {
        var element = layui.element;
        element.init();
    });
</script>
</body>
</html>
