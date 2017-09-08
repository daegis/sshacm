<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>人员修改</title>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 15px;">
    <legend>人员修改</legend>
</fieldset>
<form class="layui-form" action="" id="mainForm">
    <div class="layui-form-item">
        <label class="layui-form-label">网名 / 昵称</label>
        <div class="layui-input-block">
            <input type="text" name="nickname" lay-verify="required" placeholder="请输入网名或昵称" autocomplete="off"
                   class="layui-input" value="<s:property value="customer.nickname"/>">
        </div>
    </div>
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">网名 / 昵称</label>
        <div class="layui-input-block">
            <input type="text" name="cid" autocomplete="off"
                   class="layui-input" value="<s:property value="customer.cid"/>">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-inline">
                <input type="tel" name="mobile" lay-verify="phone" placeholder="输入手机号" autocomplete="off"
                       class="layui-input" value="<s:property value="customer.mobile"/>">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">真实姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="name" autocomplete="off" class="layui-input" placeholder="输入真实姓名"
                       value="<s:property value="customer.name"/>">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">单选框</label>
            <div class="layui-input-block">
                <s:if test='customer.gender=="男"'>
                    <input type="radio" name="gender" value="男" title="男" checked="">
                    <input type="radio" name="gender" value="女" title="女">
                </s:if>
                <s:else>
                    <input type="radio" name="gender" value="男" title="男">
                    <input type="radio" name="gender" value="女" title="女" checked="">
                </s:else>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">身份证号</label>
        <div class="layui-input-block">
            <input type="text" name="idNumber" placeholder="输入身份证号, 请确保输入的正确性" autocomplete="off"
                   class="layui-input" value="<s:property value="customer.idNumber"/>">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-block">
            <input type="text" name="address" placeholder="输入地址" autocomplete="off"
                   class="layui-input" value="<s:property value="customer.address"/>">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">特殊照顾</label>
        <div class="layui-input-block">
            <input type="checkbox" name="special" lay-skin="switch" lay-text="ON|OFF">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">人员备注</label>
        <div class="layui-input-block">
            <textarea name="cnote" placeholder="请输入内容" class="layui-textarea"><s:property
                    value="customer.cnote"/></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">保存信息</button>
        </div>
    </div>
</form>
<script>
    layui.use(['form', 'jquery', 'layer'], function () {
        var form = layui.form
            , layer = layui.layer;
        var $ = layui.jquery;

        form.render();

        form.on('submit(demo1)', function (formdata) {
            $.ajax({
                url: '${pageContext.request.contextPath}/customerAction_save.action',
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
                            window.location = "customer_list.jsp";
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

    });
</script>
</body>
</html>
