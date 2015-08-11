<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="header.jsp"></jsp:include>
<body>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text"/>
<jsp:include page="changeLocale.jsp"/>
    <div class="container">
        <div class="row">
            <div class="span4 offset4 log">
                <legend><fmt:message key="autorization"/></legend>
                <form action="controller/login" name="login" method="post">
                    <input type="text" name="firstName" value="" placeholder="<fmt:message key="enterLogin"/>"
                           class="span4">
                    <input type="password" name="password" value="" placeholder="<fmt:message key="enterPass"/>"
                           class="span4">
                    <input type="submit" id="loginBtn" value="<fmt:message key="login"/>" class="btn btn-success span4">
                </form>
                <form action="controller/registerPage" name="registerPage" method="get">
                    <input type="submit" value="<fmt:message key="register"/>" class="btn btn-info span4">
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
