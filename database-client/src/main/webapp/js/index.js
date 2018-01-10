$(function () {

    var pageFun = {

        bindEvent : function () {
            $("#submit_line").click(function () {
                pageFun.submit_line();
            });
            $("#submit_change").click(function () {
                pageFun.submit_change();
            });
            $("#submit_station").click(function () {
                pageFun.submit_station();
            });
        },
        submit_line : function () {
            $("#change_plan").hide();
            $("#bus_site_stop").hide();
            $("#bus_line_site").show();
            $("#bus_line_site").empty();
            var lineName = $.trim($("#origin_1").val());
            if (lineName == "") {
                myAlert("错误提示","线路名称不能为空",null);
                return;
            }
            $.ajax({
                type: "POST",
                url: Constants.serverPath + "op/getLines.do",
                data: {"lineName" : lineName},
                dataType: "json",
                async : false,
                success: function(data){
                    var result = data.data;
                    console.log(result);
                    if (!data.success) {
                        var errorMsg = data.message;
                        myAlert("错误提示",errorMsg,function () {
                            window.location.href = Constants.serverPath + "index.jsp";
                        });
                        return;
                    }
                    var html = "<div class='bus_site_layer'>";
                    var i = 0;
                    for (i = 0;i < result.lines.length;i ++) {
                        html += "<div><i>" + (i + 1) + "</i><a>"
                            + result.lines[i] + "</a></div>";
                        if (i == 24) {
                            html += "</div><div class='bus_site_layer'>";
                        }
                    }
                    html += "</div>";
                    $("#bus_line_site").html(html);
                },
                error : function () {
                    alert("系统错误，请联系管理员");
                }
            });
        },
        submit_change : function () {
            $("#change_plan").show();
            $("#bus_line_site").hide();
            $("#bus_site_stop").hide();
            $("#change_plan").empty();
            var startStation = $.trim($("#origin").val());
            var endStation = $.trim($("#destination").val());
            if (startStation == "") {
                myAlert("错误提示","起点不能为空",null);
                return;
            }
            if (endStation == "") {
                myAlert("错误提示","终点不能为空",null);
                return;
            }
            $.ajax({
                type: "POST",
                url: Constants.serverPath + "op/getChangeLine.do",
                data: {"startStation" : startStation,
                "endStation":endStation},
                dataType: "json",
                async : false,
                success: function(data){
                    var result = data.data;
                    console.log(result);
                    if (!data.success) {
                        var errorMsg = data.message;
                        myAlert("错误提示",errorMsg,function () {
                            window.location.href = Constants.serverPath + "index.jsp";
                        });
                        return;
                    }
                    var list = result.list;
                    console.log(list);
                    var html = "";
                    for (var i = 0;i <list.length;i ++) {
                        html += "<div class='cr_plan_top'><span class='cr_plan_img'>" + "方案" + (i + 1) + "</span>";
                        html += "</div>";
                        html += "<div class='cr_plan_results'>" + "途经" + list[i].count + "个站点</div>";
                        html += "<div class='cr_plan_content'>";
                        html += '<div class="cr_plan_c2">';
                        html += '<div class="busline_star">';
                        html += '<div class="busline_bg"><a class="cr_plan_link1">' + list[i].start.name + '</a></div>';
                        html += '</div>';
                        html += '<div class="busline_line"><a class="cr_plan_link2">' + list[i].line1.name + '</a></div>';
                        html += '<div class="busline_site">';
                        html += '<div class="busline_bg"><a class="cr_plan_link1">' + list[i].rote.name + '</a></div>';
                        html += '</div>';
                        html += '<div class="busline_line"><a class="cr_plan_link2">' + list[i].line2.name + '</a>';
                        html += '</div>';
                        html += '<div class="busline_end">';
                        html += '<div class="busline_bg"><a class="cr_plan_link1">' + list[i].end.name + '</a></div>';
                        html += '</div>';
                        html += '</div>';
                        html += '</div>';
                        html += '<br>';
                    }
                    $("#change_plan").html(html);
                },
                error : function () {
                    alert("系统错误，请联系管理员");
                }
            });
        },

        submit_station : function () {
            $("#change_plan").hide();
            $("#bus_line_site").hide();
            $("#bus_site_stop").show();
            $("#bus_site_stop").empty();
            var stationName = $.trim($("#origin_2").val());
            if (stationName == "") {
                myAlert("错误提示","站点名称不能为空",null);
                return;
            }
            $.ajax({
                type: "POST",
                url: Constants.serverPath + "op/getLinesByStation.do",
                data: {"stationName" : stationName},
                dataType: "json",
                async : false,
                success: function(data){
                    var result = data.data;
                    console.log(result);
                    if (!data.success) {
                        var errorMsg = data.message;
                        myAlert("错误提示",errorMsg,function () {
                            window.location.href = Constants.serverPath + "index.jsp";
                        });
                        return;
                    }
                    var html = "";
                    for (var i = 0;i < result.length;i ++) {
                        html += "<a class=''>" + result[i] + "</a>";
                        html +="<br>";
                    }
                    $("#bus_site_stop").html(html);
                },
                error : function () {
                    alert("系统错误，请联系管理员");
                }
            });
        },
        init : function () {
            $("#bus_site_stop").hide();
        }
    };
    pageFun.init();
    pageFun.bindEvent();
});
(function ($) {
    $.alerts = {
        alert: function (title, message, callback) {
            if (title == null) title = 'Alert';
            $.alerts._show(title, message, null, 'alert', function (result) {
                if (callback) callback(result);
            });
        },

        confirm: function (title, message, callback) {
            if (title == null) title = 'Confirm';
            $.alerts._show(title, message, null, 'confirm', function (result) {
                if (callback) callback(result);
            });
        },


        _show: function (title, msg, value, type, callback) {

            var _html = "";

            _html += '<div id="mb_box"></div><div id="mb_con"><span id="mb_tit">' + title + '</span>';
            _html += '<div id="mb_msg">' + msg + '</div><div id="mb_btnbox">';
            if (type == "alert") {
                _html += '<input id="mb_btn_ok" type="button" value="确定" />';
            }
            if (type == "confirm") {
                _html += '<input id="mb_btn_no" type="button" value="取消" />';
                _html += '<input id="mb_btn_ok" type="button" value="确定" />';
            }
            _html += '</div></div>';

            //必须先将_html添加到body，再设置Css样式
            $("body").append(_html);
            GenerateCss();

            switch (type) {
                case 'alert':

                    $("#mb_btn_ok").click(function () {
                        $.alerts._hide();
                        callback(true);
                    });
                    $("#mb_btn_ok").focus().keypress(function (e) {
                        if (e.keyCode == 13 || e.keyCode == 27) $("#mb_btn_ok").trigger('click');
                    });
                    break;
                case 'confirm':

                    $("#mb_btn_ok").click(function () {
                        $.alerts._hide();
                        if (callback) callback(true);
                    });
                    $("#mb_btn_no").click(function () {
                        $.alerts._hide();
                        if (callback) callback(false);
                    });
                    $("#mb_btn_no").focus();
                    $("#mb_btn_ok, #mb_btn_no").keypress(function (e) {
                        if (e.keyCode == 13) $("#mb_btn_ok").trigger('click');
                        if (e.keyCode == 27) $("#mb_btn_no").trigger('click');
                    });
                    break;
            }
        },
        _hide: function () {
            $("#mb_box,#mb_con").remove();
        }
    }
    // Shortuct functions
    myAlert = function (title, message, callback) {
        $.alerts.alert(title, message, callback);
    }

    myConfirm = function (title, message, callback) {
        $.alerts.confirm(title, message, callback);
    };


    //生成Css
    var GenerateCss = function () {

        $("#mb_box").css({
            width: '100%', height: '100%', zIndex: '99999', position: 'fixed',
            filter: 'Alpha(opacity=60)', backgroundColor: 'black', top: '0', left: '0', opacity: '0.6'
        });

        $("#mb_con").css({
            zIndex: '999999', width: '350px', height: '200px', position: 'fixed',
            backgroundColor: 'White',
        });

        $("#mb_tit").css({
            display: 'block', fontSize: '14px', color: '#444', padding: '10px 15px',
            backgroundColor: '#fff', borderRadius: '15px 15px 0 0',
            fontWeight: 'bold'
        });

        $("#mb_msg").css({
            padding: '20px', lineHeight: '40px', textAlign: 'center',
            fontSize: '18px', color: '#4c4c4c'
        });

        $("#mb_ico").css({
            display: 'block', position: 'absolute', right: '10px', top: '9px',
            border: '1px solid Gray', width: '18px', height: '18px', textAlign: 'center',
            lineHeight: '16px', cursor: 'pointer', borderRadius: '12px', fontFamily: '微软雅黑'
        });

        $("#mb_btnbox").css({margin: '15px 0px 10px 0', textAlign: 'center'});
        $("#mb_btn_ok,#mb_btn_no").css({
            width: '80px',
            height: '30px',
            color: 'white',
            border: 'none',
            borderRadius: '4px'
        });
        $("#mb_btn_ok").css({backgroundColor: '#41a259'});
        $("#mb_btn_no").css({backgroundColor: 'gray', marginRight: '40px'});


        //右上角关闭按钮hover样式
        $("#mb_ico").hover(function () {
            $(this).css({backgroundColor: 'Red', color: 'White'});
        }, function () {
            $(this).css({backgroundColor: '#DDD', color: 'black'});
        });

        var _widht = document.documentElement.clientWidth; //屏幕宽
        var _height = document.documentElement.clientHeight; //屏幕高

        var boxWidth = $("#mb_con").width();
        var boxHeight = $("#mb_con").height();

        //让提示框居中
        $("#mb_con").css({top: (_height - boxHeight) / 2 + "px", left: (_widht - boxWidth) / 2 + "px"});
    }


})(jQuery);