<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description">
    <meta name="author">

    <title>Welcome</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/header-style.css" rel="stylesheet">
    <script src="<c:url value="/resources/scripts/sockjs-0.3.4.min.js"/>"></script>
    <script src="<c:url value="/resources/scripts/stomp.js"/>"></script>
    <script src="<c:url value="/resources/scripts/jquery-1.10.2.min.js"/>"></script>
    <script src="<c:url value="/resources/scripts/bootstrap/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/scripts/knockout-3.0.0.js"/>"></script>
    <script src="<c:url value="/resources/scripts/connectToServer.js"/>"></script>
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</head>

<body>

    <c:import url="header.jsp" />
    <%--<jsp:include page="header.jsp"/>--%>


    <div>
        Welcome ${pageContext.request.userPrincipal.name}
    </div>

    <div>
        Welcome to Endava Event Manager
    </div>

    <div>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
    </div>

</body>
</html>