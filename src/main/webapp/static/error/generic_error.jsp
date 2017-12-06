<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
</head>

<body style="padding: 0px; margin: 0px">
<div class="error-wrapper">
    <input type="image" height="250" width="300" src="${contextPath}/resources/images/octopus-resized.png"/>

    <div class="error-msg">
        Oops!
    </div>

    <div class="error-msg">
        <c:if test="${not empty errCode}">
            ${errCode} : System Errors
        </c:if>
    </div>

    <div class="error-msg">
        <c:if test="${empty errCode}">
            System Errors
        </c:if>
    </div>

    <div class="error-msg">
        <c:if test="${not empty errMsg}">
            ${errMsg}
        </c:if>
    </div>

    <div style="margin-top: 10px;">
        <a href="/welcome" class="greeting-btn">Home</a>
    </div>
</div>
</body>
</html>