<%@page import="uuu.sportsims.model.BasketballService"%>
<%@page import="uuu.sportsims.domain.Basketball"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="uuu.sportsims.domain.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8" info="下注明細"%>
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

    List<Basketball> selectGameList = (ArrayList) session.getAttribute("gameList");
    List<String> selectItemList = (ArrayList) session.getAttribute("itemList");
    if (selectGameList == null || selectGameList.size() == 0) { //沒有選擇賽事回首頁
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }

    BasketballService bService = new BasketballService();
%>
<link type="text/css" rel="stylesheet" href="<%=application.getContextPath()%>/css/content_2.css"/>
<link type="text/css" rel="stylesheet" href="<%=application.getContextPath()%>/css/content_form.css"/>
<link type="text/css" rel="stylesheet" href="<%=application.getContextPath()%>/css/content_2_table_1.css"/>
<div class="content">
    <div class="contentLeft" style="text-align: left;">
        <%List<String> errors = (List<String>) request.getAttribute("errors");%>
        <%if (errors != null && errors.size() > 0) {%>
        <div class="error">
            <ul>
                <%for (String msg : errors) {%>
                <li><%=msg%></li><%}%>
            </ul>
        </div>
        <%}%>
        <form method="POST" action="bet.do">
            <p>
                <label for="pass">過關組合：</label>
                <%for (int i = 0; i < selectGameList.size(); i++) {%>
                <input type="checkbox" class="pass" id="<%=i + 1%>" name="pass<%=i + 1%>" autocomplete="off"/><label for="pass<%=i + 1%>">過<%=i + 1%>關</label>
                <%}%>
            </p>
            <p>
                <label>總組合數：</label>
                <span id="combs"></span>
            </p>
            <p>
                <label for="stake">每注金額：10元x</label>
                <input type="text" id="stake" name="stake" value="" autocomplete="off" required/>
            </p>
            <p>
                <label>總額：</label>
                <span id="total"></span>
            </p>
            <p>
                <label>預估最高中獎金額：</label>
                <span id="price"></span>
            </p>
            <table>
                <tr>
                    <th>賽事編號</th>
                    <th>開賽時間</th>
                    <th>客隊</th>
                    <th>主隊</th>
                    <th>項目</th>
                    <th>投注值</th>
                    <th>賠率</th>
                </tr>
                <%for (int i = 0; i < selectGameList.size(); i++) {%>
                <%
                    Basketball game = selectGameList.get(i);
                    String item = selectItemList.get(i);
                %>
                <tr>
                    <td><%=game.getGameId()%></td>
                    <td><%=game.getDateTimeString()%></td>
                    <td><%=game.getAwayName()%></td>
                    <td><%=game.getHomeName()%></td>
                    <td><%=bService.getItemToString(item)%></td>
                    <%if (item.equals("away") || item.equals("home")) {%>
                    <td>主<%=game.getPointSpread() > 0 ? "+" : ""%><%=game.getPointSpread()%></td>
                    <%} else if (item.equals("over") || item.equals("under")) {%>
                    <td><%=game.getTotal()%></td>
                    <%} else {%>
                    <td></td>
                    <%}%>
                    <td class="odds"><%=game.getOddsByItem(item)%></td>
                </tr>
                <%}%>
            </table>
            <p style="text-align: center;margin-top:50px">
                <input type="submit" value="確認下注"/>
            </p>
        </form>
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
<script>
    $(document).ready(function () {
        $(window).keydown(function (event) {
            if (event.keyCode == 13) {
                event.preventDefault();
                return false;
            }
        });
    });
    $("#stake").change(stakeHandler);
    $(".pass").click(passHandler);
    function stakeHandler() {
        if (!/^\d+$/.test($("#stake").val())) {
            $("#stake").val("");
            $("#total").html("");
        }
        passHandler();
    }
    function passHandler() {
        var selectionsStr = "<%=selectGameList.size()%>";
        var stakeStr = $("#stake").val() * 10;
        var passStr = "";
        for (var i = 0; i < $(".pass:checked").length; i++) {
            passStr += $(".pass:checked").eq(i).attr("id");
            if (i !== $(".pass:checked").length - 1) {
                passStr += "_";
            }
        }
        var oddsStr = "";
        for (var i = 0; i < $(".odds").length; i++) {
            oddsStr += $(".odds").eq(i).html();
            if (i !== $(".odds").length - 1) {
                oddsStr += "_";
            }
        }
        $.ajax({
            method: "POST",
            data: {selectionsStr: selectionsStr, passStr: passStr},
            url: "<%=application.getContextPath()%>/comb_cal.do"
        }).done(combDoneHandler);
        $.ajax({
            method: "POST",
            data: {selectionsStr: selectionsStr, stakeStr: stakeStr, passStr: passStr, oddsStr: oddsStr},
            url: "<%=application.getContextPath()%>/price_cal.do"
        }).done(passDoneHandler);
    }
    function passDoneHandler(price) {
        $("#price").html(price);
    }
    function combDoneHandler(combs) {
        console.log(combs);
        $("#combs").html(combs);
        $("#total").html(($("#stake").val() * 10 * combs + "").replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,"));
    }
</script>
<%@include file="/WEB-INF/subviews/footer.jsp"%>