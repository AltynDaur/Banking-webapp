<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header.jsp"></jsp:include>
</head>
<body>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text"/>
<div class="container">
    <div class="row">
        <div class="span4 offset4 log">
            <legend><fmt:message key="registration"/> </legend>
            <form action="register" name="register" method="post">
                <input type="text" name="firstName" value="" placeholder="<fmt:message key="login"/>" class="span4">
                <input type="text" name="password" value="" placeholder="<fmt:message key="password"/>" class="span4">
                <input type="text" name="repeatPass" value="" placeholder="<fmt:message key="repeatPass"/>" class="span4">
                <input type="text" name="email" value="" placeholder="<fmt:message key="email"/>" class="span4">

                <div class="btn-group span4">
                    <input type="submit" value="<fmt:message key="register"/>" class="btn btn-success">
                    <input type="reset" value="<fmt:message key="reset"/>" class="btn btn-danger">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
