<%@page import="uuu.sportsims.domain.Member"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8" info="修改會員資料"%>
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
        <form method="POST" action="update_info.do">
            <p>
                <input type="text" id="id" name="id" value="<%=member.getId()%>" required disabled/>
            </p>
            <p>
                <input type="password" id="password" name="password" placeholder="請輸入密碼" minlength="6" maxlength="20" required/>
            </p><br/>
            <p>
                <input type="text" id="nickname" name="nickname" placeholder="請輸入暱稱" minlength="1" maxlength="20" value="<%=request.getMethod().equalsIgnoreCase("post") ? request.getParameter("nickname") : member.getNickname()%>" required/>
            </p>
            <p>
                <input type="email" id="email" name="email" placeholder="請輸入E-mail" value="<%=request.getMethod().equalsIgnoreCase("post") ? request.getParameter("email") : member.getEmail()%>" required/>
            </p>
            <p>
                <input type="date" id="birthday" name="birthday" placeholder="請輸入生日, ex:2016-1-1" value="<%=request.getMethod().equalsIgnoreCase("post") ? request.getParameter("birthday") : member.getBirthdayString()%>" required/>
            </p>
            <input type="submit" value="送出修改"/>
        </form>
    </div>
</div>
<%@include file="/WEB-INF/subviews/footer.jsp"%>