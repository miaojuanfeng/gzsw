<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>预报</title>
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
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md3">
        <div class="layui-card layui-form" style="height: 663px;overflow: scroll;">
          <div class="layui-card-header">预报参数</div>
          <table class="layui-table" style="margin:0;">
            <colgroup>
              <col width="40%">
              <col width="60%">
            </colgroup>
            <tbody>
            <%--<tr>--%>
              <%--<td>站点类型</td>--%>
              <%--<td class="input-tr">--%>
                <%--<select name="sttp" lay-filter="sttp" lay-verify="required" lay-search="">--%>
                  <%--<option value="">请选择</option>--%>
                  <%--<c:forEach items="${sttps}" var="sttp" varStatus="id">--%>
                    <%--<option value="${sttp.code}">${sttp.text}</option>--%>
                  <%--</c:forEach>--%>
                <%--</select>--%>
              <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
              <%--<td>预报站点</td>--%>
              <%--<td class="input-tr">--%>
                <%--<select name="station" lay-filter="station" lay-verify="required" lay-search="">--%>
                  <%--<option value="">请选择</option>--%>
                <%--</select>--%>
              <%--</td>--%>
            <%--</tr>--%>
            <tr>
              <td>河系方案</td>
              <td class="input-tr">
                <select name="model" lay-filter="model" lay-verify="required" lay-search="">
                  <option value="">请选择</option>
                  <c:forEach items="${models}" var="model" varStatus="id">
                    <option value="${model.id}">${model.name}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td>预报时间</td>
              <td class="input-tr">
                <input id="forecastTime" type="text" name="forecastTime" placeholder="请选择" autocomplete="off" class="layui-input" value="${forecastTime}">
              </td>
            </tr>
            <tr>
              <td>影响时间</td>
              <td class="input-tr">
                <input id="affectTime" type="text" name="affectTime" placeholder="请选择" autocomplete="off" class="layui-input" value="${affectTime}">
              </td>
            </tr>
            <tr>
              <td>预报天数</td>
              <td class="input-tr">
                <select name="day" lay-verify="required" lay-search="">
                  <option value="">请选择</option>
                  <option value="3">3天</option>
                  <option value="5">5天</option>
                </select>
              </td>
            </tr>
            <tr>
              <td>未来降雨</td>
              <td class="input-tr">
                <select name="unit" lay-verify="required" lay-search="">
                  <option value="">请选择</option>
                  <option value="0">实测雨量</option>
                  <option value="2">日本台</option>
                  <option value="6">欧洲台</option>
                </select>
              </td>
            </tr>
            </tbody>
          </table>

          <div class="layui-card-header">流域河系</div>
          <div id="area" class="demo-tree demo-tree-box" style="width: 100%; height: 240px; overflow-y: scroll; padding-top:10px;"></div>

          <%--<div class="layui-card-header"><span class="stname"></span>参数调整</div>--%>
          <%--<table class="layui-table table-param" style="margin:0;">--%>
            <%--<colgroup>--%>
              <%--<col width="25%">--%>
              <%--<col width="25%">--%>
              <%--<col width="25%">--%>
              <%--<col width="25%">--%>
            <%--</colgroup>--%>
            <%--<tbody>--%>
            <%--<tr>--%>
              <%--<td>SM</td>--%>
              <%--<td class="input-tr">--%>
                <%--<input id="SM" type="text" name="SM" placeholder="请输入" autocomplete="off" class="layui-input">--%>
              <%--</td>--%>
              <%--<td>CI</td>--%>
              <%--<td class="input-tr">--%>
                <%--<input id="CI" type="text" name="CI" placeholder="请输入" autocomplete="off" class="layui-input">--%>
              <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
              <%--<td>CS</td>--%>
              <%--<td class="input-tr">--%>
                <%--<input id="CS" type="text" name="CS" placeholder="请输入" autocomplete="off" class="layui-input">--%>
              <%--</td>--%>
              <%--<td>L</td>--%>
              <%--<td class="input-tr">--%>
                <%--<input id="L" type="text" name="L" placeholder="请输入" autocomplete="off" class="layui-input">--%>
              <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
              <%--<td>KE</td>--%>
              <%--<td class="input-tr">--%>
                <%--<input id="KE" type="text" name="KE" placeholder="请输入" autocomplete="off" class="layui-input">--%>
              <%--</td>--%>
              <%--<td>XE</td>--%>
              <%--<td class="input-tr">--%>
                <%--<input id="XE" type="text" name="XE" placeholder="请输入" autocomplete="off" class="layui-input">--%>
              <%--</td>--%>
            <%--</tr>--%>
            <%--</tbody>--%>
          <%--</table>--%>

          <%--<div class="layui-card-header"><span class="stname"></span>状态调整</div>--%>
          <%--<table class="layui-table table-param" style="margin:0;">--%>
            <%--<colgroup>--%>
              <%--<col width="25%">--%>
              <%--<col width="25%">--%>
              <%--<col width="25%">--%>
              <%--<col width="25%">--%>
            <%--</colgroup>--%>
            <%--<tbody>--%>
            <%--<tr>--%>
              <%--<td>WU0</td>--%>
              <%--<td class="input-tr">--%>
                <%--<input id="WU0" type="text" name="WU0" placeholder="请输入" autocomplete="off" class="layui-input">--%>
              <%--</td>--%>
              <%--<td>WL0</td>--%>
              <%--<td class="input-tr">--%>
                <%--<input id="WL0" type="text" name="WL0" placeholder="请输入" autocomplete="off" class="layui-input">--%>
              <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
              <%--<td>WD0</td>--%>
              <%--<td class="input-tr">--%>
                <%--<input id="WD0" type="text" name="WD0" placeholder="请输入" autocomplete="off" class="layui-input">--%>
              <%--</td>--%>
              <%--<td>S0</td>--%>
              <%--<td class="input-tr">--%>
                <%--<input id="S0" type="text" name="S0" placeholder="请输入" autocomplete="off" class="layui-input">--%>
              <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
              <%--<td>FR0</td>--%>
              <%--<td class="input-tr">--%>
                <%--<input id="FR0" type="text" name="FR0" placeholder="请输入" autocomplete="off" class="layui-input">--%>
              <%--</td>--%>
              <%--<td>QRS0</td>--%>
              <%--<td class="input-tr">--%>
                <%--<input id="QRS0" type="text" name="QRS0" placeholder="请输入" autocomplete="off" class="layui-input">--%>
              <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
              <%--<td>QRSS0</td>--%>
              <%--<td class="input-tr">--%>
                <%--<input id="QRSS0" type="text" name="QRSS0" placeholder="请输入" autocomplete="off" class="layui-input">--%>
              <%--</td>--%>
              <%--<td>QRG0</td>--%>
              <%--<td class="input-tr">--%>
                <%--<input id="QRG0" type="text" name="QRG0" placeholder="请输入" autocomplete="off" class="layui-input">--%>
              <%--</td>--%>
            <%--</tr>--%>
            <%--</tbody>--%>
          <%--</table>--%>
        </div>
      </div>
      <div class="layui-col-md9">
        <div class="layui-card layui-form">
          <div class="layui-card-header">
            <button lay-submit="" lay-filter="forecast1" class="layui-btn layui-btn-sm">预报流量</button>
            <%--<button lay-submit="" lay-filter="forecast2" class="layui-btn layui-btn-sm">预报水位</button>--%>
          </div>
          <div class="layui-card-body layui-row">
            <div id="chart" style="width:100%;height:600px;"><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script>
  layui.config({
    base: base //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'admin', 'echarts', 'table', 'tree', 'form', 'laydate'], function () {
      var $ = layui.$
          ,admin = layui.admin
          ,tree = layui.tree
          ,form = layui.form
          ,laydate = layui.laydate
          ,echarts = layui.echarts;

      var data1 = [];
      var paramStcd = '';
      // var data1 = [{
      //     title: '江西'
      //     ,id: 1
      //     ,children: [{
      //         title: '南昌'
      //         ,id: 1000
      //         ,children: [{
      //             title: '青山湖区'
      //             ,id: 10001
      //         },{
      //             title: '高新区'
      //             ,id: 10002
      //         }]
      //     },{
      //         title: '九江'
      //         ,id: 1001
      //     },{
      //         title: '赣州'
      //         ,id: 1002
      //     }]
      // },{
      //     title: '广西'
      //     ,id: 2
      //     ,children: [{
      //         title: '南宁'
      //         ,id: 2000
      //     },{
      //         title: '桂林'
      //         ,id: 2001
      //     }]
      // },{
      //     title: '陕西'
      //     ,id: 3
      //     ,children: [{
      //         title: '西安'
      //         ,id: 3000
      //     },{
      //         title: '延安'
      //         ,id: 3001
      //     }]
      // }];


      laydate.render({
          elem: '#forecastTime'
          ,type: 'datetime'
      });

      laydate.render({
          elem: '#affectTime'
          ,type: 'datetime'
      });

      tree.render({
          elem: '#area'
          ,id: 'area'
          ,data: data1
          ,onlyIconControl: true
          ,click: function(obj){
              // layer.msg(JSON.stringify(obj.data));
              // layer.msg(JSON.stringify(data1));
              // $(".stname").html(obj.data.stname);
              // setParam(obj.data);  因为分了产流和汇流
              loadStation(obj.data.stcd);
          }
      });

      form.on('select(sttp)', function(data){
          $("select[name=station]").html('<option value="">请选择</option>');
          $("select[name=model]").html('<option value="">请选择</option>');
          form.render('select');
          var sttp = $("select[name=sttp]").val();
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
                      $("select[name=station]").append(html);
                      form.render('select');
                      layer.close(loading);
                  }
              );
          }
      });

      /* 根据站点获取河系 */
      form.on('select(station)', function(data){
          var loading = layer.load(0);
          $("select[name=model]").html('<option value="">请选择</option>');
          form.render('select');
          $.post(
              '${pageContext.request.contextPath}/model/getModel',
              {
                  stcd: data.value
              },
              function (data) {
                  var html = '';
                  $.each(data, function (key, value) {
                      html += '<option value="' + value.id + '">' + value.name + '</option>';
                  });
                  $("select[name=model]").append(html);
                  form.render('select');
                  layer.close(loading);
              }
          );
      });

      /* 根据河系获取流域河系 */
      form.on('select(model)', function(data){
          data1 = {};
          tree.reload('area', {
              data: data1
          });
          if( data.value != '' ){
            var loading = layer.load(0);
            $.post(
                '${pageContext.request.contextPath}/model/getArea',
                {
                    modelId: data.value
                },
                function (data) {
                    data1 = $.parseJSON(data);
                    // if(data1.length > 0){
                    //     setParam(data1[0]);
                    // }else{
                    //     clearParam();
                    // }
                    tree.reload('area', {
                        data: data1
                    });
                    layer.close(loading);
                }
            );
          }
      });

      function loadStation(stcd) {
          var loading = layer.load(0);
          $.post({
              url: "${pageContext.request.contextPath}/forecast/station",
              contentType: "application/x-www-form-urlencoded",
              data: {
                  stcd: stcd,
              },
              success : function(data) {
                  var result = $.parseJSON(data);
                  draw(result.timeArr, result.P, result.QTRR, result.QTRR);
                  layer.close(loading);
              }
          }).fail(function(response) {
              parent.layer.alert("计算出错，请重试", {title: '错误'});
              layer.close(loading);
          });
          return false;
      }

      function setParam(data) {
          paramStcd = data.stcd;
          $(".stname").html(data.stname);

          $("input[name=SM]").val(data.plan.SM);
          $("input[name=CI]").val(data.plan.CI);
          $("input[name=CS]").val(data.plan.CS);
          $("input[name=L]").val(data.plan.L);
          $("input[name=KE]").val(data.KE);
          $("input[name=XE]").val(data.XE);

          $("input[name=WU0]").val(data.plan.WU0);
          $("input[name=WL0]").val(data.plan.WL0);
          $("input[name=WD0]").val(data.plan.WD0);
          $("input[name=S0]").val(data.plan.S0);
          $("input[name=FR0]").val(data.plan.FR0);
          $("input[name=QRS0]").val(data.plan.QRS0);
          $("input[name=QRSS0]").val(data.plan.QRSS0);
          $("input[name=QRG0]").val(data.plan.QRG0);
      }

      function clearParam() {
          paramStcd = "";
          $(".stname").html("");

          $("input[name=SM]").val("");
          $("input[name=CI]").val("");
          $("input[name=CS]").val("");
          $("input[name=L]").val("");
          $("input[name=KE]").val("");
          $("input[name=XE]").val("");

          $("input[name=WU0]").val("");
          $("input[name=WL0]").val("");
          $("input[name=WD0]").val("");
          $("input[name=S0]").val("");
          $("input[name=FR0]").val("");
          $("input[name=QRS0]").val("");
          $("input[name=QRSS0]").val("");
          $("input[name=QRG0]").val("");
      }

      function updateParam() {
          function each(data) {
              data.forEach(function (e, index) {
                  if (e.stcd == paramStcd) {
                      e.plan.sm = $("input[name=SM]").val();
                      e.plan.ci = $("input[name=CI]").val();
                      e.plan.cs = $("input[name=CS]").val();
                      e.plan.l = $("input[name=L]").val();
                      e.ke = $("input[name=KE]").val();
                      e.xe = $("input[name=XE]").val();

                      e.plan.wu0 = $("input[name=WU0]").val();
                      e.plan.wl0 = $("input[name=WL0]").val();
                      e.plan.wd0 = $("input[name=WD0]").val();
                      e.plan.s0 = $("input[name=S0]").val();
                      e.plan.fr0 = $("input[name=FR0]").val();
                      e.plan.qrs0 = $("input[name=QRS0]").val();
                      e.plan.qrss0 = $("input[name=QRSS0]").val();
                      e.plan.qrg0 = $("input[name=QRG0]").val();

                      return;
                  }
                  if ( e.children != undefined && e.children.length > 0) {
                      each(e.children);
                  }
              })
          }
          each(data1);
      }

      $(".table-param input").keyup(function () {
          updateParam();
      });

      var chart = echarts.init(document.getElementById('chart'));

      function draw(xTitle, xData1, xData2, xData3) {
          var option = {
              title: {
                  text: '今日流量趋势',
                  x: 'center',
                  textStyle: {
                      fontSize: 14
                  }
              },
              tooltip: {
                  trigger: 'axis'
              },
              legend: {
                  data: ['', '']
              },
              xAxis: [{
                  type: 'category',
                  boundaryGap: false,
                  data: xTitle
              }],
              yAxis: [{
                  type: 'value'
              }],
              series: [
                  {
                      name:'降雨量',
                      type:'bar',
                      // itemStyle:{
                      //     normal:{
                      //         color:'#7EC0EE',
                      //
                      //     }
                      // },
                      // barWidth: '40%',
                      // yAxisIndex:1,
                      // animation: true,
                      data: xData1
                  },
                  {
                      name:'实测水位',
                      type:'line',
                      animation: true,
                      smooth: true,
                      symbol: 'circle',
                      symbolSize: 1,
                      itemStyle:{
                          normal:{
                              color:'#7EC0EE',
                          }
                      },
                      lineStyle: {
                          normal: {
                              color:'#7EC0EE',
                              width: 3,
                              shadowColor: 'rgba(0,0,0,0.4)',
                              shadowBlur: 10,
                              shadowOffsetY: 10
                          }
                      },
                      data: xData2
                  },
                  {
                      name:'预报水位',
                      type:'line',
                      animation: true,
                      smooth: true,
                      symbol: 'circle',
                      symbolSize: 1,
                      itemStyle:{
                          normal:{
                              color:'#ff0000',

                          }
                      },
                      lineStyle: {
                          normal: {
                              color:'#ff0000',
                              width: 3,
                              shadowColor: 'rgba(0,0,0,0.4)',
                              shadowBlur: 10,
                              shadowOffsetY: 10
                          }
                      },
                      data: xData3,
                      // markLine: {
                      //     itemStyle: {
                      //         normal: { lineStyle: { color:'#CD2626' },
                      //             label: { show: true , position:'middle' }
                      //         }
                      //     },
                      //     data: [
                      //
                      //         {
                      //             name: '警戒水位',
                      //             yAxis: 22
                      //         }
                      //     ]
                      // },
                      // markPoint: {
                      //     symbolSize: 65,
                      //     data: [
                      //         {type: 'max', name: '最大值'}
                      //
                      //     ]
                      // }
                  }
              ]
          };
          chart.setOption(option);
      }

      /* 预报流量 */
      form.on('submit(forecast1)', function(data){
          // parent.layer.alert(JSON.stringify(data.field), {
          //   title: '最终的提交信息'
          // })
          // var submit = true;
          // if ($("input[name=name]").val() == "" ||
          //     data1.length == 0) {
          //     submit = false;
          // }
          // if (!submit) {
          //     layer.msg('请填妥相关信息');
          //     return false;
          // }
          var loading = layer.load(0);
          $.post({
              url: "${pageContext.request.contextPath}/forecast/compute",
              contentType: "application/x-www-form-urlencoded",
              data: {
                  type: 1,
                  stcd: $("select[name=station]").val(),
                  forecastTime: $("input[name=forecastTime]").val(),
                  affectTime: $("input[name=affectTime]").val(),
                  day: $("select[name=day]").val(),
                  unit: $("select[name=unit]").val(),
                  data: JSON.stringify(data1)
              },
              success : function(data) {
                  var result = $.parseJSON(data);
                  draw(result.timeArr, result.P, result.QTRR, result.QTRR);
                  layer.close(loading);
              }
          }).fail(function(response) {
              parent.layer.alert("计算出错，请重试", {title: '错误'});
              layer.close(loading);
          });
          return false;
      });

      /* 预报水位 */
      form.on('submit(forecast2)', function(data){
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
          var id = $("input[name=id]").val();
          var update = '';
          if( id != '' ){
              update = '/' + id;
          }
          $.post({
              url: "${pageContext.request.contextPath}/model/insert" + update,
              contentType: "application/x-www-form-urlencoded",
              data: {
                  name: $("input[name=name]").val(),
                  stcd: $("input[name=stcd]").val(),
                  data: JSON.stringify(data1)
              },
              success : function(result) {
                  parent.layer.alert("数据保存成功", {
                      title: '成功'
                  }, function () {
                      alert('关闭当前页面');
                  })
              }
          }).fail(function(response) {
              parent.layer.alert("数据保存失败", {
                  title: '错误'
              })
          });
          return false;
      });

  });
  </script>
</body>
</html>

