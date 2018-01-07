<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String serverPort = "";
    if(request.getServerPort() != 80) {
        serverPort = ":" + request.getServerPort();
    }
    String serverPath = request.getScheme() + "://" + request.getServerName() + serverPort + path + "/";
    String basePath = serverPath;
    if(application.getAttribute("basePath") != null && application.getAttribute("basePath") != "") {
        basePath = application.getAttribute("basePath").toString();
    }
    pageContext.setAttribute("basePath", basePath);
    pageContext.setAttribute("serverPath", serverPath);
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>管理员登录界面</title>
    <!-- Custom Theme files -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="${pageContext.request.contextPath}/js/admin/login.js"></script>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- Custom Theme files -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords"
          content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <script type="text/javascript">
        window.Constants = {
            "basePath" : "${basePath}",
            "baseResoucePath" : "${baseResoucePath}",
            "serverPath" : "${serverPath}",
            "sourceVersion" : "${sourceVersion }"
        };
    </script>
</head>

<body>
<div class="login">
    <h2>管理员登录</h2>
    <div class="login-top">
        <form>
            <label>用户名:</label>
            <input type="text" value="" id="username">
            <label>密码:</label>
            <input type="password" value="" id="password">
        </form>
        <div class="forgot">
            <input type="submit" value="登录" id="submit">
        </div>
    </div>
</div>
</body>
</html>