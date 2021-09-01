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
        <div class="layui-card-header">降径关系线列表</div>
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
            <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="import">导入降径关系线</button>
          </div>
          <table id="data-table" class="layui-hide" lay-filter="data"></table>
        </div>
      </div>
    </div>
  </div>
</div>

<script type="text/html" id="edit">
    <a class="layui-btn layui-btn-sm" lay-event="see">查看关系线</a>
    <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">删除</a>
</script>

<form class="layui-form" lay-filter="contentBox" id="contentBox" style="display: none;">
    <table id="see-table" class="layui-hide" lay-filter="see-data"></table>
</form>

<form class="layui-form" lay-filter="contentBox2" id="contentBox2" style="padding:15px;display: none;">
  <div class="layui-form-item">
    <label class="layui-form-label">方案名称</label>
    <div class="layui-input-block">
      <input type="text" id="planName" name="planName" autocomplete="off" class="layui-input" placeholder="请输入"/>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">文件类型</label>
    <div class="layui-input-block">
      <select name="fileType" lay-filter="fileType" lay-search="">
        <option value="">请选择</option>
        <option value="1">关系线</option>
        <option value="2">关系点</option>
      </select>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">选择文件</label>
    <div id="uploadBox" class="layui-input-block"></div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label"></label>
    <div class="layui-input-block">
      <button type="button" class="layui-btn" id="test9">开始上传</button>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label"></label>
    <div class="layui-input-block">
      <div><a href="${pageContext.request.contextPath}/assets/excel/rainrun.xlsx">下载：降径关系线文件模板.xlsx</a></div>
      <div style="margin-top:10px;"><a href="${pageContext.request.contextPath}/assets/excel/rainrun_point.xlsx">下载：降径关系点文件模板.xlsx</a></div>
    </div>
  </div>
</form>

<script>
    layui.config({
        base: base //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table', 'upload'], function(){
        var $ = layui.$
            ,admin = layui.admin
            ,table = layui.table
            ,upload = layui.upload
            ,form = layui.form;
        ajaxSetup($, '由于您长时间没有操作, 请重新登录。');

        table.render({
            elem: '#data-table'
            ,method: 'post'
            ,url: "${pageContext.request.contextPath}/rainRun/list"
            ,cols: [[
                {field:'id', width:80, title: 'ID', sort: true}
                ,{field:'sttype', width:120, title: '站点类型'}
                ,{field:'stname', width:120, title: '站点名称'}
                ,{field:'name', title: '方案名称'}
                ,{field:'lname', title: '降径线名称'}
                ,{field:'createTime', width:130, title: '创建时间'}
                ,{fixed: 'right', width:180, align:'center', toolbar: '#edit', title: '操作'}
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
                    ,area:["600px","420px"]
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
                        console.log(obj.data);
                        table.render({
                            elem: '#see-table'
                            ,method: 'post'
                            ,where: {
                                pid: obj.data.id
                            }
                            ,url: "${pageContext.request.contextPath}/rainRun/pointList"
                            ,cols: [[
                                {field:'id', width:80, title: 'ID', sort: true}
                                ,{field:'lname', width:160, title: 'NAME'}
                                ,{field:'pino', width:120, title: 'PINO'}
                                ,{field:'d', title: 'R'}
                                ,{field:'r', title: 'P'}
                            ]]
                            ,page: false
                        });
                    }
                });
            }else if(obj.event === 'delete'){
                deleteConfirm('rainrun/delete', '确认删除吗？', obj);
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
                            $.each(data.data, function (key, value) {
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
            var box = layer.open({
                type: 1
                ,offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                ,id: 'layerDemo2' //防止重复弹出
                ,content: $('#contentBox2')
                ,title: '雨量方案导入'
                ,area:["500px","440px"]
                ,btn: []
                ,btnAlign: 'c' //按钮居中
                ,shade: 0.2 //不显示遮罩
                ,btn1: function(index, layero){

                }
                ,cancel: function(){
                    layer.close(box);
                }
                ,success: function(layero, index){
                    $("input[name=planName]").val('');
                    $("select[name=fileType]").val('');
                    form.render('select');
                    $("#uploadBox").html(
                        "<div class='layui-upload-drag' id='test8' style='width: calc(100% - 62px)'>" +
                        "<i class='layui-icon'></i>" +
                        "<p>点击上传，或将文件拖拽到此处</p>" +
                        "</div>"
                    );
                    //选完文件后不自动上传
                    upload.render({
                        elem: '#test8'
                        ,url: '${pageContext.request.contextPath}/excel/importRainRun' //此处配置你自己的上传接口即可
                        ,auto: false
                        ,data: {
                            name: function(){ return $("input[name=planName]").val()},
                            fileType: function(){ return $("select[name=fileType]").val()}
                        }
                        //,multiple: true
                        ,accept: 'file' //普通文件
                        ,exts: 'xls|xlsx'
                        ,bindAction: '#test9'
                        ,before: function(obj){
                            layer.load(0);
                        }
                        ,done: function(res){
                            if( res.code == 200 ) {
                                layer.msg('上传成功');
                                layer.close(box);
                                table.reload('data-table');
                            }else{
                                layer.msg(res.msg);
                            }
                            layer.closeAll("loading");
                        }
                        ,error: function (res) {
                            layer.msg('上传失败，请检查文件格式');
                            layer.closeAll("loading");
                        }
                    });
                }
            });
        });

        $("#search").click(function () {
            table.reload('data-table', {
                page: {
                    curr: 1
                }
                ,where: {
                    sttp: $("select[name=sttp]").val(),
                    stcd: $("select[name=station]").val(),
                    name: $("input[name=name]").val()
                }
            });
        });

    });
</script>
</body>
</html>
