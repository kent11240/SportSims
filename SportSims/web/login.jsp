<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8" info="登入"%>
<jsp:include page="/WEB-INF/subviews/header.jsp">
    <jsp:param name="subtitle" value="<%=this.getServletInfo()%>" />
</jsp:include>
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
    </div>
    <%}%>
    <div class="center">
        <form method="POST" action="login.do">
            <p>
                <input type="text" id="id" name="id" placeholder="請輸入帳號" minlength="1" maxlength="50" pattern="^[a-zA-Z0-9]\w+$" tabindex="1" value="<%=request.getMethod().equalsIgnoreCase("post") ? request.getParameter("id") : ""%>" required/>
            </p>
            <p>
                <input type="password" id="password" name="password" placeholder="請輸入密碼" minlength="6" maxlength="20" tabindex="2" required/>
            </p>
            <p>
                <input type="text" id="check_code" name="check_code" placeholder="請輸入驗證碼" tabindex="3" required/><br/>
                <img id="check_image" src="images/login_check_code.jpg"> <a href="javascript:refresh()"><img id="refreshIcon" src="images/refresh.png"/></a>
            </p>
            <input type="submit" value="登入"/>
        </form>
    </div>
</div>
<script>
    function refresh() {
        var image = document.getElementById("check_image");
        image.src = "images/login_check_code.jpg?get=" + new Date();
    }
</script>
<%@include file="/WEB-INF/subviews/footer.jsp"%>