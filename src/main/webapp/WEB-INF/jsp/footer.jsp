<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<p align="right">Thanks for visiting
    <c:if test="${not empty sessionScope.user}">
        ${sessionScope.user.getName()}
    </c:if>

</p>
