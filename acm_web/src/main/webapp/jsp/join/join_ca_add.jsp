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
    <legend>未参加本活动人员名单</legend>
</fieldset>
<div class="demoTable" style="margin-top: 10px;margin-left: 10px">
    搜索:
    <div class="layui-inline">
        <input class="layui-input" name="keyword" id="reloadInput" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload" id="reloadBtn">搜索</button>
    <button class="layui-btn layui-btn-danger" id="cancelBtn">重置搜索</button>
</div>
<table id="dataTable" lay-filter="dataTable"></table>


<script>
    layui.use(['form', 'jquery', 'layer', 'laydate', 'table'], function () {
        var form = layui.form
            , layer = layui.layer;
        var $ = layui.jquery;
        var table = layui.table;
        var laydate = layui.laydate;
        form.render();
        // ----------------------------
        var tableIns = table.render({
            elem: '#dataTable',
            cols: [[
                {field: 'cid', title: 'ID', width: 80, sort: true, fixed: true, align: 'center'}
                , {field: 'nickname', title: '昵称', width: 120, align: 'center'}
                , {field: 'mobile', title: '手机号', width: 120, align: 'center'}
                , {field: 'gender', title: '性别', width: 60, align: 'center', templet: '#sexTpl'}
                , {field: 'address', title: '地址', width: 120, align: 'center'}
                , {field: 'name', title: '真实姓名', width: 90, align: 'center'}
                , {field: 'idNumber', title: '身份证号', width: 185, align: 'center'}
                , {field: 'cnote', title: '备注信息', width: '180', align: 'center'}
                , {fixed: 'right', width: 100, align: 'center', toolbar: '#bar'}
            ]],
            page: true,
            limits: [8, 10, 20, 30, 50],
            limit: 8,
            <%--url: '${pageContext.request.contextPath}/customerAction_findByPage.action'--%>
            url: '${pageContext.request.contextPath}/customerAction_findByNotInActivity.action?aid=<s:property value="activity.aid"/>'
        });
        table.on('tool(dataTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.alert('添加成功, 请稍后到活动详情中编辑该人员的活动信息' + data.cid + ':' +<s:property value="activity.aid"/>, {
                    icon: 1,
                    offset: '100px',
                    title: '成功通知'
                }, function (index) {
                    var $keyword = $('#reloadInput').val();
                    tableIns.reload({
                        where: {
                            keyword: $keyword
                        }
                    });
                    layer.close(index);
                });
            }
        });
        $('#reloadBtn').on('click', function () {
            var $keyword = $('#reloadInput').val();
            tableIns.reload({
                where: {
                    keyword: $keyword
                }
            });
        });
        $('#cancelBtn').on('click', function () {
            window.location.reload();
        });
    })
</script>
<script type="text/html" id="bar">
    <button class="layui-btn layui-btn-small layui-btn-danger" lay-event="detail">关联活动</button>
</script>
<script type="text/html" id="sexTpl">
    {{#  if(d.gender === '女'){ }}
    <span style="color: #F581B1;">{{ d.gender }}</span>
    {{#  } else { }}
    {{ d.gender }}
    {{#  } }}
</script>
</body>
</html>
