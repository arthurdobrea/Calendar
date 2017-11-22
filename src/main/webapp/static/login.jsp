<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description">
    <meta name="author">

    <title>Log in with your account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>


    <script src="${contextPath}/resources/js/jquery.ui.widget.js"></script>
    <script src="${contextPath}/resources/js/jquery.iframe-transport.js"></script>
    <script src="${contextPath}/resources/js/jquery.fileupload.js"></script>
    <script src="${contextPath}/resources/js/myuploadfunction.js"></script>
</head>
<body>


<div class="container" style="height: 500px">
    <form method="POST" action="${contextPath}/login" class="form-signin">
        <input type="image" src="${contextPath}/resources/Logo.png"/>
        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="USERNAME"
                   autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="PASSWORD"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <label><input type="checkbox" id="remember-me" name="remember-me"> Remember Me</label>
            <button class="btn btn-lg btn-primary btn-block" type="submit"></button>
            <h4 class="text-center">Don't have an account?<a href="${contextPath}/registration">Register</a></h4>
        </div>
    </form>
</div>
<%--<h1>Spring MVC - jQuery File Upload</h1>--%>
<%--<div style="width:500px;padding:20px">--%>

    <%--<input id="fileupload" type="file" path = "nope" name="files[]" data-url="${contextPath}/upload" multiple>--%>


<%--</div>--%>
</body>
</html>