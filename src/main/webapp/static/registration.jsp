<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description">
    <meta name="author">

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/header-style.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form:form method="POST" modelAttribute="userForm" class="form-signin-reg form-signin form-signin-reg" enctype = "multipart/form-data">
        <div class="image"><input type="image" src="${contextPath}/resources/icons/logo.png"/></div>
        <spring:bind path="multipartFile">
            <div id="photo" class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="file" path="multipartFile" class="form-control"
                            autofocus="true"/>
                <form:errors path="multipartFile"/>
            </div>
        </spring:bind>
        <spring:bind path="username">
            <div id="username" class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
            <c:choose>
            <c:when test="${status.error}">
                <c:set var="placeholder" value="Username must be between 6 and 32 characters."/>
                <c:set var="placeColor" value="#df3f14"/>
            </c:when>
            <c:otherwise>
                <c:set var="placeholder" value="USERNAME"/>
                <c:set var="placeColor" value="#48545B"/>
            </c:otherwise>
        </c:choose>
                <form:input cssClass=".has-error form-control" type="text" path="username" class="form-control" placeholder='${placeholder}'
                            autofocus="true"/>
                <%--<form:errors path="username" id="reg-error"/>--%>
            </div>
        </spring:bind>

        <div class="first-last-div" >
            <spring:bind path="lastname">
                <div id="lastname" class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                    <c:choose>
                        <c:when test="${status.error}">
                            <c:set var="placeholder" value="must be more 6 characters."/>
                            <c:set var="placeColor" value="#df3f14"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="placeholder" value="LAST NAME"/>
                            <c:set var="placeColor" value="#48545B"/>
                        </c:otherwise>
                    </c:choose>
                    <form:input type="text" path="lastname" class="form-control" placeholder="${placeholder}"
                                autofocus="true"/>
                    <%--<form:errors path="lastname" class="reg-last-first-error"/>--%>
                </div>
            </spring:bind>

            <spring:bind path="firstname">
                <div id="firstname" class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                    <c:choose>
                        <c:when test="${status.error}">
                            <c:set var="placeholder" value="must be more 6 characters."/>
                            <c:set var="placeColor" value="#df3f14"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="placeholder" value="FIRST NAME"/>
                            <c:set var="placeColor" value="#48545B"/>
                        </c:otherwise>
                    </c:choose>
                    <form:input type="text" path="firstname" class="form-control" placeholder="${placeholder}"
                                autofocus="true"/>
                    <%--<form:errors path="firstname" class="reg-last-first-error"/>--%>
                </div>
            </spring:bind>
        </div>
        <spring:bind path="confirmPassword">
            <div id="confirm-pass" class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                <c:choose>
                    <c:when test="${status.error}">
                        <c:set var="placeholder" value="Password don't match."/>
                        <c:set var="placeColor" value="#df3f14"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="placeholder" value="CONFIRM PASSWORD"/>
                        <c:set var="placeColor" value="#48545B"/>
                    </c:otherwise>
                </c:choose>
                <form:input type="password" path="confirmPassword" class="form-control" placeholder="${placeholder}"
                            autofocus="true"/>
                <%--<form:errors path="confirmPassword" id="reg-error"/>--%>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                <c:choose>
                    <c:when test="${status.error}">
                        <c:set var="placeholder" value="Password must be over 8 characters."/>
                        <c:set var="placeColor" value="#df3f14"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="placeholder" value="PASSWORD"/>
                        <c:set var="placeColor" value="#48545B"/>
                    </c:otherwise>
                </c:choose>
                <form:input type="password" path="password" class="form-control" placeholder="${placeholder}"
                            autofocus="true"/>
                <%--<form:errors path="password" id="reg-error"/>--%>
            </div>
        </spring:bind>


        <spring:bind path="email">
            <div id="email" class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                <c:choose>
                    <c:when test="${status.error}">
                        <c:set var="placeholder" value="enter a valid email."/>
                        <c:set var="placeColor" value="#df3f14"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="placeholder" value="EMAIL"/>
                        <c:set var="placeColor" value="#48545B"/>
                    </c:otherwise>
                </c:choose>
                <form:input type="email" path="email" class="form-control" placeholder="${placeholder}"
                            autofocus="true"/>
                <%--<form:errors path="email" id="reg-error"/>--%>
            </div>
        </spring:bind>

        <spring:bind path="position">
            <div class="assignment form-group ${status.error ? 'has-error' : ''}">
                <c:choose>
                    <c:when test="${status.error}">
                        <c:set var="placeholder" value="Username must be between 6 and 32 characters."/>
                        <c:set var="placeColor" value="#df3f14"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="placeholder" value="ASSIGNMENT NAME"/>
                        <c:set var="placeColor" value="#48545B"/>
                    </c:otherwise>
                </c:choose>
                <form:input type="text" path="position" class="form-control" placeholder="${placeholder}"
                            autofocus="true"/>
                <%--<form:errors path="position" id="reg-error"/>--%>
            </div>
        </spring:bind>
        <div id="btn-reg-div"><button id="btn-reg" class="btn btn-lg btn-primary btn-block" type="submit">Register</button></div>
    </form:form>
</div>
</body>
</html>