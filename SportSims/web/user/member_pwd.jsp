<%@page import="uuu.sportsims.domain.Member"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8" info="修改會員密碼"%>
<jsp:include page="/WEB-INF/subviews/header.jsp">
    <jsp:param name="subtitle" value="<%=this.getServletInfo()%>" />
</jsp:include>
<%
    Member member = (Member) session.getAttribute("user");
    //檢查登入(session)
    if (member == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<link type="text/css" rel="stylesheet" href="<%=application.getContextPath()%>/css/content_1.css"/>
<link type="text/css" rel="stylesheet" href="<%=application.getContextPath()%>/css/content_form.css"/>
<div class="content">
    <%List<String> errors = (List<String>) request.getAttribute("errors");%>
    <%if (errors != null && errors.size() > 0) {%>
    <div class="error">
        <ul>
            <%for (String msg : errors) {%>
            <li><%=msg%></li><%}%>
        </ul>
    </div><%}%>
    <div class="center">
        <form method="POST" action="update_pwd.do">
            <p>
                <input type="text" id="id" name="id" value="<%=member.getId()%>" required disabled/>
            </p>
            <p>
                <input type="password" id="nickname" name="passwordo" placeholder="請輸入原密碼" minlength="6" maxlength="20" required/>
            </p>
            <p>
                <input type="password" id="password" name="password" placeholder="請輸入新密碼" minlength="6" maxlength="20" required/>
            </p>
            <p>
                <input type="password" id="passwordc" name="passwordc" placeholder="確認新密碼" minlength="6" maxlength="20" required/>
            </p>
            <input type="submit" value="送出修改"/>
        </form>
    </div>
</div>
<%@include file="/WEB-INF/subviews/footer.jsp"%>