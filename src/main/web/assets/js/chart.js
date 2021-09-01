function draw1(data) {
    document.getElementById('chart0').style.display = 'block';
    document.getElementById('chart1').style.display = 'none';
    document.getElementById('chart2').style.display = 'none';
    echarts.init(document.getElementById('chart0')).dispose();
    echarts.init(document.getElementById('chart1')).dispose();
    echarts.init(document.getElementById('chart2')).dispose();
    var chart0 = echarts.init(document.getElementById('chart0'));
    var option0 = {
        title: {
            text: data.stname + '洪水过程',
            x: 'center',
            align: 'right'
        },
        grid: {
            bottom: 80
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                animation: false,
                label: {
                    backgroundColor: '#505765'
                }
            }
        },
        legend: {
            data: ['降雨量', '实测' + data.forecastText, '预报' + data.forecastText],
            x: 'center',
            top: '30px'
        },
        dataZoom: [
            {
                show: false,
                realtime: true,
                // start: 65,
                // end: 85
            },
            {
                type: 'inside',
                realtime: true,
                start: 65,
                end: 85
            }
        ],
        xAxis: [
            {
                type: 'category',
                boundaryGap: true,
                axisLine: {onZero: false},
                data: data.timeArr.map(function (str) {
                    return str.replace('-', '/').replace('-', '/').replace(' ', '\n')
                })
            }
        ],
        yAxis: [
            {
                name: data.forecastUnit,
                type: 'value',
                max: data.riverMax,
                min: data.riverMin,
            },
            {
                name: '降雨量(mm)',
                nameLocation: 'start',
                max: data.rainfallMax,
                type: 'value',
                inverse: true
            }
        ],
        series: [
            {
                name: '实测' + data.forecastText,
                type: 'line',
                animation: true,
                smooth: true,
                symbol: 'circle',
                symbolSize: 1,
                itemStyle: {
                    normal: {
                        color: '#7EC0EE',

                    }
                },
                lineStyle: {
                    normal: {
                        color: '#7EC0EE',
                        width: 2,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 6,
                        shadowOffsetY: 6
                    }
                },
                data: data.River
            },
            {
                name: '预报' + data.forecastText,
                type: 'line',
                animation: true,
                smooth: true,
                symbol: 'circle',
                symbolSize: 1,
                itemStyle: {
                    normal: {
                        color: data.forecastColor,
                    }
                },
                lineStyle: {
                    normal: {
                        color: data.forecastColor,
                        width: 2,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 6,
                        shadowOffsetY: 6
                    }
                },
                data: data.QTRR
            },
            {
                name: '降雨量',
                type: 'bar',
                itemStyle: {
                    normal: {
                        color: '#7EC0EE',

                    }
                },
                // barWidth: '40%',
                yAxisIndex: 1,
                animation: true,
                data: data.P
            }
        ]
    };
    chart0.setOption(option0);
}

function draw2(data) {
    document.getElementById('chart0').style.display = 'none';
    document.getElementById('chart1').style.display = 'block';
    document.getElementById('chart2').style.display = 'block';
    echarts.init(document.getElementById('chart0')).dispose();
    echarts.init(document.getElementById('chart1')).dispose();
    echarts.init(document.getElementById('chart2')).dispose();
    var chart1 = echarts.init(document.getElementById('chart1'));
    var chart2 = echarts.init(document.getElementById('chart2'));
    var option1 = {
        title: {
            text: data.stname + '洪水过程',
            x: 'center',
            align: 'right'
        },
        grid: {
            bottom: 80
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
            }
        },
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                animation: false,
                label: {
                    backgroundColor: '#505765'
                }
            }
        },
        legend: {
            data:['降雨量','水位'],
            x: 'center',
            top: '30px'
        },
        dataZoom: [
            {
                show: false,
                realtime: true,
                // start: 65,
                // end: 85
            },
            {
                type: 'inside',
                realtime: true,
                start: 65,
                end: 85
            }
        ],
        xAxis: [
            {
                type: 'category',
                boundaryGap : true,
                axisLine: {onZero: false},
                data: data.timeArr.map(function (str) {
                    return str.replace('-', '/').replace('-', '/').replace(' ', '\n')
                })
            }
        ],
        yAxis: [
            {
                name: '水位(m)',
                type: 'value',
                max: data.riverMax,
                min: data.riverMin
            },
            {
                name: '降雨量(mm)',
                nameLocation: 'start',
                max: data.rainfallMax,
                type: 'value',
                inverse: true
            }
        ],
        series: [
            {
                name:'水位',
                type:'line',
                animation: true,
                smooth: true,
                symbol: 'circle',
                symbolSize: 1,
                itemStyle:{
                    normal:{
                        color: '#ffff00',
                    }
                },
                lineStyle: {
                    normal: {
                        color: '#ffff00',
                        width: 2,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 6,
                        shadowOffsetY: 6
                    }
                },
                data: data.Z
            },
            {
                name:'降雨量',
                type:'bar',
                itemStyle:{
                    normal:{
                        color:'#7EC0EE',

                    }
                },
                // barWidth: '40%',
                yAxisIndex:1,
                animation: true,
                data: data.P
            }
        ]
    };
    var option2 = {
        title: {
            text: data.stname + '洪水过程',
            x: 'center',
            align: 'right'
        },
        grid: {
            bottom: 80
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
            }
        },
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                animation: false,
                label: {
                    backgroundColor: '#505765'
                }
            }
        },
        legend: {
            data:['降雨量','预报入库','实测入库','出库'],
            x: 'center',
            top: '30px'
        },
        dataZoom: [
            {
                show: false,
                realtime: true,
                // start: 65,
                // end: 85
            },
            {
                type: 'inside',
                realtime: true,
                start: 65,
                end: 85
            }
        ],
        xAxis: [
            {
                type: 'category',
                boundaryGap : true,
                axisLine: {onZero: false},
                data: data.timeArr.map(function (str) {
                    return str.replace('-', '/').replace('-', '/').replace(' ', '\n')
                })
            }
        ],
        yAxis: [
            {
                name: '流量(m³/s)',
                type: 'value',
                max: data.yMax,
                min: data.yMin
            },
            {
                name: '降雨量(mm)',
                nameLocation: 'start',
                max: data.rainfallMax,
                type: 'value',
                inverse: true
            }
        ],
        series: [
            {
                name:'预报入库',
                type:'line',
                animation: true,
                smooth: true,
                symbol: 'circle',
                symbolSize: 1,
                itemStyle:{
                    normal:{
                        color: data.forecastColor,
                    }
                },
                lineStyle: {
                    normal: {
                        color: data.forecastColor,
                        width: 2,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 6,
                        shadowOffsetY: 6
                    }
                },
                data: data.QTRR
            },
            {
                name:'实测入库',
                type:'line',
                animation: true,
                smooth: true,
                symbol: 'circle',
                symbolSize: 1,
                itemStyle:{
                    normal:{
                        color: '#0000ff',
                    }
                },
                lineStyle: {
                    normal: {
                        color: '#0000ff',
                        width: 2,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 6,
                        shadowOffsetY: 6
                    }
                },
                data: data.INQ
            },
            {
                name:'出库',
                type:'line',
                animation: true,
                smooth: true,
                symbol: 'circle',
                symbolSize: 1,
                itemStyle:{
                    normal:{
                        color: '#00ff00',
                    }
                },
                lineStyle: {
                    normal: {
                        color: '#00ff00',
                        width: 2,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 6,
                        shadowOffsetY: 6
                    }
                },
                data: data.OQ
            },
            {
                name:'降雨量',
                type:'bar',
                itemStyle:{
                    normal:{
                        color:'#7EC0EE',

                    }
                },
                // barWidth: '40%',
                yAxisIndex:1,
                animation: true,
                data: data.P
            }
        ]
    };
    chart1.setOption(option1);
    chart2.setOption(option2);
}

