function draw1(data) {
    echarts.init(document.getElementById('chart')).dispose();
    var chart = echarts.init(document.getElementById('chart'));
    var option = {
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
    chart.setOption(option);
}

function draw2(data) {
    echarts.init(document.getElementById('chart')).dispose();
    var chart = echarts.init(document.getElementById('chart'));
    var option = {
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
            data:['降雨量','预报入库','实测入库','水位','出库'],
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
                name: '数值',
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
                        color: '#ff0000',
                    }
                },
                lineStyle: {
                    normal: {
                        color: '#ff0000',
                        width: 2,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 6,
                        shadowOffsetY: 6
                    }
                },
                data: data.INQ
            },
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
    chart.setOption(option);
}

function draw3(data) {
    var color = ['#ff0000', '#ffff00', '#00ff00', '#0000ff', '#ff00ff'];
    var chart = echarts.init(document.getElementById('chart2'));
    var option = {
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
            data:['降雨量',data.stname+'演算后',data.stname+'演算前'],
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
                name: '数值',
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
                name: data.stname + '演算后',
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
                name: data.stname + '演算前',
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
        option.series.push(t);
        option.legend.data.push(d.stname);
    }
    chart.setOption(option);
}