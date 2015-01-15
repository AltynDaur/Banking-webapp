<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

            <div class="span4 offset4">

                <form action="controller/admin/addUser" method="post">
                    <table class="table">
                        <tr>
                            <td><fmt:message key="login"/></td>
                            <td><input type="text" name="userName" value=""></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="password"/></td>
                            <td><input type="text" name="password" value=""></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="email"/></td>
                            <td><input type="text" name="email" value=""></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="role"/></td>
                            <td><select name="role">
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role}">${role}</option>
                                </c:forEach>
                            </select></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <div class="btn-group">
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


            <div class="span4 offset4">

                <form action="controller/admin/updateUser" method="post">
                    <input type="hidden" name="userID" value="${user.getId()}">
                    <input type="hidden" name="currentPass" value="${user.getPassword()}">
                    <input type="hidden" name="role" value="${user.getRole()}">
                    <table>
                        <tr>
                            <td><fmt:message key="login"/></td>
                            <td><input type="text" name="userName" value="${user.getName()}"></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="password"/></td>
                            <td><input type="text" name="password" value="${user.getPassword()}"></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="email"/></td>
                            <td><input type="text" name="email" value="${user.getEmail()}"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <div class="btn-group">
                                    <input type="submit" value="<fmt:message key="update"/>" class="btn btn-success">
                                    <input type="reset" value="<fmt:message key="reset"/>" class="btn btn-danger">
                                </div>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>


        </div>
    </c:when>
    <c:otherwise>
        <fmt:message key="${message}"/>
    </c:otherwise>
</c:choose>

<jsp:include page="../footer.jsp"/>
</body>
</html>
