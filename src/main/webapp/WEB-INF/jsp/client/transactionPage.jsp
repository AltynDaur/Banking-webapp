<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../header.jsp"/>
<body>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="text"/>
<jsp:include page="clientNavBar.jsp"/>
<div class="container" style="margin-top: 80px">
    <table class="table">
        <fmt:message key="transaction"/>
        <form action="controller/client/transaction" method="post">
            <tr>
                <td><fmt:message key="from"/>:</td>
                <td><input type="text" name="currentAccID" value="${currentAccID}" readonly></td>
            </tr>
            <tr>
                <td><fmt:message key="to"/></td>
                <td><input type="text" name="targetAccID" value=""></td>
            </tr>
            <tr>
                <td><fmt:message key="amount"/></td>
                <td><input type="text" name="amount" value=""></td>
            </tr>
            <tr>
                <td><fmt:message key="currency"/></td>
                <td>
                    <select name="currency">
                        <option value="dollar"><fmt:message key="dollar"/></option>
                        <option value="euro"><fmt:message key="euro"/></option>
                        <option value="rusrub"><fmt:message key="rusrub"/></option>
                        <option value="kazten"><fmt:message key="kazten"/></option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><div class="btn-group span6 offset2">
                    <input type="submit" value="<fmt:message key="transaction"/>" class="btn btn-success">
                    <input type="reset" value="<fmt:message key="reset"/>" class="btn btn-danger">
                </div></td>

            </tr>

        </form>
    </table>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
