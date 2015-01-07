<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../header.jsp"/>
</head>
<body>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text"/>
<jsp:include page="adminNavBar.jsp"/>
<c:choose>
    <c:when test="${action == 'add'}">
        <div class="container" style="margin-top: 80px">
            <form action="controller/admin/addAccType" method="post">
                <table class="table">
                    <tr>
                        <td><fmt:message key="accTypeName"/></td>
                        <td><input type="text" name="accTypeName" value=""></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="percent"/></td>
                        <td><input type="text" name="percent" value=""></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="period"/></td>
                        <td><input type="text" name="period" value=""></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="currency"/></td>
                        <td><select name="currency">
                            <option value="dollar"><fmt:message key="dollar"/></option>
                            <option value="euro"><fmt:message key="euro"/></option>
                            <option value="rusrub"><fmt:message key="rusrub"/></option>
                            <option value="kazten"><fmt:message key="kazten"/></option>
                        </select></td>
                    </tr>
                </table>
                <div class="btn-group span6 offset2">
                    <input type="submit" value="<fmt:message key="add"/>" class="btn btn-success">
                    <input type="reset" value="<fmt:message key="reset"/>" class="btn btn-danger">
                </div>

            </form>
        </div>
    </c:when>
    <c:when test="${action == 'update'}">
        <div class="container" style="margin-top: 80px">
            <form action="controller/admin/updateAccType" method="post">
                <input type="hidden" name="accTypeID" value="${accTypeID}">
                <table class="table">
                    <tr>
                        <td><fmt:message key="accTypeName"/></td>
                        <td><input type="text" name="accTypeName" value="${accTypeName}"></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="percent"/></td>
                        <td><input type="text" name="percent" value="${percent}"></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="period"/></td>
                        <td><input type="text" name="period" value="${period}"></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="currency"/></td>
                        <td><select name="currency">
                            <option value="dollar"><fmt:message key="dollar"/></option>
                            <option value="euro"><fmt:message key="euro"/></option>
                            <option value="rusrub"><fmt:message key="rusrub"/></option>
                            <option value="kazten"><fmt:message key="kazten"/></option>
                        </select></td>
                    </tr>
                </table>
                <div class="btn-group span6 offset2">
                    <input type="submit" value="<fmt:message key="update"/>" class="btn btn-success">
                    <input type="reset" value="<fmt:message key="reset"/>" class="btn btn-danger">
                </div>

            </form>
        </div>
    </c:when>
    <c:otherwise>
        <fmt:message key="${message}"/>
    </c:otherwise>
</c:choose>
</body>
</html>
