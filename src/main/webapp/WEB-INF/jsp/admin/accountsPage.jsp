<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../header.jsp"/>
<body>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text"/>
<jsp:include page="adminNavBar.jsp"/>
<c:choose>
    <c:when test="${action == 'add'}">
        <div class="container" style="margin-top: 80px">
            <div class="span6 offset2">
                <form action="controller/admin/addAccount" method="post">
                    <table class="table">
                        <tr>
                            <td><fmt:message key="owner"/></td>
                            <td><input type="text" name="userName" value=""></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="accTypeName"/></td>
                            <td><input type="text" name="accTypeName" value=""></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="amount"/></td>
                            <td><input type="text" name="amount" value=""></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <div class="btn-group span6">
                                    <input type="submit" value="<fmt:message key="add"/>" class="btn btn-success">
                                    <input type="reset" value="<fmt:message key="reset"/>" class="btn btn-danger">
                                </div>
                            </td>
                        </tr>
                    </table>


                </form>
            </div>
        </div>
    </c:when>
    <c:when test="${action == 'update'}">
        <div class="container" style="margin-top: 80px">
            <form action="controller/admin/updateAccount" method="post">
                <input type="hidden" name="accID" value="${accID}">
                <table class="table">
                    <tr>
                        <td><fmt:message key="owner"/></td>
                        <td><input type="text" readonly value="${owner}"></td>

                    </tr>
                    <tr>
                        <td><fmt:message key="accTypeName"/></td>
                        <td><input type="text" readonly value="${accType}"/></td>
                    </tr>
                    <tr>
                        <td><fmt:message key="amount"/></td>
                        <td><input type="text" name="amount" value="${amount}"></td>
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
<jsp:include page="../footer.jsp"/>
</body>
</html>
