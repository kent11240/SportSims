<%@page import="uuu.sportsims.model.LotteryService"%>
<%@page import="uuu.sportsims.domain.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Member member = (Member) session.getAttribute("user");
    LotteryService lService = new LotteryService();
    if (member != null) {
        lService.claimLottery(member, lService.getAllByMember(member)); //每次載入header都檢查該登入的會員所有彩券狀態
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>sportSims<%= request.getParameter("subtitle") != null ? '-' + request.getParameter("subtitle") : ""%></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="<%=application.getContextPath()%>/js/jquery-1.12.1.min.js" type="text/javascript"></script>
        <link type="text/css" rel="stylesheet" href="<%=application.getContextPath()%>/css/main.css"/>
    </head>
    <body>
        <div class="main">
            <a href="<%=application.getContextPath()%>/index.jsp"><img src="<%=application.getContextPath()%>/images/logo.png" id="logo"/></a>
            <ul>
                <a href="<%=application.getContextPath()%>/index.jsp"><li>賽事列表</li></a>
                <a href="<%=application.getContextPath()%>/user/lottery.jsp"><li>查詢彩券</li></a>
                <a href="<%=application.getContextPath()%>/user/member.jsp"><li>會員資料管理</li></a>
            </ul>
            <span>
                <%=member != null ? member.getNickname() : ""%> <%=member != null ? '(' + member.getId() + ')' : ""%>
                <%if (member == null) {%>
                <a href="<%=application.getContextPath()%>/register.jsp">註冊</a> 
                <a href="<%=application.getContextPath()%>/login.jsp">登入</a> 
                <%} else {%>
                <a href="<%=application.getContextPath()%>/user/logout.do">登出</a>
                <%}%>
            </span>
        </div>