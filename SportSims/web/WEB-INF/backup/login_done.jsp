<%@page import="uuu.sportsims.domain.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Member member = (Member) request.getAttribute("user");%>
<!DOCTYPE html>
<html>
    <head>
        <title>sportSims-Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link type="text/css" rel="stylesheet" href="./css/main.css"/>
        <link type="text/css" rel="stylesheet" href="./css/content_1.css"/>
    </head>
    <body>
        <div class="main">
            <a href="index.jsp"><img src="images/logo.png" id="logo"/></a>
            <ul>
                <a href="index.jsp"><li>賽事列表</li></a>
                <a href="lottery.html"><li>查詢彩券</li></a>
                <a href="member.html"><li>會員資料管理</li></a>
            </ul>
            <span>
                <a href="register.jsp">註冊</a> 
                <a href="login.jsp">登入</a> 
                <a href="logout.html">登出</a>
            </span>
        </div>
        <div class="content">
            <div class="center">
                <p>登入成功, <%=member != null ? member.getNickname() : ""%>(<%=member != null ? member.getId() : ""%>)</p>
            </div>
        </div>
        <div class="footer">Copyright &COPY; 2016 sportSims. All rights reserved.</div>
    </body>
</html>