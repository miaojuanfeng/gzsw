<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>站点列表</title>
  <%@ include file="../linker.jsp" %>
</head>
<body>

<div class="layui-fluid">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md12">
      <div class="layui-card">
        <div class="layui-card-header">站点列表</div>
        <div class="layui-card-body">
          <div class="layui-form-item">
            <div class="layui-form">
              <div class="layui-form-item">
                <div class="layui-inline">
                  <label class="layui-form-label">站点类型</label>
                  <div class="layui-input-inline">
                    <select name="sttp" lay-filter="sttp" lay-search="">
                      <option value="">请选择</option>
                      <c:forEach items="${sttps}" var="sttp" varStatus="id">
                        <option value="${sttp.code}" <c:if test="${plan.sttype==sttp.code}">selected</c:if>>${sttp.text}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                <div class="layui-inline">
                  <label class="layui-form-label">站点代码</label>
                  <div class="layui-input-inline">
                    <input type="text" name="stcd" class="layui-input" placeholder="支持模糊查询">
                  </div>
                </div>
                <div class="layui-inline">
                  <label class="layui-form-label">站点名称</label>
                  <div class="layui-input-inline">
                    <input type="text" name="name" class="layui-input" placeholder="支持模糊查询">
                  </div>
                </div>
                <button class="layui-btn layui-btn-sm" id="search">立即搜索</button>
              </div>
            </div>
          </div>
          <div class="layui-form-item">
            <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="refresh">同步站点</button>
            <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="adsorb">更新临近站</button>
          </div>
          <table id="data-table" class="layui-hide" lay-filter="data"></table>
        </div>
      </div>
    </div>
  </div>
</div>

<script type="text/html" id="edit">
    <a class="layui-btn layui-btn-sm" lay-event="setting">设置</a>
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
            ,url: "${pageContext.request.contextPath}/station/list"
            ,cols: [[
                {field:'id', width:80, title: 'ID', sort: true}
                ,{field:'sttype', width:120, title: '站点类型'}
                ,{field:'stcd', width:120, title: '站点代码'}
                ,{field:'stname', width:120, title: '站点名称'}
                ,{field:'lgtd', width:120, title: '经度'}
                ,{field:'lttd', width:120, title: '纬度'}
                ,{field:'nearStcd', width:120, title: '临近站代码'}
                ,{field:'nearStname', width:120, title: '临近站名称'}
                ,{field:'dis', title: '临近站距离(km)'}
                // ,{fixed: 'right', width:140, align:'center', toolbar: '#edit', title: '操作'}
            ]]
            ,page: true
        });

        //监听工具条
        table.on('tool(data)', function(obj){
            if(obj.event === 'setting'){
                openTabsPage('station/update/' + obj.data.id, '站点设置');
            }
        });

        form.on('submit(refresh)', function(data){
            commonConfirm('station/refresh', '将删除所有本地站点数据，并同步远程数据库站点，确定这样操作吗？', null, function () {
                table.reload('data-table', {
                    page: {
                        curr: 1
                    }
                });
            });
        });

        form.on('submit(adsorb)', function(data){
            commonConfirm('station/adsorb', '将根据经纬度数据，计算临近站点，耗时较长，确定这样操作吗？', null, function () {
                table.reload('data-table', {
                    page: {
                        curr: 1
                    }
                });
            });
        });

        $("#search").click(function () {
            table.reload('data-table', {
                page: {
                    curr: 1
                }
                ,where: {
                    sttp: $("select[name=sttp]").val(),
                    stcd: $("input[name=stcd]").val(),
                    name: $("input[name=name]").val()
                }
            });
        });

    });
</script>
</body>
</html>
