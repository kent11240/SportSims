<%@page import="uuu.sportsims.domain.Lottery"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="uuu.sportsims.model.LotteryService"%>
<%@page import="uuu.sportsims.domain.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8" info="查詢彩券"%>
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
    List<Lottery> list = service.getAllByMember(member);
%>
<link type="text/css" rel="stylesheet" href="<%=application.getContextPath()%>/css/content_2.css"/>
<link type="text/css" rel="stylesheet" href="<%=application.getContextPath()%>/css/content_2_table_list.css"/>
<div class="content">
    <div class="contentLeft">
        <table>
            <tr>
                <th>彩券編號</th>
                <th>下注時間</th>
                <th>組合數</th>
                <th>每組合金額</th>
                <th>總下注金額</th>
                <th>中獎金額</th>
                <th>彩券狀態</th>
                <th>查看明細</th>
            </tr>
            <%for (Lottery lottery : list) {%>
            <tr>
                <td><%=lottery.getLotteryId()%></td>
                <td><%=lottery.getDateTimeString()%></td>
                <td><%=lottery.getCombination(lottery.getPass())%></td>
                <td><%=df.format(lottery.getStake())%></td>
                <td><%=df.format(lottery.getCombination(lottery.getPass()) * lottery.getStake())%></td>
                <td><%=df.format(lottery.getPrice())%></td>
                <td<%=lottery.getStatus() == Lottery.Status.WIN ? " style='color:red;font-weight:bold;'" : ""%>><%=lottery.getStatus().getDescription()%></td>
                <td><a href="<%=application.getContextPath()%>/user/lottery_detail.jsp?show=<%=lottery.getLotteryId()%>">查看</a></td>
            </tr>
            <%}%>
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