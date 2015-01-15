<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body bgcolor="#6495ed">
<jsp:include page="../header.jsp"></jsp:include>

<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text"/>
<jsp:include page="adminNavBar.jsp"/>
<jsp:include page="../changeLocale.jsp"/>
<c:if test="${not empty message}">
    <fmt:message key="${message}"/>
</c:if>

<c:choose>

    <c:when test="${not empty requestScope.accTypeList}">

        <div class="container" style="margin-top: 80px">
            <legend><fmt:message key="accountsTypes"/></legend>
            <form action="controller/admin/accountsTypePage" method="post">
                <input type="hidden" name="action" value="add">
                <input type="submit" value="<fmt:message key="add"/>" class="btn btn-primary">
            </form>
            <table class="table table-hover">
                <tr>
                    <td><fmt:message key="accTypeName"/></td>
                    <td><fmt:message key="percent"/></td>
                    <td><fmt:message key="period"/></td>
                    <td><fmt:message key="currency"/></td>
                    <td><fmt:message key="update"/></td>
                    <td><fmt:message key="delete"/></td>
                </tr>
                <c:forEach items="${requestScope.accTypeList}" var="accType">
                    <tr>
                        <td>${accType.getName()}</td>
                        <td>${accType.getPercent()}</td>
                        <td>${accType.getPeriod()}</td>
                        <td>${accType.getCurrency()}</td>
                        <td>
                            <form action="controller/admin/accountsTypePage" method="post">
                                <input type="hidden" name="accType" value="${accType}">
                                <input type="hidden" name="action" value="update">
                                <input type="submit"
                                       value="<fmt:message key="update"/>" class="btn btn-warning">
                            </form>
                        </td>
                        <td>
                            <form action="controller/admin/deleteAccType" method="post">
                                <input type="hidden" name="accType" value="${accType}">
                                <input type="submit"
                                       value="<fmt:message key="delete"/>" class="btn btn-danger">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:when test="${not empty accList}">
        <div class="container" style="margin-top: 80px">
            <legend><fmt:message key="accounts"/></legend>
            <form action="controller/admin/accountsPage" method="post">
                <input type="hidden" name="action" value="add">
                <input type="submit" value="<fmt:message key="add"/>" class="btn btn-primary">
            </form>
            <table class="table table-hover">
                <tr>
                    <td><fmt:message key="accID"/></td>
                    <td><fmt:message key="owner"/></td>
                    <td><fmt:message key="accTypeName"/></td>
                    <td><fmt:message key="amount"/></td>
                    <td><fmt:message key="currency"/></td>
                    <td><fmt:message key="update"/></td>
                    <td><fmt:message key="delete"/></td>
                </tr>
                <c:forEach items="${accList}" var="account">

                    <tr>
                        <td>${account.getId()}</td>
                        <td>${account.getOwner()}</td>
                        <td>${account.getAcctype()}</td>
                        <td>${account.getAmount()}</td>
                        <td>${account.getCurrency()}</td>
                        <td>
                            <form action="controller/admin/accountsPage" method="post">
                                <input type="hidden" name="accID" value="${account}">
                                <input type="hidden" name="action" value="update">
                                <input type="submit"
                                       value="<fmt:message key="update"/>" class="btn btn-warning">
                            </form>
                        </td>
                        <td>
                            <form action="controller/admin/deleteAccount" method="post">
                                <input type="hidden" name="accID" value="${account}">
                                <input type="submit"
                                       value="<fmt:message key="delete"/>" class="btn btn-danger">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:when test="${not empty usersList}">
        <div class="container" style="margin-top: 80px">
            <legend><fmt:message key="users"/></legend>
            <form action="controller/admin/usersPage" method="post">
                <input type="hidden" name="action" value="add">
                <input type="submit" value="<fmt:message key="add"/>" class="btn btn-primary">
            </form>
            <table class="table table-hover">
                <tr>
                    <td><fmt:message key="login"/></td>
                    <td><fmt:message key="email"/></td>
                    <td><fmt:message key="role"/></td>
                    <td><fmt:message key="update"/></td>
                    <td><fmt:message key="delete"/></td>
                </tr>
                <c:forEach items="${usersList}" var="user">
                    <tr>
                        <td>${user.getName()}</td>
                        <td>${user.getEmail()}</td>
                        <td>${user.getRole()}</td>
                        <td>
                            <form action="controller/admin/usersPage" method="post">
                                <input type="hidden" name="userID" value="${user.getId()}">
                                <input type="hidden" name="action" value="update">
                                <input type="submit"
                                       value="<fmt:message key="update"/>" class="btn-warning">
                            </form>
                        </td>
                        <td>
                            <form action="controller/admin/deleteUser" method="post">
                                <input type="hidden" name="user" value="${user}">
                                <input type="submit"
                                       value="<fmt:message key="delete"/>" class="btn-danger">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:when test="${not empty exchangeRates}">
        <div class="container" style="margin-top: 80px">
            <legend><fmt:message key="exchangeRates"/></legend>
            <form action="controller/admin/exchangeRatesPage" method="post">
                <input type="hidden" name="action" value="add">
                <input type="submit" value="<fmt:message key="add"/>" class="btn btn-primary">
            </form>
            <table class="table table-hover">
                <tr>
                    <td><fmt:message key="currency"/></td>
                    <td><fmt:message key="rate"/></td>
                </tr>
                <c:forEach items="${exchangeRates}" var="rate">
                    <tr>
                        <td>${rate.getCurrency()}</td>
                        <td>${rate.getValue()}</td>
                        <td>
                            <form action="controller/admin/exchangeRatesPage" method="post">
                                <input type="hidden" name="currencyID" value="${rate.getId()}">
                                <input type="hidden" name="action" value="update">
                                <input type="submit"
                                       value="<fmt:message key="update"/>" class="btn-warning">
                            </form>
                        </td>
                        <td>
                            <form action="controller/admin/deleteExchangeRate" method="post">
                                <input type="hidden" name="exchangeRate" value="${rate}">
                                <input type="submit"
                                       value="<fmt:message key="delete"/>" class="btn-danger">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:when test="${not empty transRecordsList}">

        <div class="container" style="margin-top: 80px">
            <legend><fmt:message key="transactionHistory"/></legend>

            <table class="table table-hover">
                <tr>
                    <td><fmt:message key="transHistRecord"/></td>
                    <td><fmt:message key="from"/></td>
                    <td><fmt:message key="finalAcc"/></td>
                    <td><fmt:message key="amount"/></td>
                    <td><fmt:message key="currency"/></td>

                </tr>
                <c:forEach items="${transRecordsList}" var="transRecord">
                    <tr>
                        <td>${transRecord.getId()}</td>
                        <td>${transRecord.getStartAcc()}</td>
                        <td>${transRecord.getFinalAcc()}</td>
                        <td>${transRecord.getAmount()}</td>
                        <td>${transRecord.getCurrency()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:otherwise>

    </c:otherwise>
</c:choose>

<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
