<%@page import="uuu.sportsims.model.BasketballService"%>
<%@page import="uuu.sportsims.domain.Lottery"%>
<%@page import="uuu.sportsims.model.LotteryService"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="uuu.sportsims.domain.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8" info="彩券明細"%>
<jsp:include page="/WEB-INF/subviews/header.jsp">
    <jsp:param name="subtitle" value="<%=this.getServletInfo()%>" />
</jsp:include>
<%
    DecimalFormat df = new DecimalFormat("#,##0");

    Member member = (Member) session.getAttribute("user");
    //檢查登入(session)
    if (member == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    LotteryService service = new LotteryService();
    Lottery lottery = service.getByKeyAndMember((Integer.parseInt(request.getParameter("show"))), member);
    if (lottery == null) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/error_404.jsp");
        dispatcher.forward(request, response);
        return;
    }

    BasketballService bService = new BasketballService();
%>
<link type="text/css" rel="stylesheet" href="<%=application.getContextPath()%>/css/content_2.css"/>
<link type="text/css" rel="stylesheet" href="<%=application.getContextPath()%>/css/content_2_table_1.css"/>
<div class="content">
    <div class="contentLeft">
        <table class="detail">
            <tr>
                <th>彩券編號</th><td><%=lottery.getLotteryId()%></td>
                <th>下注時間</th><td><%=lottery.getDateTimeString()%></td>
                <th>彩券狀態</th><td><%=lottery.getStatus().getDescription()%></td>
                <th>預估中獎金額</th><td><%=df.format(service.getHighestPrice(lottery))%></td>
            </tr>
            <tr>
                <th>組合數</th><td><%=lottery.getCombination(lottery.getPass())%></td>
                <th>每組合金額</th><td><%=df.format(lottery.getStake())%></td>
                <th>總下注金額</th><td><%=df.format(lottery.getCombination(lottery.getPass()) * lottery.getStake())%></td>
                <th>中獎金額</th><td><%=df.format(lottery.getPrice())%></td>
            </tr>
            <table>
                <tr>
                    <th>賽事編號</th>
                    <th class="teamName">客隊</th>
                    <th class="teamName">主隊</th>
                    <th>項目</th>
                    <th>投注值</th>
                    <th>賠率</th>
                    <th>總賽果</th>
                    <th>第一節賽果</th>
                    <th>結果</th>
                </tr>
                <%for (int i = 0; i < lottery.getSelections(); i++) {%>
                <tr>
                    <td><%=lottery.getGame()[i].getGameId()%></td>
                    <td><%=lottery.getGame()[i].getAwayName()%></td>
                    <td><%=lottery.getGame()[i].getHomeName()%></td>
                    <td><%=bService.getItemToString(lottery.getItem()[i])%></td>
                    <%if (lottery.getItem()[i].equals("away") || lottery.getItem()[i].equals("home")) {%>
                    <td>主<%=lottery.getGame()[i].getPointSpread() > 0 ? "+" : ""%><%=lottery.getGame()[i].getPointSpread()%></td>
                    <%} else if (lottery.getItem()[i].equals("over") || lottery.getItem()[i].equals("under")) {%>
                    <td><%=lottery.getGame()[i].getTotal()%></td>
                    <%} else {%>
                    <td></td>
                    <%}%>
                    <td> <%=lottery.getGame()[i].getOddsByItem(lottery.getItem()[i])%></td>
                    <td><%=lottery.getGame()[i].getAwayResult()%>:<%=lottery.getGame()[i].getHomeResult()%></td>
                    <td><%=lottery.getGame()[i].getAwayQ1Result()%>:<%=lottery.getGame()[i].getHomeQ1Result()%></td>
                    <td style="<%=bService.checkGamePass(lottery.getGame()[i], lottery.getItem()[i]) == 1 ? "color:red;font-weight:bold;" : ""%>"><%=bService.checkGamePass(lottery.getGame()[i], lottery.getItem()[i]) == 0 ? "未過關" : bService.checkGamePass(lottery.getGame()[i], lottery.getItem()[i]) == 1 ? "已過關" : "尚未派彩"%></td>
                </tr>
                <%}%>
            </table>
        </table>
    </div>
    <div class="contentRight">
        <div class="memberState">
            <fieldset>
                <legend><img src="<%=application.getContextPath()%>/images/info.png" alt="" id="info"/> 個人資訊</legend>
                    <%if (member == null) {%>
                <a href="<%=application.getContextPath()%>/login.jsp"><span>請先登入</span></a>
                <%} else {%>
                您好, <%=member.getNickname()%><br/>
                剩餘金額: <%=df.format((long) member.getMoney())%>
                <%}%>
            </fieldset>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/subviews/footer.jsp"%>