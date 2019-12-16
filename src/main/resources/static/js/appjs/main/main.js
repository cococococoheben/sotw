

var dayFind;
var dayFindValue;

var listMoney;
var expendList;

var appUserName;
var appUserNameValue;

var nameFirst;
var nameSecond;
var nameAll;



$.ajax({
    cache : true,
    type : "GET",
    url : "/user/appUser/listMain",
    data : "",// 你的formid
    async : false,
    error : function(request) {
        parent.layer.alert("Connection error");
    },
    success : function(data) {

        $('#appUserOnLineName').text(data.appUserOnLineName)

        if (data.appUserOnLineValue) {
            $('#appUserOnLineValue').text(data.appUserOnLineValue)
        }else {
            $('#appUserOnLineValue').text("0")
        }


        //今日充值笔数
        $('#todayRechargeName').text(data.todayRechargeName)

        if (data.todayRechargeNum) {
            $('#todayRechargeNum').text(data.todayRechargeNum)
        }else {
            $('#todayRechargeNum').text("0")
        }


        //今天申请提现笔数
        $('#todayWithdrawName').text(data.todayWithdrawName)

        if (data.todayWithdrawNumber) {
            $('#todayWithdrawNumber').text(data.todayWithdrawNumber)
        }else {
            $('#todayWithdrawNumber').text("0")
        }
        //今日申请提现金额
        $('#todayWithdrawSumName').text(data.todayWithdrawSumName)

        if (data.todayWithdrawSumPoints) {
            $('#todayWithdrawSumPoints').text(data.todayWithdrawSumPoints)
        }else {
            $('#todayWithdrawSumPoints').text("0")
        }

        //今天付款金额
        $('#todayRechargePayName').text(data.todayRechargePayName)

        if (data.todayRechargePayNum) {
            $('#todayRechargePayNum').text(data.todayRechargePayNum)
        }else {
            $('#todayRechargePayNum').text("0")
        }


        //今日注册人数
        $('#todayNewUserName').text(data.todayNewUserName)

        if (data.todayNewUserNum) {
            $('#todayNewUserNum').text(data.todayNewUserNum)
        }else {
            $('#todayNewUserNum').text("0")
        }

        //代理人数
        $('#countAgencyName').text(data.countAgencyName)

        if (data.countAgencyNumb) {
            $('#countAgencyNumb').text(data.countAgencyNumb)
        }else {
            $('#countAgencyNumb').text("0")
        }

        //代理人数
        $('#countAppUserName').text(data.countAppUserName)

        if (data.countAppUserNumb) {
            $('#countAppUserNumb').text(data.countAppUserNumb)
        }else {
            $('#countAppUserNumb').text("0")
        }

        $('#treePic').text(data.treePic)

        listMoney=data.moneyList;
        expendList=data.expendList;
        dayFind=data.dayFind;
        dayFindValue=data.dayFindValue;
        appUserNameValue=data.appUserNameValue;
        appUserName=data.appUserName;
        nameFirst=data.nameFirst;
        nameSecond=data.nameSecond;
        nameAll=data.nameAll;
        findMesgFist();
        findMesgSe();

    }
});


function findMesgFist() {
    $('#treeNameDay').text(appUserName)
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:appUserNameValue
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: dayFind,
            axisLabel: {
                show: true,
                textStyle: {
                    fontSize: '10',
                    color: '#1f1f1f'
                }
            }
        },
        yAxis: {
            type: 'value'
        },
        minInterval:1,
        series: [
            {
                name:appUserNameValue,
                type:'line',
                stack: '总量',
                data:dayFindValue,

            }
        ]
    };

    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
}



function findMesgSe() {

    var dom = document.getElementById("container1");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    var posList = [
        'left', 'right', 'top', 'bottom',
        'inside',
        'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
        'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
    ];

    app.configParameters = {
        rotate: {
            min: -90,
            max: 90
        },
        align: {
            options: {
                left: 'left',
                center: 'center',
                right: 'right'
            }
        },
        verticalAlign: {
            options: {
                top: 'top',
                middle: 'middle',
                bottom: 'bottom'
            }
        },
        position: {
            options: echarts.util.reduce(posList, function (map, pos) {
                map[pos] = pos;
                return map;
            }, {})
        },
        distance: {
            min: 0,
            max: 100
        }
    };

    app.config = {
        rotate: 90,
        align: 'left',
        verticalAlign: 'middle',
        position: 'insideBottom',
        distance: 15,
        onChange: function () {
            var labelOption = {
                normal: {
                    rotate: app.config.rotate,
                    align: app.config.align,
                    verticalAlign: app.config.verticalAlign,
                    position: app.config.position,
                    distance: app.config.distance
                }
            };
            myChart.setOption({
                series: [{
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }]
            });
        }
    };


    var labelOption = {
        normal: {
            show: true,
            position: app.config.position,
            distance: app.config.distance,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            rotate: app.config.rotate,
            formatter: '{c}  {name|{a}}',
            fontSize: 16,
            rich: {
                name: {
                    textBorderColor: '#fff'
                }
            }
        }
    };

    option = {
        color: ['#003366', '#e5323e'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: nameAll
        },


        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                axisTick: {show: false},
                data: ['最近一月', '最近一周', '最近一日']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: nameFirst,
                type: 'bar',
                barGap: 0,
                label: labelOption,
                data: listMoney
            },
            {
                name: nameSecond,
                type: 'bar',
                label: labelOption,
                data: expendList
            }
        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    };
}





function findAppUserMess(day) {

    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    $.ajax({
        cache : false,
        type : "GET",
        url : "/user/appUser/listDayFind",
        data : {
            'dayTime':day
        },// 你的formid
        async : $('#day').text(),
        dataType: "json",
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            dayFindValue=data.dayFindValue;
            appUserNameValue=data.appUserNameValue;
            dayFind=data.dayFind;
            appUserName=data.appUserName;
        }
    });


    $('#treeNameDay').text(appUserName)

    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:appUserNameValue
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: dayFind,

            axisLabel: {
                show: true,
                textStyle: {
                    fontSize: '10',
                    color: '#1f1f1f'
                }
            }
        },
        yAxis: {
            type: 'value'
        },

        minInterval:1,
        series: [
            {
                name:appUserNameValue,
                type:'line',
                stack: '总量',
                data:dayFindValue
            }
        ]
    };

    myChart.setOption(option,true)

}

