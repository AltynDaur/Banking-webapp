<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta name="author" content="Altynbekov Dauren, altyndaur.krg@mail.ru"/>
    <meta name="keywords" content="Banking, accounts"/>
    <title>Banking: Thanks for visiting</title>
    <base href="/Webapp/">
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <fmt:setLocale value="${language}"/>
    <fmt:setBundle basename="text"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
    <style>
        body {
            background-image: url(${pageContext.request.contextPath}/resources/images/background.jpg);
            background-color: #a9a9a9;
        }
        .well{
            margin-top: 40px;
            background-color: transparent;
            border-color: transparent;
        }
        .log{
            margin-top: 100px;
        }
    </style>
</head>