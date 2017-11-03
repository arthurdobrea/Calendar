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
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>

<body>

<div class="container">

    <form:form method="POST" modelAttribute="user" class="form-signin">
        <form:input type="hidden" path="id" id="id"/>
        <h2 class="form-signin-heading">Edit user </h2>


        <spring:bind path="id">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="hidden" path="id" class="form-control" placeholder="ID"
                            autofocus="true"></form:input>
                <form:errors path="id"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="username" path="username" class="form-control" placeholder="Username" readonly="true"
                            autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="firstname">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="firstname" class="form-control" placeholder="First name"
                            autofocus="true"></form:input>
                <form:errors path="firstname"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="lastname">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="lastname" class="form-control" placeholder="Last name"
                            autofocus="true"></form:input>
                <form:errors path="lastname"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="email" path="email" class="form-control" placeholder="Email"
                            autofocus="true"></form:input>
                <form:errors path="email"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control" placeholder="New password"
                            autofocus="true"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="roles">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:select path="roles" items="${list_of_roles}" multiple="true" itemValue="name" itemLabel="name" class="form-control input-sm"
                            autofocus="true"></form:select>
                <form:errors path="roles"></form:errors>
            </div>
        </spring:bind>

        <%--<div class="row">--%>
            <%--<div class="form-group col-md-12">--%>
                <%--<label class="col-md-3 control-lable" for="roles">Roles</label>--%>
                <%--<div class="col-md-7">--%>
                    <%--<form:select path="roles" items="${list_of_roles}" multiple="true" itemValue="name" itemLabel="name" class="form-control input-sm" />--%>
                    <%--<div class="has-error">--%>
                        <%--<form:errors path="roles" class="help-inline"/>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<c:forEach items="${list_of_roles}" var="roles">--%>
            <%--<p>Name: ${roles.name}--%>
                <%--<a href="/updateEvent?eventId=${event.id}" class="btn">Update</a>--%>
                <%--<a href="/deleteEvent?eventId=${event.id}" class="btn">Delete</a>--%>

            <%--</p>--%>
        <%--</c:forEach>--%>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>