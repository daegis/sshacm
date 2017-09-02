<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>活动列表</title>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<table id="dataTable" lay-filter="dataTable"></table>
<script>
    layui.use('table', function () {
        var $ = layui.jquery;
        var table = layui.table;
        table.render({
            elem: '#dataTable',
//            height: 700,
            cols: [[
                {field: 'aid', title: 'ID', width: 40, sort: true, fixed: true, align: 'center'}
                , {field: 'activityName', title: '活动名称', width: 220, align: 'center'}
                , {field: 'activityTime', title: '活动日期', width: 120, align: 'center'}
                , {field: 'dayCount', title: '天数', width: 80, align: 'center'}
                , {field: 'activityPrice', title: '活动价格', width: 120, align: 'center'/*, templet: '#sexTpl'*/}
                , {field: 'activityBus', title: '使用车辆', width: 120, align: 'center'}
                , {field: 'currentCustomerCount', title: '活动当前人数', width: 120, align: 'center'}
                , {fixed: 'right', width: 200, align: 'center', toolbar: '#bar'}
            ]],
            page: true,
            url: '${pageContext.request.contextPath}/activityAction_findByPage.action'
        });
        table.on('tool(dataTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                var url = "${pageContext.request.contextPath}/activityAction_detail?aid=" + data.aid;
                window.location = url;
            } else if (obj.event === 'del') {
                layer.confirm('真的删除行么, id=' + data.cid, function (index) {
                    obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                var url = "${pageContext.request.contextPath}/activityAction_update?aid=" + data.aid;
                window.location = url;
            }
        });
    })
</script>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">活动详情</a>
    <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
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
