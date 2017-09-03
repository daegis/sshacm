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
            <col width="120">
            <col width="120">
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
            <th>操作</th>
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
            <td>
                <a href="${pageContext.request.contextPath}/activityAction_associate.action?aid=<s:property value="activity.aid"/>"
                   class="layui-btn"><i class="layui-icon">&#xe608;</i>为活动添加人员</a>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<fieldset class="layui-elem-field layui-field-title">
    <legend>当前人员名单</legend>
</fieldset>

<table id="dataTable" lay-filter="dataTable"></table>

<script>
    layui.use(['element', 'table'], function () {
        var element = layui.element;
        element.init();
        var table = layui.table;
        table.render({
            elem: '#dataTable'
            ,
            height: 440
            ,
            cols: [[
                {field: 'jid', title: 'ID', width: 40, sort: true, fixed: true, align: 'center'}
                , {field: 'customerNickname', title: '昵称', width: 120, align: 'center'}
                , {field: 'customerName', title: '姓名', width: 120, align: 'center'}
                , {field: 'joinTime', title: '报名日期', sort: true, width: 150, align: 'center'}
                , {field: 'discount', title: '折扣', width: 120, align: 'center'}
                , {field: 'prepay', title: '预付', width: 120, align: 'center'}
                , {field: 'payMethod', title: '方式', width: 120, align: 'center', templet: '#methodTpl'}
                , {field: 'restPay', title: '余款', sort: true, width: 120, align: 'center'}
                , {field: 'jnote', title: '备注信息', width: 120, align: 'center'}
                , {fixed: 'right', title: '操作', width: 250, align: 'center', toolbar: '#barDemo'}
            ]]
            ,
            url: '${pageContext.request.contextPath}/activityAction_findForDetail.action?aid=<s:property value="activity.aid"/>'
        });

        table.on('tool(dataTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if (layEvent === 'detail') { //查看
                //do somehing
//                layer.alert("jid=" + data.jid);
                window.location = "${pageContext.request.contextPath}/joinAction_update.action?jid=" + data.jid;
            } else if (layEvent === 'del') { //删除
                layer.confirm('真的删除行么', function (index) {
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if (layEvent === 'edit') { //编辑
                layer.msg(data.customerCid);
                window.location = "${pageContext.request.contextPath}/customerAction_update?cid=" + data.customerCid;
            }
        });
    });
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-mini" lay-event="detail">活动信息</a>
    <a class="layui-btn layui-btn-mini" lay-event="edit">个人信息</a>
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">从活动中移除</a>
</script>
<script type="text/html" id="methodTpl">
    {{#  if(d.payMethod === '未付款'){ }}
    <span style="color: red">{{ d.payMethod }}</span>
    {{#  } else { }}
    {{ d.payMethod }}
    {{#  } }}
</script>
</body>
</html>
