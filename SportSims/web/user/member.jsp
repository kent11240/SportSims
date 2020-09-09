<%@page import="uuu.sportsims.domain.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8" info="會員資料管理"%>
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
<div class="content">
    <div class="center">
        <div class="button">
            <a href="<%=application.getContextPath()%>/user/member_pwd.jsp"><span>修改會員密碼</span></a>
            <a href="<%=application.getContextPath()%>/user/member_info.jsp"><span>修改會員資料</span></a>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/subviews/footer.jsp"%>