<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>活动添加</title>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
    <legend>活动添加</legend>
</fieldset>
<form class="layui-form" action="" id="mainForm">
    <div class="layui-form-item">
        <label class="layui-form-label">活动名称</label>
        <div class="layui-input-block">
            <input type="text" name="activityName" lay-verify="required" placeholder="请输入活动名称" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">活动日期</label>
        <div class="layui-input-block">
            <input type="text" name="activityDate" autocomplete="off" id="date" placeholder="请选择活动的日期"
                   readonly="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">活动天数</label>
        <div class="layui-input-block">
            <input type="text" name="dayCount" lay-verify="number" placeholder="请输入活动天数" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">活动价格</label>
        <div class="layui-input-block">
            <input type="text" name="activityPrice" lay-verify="number" placeholder="请输入活动价格" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">使用车辆</label>
        <div class="layui-input-block">
            <input type="text" name="activityBus" placeholder="请输入使用车辆" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">保存信息</button>
            <button type="reset" class="layui-btn layui-btn-primary" id="reset">重新填写</button>
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

        form.on('submit(demo1)', function (formdata) {
            $.ajax({
                url: '${pageContext.request.contextPath}/activityAction_save.action',
                type: 'post',
                data: formdata.field,
                dataType: 'json',
                error: function () {
                    layer.alert("连接服务器出现了问题, 请稍后重试", {
                        title: '提示信息'
                    })
                },
                success: function (data) {
                    if (data.success) {
                        layer.alert(data.message, {
                            title: '提示信息'
                        }, function () {
                            window.location = "${pageContext.request.contextPath}/jsp/activity_list.jsp";
                        })
                    } else {
                        layer.alert(data.message, {
                            title: '提示信息'
                        });
                    }
                }
            });
            return false;
        });
        laydate.render({
            elem: '#date'
        });
    });
</script>
</body>
</html>
