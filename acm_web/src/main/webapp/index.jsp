<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>人员活动管理系统</title>
    <script src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">人员活动管理系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item layui-this"><a href="${pageContext.request.contextPath}/">管理系统</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">目的地管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">选项1</a></dd>
                    <dd><a href="javascript:;">选项2</a></dd>
                    <dd><a href="javascript:;">选项3</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="javascript:;">大数据</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">解决方案</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">移动模块</a></dd>
                    <dd><a href="javascript:;">后台模版</a></dd>
                    <dd class="layui-this"><a href="javascript:;">选中项</a></dd>
                    <dd><a href="javascript:;">电商平台</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="javascript:;">探险活动</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    当前登录用户: <shiro:user><shiro:principal property="nickname"/></shiro:user>
                </a>
            </li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/userAction_logout.action">退出登录</a>
            </li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="demo">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">人员活动管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="${pageContext.request.contextPath}/jsp/customer/customer_add.jsp"
                               target="mainFrame">人员添加</a>
                        </dd>
                        <dd>
                            <a href="${pageContext.request.contextPath}/jsp/customer/customer_list.jsp"
                               target="mainFrame">人员列表</a>
                        </dd>
                        <dd><a href="${pageContext.request.contextPath}/jsp/activity/activity_add.jsp"
                               target="mainFrame">活动添加</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/jsp/activity/activity_list.jsp"
                               target="mainFrame">活动列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">报表管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/activityAction_report.action"
                               target="mainFrame">生成活动报表</a>
                        </dd>
                        <dd><a href="javascript:;">待添加功能</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">系统管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="${pageContext.request.contextPath}/jsp/user/user_detail.jsp" target="mainFrame">修改密码</a>
                        </dd>
                        <dd><a href="${pageContext.request.contextPath}/userAction_loginRecord.action"
                               target="mainFrame">登录记录查询</a></dd>
                    </dl>
                </li>
                <%--<li class="layui-nav-item"><a href="">云市场</a></li>--%>
                <%--<li class="layui-nav-item"><a href="">发布商品</a></li>--%>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="overflow: hidden">
        <!-- 内容主体区域 -->
        <iframe src="${pageContext.request.contextPath}/html/index.html" frameborder="0" name="mainFrame" width="100%"
                height="100%"></iframe>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © Aegis - Thread
    </div>
</div>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;
        element.init();
        element.on('nav(demo)', function (elem) {

        });
    });
</script>
</body>
</html>
