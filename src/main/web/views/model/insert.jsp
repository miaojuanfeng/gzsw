<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>新建河系</title>
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
      <div class="layui-row layui-col-space15">

        <div class="layui-col-md8">
          <div class="layui-card">
            <div class="layui-card-header">
              <div style="float: left;">流域河系</div>
              <div style="float: right;">
                <button id="insert-root" type="button" class="layui-btn layui-btn-sm">插入根节点</button>
              </div>
              <div style="clear:both;float:none;"></div>
            </div>
            <div class="layui-card-body" style="padding: 15px;">
                <div id="test9" class="demo-tree demo-tree-box" style="width: 100%; height: 392px; overflow: scroll;"></div>
            </div>
          </div>
        </div>

        <div class="layui-col-md4">
          <div class="layui-card">
            <div class="layui-card-header">基本信息</div>
            <div class="layui-card-body" style="padding: 15px;">
              <table class="layui-table" style="margin:0;">
                <colgroup>
                  <col width="30%">
                  <col width="70%">
                </colgroup>
                <tbody>
                <tr>
                  <td>河系名称</td>
                  <td class="input-tr">
                    <input type="hidden" name="id" value="${id}">
                    <input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入河系名称" class="layui-input" value="${name}">
                    <input type="hidden" name="stcd" value="${stcd}">
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="layui-card">
            <div class="layui-card-header">站点信息</div>
            <div class="layui-card-body">
              <table class="layui-table" style="margin:0;">
                <colgroup>
                  <col width="30%">
                  <col width="70%">
                </colgroup>
                <tbody>
                <tr>
                  <td>站点名称</td>
                  <td id="td-stname"></td>
                </tr>
                <tr>
                  <td>预报方案</td>
                  <td id="td-plan"></td>
                </tr>
                <tr>
                  <td>KE</td>
                  <td id="td-ke"></td>
                </tr>
                <tr>
                  <td>XE</td>
                  <td id="td-xe"></td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>

      </div>
      <div class="layui-form-item layui-layout-admin">
        <div class="layui-input-block">
          <div class="layui-footer" style="left: 0;">
            <button class="layui-btn" lay-submit="" lay-filter="save">立即提交</button>
            <button class="layui-btn layui-btn-primary" lay-submit="" lay-filter="close">关闭</button>
          </div>
        </div>
      </div>
    </form>
  </div>


  <form class="layui-form" lay-filter="addform" id="addform" style="padding:15px;display: none;">
    <div class="layui-form-item">
      <table class="layui-table" style="margin:0;">
        <colgroup>
          <col width="30%">
          <col width="70%">
        </colgroup>
        <tbody>
        <tr>
          <td>站点名称</td>
          <td class="input-tr">
              <div style="width: 30%;float:left;">
                <select name="sttp" lay-filter="sttp" lay-verify="required" lay-search="">
                    <option value="">请选择</option>
                    <c:forEach items="${sttps}" var="sttp" varStatus="id">
                    <option value="${sttp.code}">${sttp.text}</option>
                    </c:forEach>
                </select>
              </div>
              <div style="width: 70%;float:right;">
                <select name="station" lay-filter="station" lay-verify="required" lay-search="">
                    <option value="">请选择</option>
                </select>
              </div>
          </td>
        </tr>
        <tr>
          <td>预报方案</td>
          <td class="input-tr">
          <select name="plan" lay-verify="required" lay-search=""></select>
          </td>
        </tr>
        <tr id="oq-tr" style="display: none">
          <td>INTV</td>
          <td class="input-tr">
            <input type="text" name="INTV" lay-verify="INTV" autocomplete="off" placeholder="请输入" class="layui-input">
          </td>
        </tr>
        <tr>
          <td>KE</td>
          <td class="input-tr">
            <input type="text" name="KE" lay-verify="KE" autocomplete="off" placeholder="请输入" class="layui-input">
          </td>
        </tr>
        <tr>
          <td>XE</td>
          <td class="input-tr">
            <input type="text" name="XE" lay-verify="XE" autocomplete="off" placeholder="请输入" class="layui-input">
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </form>


  <script>
  layui.config({
    base: base //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form', 'laydate', 'tree'], function(){
    var $ = layui.$
    ,admin = layui.admin
    ,tree = layui.tree
    ,form = layui.form;

    var data1 = ${data};
    if (data1.length > 0) {
        $("#insert-root").addClass("layui-btn-disabled");
    }

    tree.render({
        elem: '#test9'
        ,id: 'test9'
        ,data: data1
        ,onlyIconControl: true
        ,customOperate: true
        ,edit: ['add', 'update', 'del'] //操作节点的图标
        ,click: function(obj){
            var data = obj.data;
            $('#td-stname').html(data.stname);
            $('#td-plan').html(data.planName);
            $('#td-ke').html(data.ke);
            $('#td-xe').html(data.xe);
        }
        ,operate: function (obj) {
            var type = obj.type;
            var data = obj.data;
            var elem = obj.elem;
            var deptId = data.id;
            var parentId = data.parentId;

            if( type == 'add' ){
                openForm("add", deptId);
            }else if( type == 'update' ){
                openForm("update", deptId);
            }else if( type == 'del' ){
                function each(data) {
                    data.forEach(function (e, index) {
                        if (e.id == deptId) {
                            data.splice(index, 1);
                        }
                        if ( e.children != undefined && e.children.length > 0) {
                            each(e.children);
                        }
                    })
                }
                each(data1);
                if (data1.length == 0) {
                    $("#insert-root").removeClass("layui-btn-disabled");
                }
                tree.reload('test9', {
                    data: data1
                });
            }
        }
    });

    function clearForm() {
        $("#addform select[name=station]").val('');
        $("#addform select[name=plan]").val('');
        $("#addform input[name=KE]").val('');
        $("#addform input[name=XE]").val('');
        $("#addform input[name=INTV]").val('');
        form.render();
    }

    function setForm(stcd, planId) {
        $("#addform select[name=plan]").html('<option value="">请选择</option>');
        form.render('select');
        var loading = layer.load(0);
        $.post(
            '${pageContext.request.contextPath}/plan/getPlan',
            {
                stcd: stcd
            },
            function (data) {
                var html = '';
                $.each(data, function (key, value) {
                    html += '<option value="' + value.id + '">' + value.name + '</option>';
                });
                $("#addform select[name=plan]").append(html);
                $("#addform select[name=plan]").val(planId);
                form.render('select');
                layer.close(loading);
            }
        );
    }

    function openForm(action, deptId){
        layer.open({
            type: 1
            ,offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
            ,id: 'layerDemo1' //防止重复弹出
            ,content: $('#addform')
            ,area:["500px","420px"]
            ,btn: ['确定', '取消']
            ,btnAlign: 'c' //按钮居中
            ,shade: 0.2 //不显示遮罩
            ,btn1: function(index, layero){
                if(action == "new" || action == "add") {
                    var submit = true;
                    if ($("#addform select[name=station]").val() == "" ||
                        $("#addform select[name=plan]").val() == "" ||
                        $("#addform input[name=KE]").val() == "" ||
                        $("#addform input[name=XE]").val() == "") {
                        submit = false;
                    }
                    if (!submit) {
                        layer.msg('请填妥相关信息');
                        return false;
                    }
                    var station = {
                        title: $("#addform select[name=station]").find("option:selected").text(),
                        stcd: $("#addform select[name=station]").val(),
                        sttype: $("#addform select[name=sttp]").val(),
                        stname: $("#addform select[name=station]").find("option:selected").text(),
                        planId: $("#addform select[name=plan]").val(),
                        planName: $("#addform select[name=plan]").find("option:selected").text(),
                        ke: $("#addform input[name=KE]").val(),
                        xe: $("#addform input[name=XE]").val(),
                        id: new Date().getTime(),
                        spread: true
                    };
                    if (deptId == 0) {
                        data1.push(station);
                        $("input[name=stcd]").val(station.stcd);
                    } else {
                        function each(data) {
                            data.forEach(function (e) {
                                if (e.id == deptId) {
                                    if (e.children == undefined) {
                                        e.children = [];
                                    }
                                    e.children.push(station);
                                }
                                if (e.children != undefined && e.children.length > 0) {
                                    each(e.children);
                                }
                            })
                        }
                        each(data1);
                    }
                    if (data1.length > 0) {
                        $("#insert-root").addClass("layui-btn-disabled");
                    }
                    tree.reload('test9', {
                        data: data1
                    });
                    clearForm();
                    layer.closeAll();
                }else if(action == "update"){
                    function each(data) {
                        data.forEach(function (e) {
                            if (e.id == deptId) {
                                e.title = $("#addform select[name=station]").find("option:selected").text();
                                e.stcd = $("#addform select[name=station]").val();
                                e.sttype = $("#addform select[name=sttp]").val();
                                e.stname = $("#addform select[name=station]").find("option:selected").text();
                                e.planId = $("#addform select[name=plan]").val();
                                e.planName = $("#addform select[name=plan]").find("option:selected").text();
                                e.ke = $("#addform input[name=KE]").val();
                                e.xe = $("#addform input[name=XE]").val();
                            }
                            if (e.children != undefined && e.children.length > 0) {
                                each(e.children);
                            }
                        })
                    }
                    each(data1);
                    tree.reload('test9', {
                        data: data1
                    });
                    clearForm();
                    layer.closeAll();
                }
            }
            ,btn2: function(index, layero){
                clearForm();
                layer.closeAll();
            }
            ,cancel: function(){ //右上角关闭回调
                clearForm();
                layer.closeAll();
            }
            ,success: function(layero, index){  //弹出成功的回调
                if(action == "update"){
                    function each(data) {
                        for(var i=0; i<data.length; i++){
                            var e = data[i];
                            if (e.id == deptId) {
                                return e;
                            }
                            if (e.children != undefined && e.children.length > 0) {
                                var e = each(e.children);
                                if( e != undefined ){
                                    return e;
                                }
                            }
                        }
                        return undefined;
                    }
                    var e = each(data1);
                    // console.log(e);
                    clearForm();
                    setForm(e.stcd, e.planId);
                    $("#addform select[name=sttp]").val(e.sttype);
                    getStation(e.sttype, e.stcd);
                    $("#addform input[name=KE]").val(e.ke);
                    $("#addform input[name=XE]").val(e.xe);
                    form.render();
                }
            }
        });
    }

    function getStation(sttp, val){
        if( sttp != "" ){
            var loading = layer.load(0);
            $.post(
                '${pageContext.request.contextPath}/station/getStation',
                {
                    sttp: sttp
                },
                function (data) {
                    var html = '';
                    $.each(data, function (key, value) {
                        html += '<option value="' + value.stcd + '">' + value.stname + '</option>';
                    });
                    $("#addform select[name=station]").append(html);
                    if( val != null ){
                        $("#addform select[name=station]").val(val);
                    }
                    form.render('select');
                    layer.close(loading);
                }
            );
        }
    }

    form.on('select(sttp)', function(data){
        $("#addform select[name=station]").html('<option value="">请选择</option>');
        $("#addform select[name=plan_cl]").html('<option value="">请选择</option>');
        $("#addform select[name=plan_hl]").html('<option value="">请选择</option>');
        form.render('select');
        var sttp = $("select[name=sttp]").val();
        getStation(sttp, null);
        if( sttp == 'RR' ){
            $("#addform #oq-tr").show();
        }else{
            $("#addform #oq-tr").hide();
        }
    });

    form.on('select(station)', function(data){
        $("#addform select[name=plan]").html('<option value="">请选择</option>');
        form.render('select');
        var stcd = $("select[name=station]").val();
        if( stcd != "" ){
            var loading = layer.load(0);
            $.post(
                '${pageContext.request.contextPath}/plan/getPlan',
                {
                    stcd: stcd
                },
                function (data) {
                    var html = '';
                    $.each(data, function (key, value) {
                        html += '<option value="' + value.id + '">' + value.name + '</option>';
                    });
                    $("#addform select[name=plan]").append(html);
                    form.render('select');
                    layer.close(loading);
                }
            );
        }
    });
    
    form.render(null, 'component-form-group');
    
    /* 自定义验证规则 */
    form.verify({
      title: function(value){
        if(value.length < 5){
          return '标题至少得5个字符啊';
        }
      }
      ,pass: [/(.+){6,12}$/, '密码必须6到12位']
      ,content: function(value){
        layedit.sync(editIndex);
      }
    });

    /* 插入根节点 */
    $("#insert-root").click(function(){
        if( data1.length > 0 ){
            return false;
        }
        openForm("new", 0);
    });

    var openTree = function(treeData, objId) {
        var nodeId = familyTree(treeData, objId);
        function each(data) {
            data.forEach(e => {
                if (nodeId.indexOf(e.id) != -1) {
                e.spread = true;
              }
              if (e.children.length > 0) {
                  each(e.children);
              }
            })
        }
        each(treeData);
        return treeData;
    };

    // 查找一个节点的所有父节点
    var familyTree = function(arr1, id) {
        var temp = [];
        var forFn = function(arr, id) {
            for (var i = 0; i < arr.length; i++) {
                var item = arr[i]
                if (item.id === id) {
                    temp.push(item.id);
                    forFn(arr1, item.parentId);
                    break
                } else {
                    if (item.children) {
                        forFn(item.children, id);
                    }
                }
            }
        }
        forFn(arr1, id);
        return temp;
    };
    
    /* 监听提交 */
    form.on('submit(save)', function(data){
      // parent.layer.alert(JSON.stringify(data.field), {
      //   title: '最终的提交信息'
      // })
      var submit = true;
      if ($("input[name=name]").val() == "" ||
          data1.length == 0) {
          submit = false;
      }
      if (!submit) {
          layer.msg('请填妥相关信息');
          return false;
      }
      var action = $("input[name=id]").val() == '' ? 'insert' : 'update/' + $("input[name=id]").val();
      $.post({
          url: "${pageContext.request.contextPath}/model/" + action,
          contentType: "application/x-www-form-urlencoded",
          data: {
              name: $("input[name=name]").val(),
              stcd: $("input[name=stcd]").val(),
              data: JSON.stringify(data1)
          },
          success : function(result) {
              parent.layer.alert("数据保存成功", function (index) {
                  parent.layer.close(index);
                  parent.layui.admin.events.closeThisTabs();
              })
          }
      }).fail(function(response) {
          parent.layer.alert("数据保存失败");
      });
      return false;
    });

    /* 监听关闭 */
    form.on('submit(close)', function(data){
        parent.layui.admin.events.closeThisTabs();
    });
  });
  </script>
</body>
</html>
