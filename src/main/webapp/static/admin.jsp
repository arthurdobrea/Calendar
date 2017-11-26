<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Edit account</title>

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
    <a href="/welcome" class="btn_calendar">Home</a>
    <a href="/index" class="btn_calendar">Calendar</a>
    <a href="/userControlPanel" class="btn_calendar">User Panel</a>
    <a href="/createEvent" class="btn_calendar">Create new event</a>
    <a href="/userPage" class="btn_calendar">User Page</a>
    <a href="/events" class="btn_calendar">All events</a>
    <a href="/logout" class="btn_calendar">Logout</a>
    <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
        <a href="/admin" class="btn_calendar">Admin page</a>
    </c:if>
    <c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">
        <a href="/admin" class="btn_calendar">Admin page</a>
    </c:if>

<div class="generic-container">
    <%--<%@include file="authheader.jsp" %>--%>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">User Administration</span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Username</th>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Email</th>
                <th>Role</th>
                <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
                    <th width="100"></th>
                </c:if>
                <c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">
                    <th width="100"></th>
                </c:if>
                <c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">
                    <th width="100"></th>
                </c:if>
            </tr>
            </thead>

            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                    <th>${user.username}</th>
                    <th>${user.firstname}</th>
                    <th>${user.lastname}</th>
                    <th>${user.email}</th>
                        <c:set var="roles" value="${user.roles}"/>
                        <c:set var="role" value="${fn:substringAfter(roles, 'ROLE_')}"/>
                        <th>${fn:substringBefore(role, "\'")}</th>

                        <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
                            <th><a href="<c:url value='/edit-user-${user.username}' />" class="btn custom-width">edit</a></th>
                        </c:if>
                        <c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">
                            <th><a href="<c:url value='/edit-user-${user.username}' />" class="btn custom-width">edit</a></th>
                        </c:if>

                        <c:choose>
                            <c:when test="${pageContext.request.remoteUser.equals(user.username)}"></c:when>
                            <c:otherwise>
                                <c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">
                                    <th><a href="<c:url value='/delete-user-${user.username}' />" class="btn custom-width">delete</a></th>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>