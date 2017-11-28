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


    <script src='${contextPath}/resources/js/jquery.min.js'></script>
    <script src='${contextPath}/resources/js/jquery-ui.min.js'></script>
    <script src="${contextPath}/resources/js/userProfile.js"></script>
    <script src='${contextPath}/resources/js/moment.js'></script>

</head>

<body>

    <c:import url="header.jsp" />

    <div class="mascot"></div>


    <table class="greeting-wrapper">
        <tr>
            <td class="greeting_1">
                Welcome to Endava Event Manager
            </td>
        </tr>
        <tr>
            <td class="greeting_2">
                You are logged as ${pageContext.request.userPrincipal.name}
            </td>
        </tr>
        <tr>
            <td class="greeting_3">
                Endava Event Manager was created with love and passion by a team of highly pissed-off interns during a fucking long six week period.
            </td>
        </tr>
        <tr>
            <td class="greeting_4">
                <a href="/createEvent" data-toggle="modal"  data-toggle="#AddEvent" class="greeting-btn">Add event</a>
                <%--<a href="events" class="greeting-btn">See events</a>--%>
            </td>
        </tr>
    </table>

</body>
</html>