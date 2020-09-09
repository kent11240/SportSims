<%@page contentType="text/html" isErrorPage="true" info="系統錯誤"%>
<%@page pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/subviews/header.jsp">
    <jsp:param name="subtitle" value="<%=this.getServletInfo()%>" />
</jsp:include>
<link type="text/css" rel="stylesheet" href="<%=application.getContextPath()%>/css/content_1.css"/>
<div class="content">
    <div class="center">
        系統發生錯誤, 請回報客服。 回<a href="<%=application.getContextPath()%>/index.jsp">首頁</a>
    </div>
</div>
<%@include file="/WEB-INF/subviews/footer.jsp"%>