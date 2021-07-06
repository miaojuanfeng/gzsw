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

      laydate.render({
          elem: '#startTime'
          ,type: 'datetime'
          ,done: function(value, date){ //监听日期被切换
              getChart(stcd, stname);
          }
      });

      laydate.render({
          elem: '#endTime'
          ,type: 'datetime'
          ,done: function(value, date){ //监听日期被切换
              getChart(stcd, stname);
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
                  subtext: data.stname,
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
                      label: {
                          normal: {
                              show: true,
                              position: 'top'
                          }
                      },
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

      function getChart1(stcd, stname){
          // var loading = layer.load(0);
          loading(chart1);
          $.post(
              '${pageContext.request.contextPath}/station/chart1',
              {
                  stcd: stcd,
                  startTime: $("#startTime").val(),
                  endTime:   $("#endTime").val()
              },
              function (data) {
                  data.stname = stname;
                  drawChart1(data);
                  // layer.close(loading);
                  stopLoading(chart1);
              }
          );
      }

      function drawChart2(data) {
          var option2 = {
              title: {
                  text: '水位过程',
                  subtext: data.stname,
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

      function getChart2(stcd, stname){
          loading(chart2);
          $.post(
              '${pageContext.request.contextPath}/station/chart2',
              {
                  stcd: stcd,
                  startTime: $("#startTime").val(),
                  endTime:   $("#endTime").val()
              },
              function (data) {
                  data.stname = stname;
                  drawChart2(data);
                  // layer.close(loading);
                  stopLoading(chart2);
              }
          );
      }

      function getChart(stcd, stname){
          getChart1(stcd, stname);
          getChart2(stcd, stname);
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
          getChart(stcd, stname);
      });
      $("#startTime, #endTime").on("change", function () {
          getChart(stcd, stname);
      });

      var stcd = '62303500';
      var stname = '汾坑';
      getChart(stcd, stname);

      // var option3 = {
      //     //backgroundColor: "#ffffff",
      //     series: [{
      //         name: '预警等级',
      //         type: 'gauge',
      //         title: {				// 仪表盘标题。
      //             show: true,				// 是否显示标题,默认 true。
      //             offsetCenter: [0,"30%"],//相对于仪表盘中心的偏移位置，数组第一项是水平方向的偏移，第二项是垂直方向的偏移。可以是绝对的数值，也可以是相对于仪表盘半径的百分比。
      //             // color: "#4DBEEE",			// 文字的颜色,默认 #333。
      //             fontSize: 20,			// 文字的字体大小,默认 15。
      //         },
      //         pointer: {    // 仪表盘指针。
      //             show: true,    // 是否显示指针,默认 true。
      //             length: "70%",   // 指针长度，可以是绝对数值，也可以是相对于半径的百分比,默认 80%。
      //             width: 5,    // 指针宽度,默认 8。
      //         },
      //         detail: {
      //             formatter: '{value}',
      //             offsetCenter: [0,"50%"],
      //         },
      //         axisTick: {				// 刻度(线)样式。
      //             show: true,				// 是否显示刻度(线),默认 true。
      //             splitNumber: 5,			// 分隔线之间分割的刻度数,默认 5。
      //             length:12,				// 刻度线长。支持相对半径的百分比,默认 8。
      //             lineStyle: {			// 刻度线样式。
      //                 // color: "#eee",				//线的颜色,默认 #eee。
      //                 opacity: 1,					//图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
      //                 width: 1,					//线度,默认 1。
      //                 type: "solid",				//线的类型,默认 solid。 此外还有 dashed,dotted
      //                 shadowBlur: 10,				//(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
      //                 // shadowColor: "#fff",		//阴影颜色。支持的格式同color。
      //             },
      //         },
      //         axisLabel: {			// 刻度标签。
      //             show: false,				// 是否显示标签,默认 true。
      //             distance: 5,			// 标签与刻度线的距离,默认 5。
      //             // color: "#fff",			// 文字的颜色,默认 #fff。
      //             fontSize: 12,			// 文字的字体大小,默认 5。
      //             formatter: "{value}",	// 刻度标签的内容格式器，支持字符串模板和回调函数两种形式。 示例:// 使用字符串模板，模板变量为刻度默认标签 {value},如:formatter: '{value} kg'; // 使用函数模板，函数参数分别为刻度数值,如formatter: function (value) {return value + 'km/h';}
      //         },
      //         axisLine: {
      //             show: true,
      //             lineStyle: {
      //                 width: 23,
      //                 shadowBlur: 0,
      //                 // color: [
      //                 //     [0.3, '#26d0ce'],
      //                 //     [0.7, '#4DBEEE'],
      //                 //     [1, '#F775A9']
      //                 // ]
      //             }
      //         },
      //         data: [{
      //             value: 123,
      //             name: '预警等级',
      //         }]
      //     }]
      // };
      // var chart3 = echarts.init(document.getElementById('chart3'));
      // chart3.setOption(option3);
      //
      // var option4 = {
      //     title: {
      //         text: '流域平均降雨',
      //         subtext: '0000',
      //         x: 'center',
      //         align: 'right',
      //         textStyle: {
      //             fontSize: 14
      //         }
      //     },
      //     tooltip : {
      //         trigger: 'axis',
      //         axisPointer: {
      //             type: 'cross',
      //             animation: false,
      //             // label: {
      //             //     backgroundColor: '#505765'
      //             // }
      //         }
      //     },
      //     xAxis : [{
      //         type : 'category',
      //         data: [
      //             0000,0000,0000,0000
      //         ]
      //     }],
      //     yAxis : [{
      //         name: '降雨量(mm)',
      //         type: 'value',
      //     }],
      //     series: [{
      //         data: [
      //             0000,0000,0000,0000
      //         ],
      //         type: 'line',
      //         name:'日雨量',
      //         symbol: 'circle',//折线点设置为实心点
      //         symbolSize: 1,   //折线点的大小
      //         // label: {
      //         //        normal: {
      //         //            show: true,
      //         //             //  color:'#26d0ce',
      //         //            position: 'top'
      //         //        }
      //         //    },
      //         smooth: true,
      //         // itemStyle:{
      //         //     normal:{
      //         //         color:'#4DBEEE',
      //         //     }
      //         // },
      //         // lineStyle: {
      //         //     normal: {
      //         //         color:'#4DBEEE',
      //         //         width: 3,
      //         //         shadowColor: 'rgba(0,0,0,0.4)',
      //         //         shadowBlur: 10,
      //         //         shadowOffsetY: 10
      //         //     }
      //         // }
      //     }]
      // };
      //
      // var chart4 = echarts.init(document.getElementById('chart4'));
      // chart4.setOption(option4);
  });
  </script>
</body>
</html>

