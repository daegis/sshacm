<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>摄影团免责声明</title>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<p style="font-size: 25px;margin-top: 20px;text-align: center">
    <s:property value="activity.activityName"/>免责声明
</p>
<hr class="layui-bg-green">
<p style="font-size: 18px">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本人自愿报名参加这次自助游性质的摄影活动，
    活动中对自己的人身安全和摄影器材等物品的安全负责，
    如果发生任何意外事故和危险，由本团在中国平安保险公司为每人买的保险来赔付，
    本人志愿只享受保险待遇，任何其它组织、公司和任何个人无须对自己承担任何法律和任何经济责任.<br>
</p>
<p style="margin-left: 75%;font-size: 18px">
    <s:property value="activity.activityTime"/>
</p>
<div class="layui-form">
    <table class="layui-table">
        <colgroup>
            <col width="60">
            <col width="120">
            <col width="150">
            <col>
        </colgroup>
        <thead style="display:table-header-group">
        <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>身份证号码</th>
            <th>本人签字</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="activity.caList" status="st">
            <tr>
                <td><s:property value="#st.index+1"/></td>
                <td><s:property value="customer.name"/></td>
                <td><s:property value="customer.idNumber"/></td>
                <td></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>
<script>
    layui.use('jquery', function () {
        var $ = layui.jquery;

    })
</script>
</body>
</html>
