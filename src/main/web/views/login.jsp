<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script language="JavaScript">//判断当前登录页面是否最顶层，否则让最顶层页面跳转到该登录页面
    if (window != top) {
        top.location.href = location.href;
    }
</script>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>系统登录</title>
    <link rel="stylesheet" href="<c:url value="/assets/css/login.css"></c:url>" media="all">
    <%@ include file="linker.jsp" %>
</head>
<body style="background-image: url('assets/images/bg1.gif'); background-repeat: repeat;">

<div class="login-main">
    <div class="login-box">
        <header class="layui-elip">系统登录</header>
        <form class="layui-form">
            <div class="layui-input-inline">
                <span class="input-icon" style="background-image:url(<c:url value="/assets/images/login_u.png"></c:url>);"></span>
                <input class="input-text" type="text" name="username" required lay-verify="required" placeholder="手机号" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline">
                <span class="input-icon" style="background-image:url(<c:url value="/assets/images/login_p.png"></c:url>);"></span>
                <input class="input-text" type="password" name="password" required lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
                <div class="layui-input-inline">
                    <a id="login" class="layui-btn layui-btn-normal" style="width:100%;background-color:#1E9F95;">登录</a>
                </div>
            </div>
            <div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
                <p style="margin-bottom:15px;text-align:center;">没有账号？<a id="register" style="text-decoration:underline" href="javascript:;">立即注册</a></p>
            </div>
            <hr/>
            <p>
                <span href="javascript:;" class="fl">赣州洪水预报系统</span><span href="javascript:;" class="fr">青年版</span>
            </p>
        </form>
    </div>
</div>

<script type="text/javascript">
    layui.use(['form'], function () {
        var form = layui.form
            ,$ = layui.jquery;

        $("#login").click(function () {
            if( $("input[name=username]").val() == '' ||
                $("input[name=password]").val() == '' ){
                layer.msg("请填妥用户信息");
                return;
            }
            var loading = layer.load(0);//load是转圈圈的组件，0是样式
            $.post({
                url: "${pageContext.request.contextPath}/login",
                contentType: "application/x-www-form-urlencoded",
                data: {
                    username: $("input[name=username]").val(),
                    password: $("input[name=password]").val()
                },
                success : function(data) {
                    if( data.code == 200 ) {
                        location.href = "${pageContext.request.contextPath}";
                    }else{
                        layer.msg(data.msg);
                    }
                    layer.close(loading);
                }
            }).fail(function(response) {
                layer.msg("登录失败，请重试");
                layer.close(loading);
            });
        });

        $("#register").click(function () {
            layer.msg("请联系管理员开通账号");
        });
    });
</script>
</body>
</html>