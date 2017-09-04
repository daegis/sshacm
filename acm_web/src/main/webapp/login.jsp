<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登录</title>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>

<div class="layui-container" style="width: 1080px">
    <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
            <div class="layui-form layui-form-pane">
                <form method="post">
                    <div class="layui-form-item"><label for="L_email" class="layui-form-label">用户名</label>
                        <div class="layui-input-inline"><input type="text" id="L_email" name="username" required
                                                               lay-verify="required" autocomplete="off"
                                                               class="layui-input"></div>
                    </div>
                    <div class="layui-form-item"><label for="L_pass" class="layui-form-label">密码</label>
                        <div class="layui-input-inline"><input type="password" id="L_pass" name="pass" required
                                                               lay-verify="required" autocomplete="off"
                                                               class="layui-input"></div>
                    </div>
                    <div class="layui-form-item"><label for="L_vercode" class="layui-form-label">验证码</label>
                        <div class="layui-input-inline"><input type="text" id="L_vercode" name="vercode"
                                                               required lay-verify="required"
                                                               placeholder="请输入验证码" autocomplete="off"
                                                               class="layui-input"></div>
                        <div class="layui-form-mid">
                            这里是一个验证码占位符~~
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <button class="layui-btn" lay-filter="*" lay-submit>立即登录</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
