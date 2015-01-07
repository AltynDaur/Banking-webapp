<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="header.jsp"/>
<body>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<div class="container">
    <div class="row">
        <div class="span4 offset4 log">
            <legend><fmt:message key="error"/> ${errCode}</legend>
            <fmt:message key="${errorMessage}"/>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
