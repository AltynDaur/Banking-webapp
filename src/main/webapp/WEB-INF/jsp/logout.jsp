<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text"/>
<div class="container row span2">
    <form action="controller/logout" method="post">
        <input align="left" type="submit" value="<fmt:message key="logout"/>">
    </form>
</div>
