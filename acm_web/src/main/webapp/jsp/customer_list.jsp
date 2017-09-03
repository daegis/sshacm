<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人员列表</title>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<div class="demoTable" style="margin-top: 10px;margin-left: 10px">
    搜索:
    <div class="layui-inline">
        <input class="layui-input" name="keyword" id="reloadInput" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload" id="reloadBtn">搜索</button>
</div>
<table id="dataTable" lay-filter="dataTable"></table>
<script>
    layui.use('table', function () {
        var $ = layui.jquery;
        var table = layui.table;
        var tableIns = table.render({
            elem: '#dataTable',
//            height: 700,
            cols: [[
                {field: 'cid', title: 'ID', width: 40, sort: true, fixed: true, align: 'center'}
                , {field: 'nickname', title: '昵称', width: 80, align: 'center'}
                , {field: 'mobile', title: '手机号', width: 120, align: 'center'}
                , {field: 'gender', title: '性别', width: 60, align: 'center', templet: '#sexTpl'}
                , {field: 'address', title: '地址', width: 120, align: 'center'}
                , {field: 'name', title: '真实姓名', width: 90, align: 'center'}
                , {field: 'idNumber', title: '身份证号', width: 185, align: 'center'}
                , {field: 'firstAdd', title: '最后编辑时间', sort: true, width: 180, align: 'center'}
                , {field: 'cnote', title: '备注信息', width: '180', align: 'center'}
                , {fixed: 'right', width: 200, align: 'center', toolbar: '#bar'}
            ]],
            page: true,
            url: '${pageContext.request.contextPath}/customerAction_findByPage.action'
        });
        table.on('tool(dataTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.msg('ID：' + data.cid + ' 的待定操作');
            } else if (obj.event === 'del') {
                layer.confirm('真的删除行么, id=' + data.cid, function (index) {
                    obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
//                layer.alert(data.cid);
                var url = "${pageContext.request.contextPath}/customerAction_update?cid=" + data.cid;
                window.location = url;
            }
        });
        $('#reloadBtn').on('click', function () {
            var $keyword = $('#reloadInput').val();
            tableIns.reload({
                where: {
                    keyword: $keyword
                }
            });
            alert("点击了搜索按钮");
        });
    })
</script>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">指派活动</a>
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
