<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>用户列表</title>
  <%@ include file="../linker.jsp" %>
</head>
<body>

<div class="layui-fluid">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md12">
      <div class="layui-card">
        <div class="layui-card-header">用户列表</div>
        <div class="layui-card-body">
          <div class="layui-form-item">
            <div class="layui-form">
              <div class="layui-form-item">
                <div class="layui-inline">
                  <label class="layui-form-label">手机号</label>
                  <div class="layui-input-inline">
                    <input type="text" name="phone" class="layui-input" placeholder="支持模糊查询">
                  </div>
                </div>
                <div class="layui-inline">
                  <label class="layui-form-label">姓名</label>
                  <div class="layui-input-inline">
                    <input type="text" name="name" class="layui-input" placeholder="支持模糊查询">
                  </div>
                </div>
                <button class="layui-btn layui-btn-sm" id="search">立即搜索</button>
              </div>
            </div>
          </div>
          <%--<div class="layui-form-item">--%>
          <%--<button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="insert">新增方案</button>--%>
          <%--</div>--%>
          <table id="data-table" class="layui-hide" lay-filter="data"></table>
        </div>
      </div>
    </div>
  </div>
</div>

<script type="text/html" id="edit">
  <a class="layui-btn layui-btn-sm" lay-event="update">编辑</a>
  <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">删除</a>
</script>

<script>
    layui.config({
        base: base //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table'], function(){
        var $ = layui.$
            ,admin = layui.admin
            ,table = layui.table
            ,form = layui.form;

        table.render({
            elem: '#data-table'
            ,method: 'post'
            ,url: "${pageContext.request.contextPath}/user/list"
            ,cols: [[
                {field:'id', width:80, title: 'ID', sort: true}
                ,{field:'phone', width:120, title: '手机号'}
                ,{field:'name', title: '姓名'}
                ,{field:'admin', width:120, title: '管理员'}
                ,{field:'createTime', width:170, title: '创建时间'}
                ,{field:'updateTime', width:170, title: '更新时间'}
                ,{fixed: 'right', width:140, align:'center', toolbar: '#edit', title: '操作'}
            ]]
            ,id: 'data-table'
            ,page: true
        });

        table.on('tool(data)', function(obj){
            if(obj.event === 'update'){
                openTabsPage('user/update/' + obj.data.id, '编辑用户');
            }else if(obj.event === 'delete'){
                deleteConfirm('user/delete', '确认删除吗？', obj);
            }
        });

        $("#search").click(function () {
            table.reload('data-table', {
                page: {
                    curr: 1
                }
                ,where: {
                    phone: $("input[name=phone]").val(),
                    name: $("input[name=name]").val()
                }
            });
        });
    });
</script>
</body>
</html>
