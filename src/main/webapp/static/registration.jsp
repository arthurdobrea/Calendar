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
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form:form method="POST" modelAttribute="userForm" class="form-signin form-signin-reg" enctype = "multipart/form-data">
        <div class="image"><input type="image" src="${contextPath}/resources/logo.png"/></div>

        <spring:bind path="email">
            <div id="email" class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                <form:input type="email" path="email" class="form-control" placeholder="EMAIL"
                            autofocus="true"></form:input>
                <form:errors path="email"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="username">
            <div id="username" class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username" class="form-control" placeholder="USERNAME"
                            autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>

        <div class="first-last-div">
        <spring:bind path="lastname">
            <div id="lastname" class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="lastname" class="form-control" placeholder="LAST NAME"
                            autofocus="true"></form:input>
                <form:errors path="lastname"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="firstname">
            <div id="firstname" class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="firstname" class="form-control" placeholder="FIRST NAME"
                            autofocus="true"></form:input>
                <form:errors path="firstname"></form:errors>
            </div>
        </spring:bind>
        </div>
        <spring:bind path="position">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="position" class="form-control" placeholder="position"
                            autofocus="true"></form:input>
                <form:errors path="position"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="confirmPassword">
            <div id="confirm-pass" class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="confirmPassword" class="form-control" placeholder="CONFIRM PASSWORD"
                            autofocus="true"></form:input>
                <form:errors path="confirmPassword"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group form-group-reg ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control" placeholder="PASSWORD"
                            autofocus="true"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="multipartFile">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="file" path="multipartFile" class="form-control"
                            autofocus="true"></form:input>
                <form:errors path="multipartFile"></form:errors>
            </div>
        </spring:bind>

        <button id="btn-reg" class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
    </form:form>
</div>
</body>
</html>