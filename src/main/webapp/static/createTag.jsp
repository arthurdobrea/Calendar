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

    <title>Create a tag</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/header-style.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/lib/bootstrap.min.js"></script>
</head>
<body>
<a href="/welcome" class="btn_calendar">Home</a>
<a href="/index" class="btn_calendar">Calendar</a>
<a href="/userControlPanel" class="btn_calendar">User Panel</a>
<a href="/create-event" class="btn_calendar">Create new event</a>
<a href="/events" class="btn_calendar">All events</a>
<a href="/logout" class="btn_calendar">Logout</a>

<div class="container">
    <form:form method="POST" modelAttribute="tagForm" class="form-signin">
        <h2 class="form-signin-heading">Create your tag</h2>
        <spring:bind path="tag">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="tag" class="form-control" placeholder="Tag"
                            autofocus="true"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="color">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="color" class="form-control" placeholder="Color"
                            autofocus="true"></form:input>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">create tag</button>
    </form:form>
</div>
</body>
</html>
