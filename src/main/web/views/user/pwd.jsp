<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>编辑用户</title>
  <%@ include file="../linker.jsp" %>
</head>
<style type="text/css">
  .input-tr{
    padding:0 !important;
  }
  .input-tr .layui-input{
    border: none !important;
  }
</style>
<body>

  <div class="layui-fluid">
    <form class="layui-form" action="" lay-filter="component-form-group">
        <div class="layui-card">
            <div class="layui-card-header">用户信息</div>
            <div class="layui-card-body" style="padding: 15px;">
                <div class="layui-form-item">
                    <label class="layui-form-label">手机号</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="text" name="phone" lay-verify="phone" autocomplete="off" placeholder="请输入手机号" class="layui-input" value="${user.phone}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入姓名" class="layui-input" value="${user.name}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">旧密码</label>
                    <div class="layui-input-block">
                        <input type="password" name="oldPwd" lay-verify="oldPwd" autocomplete="off" placeholder="${pwdText}" class="layui-input" value="">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">新密码</label>
                    <div class="layui-input-block">
                        <input type="password" name="password" lay-verify="password" autocomplete="off" placeholder="${pwdText}" class="layui-input" value="">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">确认密码</label>
                    <div class="layui-input-block">
                        <input type="password" name="confirmPwd" lay-verify="confirmPwd" autocomplete="off" placeholder="${cfmPwdText}" class="layui-input" value="">
                    </div>
                </div>
                <div class="layui-form-item layui-layout-admin">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit="" lay-filter="save">立即提交</button>
                        <button class="layui-btn layui-btn-primary" lay-submit="" lay-filter="close">关闭</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
  </div>

  <script>
  layui.config({
    base: base //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form'], function(){
    var $ = layui.$
    ,admin = layui.admin
    ,form = layui.form;
    
    /* 监听提交 */
    form.on('submit(save)', function(data){
        if( $("input[name=phone]").val() == "" ){
            layer.msg('请填妥手机号');
            return false;
        }
        if( $("input[name=name]").val() == "" ){
            layer.msg('请填妥姓名');
            return false;
        }
        if( $("input[name=oldPwd]").val() != '' && $("input[name=password]").val() == "" ){
            layer.msg('请填妥新密码');
            return false;
        }
        if( $("input[name=oldPwd]").val() != '' && $("input[name=confirmPwd]").val() == "" ){
            layer.msg('请填妥确认密码');
            return false;
        }
        var loading = layer.load(0);
        $.post({
            url: "${pageContext.request.contextPath}/user/self",
            contentType: "application/x-www-form-urlencoded",
            data: {
                phone: $("input[name=phone]").val(),
                name: $("input[name=name]").val(),
                oldPwd: $("input[name=oldPwd]").val(),
                password: $("input[name=password]").val(),
                confirmPwd: $("input[name=confirmPwd]").val()
            },
            success : function(result) {
                if( result.code == 200 ) {
                    parent.layer.alert("数据保存成功", function (index) {
                        parent.layer.close(index);
                        parent.layui.admin.events.closeThisTabs();
                    })
                }else{
                    layer.msg(result.msg);
                }
                layer.close(loading);
            }
        }).fail(function(response) {
            parent.layer.alert("数据保存失败");
            layer.close(loading);
        });
      return false;
    });

    /* 监听关闭 */
    form.on('submit(close)', function(data){
        parent.layui.admin.events.closeThisTabs();
    });
  });

  function zhzs(value) {
      value = parseFloat(value.replace(/^0{1,}/g, '')).toFixed(3);
      if( !isNaN(value) ){
          return value;
      }else{
          return parseFloat(0).toFixed(3);
      }
  }
  </script>
</body>
</html>
