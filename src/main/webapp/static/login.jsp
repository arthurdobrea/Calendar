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
<div class="login_form for_shadow">
    <form method="POST" action="${contextPath}/login">
        <input class="endava_logo" type="image" src="${contextPath}/resources/icons/Logo.png"/>
        <div class=" ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input autocomplete="false" name="username" class="login_input" type="text" placeholder="USERNAME"
                   autofocus="true" />
            <input autocomplete="false" name="password" class="login_input" type="password" placeholder="PASSWORD" />
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div style="margin-top: 40px">
                <div style="float: left"><label id="remember_me" class="modal-header edit_profile_header"><input type="checkbox" name="remember-me"/>
                    <span class="capital_text"> Remember me</span></label></div>
                    <div style="float: right; margin-right: 35px"><span class="capital_text">Forgot password?</span></div>
            </div>
            <button type="submit" class="btn_login_submit">LOG IN</button>
            <p class="capital_text">Don't have an account?<a href="${contextPath}/registration"> Register</a></p>
        </div>
    </form>
</div>
<%--<h1>Spring MVC - jQuery File Upload</h1>--%>
<%--<div style="width:500px;padding:20px">--%>
    <%--<input id="fileupload" type="file" path = "nope" name="files[]" data-url="${contextPath}/upload" multiple>--%>
<%--</div>--%>
</body>
</html>