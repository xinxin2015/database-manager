<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="mobile-agent" content="format=xhtml; url=http://m.8684.cn/qingdao_bus">
    <title>青岛公交查询_青岛公交车线路查询_青岛公交路线查询_青岛公交</title>
    <meta name="keywords" content="青岛公交,青岛公交路线,青岛公交查询,青岛公交网"/>
    <meta name="description" content="青岛公交线路查询,青岛公交地图,青岛公交查询,青岛公交路线查询,青岛公交网,青岛公交车路线查询,青岛公交车线路查询,青岛坐车网,青岛公交查询系统。"/>
    <meta name="applicable-device" content="pc"/>
    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" type="text/css" href="http://css.8684.cn/citys/v2/css/style.min.css"/>
    <script type="text/javascript" src="http://js.8684.cn/citys/v2/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/140.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base.min.js"></script>
</head>
<body></body>
</div>
<div class="index_banner">
    <div class="banner_content style2">
        <div class="logo_img"><a href="http://www.8684.cn/"><img src="http://css.8684.cn/citys/v2/images/logo.png"/></a>
        </div>
        <h1 class="banner_txt">青岛公交查询</h1><span class="city_show" id="city_show"><span class="city_name">青岛</span><span
            class="city_switch">[切换城市]</span></span>
        <div class="city_layer" id="city_layer">
            <div class="hot_city">
                <div class="hot_city_txt">热门城市<span class="city_close">x</span></div>
                <div class="hot_city_list"><a id="beijing" href="http://beijing.8684.cn/">北京</a>
                    <a id="guangzhou" href="http://guangzhou.8684.cn/">广州</a>
                    <a id="shenzhen" href="http://shenzhen.8684.cn/">深圳</a>
                    <a id="chengdu" href="http://chengdu.8684.cn/">成都</a>
                    <a id="tianjin" href="http://tianjin.8684.cn/">天津</a>
                    <a id="wuhan" href="http://wuhan.8684.cn/">武汉</a>
                    <a id="hangzhou" href="http://hangzhou.8684.cn/">杭州</a>
                    <a id="shanghai" href="http://shanghai.8684.cn/">上海</a>
                    <a id="nanjing" href="http://nanjing.8684.cn/">南京</a>
                    <a id="suzhou" href="http://suzhou.8684.cn/">苏州</a>
                    <a id="fuzhou" href="http://fuzhou.8684.cn/">福州</a>
                    <a id="xiamen" href="http://xiamen.8684.cn/">厦门</a>
                    <a id="changsha" href="http://changsha.8684.cn/">长沙</a>
                    <a id="chongqing" href="http://chongqing.8684.cn/">重庆</a>
                    <a id="foshan" href="http://foshan.8684.cn/">佛山</a>
                    <a id="dongguan" href="http://dongguan.8684.cn/">东莞</a>
                    <a id="hefei" href="http://hefei.8684.cn/">合肥</a>
                    <a id="nanning" href="http://nanning.8684.cn/">南宁</a>
                    <a id="zhengzhou" href="http://zhengzhou.8684.cn/">郑州</a>
                    <a id="zhuhai" href="http://zhuhai.8684.cn/">珠海</a>
                </div>
            </div>
            <div class="city_tab">
                <span id="one1" onclick="setTab('one',1,2)" class="act">按省份</span>
                <span id="one2" onclick="setTab('one',2,2)">按拼音</span>
            </div>
            <div id="con_one_1" class="city_box"></div>
            <div id="con_one_2" class="city_box hidden"></div>
        </div>
    </div>
</div>
<div class="query_module">
    <div id="auto973adid1231"><span class="close_cz" onclick="$(this).parent('div').hide();"></span>
    </div>
    <div id="auto973adid1696">
        <span class="close_cz" onclick="$(this).parent('div').hide();"></span>
    </div>
    <div class="query_module_left">
        <span class="act" id="two1" onclick="setTab('two',1,3)">换乘查询</span>
        <span id="two2" onclick="setTab('two',2,3)">线路查询</span>
        <span id="two3" onclick="setTab('two',3,3)">站点查询</span>
    </div>
    <div class="query_module_right">
        <form action="/so.php" onsubmit="return check_bus_p2p()">
            <input type="hidden" name="k" value="p2p"/>
            <div class="query_results" id="con_two_1">
                <div class="query_text_l">
                    <div class="query_hr">
                        <span class="query_txt_1">出发地</span>
                        <input type="text" id="origin" class="query_input" autocomplete="off" placeholder="请输入起点名称"
                               value="" name="q"/>
                    </div>
                    <div class="query_hr">
                        <span class="query_txt_1">目的地</span>
                        <input type="text" id="destination" class="query_input" autocomplete="off" placeholder="请输入终点名称"
                               value="" name="q1"/>
                    </div>
                </div>
                <div class="query_text_c">
                    <span class="switch" id="switch" onclick="query_tab()"></span>
                </div>
                <div class="query_text_r"><input type="submit" value="查询" class="query_button"/>
                </div>
                <div class="query_text_bottom">
                    <div class="left">
                        <span class="query_txt_1">出发地</span>
                        <dl class="select interval">
                            <dt id="zdfl_1">站点分类</dt>
                        </dl>
                        <dl class="select">
                            <dt id="zdmc_1">站点名称</dt>
                        </dl>
                    </div>
                    <div class="right">
                        <span class="query_txt_1">目的地</span>
                        <dl class="select interval">
                            <dt id="zdfl_2">站点分类</dt>
                        </dl>
                        <dl class="select">
                            <dt id="zdmc_2">站点名称</dt>
                        </dl>
                    </div>
                </div>
            </div>
        </form>
        <form action="/so.php" onsubmit="return check_bus_pp()">
            <input type="hidden" name="k" value="pp"/>
            <div class="query_results hidden" id="con_two_2">
                <div class="route_query">
                    <div class="left">
                        <input type="text" id="origin_1" class="route_query_input" autocomplete="off"
                               placeholder="线路名称，如“B25”" value="" name="q"/>
                        <dl class="select interval">
                            <dt id="xlfl">线路分类</dt>
                        </dl>
                        <dl class="select">
                            <dt id="xlmc">线路名称</dt>
                        </dl>
                    </div>
                    <div class="query_text_r">
                        <input type="submit" value="查询" class="query_button"/>
                    </div>
                </div>
            </div>
        </form>
        <form action="/so.php" onsubmit="return check_bus_p()">
            <input type="hidden" name="k" value="p"/>
            <div class="query_results hidden" id="con_two_3">
                <div class="route_query">
                    <div class="left">
                        <input type="text" id="origin_2" class="route_query_input" autocomplete="off"
                               placeholder="站点名称，如“岗顶”" value="" name="q"/>
                        <dl class="select interval">
                            <dt id="zdfl_3">站点分类</dt>
                        </dl>
                        <dl class="select">
                            <dt id="zdmc_3">站点名称</dt>
                        </dl>
                    </div>
                    <div class="query_text_r">
                        <input type="submit" value="查询" class="query_button"/>
                    </div>
                </div>
            </div>
        </form>
        <ul class="citybusTipul"></ul>
    </div>
</div>
</html>