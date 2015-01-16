<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="header.jsp"></jsp:include>
<body>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text"/>
<c:if test="${role == 'admin'}">
    <jsp:include page="admin/adminNavBar.jsp"/>
</c:if>
<c:if test="${role == 'client'}">
    <jsp:include page="client/clientNavBar.jsp"/>
</c:if>
<div class="container">
    <div class="row">
        <div class="span6 offset3 log">

            <form action="userInfoChange" name="userInfoChange" method="post">
                <table border="0" class="table">
                    <tr>
                        <td><fmt:message key="login"/>:</td>
                        <td><input type="text" name="firstName" value="${firstName}" class="span4"></td>
                    </tr>


                    <tr>
                        <td><fmt:message key="email"/> :</td>
                        <td><input type="text" name="email" value="${email}" class="span4"></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="password"/>:</td>
                        <td><input type="text" name="password" value="" class="span4"></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="repeatPass"/>:</td>
                        <td><input type="text" name="repeatPassword" value="" class="span4"></td>
                    </tr>
                    <tr><fmt:message key="enterOldPass"/> </tr>
                    <tr>
                        <td><fmt:message key="oldPass"/> :</td>
                        <td><input type="text" name="oldPass" value="" class="span4"></td>
                    </tr>

                </table>
                <div class="btn-group span6 offset2">
                    <input type="submit" value="<fmt:message key="changeInfo"/>" class="btn btn-success">
                    <input type="reset" value="<fmt:message key="reset"/>" class="btn btn-danger">
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
