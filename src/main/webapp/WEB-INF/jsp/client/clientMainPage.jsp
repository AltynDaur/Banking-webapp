<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../header.jsp"></jsp:include>
<body>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<fmt:message key="hello"/> ,${sessionScope.user.getName()}
<jsp:include page="clientNavBar.jsp"/>
<jsp:include page="../changeLocale.jsp"/>
<c:choose>
    <c:when test="${not empty accTypeList}">
        <div class="container" style="margin-top: 80px">
            <table class="table table-hover">
                <tr>
                    <td><fmt:message key="accTypeName"/></td>
                    <td><fmt:message key="percent"/></td>
                    <td><fmt:message key="period"/></td>
                    <td><fmt:message key="currency"/></td>
                </tr>
                <c:forEach items="${accTypeList}" var="accType">
                    <tr>
                        <td>${accType.getName()}</td>
                        <td>${accType.getPercent()}</td>
                        <td>${accType.getPeriod()}</td>
                        <td>${accType.getCurrency()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:when test="${not empty accList}">
        <div class="container" style="margin-top: 80px">
            <table class="table table-hover">
                <tr>
                    <td><fmt:message key="accID"/></td>
                    <td><fmt:message key="accTypeName"/></td>
                    <td><fmt:message key="amount"/></td>
                    <td><fmt:message key="currency"/></td>
                </tr>
                <c:forEach items="${accList}" var="account">
                    <tr>
                        <td>${account.getId()}</td>
                        <td>${account.getAcctype().getName()}</td>
                        <td>${account.getAmount()}</td>
                        <td>${account.getAcctype().getExchangeRate().getCurrency()}</td>
                        <td>
                            <form action="client/transactionPage" method="post">
                                <input type="hidden" name="accID" value="${account.getId()}">
                                <input type="submit" value="<fmt:message key="transaction"/>" class="btn btn-primary">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:when test="${not empty transHistoryRecords}">
        <div class="container" style="margin-top: 80px">
            <table class="table table-hover">
                <tr>
                    <td><fmt:message key="transHistRecord"/> </td>
                    <td><fmt:message key="from"/></td>
                    <td><fmt:message key="finalAcc"/></td>
                    <td><fmt:message key="amount"/></td>
                    <td><fmt:message key="currency"/></td>
                </tr>
                <c:forEach items="${transHistoryRecords}" var="transRecord">
                    <tr>
                        <td>${transRecord.getId()}</td>
                        <td>${transRecord.getStartAcc().getId()}</td>
                        <td>${transRecord.getFinalAcc().getId()}</td>
                        <td>${transRecord.getAmount()}</td>
                        <td>${transRecord.getExchangeRate().getCurrency()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:when test="${not empty exchangeRates}">
        <div class="container" style="margin-top: 80px">
            <table class="table table-hover">
                <tr>
                    <td><fmt:message key="currency"/></td>
                    <td><fmt:message key="rate"/></td>
                </tr>
                <c:forEach items="${exchangeRates}" var="exchRate">
                    <tr>
                        <td>${exchRate.getCurrency()}</td>
                        <td>${exchRate.getValue()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:otherwise>
        <div class="container" style="margin-top: 80px">
            <c:if test="${not empty message}">
                <c:out value="${message}"></c:out>
            </c:if>

        </div>
    </c:otherwise>
</c:choose>
<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
