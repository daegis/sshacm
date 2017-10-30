<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Shuilian
  Date: 2017/10/28
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<table id="dataTable" lay-filter="dataTable"></table>
<script>
    layui.use(['element', 'table', 'jquery'], function () {
        var element = layui.element;
        var $ = layui.jquery;
        element.init();
        var table = layui.table;
        table.render({
            elem: '#dataTable'
            ,
            height: 620
            ,
            cols: [[
                {field: 'jid', title: 'ID', width: 40, fixed: true, align: 'center'}
                , {field: 'customerNickname', title: '昵称', width: 120, align: 'center'}
                , {field: 'customerName', title: '姓名', width: 90, align: 'center'}
                , {field: 'customerGender', title: '性别', width: 75, align: 'center', templet: '#genderTpl'}
                , {field: 'customerAge', title: '年龄', width: 75, align: 'center', templet: '#ageTpl'}
                , {
                    field: 'busSeat',
                    title: '汽车座位',
                    sort: true,
                    width: 100,
                    align: 'center',
                    style: 'cursor: pointer;',
                    event: 'setSeat'
                }
            ]]
            ,
            url: '${pageContext.request.contextPath}/activityAction_findForDetail.action?aid=<s:property value="activity.aid"/>'
        });

        table.on('tool(dataTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'setSeat') {
                layer.prompt({
                    formType: 0
                    , title: '设置名字为 [' + data.customerName + '](' + data.customerNickname + ') 的用户的座位号'
                    , value: ''
                }, function (value, index) {
                    layer.close(index);
                    alert(value);

                    //同步更新表格和缓存对应的值
                    obj.update({
                        busSeat: value
                    });
                });
            }
        });
    });
</script>
<script type="text/html" id="genderTpl">
    {{#  if(d.customerGender === '错ID'){ }}
    <span style="color: red">{{ '非法ID' }}</span>
    {{#  } else if(d.customerGender === '女'){ }}
    <span style="color: deeppink">{{ d.customerGender }}</span>
    {{#  } else { }}
    {{ d.customerGender }}
    {{#  } }}
</script>
<script type="text/html" id="ageTpl">
    {{#  if(d.customerAge === '非法ID'){ }}
    <span style="color: red">{{ d.customerAge }}</span>
    {{#  } else if(d.customerAge >= 70){ }}
    <span style="color: green">{{ d.customerAge }}</span>
    {{#  } else if(d.customerAge >= 65){ }}
    <span style="color: crimson">{{ d.customerAge }}</span>
    {{#  } else if(d.customerAge >= 60){ }}
    <span style="color: blue">{{ d.customerAge }}</span>
    {{#  } else { }}
    {{ d.customerAge }}
    {{#  } }}
</script>
</body>
</html>
