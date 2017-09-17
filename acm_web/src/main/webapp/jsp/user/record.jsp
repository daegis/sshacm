<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>摄影团免责声明</title>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
    <legend>系统登录记录</legend>
</fieldset>
<table class="layui-table" lay-size="sm">
    <colgroup>
        <col width="150">
        <col width="200">
        <col width="200">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th>登录名</th>
        <th>登录时间</th>
        <th>登录IP</th>
        <th>登录状态</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="record" status="st">
        <tr>
            <td><s:property value="loginUsername"/></td>
            <td><s:property value="loginTime"/></td>
            <td><a href="javascript:;" onclick="check('<s:property value="loginIP"/>')"><s:property
                    value="loginIP"/></a>
            </td>
            <td><s:property value="loginStatus"/></td>
        </tr>
    </s:iterator>
    </tbody>
</table>
</body>
<script>
    var $;
    var layer;
    layui.use(['jquery', 'layer'], function () {
        $ = layui.jquery;
        layer = layui.layer;
    });

    function check(ip) {
        $.getScript("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=" + ip, function (data, textStatus) {
            try {
                eval(data);
                var result = remote_ip_info;
                layer.alert('该登录IP所在地为: '+result.country + result.province + result.city, {
                    icon: 1,
                    offset: '100px',
                    title: 'IP地址所在地查询'
                });
                remote_ip_info = undefined;
            } catch (e) {
                layer.alert("未找到该ip地址对应的数据", {
                    icon: 2,
                    offset: '100px',
                    title: 'IP地址所在地查询'
                });
            }
        });
    }
</script>
</html>
