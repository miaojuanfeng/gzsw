<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>主页</title>
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
      <div class="layui-col-md12">
        <div class="layui-row layui-col-space15">
          <div class="layui-col-md4">
            <div class="layui-card">
              <div class="layui-card-header">时间范围</div>
              <div class="layui-card-body">

                <div class="layui-carousel layadmin-carousel layadmin-shortcut">
                  <div carousel-item>
                    <table class="layui-table" style="margin:0;">
                      <colgroup>
                        <col width="40%">
                        <col width="60%">
                      </colgroup>
                      <tbody>
                      <tr>
                        <td>开始时间</td>
                        <td class="input-tr">
                          <input id="startTime" type="text" name="startTime" placeholder="请选择" autocomplete="off" class="layui-input" value="${startTime}">
                        </td>
                      </tr>
                      <tr>
                        <td>结束时间</td>
                        <td class="input-tr">
                          <input id="endTime" type="text" name="endTime" placeholder="请选择" autocomplete="off" class="layui-input" value="${endTime}">
                        </td>
                      </tr>
                      </tbody>
                    </table>
                    <%--<ul class="layui-row layui-col-space10">--%>
                      <%--<li class="layui-col-xs3">--%>
                        <%--<a lay-href="home/homepage1.html">--%>
                          <%--<i class="layui-icon layui-icon-console"></i>--%>
                          <%--<cite>主页一</cite>--%>
                        <%--</a>--%>
                      <%--</li>--%>
                      <%--<li class="layui-col-xs3">--%>
                        <%--<a lay-href="home/homepage2.html">--%>
                          <%--<i class="layui-icon layui-icon-chart"></i>--%>
                          <%--<cite>主页二</cite>--%>
                        <%--</a>--%>
                      <%--</li>--%>
                      <%--<li class="layui-col-xs3">--%>
                        <%--<a lay-href="component/layer/list.html">--%>
                          <%--<i class="layui-icon layui-icon-template-1"></i>--%>
                          <%--<cite>弹层</cite>--%>
                        <%--</a>--%>
                      <%--</li>--%>
                      <%--<li class="layui-col-xs3">--%>
                        <%--<a layadmin-event="im">--%>
                          <%--<i class="layui-icon layui-icon-chat"></i>--%>
                          <%--<cite>聊天</cite>--%>
                        <%--</a>--%>
                      <%--</li>--%>
                      <%--<li class="layui-col-xs3">--%>
                        <%--<a lay-href="component/progress/index.html">--%>
                          <%--<i class="layui-icon layui-icon-find-fill"></i>--%>
                          <%--<cite>进度条</cite>--%>
                        <%--</a>--%>
                      <%--</li>--%>
                      <%--<li class="layui-col-xs3">--%>
                        <%--<a lay-href="app/workorder/list.html">--%>
                          <%--<i class="layui-icon layui-icon-survey"></i>--%>
                          <%--<cite>工单</cite>--%>
                        <%--</a>--%>
                      <%--</li>--%>
                      <%--<li class="layui-col-xs3">--%>
                        <%--<a lay-href="user/user/list.html">--%>
                          <%--<i class="layui-icon layui-icon-user"></i>--%>
                          <%--<cite>用户</cite>--%>
                        <%--</a>--%>
                      <%--</li>--%>
                      <%--<li class="layui-col-xs3">--%>
                        <%--<a lay-href="set/system/website.html">--%>
                          <%--<i class="layui-icon layui-icon-set"></i>--%>
                          <%--<cite>设置</cite>--%>
                        <%--</a>--%>
                      <%--</li>--%>
                    <%--</ul>--%>
                    <%--<ul class="layui-row layui-col-space10">--%>
                      <%--<li class="layui-col-xs3">--%>
                        <%--<a lay-href="set/user/info.html">--%>
                          <%--<i class="layui-icon layui-icon-set"></i>--%>
                          <%--<cite>我的资料</cite>--%>
                        <%--</a>--%>
                      <%--</li>--%>
                      <%--<li class="layui-col-xs3">--%>
                        <%--<a lay-href="set/user/info.html">--%>
                          <%--<i class="layui-icon layui-icon-set"></i>--%>
                          <%--<cite>我的资料</cite>--%>
                        <%--</a>--%>
                      <%--</li>--%>
                      <%--<li class="layui-col-xs3">--%>
                        <%--<a lay-href="set/user/info.html">--%>
                          <%--<i class="layui-icon layui-icon-set"></i>--%>
                          <%--<cite>我的资料</cite>--%>
                        <%--</a>--%>
                      <%--</li>--%>
                      <%--<li class="layui-col-xs3">--%>
                        <%--<a lay-href="set/user/info.html">--%>
                          <%--<i class="layui-icon layui-icon-set"></i>--%>
                          <%--<cite>我的资料</cite>--%>
                        <%--</a>--%>
                      <%--</li>--%>
                      <%--<li class="layui-col-xs3">--%>
                        <%--<a lay-href="set/user/info.html">--%>
                          <%--<i class="layui-icon layui-icon-set"></i>--%>
                          <%--<cite>我的资料</cite>--%>
                        <%--</a>--%>
                      <%--</li>--%>
                      <%--<li class="layui-col-xs3">--%>
                        <%--<a lay-href="set/user/info.html">--%>
                          <%--<i class="layui-icon layui-icon-set"></i>--%>
                          <%--<cite>我的资料</cite>--%>
                        <%--</a>--%>
                      <%--</li>--%>
                      <%--<li class="layui-col-xs3">--%>
                        <%--<a lay-href="set/user/info.html">--%>
                          <%--<i class="layui-icon layui-icon-set"></i>--%>
                          <%--<cite>我的资料</cite>--%>
                        <%--</a>--%>
                      <%--</li>--%>
                      <%--<li class="layui-col-xs3">--%>
                        <%--<a lay-href="set/user/info.html">--%>
                          <%--<i class="layui-icon layui-icon-set"></i>--%>
                          <%--<cite>我的资料</cite>--%>
                        <%--</a>--%>
                      <%--</li>--%>
                    <%--</ul>--%>

                  </div>
                </div>

              </div>
            </div>
          </div>
          <div class="layui-col-md8">
            <div class="layui-card">
              <div class="layui-card-header">站点选择</div>
              <div class="layui-card-body">

                <div class="layui-carousel layadmin-carousel layadmin-backlog">
                  <div carousel-item>
                    <c:forEach items="${stations}" var="child" varStatus="status">
                    <ul class="layui-row layui-col-space10">
                      <c:forEach items="${child}" var="station">
                      <li class="layui-col-xs3">
                        <a href="javascript:;" stcd="${station.stcd}" stname="${station.stname}" class="layadmin-backlog-body">
                          <h3>水文站</h3>
                          <p><cite>${station.stname}</cite></p>
                        </a>
                      </li>
                      </c:forEach>
                    </ul>
                    </c:forEach>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <%--<div class="layui-col-md12">--%>
            <%--<div class="layui-card">--%>
              <%--<div class="layui-card-header">数据概览</div>--%>
              <%--<div class="layui-card-body">--%>
                <%--<div id="chart1" style="width:100%;height:340px;"><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>--%>
              <%--</div>--%>
            <%--</div>--%>
          <%--</div>--%>
        </div>
        <div class="layui-row layui-col-space15">
          <div class="layui-col-md12">
            <div class="layui-card">
              <%--<div class="layui-card-header">今日雨量</div>--%>
              <div class="layui-card-body">
                <div id="chart1" style="width:100%;height:300px;"><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>
              </div>
            </div>
            <div class="layui-card">
              <%--<div class="layui-card-header">水位过程</div>--%>
              <div class="layui-card-body">
                <div id="chart2" style="width:100%;height:300px;"><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>
              </div>
            </div>
          </div>
          <%--<div class="layui-col-md4">--%>
            <%--<div class="layui-card">--%>
              <%--&lt;%&ndash;<div class="layui-card-header">预警等级</div>&ndash;%&gt;--%>
              <%--<div class="layui-card-body">--%>
                <%--<div id="chart3" style="width:100%;height:300px;"><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>--%>
              <%--</div>--%>
            <%--</div>--%>
            <%--<div class="layui-card">--%>
              <%--&lt;%&ndash;<div class="layui-card-header">流域平均降雨</div>&ndash;%&gt;--%>
              <%--<div class="layui-card-body">--%>
                <%--<div id="chart4" style="width:100%;height:300px;"><i class="layui-icon layui-icon-loading1 layadmin-loading"></i></div>--%>
              <%--</div>--%>
            <%--</div>--%>
          <%--</div>--%>
        </div>
      </div>
    </div>
  </div>

  <script>
  layui.config({
    base: base //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'echarts', 'carousel', 'laydate'], function () {
      var $ = layui.$
          ,admin = layui.admin
          ,carousel = layui.carousel
          ,laydate = layui.laydate
          ,device = layui.device()
          ,echarts = layui.echarts;
      ajaxSetup($, '由于您长时间没有操作, 请重新登录。');

      laydate.render({
          elem: '#startTime'
          ,type: 'datetime'
          ,done: function(value, date){ //监听日期被切换
              if( value != '' ) {
                  getChart(value, $("#endTime").val());
              }
          }
      });

      laydate.render({
          elem: '#endTime'
          ,type: 'datetime'
          ,done: function(value, date){ //监听日期被切换
              if( value != '' ) {
                  getChart($("#startTime").val(), value);
              }
          }
      });

      //轮播切换
      $('.layadmin-carousel').each(function(){
          var othis = $(this);
          carousel.render({
              elem: this
              ,width: '100%'
              ,arrow: 'none'
              ,interval: othis.data('interval')
              ,autoplay: othis.data('autoplay') === true
              ,trigger: (device.ios || device.android) ? 'click' : 'hover'
              ,anim: othis.data('anim')
          });
      });

      function drawChart1(data) {
          var option1 = {
              title: {
                  text: '雨量过程',
                  subtext: stname,
                  x: 'center',
                  textStyle: {
                      fontSize: 14
                  }
              },
              tooltip: {
                  trigger: 'axis'
              },
              calculable: true,
              xAxis: [
                  {
                      type: 'category',
                      data: data.timeList.map(function (str) {
                          return str.substring(5).substring(0, 11).replace('-', '/').replace(' ', '\n')
                      })
                  }
              ],
              yAxis: [
                  {

                      name: '降雨量(mm)',
                      type: 'value',
                      max: data.max
                  }
              ],
              series: [
                  {
                      name: '累计雨量',
                      type: 'bar',
                      // label: {
                      //     normal: {
                      //         show: true,
                      //         position: 'top'
                      //     }
                      // },
                      data: data.rainfallList,
                      markLine: {
                          data: [
                              {type: 'average', name: '平均值'}
                          ]
                      }
                  }
              ]
          };
          chart1.setOption(option1);
      }
      var chart1 = echarts.init(document.getElementById('chart1'));

      function getChart1(startTime, endTime){
          // var loading = layer.load(0);
          loading(chart1);
          $.post(
              '${pageContext.request.contextPath}/station/chart1',
              {
                  stcd: stcd,
                  startTime: startTime,
                  endTime:   endTime
              },
              function (data) {
                  if( data.code == 200 ) {
                      // data.data.stname = stname;
                      drawChart1(data.data);
                      // layer.close(loading);
                  }
                  stopLoading(chart1);
              }
          );
      }

      function drawChart2(data) {
          var option2 = {
              title: {
                  text: '水位过程',
                  subtext: stname,
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
                  data: data.timeList.map(function (str) {
                      return str.replace(' ', '\n')
                  })
              }],
              yAxis: [{
                  name: '水位(m)',
                  type: 'value',
                  max: data.max,
                  min: data.min
              }],
              series: [{
                  name: '水位',
                  type: 'line',
                  smooth: true,
                  itemStyle: {normal: {areaStyle: {type: 'default'}}},
                  data: data.riverList
              }]
          };
          chart2.setOption(option2);
      }
      var chart2 = echarts.init(document.getElementById('chart2'));

      function getChart2(startTime, endTime){
          loading(chart2);
          $.post(
              '${pageContext.request.contextPath}/station/chart2',
              {
                  stcd: stcd,
                  startTime: startTime,
                  endTime:   endTime
              },
              function (data) {
                  if( data.code == 200 ) {
                      // data.data.stname = stname;
                      drawChart2(data.data);
                      // layer.close(loading);
                  }
                  stopLoading(chart2);
              }
          );
      }

      function getChart(startTime, endTime){
          getChart1(startTime, endTime);
          getChart2(startTime, endTime);
      }

      function loading(chart){
          chart.showLoading({
              effect :"bubble"
          });
      }

      function stopLoading(chart){
          chart.hideLoading();
      }

      $("a.layadmin-backlog-body").click(function () {
          stcd = $(this).attr("stcd");
          stname = $(this).attr("stname");
          getChart($("#startTime").val(), $("#endTime").val());
      });
      $("#startTime, #endTime").on("change", function () {
          getChart($("#startTime").val(), $("#endTime").val());
      });

      var stcd = '62303500';
      var stname = '汾坑';
      getChart($("#startTime").val(), $("#endTime").val());
  });
  </script>
</body>
</html>

