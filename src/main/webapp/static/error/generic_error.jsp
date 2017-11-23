<%--
  Created by IntelliJ IDEA.
  User: isirosenco
  Date: 11/10/2017
  Time: 1:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--<link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>--%>
    <%--<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">--%>
    <%--<link href="${contextPath}/resources/css/common.css" rel="stylesheet">--%>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <%--<link href='${contextPath}/resources/css/fullcalendar.css' rel='stylesheet' />--%>
    <%--<link href='${contextPath}/resources/css/fullcalendar.print.css' rel='stylesheet' media='print' />--%>
</head>
<body>
<input type="image" height="250" width="300" src="${contextPath}/resources/images/Octocat.png"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>
                    Oops!</h1>
                <div class="error-details">
                    <c:if test="${not empty errCode}">
                        <h1>${errCode} : System Errors</h1>
                    </c:if>

                    <c:if test="${empty errCode}">
                        <h1>System Errors</h1>
                    </c:if>

                    <c:if test="${not empty errMsg}">
                        <h2>${errMsg}</h2>
                    </c:if>
                </div>
                <div class="error-actions">
                    <a href="/welcome" class="btn">Home</a>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
