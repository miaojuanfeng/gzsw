<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>站点列表</title>
  <%@ include file="../linker.jsp" %>
</head>
<style>
  #contentBox .layui-table-view{
    margin:0;
  }
</style>
<body>

<div class="layui-fluid">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md12">
      <div class="layui-card">
        <div class="layui-card-header">经验单位线列表</div>
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
                  <label class="layui-form-label">预报站点</label>
                  <div class="layui-input-inline">
                    <select name="station" lay-filter="station" lay-search="">
                      <option value="">请选择</option>
                      <c:forEach items="${stations}" var="station" varStatus="id">
                        <option value="${station.stcd}" <c:if test="${plan.stcd==station.stcd}">selected</c:if>>${station.stname}</option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
                <div class="layui-inline">
                  <label class="layui-form-label">方案名称</label>
                  <div class="layui-input-inline">
                    <input type="text" name="name" class="layui-input" placeholder="支持模糊查询">
                  </div>
                </div>
                <button class="layui-btn layui-btn-sm" id="search">立即搜索</button>
              </div>
            </div>
          </div>
          <div class="layui-form-item">
            <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="import">导入经验单位线</button>
          </div>
          <table id="data-table" class="layui-hide" lay-filter="data"></table>
        </div>
      </div>
    </div>
  </div>
</div>

<script type="text/html" id="edit">
    <a class="layui-btn layui-btn-sm" lay-event="see">查看单位线</a>
</script>

<form class="layui-form" lay-filter="contentBox" id="contentBox" style="display: none;">
  <table id="see-table" class="layui-hide" lay-filter="see-data"></table>
</form>

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
        ajaxSetup($, '由于您长时间没有操作, 请重新登录。');

        table.render({
            elem: '#data-table'
            ,method: 'post'
            ,url: "${pageContext.request.contextPath}/unitLine/list"
            ,cols: [[
                {field:'id', width:80, title: 'ID', sort: true}
                ,{field:'sttype', width:120, title: '站点类型'}
                ,{field:'stname', width:120, title: '站点名称'}
                ,{field:'name', title: '方案名称'}
                ,{field:'lname', title: '关系线名称'}
                ,{field:'createTime', width:170, title: '创建时间'}
                ,{fixed: 'right', width:140, align:'center', toolbar: '#edit', title: '操作'}
            ]]
            ,page: true
        });

        //监听工具条
        table.on('tool(data)', function(obj){
            if(obj.event === 'see'){
                layer.open({
                    type: 1
                    ,offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                    ,id: 'layerDemo1' //防止重复弹出
                    ,content: $('#contentBox')
                    ,area:["500px","420px"]
                    ,btn: ['关闭']
                    ,btnAlign: 'c' //按钮居中
                    ,shade: 0.2 //不显示遮罩
                    ,btn1: function(index, layero){
                        layer.closeAll();
                    }
                    ,cancel: function(){
                        layer.closeAll();
                    }
                    ,success: function(layero, index){  //弹出成功的回调
                        table.render({
                            elem: '#see-table'
                            ,method: 'post'
                            ,where: {
                                lid: obj.data.lid
                            }
                            ,url: "${pageContext.request.contextPath}/unitLine/pointList"
                            ,cols: [[
                                {field:'id', width:80, title: 'ID', sort: true}
                                ,{field:'ptno', width:120, title: 'PTNO'}
                                ,{field:'f', title: 'F'}
                                ,{field:'h', title: 'H'}
                            ]]
                            ,page: false
                        });
                    }
                });
            }
        });

        form.on('select(sttp)', function(data){
            $("select[name=station]").html('<option value="">请选择</option>');
            form.render('select');
            var sttp = $("select[name=sttp]").val();
            if( sttp != "" ){
                var loading = layer.load(0);
                $.post(
                    '${pageContext.request.contextPath}/station/getStation',
                    {sttp: sttp},
                    function (data) {
                        if( data.code == 200 ) {
                            var html = '';
                            $.each(data, function (key, value) {
                                html += '<option value="' + value.stcd + '">' + value.stname + '</option>';
                            });
                            $("select[name=station]").append(html);
                            form.render('select');
                        }
                        layer.close(loading);
                    }
                );
            }
        });

        form.on('submit(import)', function(data){

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