function draw3(data) {
    var color = ['#ff0000', '#ffff00', '#00ff00', '#0000ff', '#ff00ff'];
    var chart3 = echarts.init(document.getElementById('chart3'));
    var option3 = {
        title: {
            text: data.stname + '输入贡献',
            x: 'center',
            align: 'right'
        },
        grid: {
            bottom: 80
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
            }
        },
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                animation: false,
                label: {
                    backgroundColor: '#505765'
                }
            }
        },
        legend: {
            data:['降雨量',data.stname,'区间'],
            x: 'center',
            top: '30px'
        },
        dataZoom: [
            {
                show: false,
                realtime: true,
                //  start: 65,
                // end: 85
            },
            {
                type: 'inside',
                realtime: true,
                start: 65,
                end: 85
            }
        ],
        xAxis: [
            {
                type: 'category',
                boundaryGap : true,
                axisLine: {onZero: false},
                data: data.timeArr.map(function (str) {
                    return str.replace('-', '/').replace('-', '/').replace(' ', '\n')
                })
            }
        ],
        yAxis: [
            {
                name: '流量',
                type: 'value',
                max: data.riverMax,
                min: data.riverMin
            },
            {
                name: '降雨量(mm)',
                nameLocation: 'start',
                max: data.rainfallMax,
                type: 'value',
                inverse: true
            }
        ],
        series: [
            {
                name:'降雨量',
                type:'bar',
                itemStyle:{
                    normal:{
                        color:'#7EC0EE',

                    }
                },
                // barWidth: '40%',
                yAxisIndex:1,
                animation: true,
                data: data.P
            },
            {
                name: data.stname,
                type:'line',
                animation: true,
                smooth: true,
                symbol: 'circle',
                symbolSize: 1,
                itemStyle:{
                    normal:{
                        color: '#ff00aa',
                    }
                },
                lineStyle: {
                    normal: {
                        color: '#ff00aa',
                        width: 2,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 6,
                        shadowOffsetY: 6
                    }
                },
                data: data.QTRR
            },
            {
                name: '区间',
                type:'line',
                animation: true,
                smooth: true,
                symbol: 'circle',
                symbolSize: 1,
                itemStyle:{
                    normal:{
                        color: '#ffaa00',
                    }
                },
                lineStyle: {
                    normal: {
                        color: '#ffaa00',
                        width: 2,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 6,
                        shadowOffsetY: 6
                    }
                },
                data: data.selfQTRR
            }
        ]
    };
    for(var i=0; i<data.listQT.length; i++){
        var d = data.listQT[i];
        var t = {
            name: d.stname,
            type:'line',
            animation: true,
            smooth: true,
            symbol: 'circle',
            symbolSize: 1,
            itemStyle:{
                normal:{
                    color: color[i%color.length],
                }
            },
            lineStyle: {
                normal: {
                    color: color[i%color.length],
                    width: 2,
                    shadowColor: 'rgba(0,0,0,0.4)',
                    shadowBlur: 6,
                    shadowOffsetY: 6
                }
            },
            data: d.QT
        }
        option3.series.push(t);
        option3.legend.data.push(d.stname);
    }
    chart3.setOption(option3);
}