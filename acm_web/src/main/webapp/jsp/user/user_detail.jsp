<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
    <legend>管理员资料修改</legend>
</fieldset>
<form class="layui-form" action="" id="mainForm">
    <div class="layui-form-item"><label for="opass" class="layui-form-label">原始密码</label>
        <div class="layui-input-inline"><input type="password" id="opass" name="password" required
                                               lay-verify="required" autocomplete="off"
                                               placeholder="请输入密码"
                                               class="layui-input"></div>
    </div>
    <div class="layui-form-item"><label for="npass1" class="layui-form-label">新密码</label>
        <div class="layui-input-inline"><input type="password" id="npass1" name="newPassword1" required
                                               lay-verify="required" autocomplete="off"
                                               placeholder="请输入密码"
                                               class="layui-input"></div>
    </div>
    <div class="layui-form-item"><label for="npass2" class="layui-form-label">验证新密码</label>
        <div class="layui-input-inline"><input type="password" id="npass2" name="newPassword2" required
                                               lay-verify="required" autocomplete="off"
                                               placeholder="请输入密码"
                                               class="layui-input"></div>
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

        form.render();

        form.on('submit(demo1)', function (formdata) {
            $.ajax({
                url: '${pageContext.request.contextPath}/userAction_changePassword.action',
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
                            window.location = "${pageContext.request.contextPath}/";
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
