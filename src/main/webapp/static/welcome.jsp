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
<a href="/welcome" class="btn">Home</a>
<a href="/index" class="btn">Calendar</a>
<a href="/userControlPanel" class="btn">User Panel</a>
<a href="/userPage" class="btn">User Page</a>
<a href="/events" class="btn">All events</a>
<c:if test="${pageContext.request.isUserInRole('ADMIN')}">
    <a href="/admin" class="btn">Admin page</a>
</c:if>
<c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">
    <a href="/admin" class="btn">Admin page</a>
</c:if>


<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>Welcome ${pageContext.request.userPrincipal.name} <a href="/createEvent">Create new event</a>| <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>
    </c:if>

</div>
<div class="container">
    <div class="row">
        <div class="col-sm-10">
            <!-- WebSocket related Twitter Bootstrap 3.0 based UI elements -->
            <div id="heading" class="masthead">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <p>&nbsp;</p>
            <!-- Connect and Disconnect buttons to establish/terminate a connection to the websocket service -->
            <p />
            <div class="panel panel-default">
                <div class="panel-body" id="conversationDiv">
                </div>
                <!-- .panel-body -->
                <div class="panel-body" id="response">

                </div>
                <!-- Div to show the server responses -->
            </div>
            <!-- .panel -->
        </div>
    </div>
    <div> <c:forEach items="${notification}" var="notification">
        <p>Name: ${notification.title} | Type of event: ${notification.eventType.view()} |
            <a href="/participants/${notification.title}"> participants</a></p>
        <p> <c:forEach items="${notification.getParticipants()}" var="participant">
        <p>${participant.username}</p>
        </p>
    </c:forEach>
        <%-- Output tags of event--%>
        <p>Tag:
            <c:forEach items="${notification.tags}" var="tag">
                | ${tag.tag} |
            </c:forEach>
        </p>
        <br>
    </c:forEach></div>
</div>
<script type="text/javascript">
    connectToServerFunc()
</script>
</body>
</html>