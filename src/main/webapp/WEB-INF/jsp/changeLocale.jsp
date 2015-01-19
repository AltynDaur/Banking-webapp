<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<div class="container row span2 well">
    <form action="changeLocale" id="changeLocale" method="post">
        <select id="language" name="language" onchange="document.getElementById('changeLocale').submit()"
                class="span2">
            <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="english"/></option>
            <option value="ru" ${language == 'ru' ? 'selected' : ''}><fmt:message key="russian"/></option>
        </select>
    </form>
</div>
 