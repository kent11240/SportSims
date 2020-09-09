<%@page import="java.util.ArrayList"%>
<%@page import="uuu.sportsims.domain.Basketball"%>
<%@page import="java.util.List"%>
<%@page import="uuu.sportsims.model.BasketballService"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="uuu.sportsims.domain.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Member member = (Member) session.getAttribute("user");
    List<Basketball> selectGameList = (ArrayList) session.getAttribute("gameList");
    List<String> selectItemList = (ArrayList) session.getAttribute("itemList");
    DecimalFormat df = new DecimalFormat("#,##0");
    DecimalFormat df2 = new DecimalFormat("#0.00");
    BasketballService service = new BasketballService();
    List<Basketball> list = service.getAllByStatus(Basketball.Status.UNSTARTED);
%>
<jsp:include page="/WEB-INF/subviews/header.jsp"/>
<link type="text/css" rel="stylesheet" href="<%=application.getContextPath()%>/css/content_2.css"/>
<link type="text/css" rel="stylesheet" href="<%=application.getContextPath()%>/css/content_2_table_1.css"/>
<div class="content">
    <div class="contentLeft">
        <%if (list.size() == 0) {%>
        <span>目前無可下注之賽事</span>
        <%}%>
        <%for (Basketball game : list) {%>
        <div>
            <table class="gameList">
                <caption><%=game.getDateTimeString()%><br/><%=game.getAwayName()%> @ <%=game.getHomeName()%></caption>
                <tr><th>讓分 <b>主<%=game.getPointSpread()%></b></th><td>客 <b><%=df2.format(game.getAway())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_away"/></td><td>主 <b><%=df2.format(game.getHome())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_home"/></td><th>大小 <b><%=game.getTotal()%></b></th><td>大 <b><%=df2.format(game.getOver())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_over"/></td><td>小 <b><%=df2.format(game.getUnder())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_under"/></td></tr>
                <tr><th>不讓分</th><td>客 <b><%=df2.format(game.getAwayPk())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_awayPk"/></td><td>主 <b><%=df2.format(game.getHomePk())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_homePk"/></td><th>單雙</th><td>單 <b><%=df2.format(game.getOdd())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_odd"/></td><td>雙 <b><%=df2.format(game.getEven())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_even"/></td></tr>
                <tr><th>第一節不讓分</th><td>客 <b><%=df2.format(game.getAwayQ1())%></b> <img src="images/add.png" alt="" class="add"  id="<%=game.getGameId()%>_awayQ1"/></td><td>和 <b><%=df2.format(game.getDrawQ1())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_drawQ1"/></td><td>主 <b><%=df2.format(game.getHomeQ1())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_homeQ1"/></td></tr>
                <tr><th>勝分差-客</th><td>1-5 <b><%=df2.format(game.getAway1to5())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_away1to5"/></td><td>6-10 <b><%=df2.format(game.getAway6to10())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_away6to10"/></td><td>11-15 <b><%=df2.format(game.getAway11to15())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_away11to15"/></td></tr>
                <tr><td></td><td>16-20 <b><%=df2.format(game.getAway16to20())%></b> <img src="images/add.png" alt="" class="add"  id="<%=game.getGameId()%>_away16to20"/></td><td>21-25 <b><%=df2.format(game.getAway21to25())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_away21to25"/></td><td>26+ <b><%=df2.format(game.getAway26())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_away26"/></td></tr>
                <tr><th>勝分差-主</th><td>1-5 <b><%=df2.format(game.getHome1to5())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_home1to5"/></td><td>6-10 <b><%=df2.format(game.getHome6to10())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_home6to10"/></td><td>11-15 <b><%=df2.format(game.getHome11to15())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_home11to15"/></td></tr>
                <tr><td></td><td>16-20 <b><%=df2.format(game.getHome16to20())%></b> <img src="images/add.png" alt="" class="add"  id="<%=game.getGameId()%>_home16to20"/></td><td>21-25 <b><%=df2.format(game.getHome21to25())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_home21to25"/></td><td>26+ <b><%=df2.format(game.getHome26())%></b> <img src="images/add.png" alt="" class="add" id="<%=game.getGameId()%>_home26"/></td></tr>
            </table>
        </div>
        <%}%>
    </div>
    <div class="contentRight">
        <div class="memberState">
            <fieldset>
                <legend><img src="<%=application.getContextPath()%>/images/info.png" alt="" id="info"/> 個人資訊</legend>
                    <%if (member == null) {%>
                請先<a href="<%=application.getContextPath()%>/login.jsp">登入</a>
                <%} else {%>
                您好, <%=member.getNickname()%><br/>
                剩餘金額: <%=df.format((long) member.getMoney())%>
                <%}%>
            </fieldset>
        </div>
        <div class="lottery">
            <fieldset id="lottery">
                <legend><img src="<%=application.getContextPath()%>/images/money.png" alt="" id="money"/> 投注區</legend>
                <p id="errorMsg"></p>
                <%if (selectGameList == null || selectGameList.size() == 0) {%>
                <span id="unselected">尚未選擇賽事</span>
                <%} else {%>
                <%for (int i = 0; i < selectGameList.size(); i++) {%>
                <%
                    Basketball game = selectGameList.get(i);
                    String item = selectItemList.get(i);
                %>
                <p><span><%=game.getAwayName()%>@<%=game.getHomeName()%></span><br/><span><%=service.getItemToString(item)%><%=item.equals("away") || item.equals("home") ? " 主<b>" + game.getPointSpread() + "</b>" : item.equals("over") || item.equals("under") ? " <b>" + game.getTotal() + "</b>" : ""%> <b><%=game.getOddsByItem(item)%></b></span></p>
                    <%}
                        }%>
            </fieldset>
            <span id="clean"><img src="<%=application.getContextPath()%>/images/clean.png" alt=""/>清除全部</span>
            <a href="<%=application.getContextPath()%>/user/bet_detail.jsp"><span id="bet"><img src="<%=application.getContextPath()%>/images/bet.png" alt=""/> 下注</span></a>
        </div>
    </div>
</div>
<script>
    $(".add").click("click", addHandler);
    $("#clean").click("click", cleanHandler);
    function addHandler() {
        $.ajax({
            method: "POST",
            data: {data: $(this).attr("id")},
            url: "<%=application.getContextPath()%>/add_lottery.do",
            statusCode: {500: function () {
                    $("#errorMsg").html("");
                    $("#errorMsg").append("同場賽事不得重複選擇！");
                },
                501: function () {
                    $("#errorMsg").html("");
                    $("#errorMsg").append("最多投注八場！");
                }}
        }).done(addDoneHandler);
    }
    function addDoneHandler(data) {
        $("#unselected").html("");
        $("#errorMsg").html("");
        $("#lottery").append(data);
    }
    function cleanHandler() {
        $.ajax({
            method: "POST",
            url: "<%=application.getContextPath()%>/clean_lottery.do"
        }).done(cleanDoneHandler);
    }
    function cleanDoneHandler() {
        $("#lottery").empty();
        $("#lottery").html('<legend><img src="<%=application.getContextPath()%>/images/money.png" alt="" id="money"/> 投注區</legend>');
        $("#lottery").append('<p id="errorMsg"></p>');
        $("#lottery").append('<span id="unselected">尚未選擇賽事</span>');
    }
</script>
<%@include file="/WEB-INF/subviews/footer.jsp"%>
