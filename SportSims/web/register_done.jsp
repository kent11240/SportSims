<%@page contentType="text/html" pageEncoding="UTF-8" info="註冊成功"%>
<jsp:include page="/WEB-INF/subviews/header.jsp">
    <jsp:param name="subtitle" value="<%=this.getServletInfo()%>" />
</jsp:include>
<link type="text/css" rel="stylesheet" href="<%=application.getContextPath()%>/css/content_1.css"/>
<div class="content">
    <div class="center">
        <p>註冊成功, 請重新<a href="<%=application.getContextPath()%>/login.jsp">登入</a></p>
    </div>
</div>
<%@include file="/WEB-INF/subviews/footer.jsp"%>