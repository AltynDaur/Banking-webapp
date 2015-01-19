<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text"/>

<div class="navbar navbar-inverse navbar-fixed-top">
    <nav class="navbar-inner">
        <div class="offset3"/>
        <a class="brand" href="admin/adminMainPage"><fmt:message key="adminMain"/></a>
        <ul class="nav">
            <li class="divider-vertical"></li>
            <li><a href="admin/users"><fmt:message key="users"/></a></li>
            <li><a href="admin/accounts"><fmt:message key="accounts"/></a></li>
            <li><a href="admin/accountTypes"><fmt:message key="accountsTypes"/></a></li>
            <li><a href="admin/exchangeRates"><fmt:message key="exchangeRates"/></a></li>
            <li><a href="admin/transactionHistory"><fmt:message key="transactionHistory"/> </a></li>
            <li><a href="userInfoChangePage"><fmt:message key="userOption"/> </a></li>
            <li class="divider-vertical"></li>
            <li><a href="logout"><fmt:message key="logout"/></a></li>
        </ul>
    </nav>
</div>