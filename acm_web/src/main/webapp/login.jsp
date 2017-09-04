<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登录</title>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body style="background-color: #e2e2e2">

<div class="layui-container" style="width: 1080px;margin-top: 10%;background-color: white;padding-top: 10px">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>用户登录</legend>
    </fieldset>
    <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
            <div class="layui-form layui-form-pane">
                <form method="post">
                    <div class="layui-form-item"><label for="L_email" class="layui-form-label">用户名</label>
                        <div class="layui-input-inline"><input type="text" id="L_email" name="username" required
                                                               lay-verify="required" autocomplete="off"
                                                               placeholder="请输入用户名"
                                                               class="layui-input"></div>
                        <div class="layui-form-mid" id="idMessage">

                        </div>
                    </div>
                    <div class="layui-form-item"><label for="L_pass" class="layui-form-label">密码</label>
                        <div class="layui-input-inline"><input type="password" id="L_pass" name="password" required
                                                               lay-verify="required" autocomplete="off"
                                                               placeholder="请输入密码"
                                                               class="layui-input"></div>
                    </div>
                    <div class="layui-form-item"><label for="L_vercode" class="layui-form-label">验证码</label>
                        <div class="layui-input-inline"><input type="text" id="L_vercode" name="captcha"
                                                               required lay-verify="required"
                                                               placeholder="请输入验证码" autocomplete="off"
                                                               class="layui-input"></div>
                        <div class="layui-form-mid" style="margin-top: -4px">
                            <img src="${pageContext.request.contextPath}/jsp/validatecode.jsp"
                                 id="cap">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <button class="layui-btn" lay-filter="login" lay-submit>立即登录</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['jquery', 'form'], function () {
        var $ = layui.jquery;
        var form = layui.form;
        form.render();
        $("#cap").click(function () {
            $("#cap").attr('src', '${pageContext.request.contextPath}/jsp/validatecode.jsp?time=' + new Date().getTime());
        });
        form.on('submit(login)', function (formdata) {
            $.ajax({
                url: '${pageContext.request.contextPath}/userAction_login.action',
                type: 'post',
                data: formdata.field,
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        window.location = "${pageContext.request.contextPath}/";
                    } else {
                        layer.alert(data.message, {
                            title: '提示信息'
                        });
                        $("#cap").attr('src', '${pageContext.request.contextPath}/jsp/validatecode.jsp?time='
                            + new Date().getTime());
                    }
                }
            });
            return false;
        })
    })
</script>
</body>
</html>
