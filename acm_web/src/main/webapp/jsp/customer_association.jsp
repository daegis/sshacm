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
    <legend>为人员指派活动</legend>
</fieldset>
<div class="layui-form">
    <table class="layui-table">
        <colgroup>
            <col width="250">
            <col width="250">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>昵称 / 网名</th>
            <th>真实姓名</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><s:property value="customer.nickname"/></td>
            <td><s:property value="customer.name"/></td>
        </tr>
        </tbody>
    </table>
</div>
<fieldset class="layui-elem-field layui-field-title">
    <legend>为当前人员指派一个活动</legend>
</fieldset>

<form class="layui-form" action="" id="mainForm">
    <div class="layui-form-item">
        <label class="layui-form-label">选择活动</label>
        <div class="layui-input-block">
            <select name="activity.aid" lay-verify="required">
                <option value="">请选择一个活动</option>
                <%--<option value="010">北京</option>--%>
                <s:iterator value="activities">
                    <option value="<s:property value="aid"/>"><s:property value="activityName"/></option>
                </s:iterator>
            </select>
        </div>
    </div>
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">id</label>
        <div class="layui-input-block">
            <input type="text" name="customer.cid" autocomplete="off"
                   class="layui-input" value="<s:property value="customer.cid"/>">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">报名日期</label>
        <div class="layui-input-block">
            <input type="text" name="joinDate" autocomplete="off" id="date" placeholder="请选择报名的日期"
                   readonly="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">折扣</label>
            <div class="layui-input-inline">
                <input type="tel" name="discount" lay-verify="number" placeholder="￥" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">预付款</label>
            <div class="layui-input-inline">
                <input type="tel" name="prepay" lay-verify="number" placeholder="￥" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">付款方式</label>
        <div class="layui-input-block">
            <select name="payMethod" lay-verify="">
                <option value="">请选择付款方式</option>
                <optgroup label="常用">
                    <option value="微信">微信</option>
                    <option value="支付宝">支付宝</option>
                </optgroup>
                <optgroup label="银行汇款">
                    <option value="建设银行">建设银行</option>
                    <option value="工商银行">工商银行</option>
                    <option value="农业银行">农业银行</option>
                    <option value="招商银行">招商银行</option>
                    <option value="其他银行">其他银行</option>
                </optgroup>
                <optgroup label="其他">
                    <option value="现金">现金</option>
                    <option value="未付款" selected>未付款</option>
                </optgroup>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注信息</label>
        <div class="layui-input-block">
            <input type="text" name="jnote" placeholder="请输入备注信息" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">保存信息</button>
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
                url: '${pageContext.request.contextPath}/joinAction_doAssociation.action',
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
                            window.location = "${pageContext.request.contextPath}/jsp/customer_list.jsp";
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
