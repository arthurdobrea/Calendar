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
<a href="/logout" class="btn">Logout</a>
<c:if test="${pageContext.request.isUserInRole('ADMIN')}">
    <a href="/admin" class="btn">Admin page</a>
</c:if>
<c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">
    <a href="/admin" class="btn">Admin page</a>
</c:if>

<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>
    </c:if>

        <h2>Events created by me: ${eventsByAuthor.size()}</h2>

        <c:forEach items="${eventsByAuthor}" var="event">
        <p>Name: ${event.title} | Type of event: ${event.eventType}
            <a href="/updateEvent?eventId=${event.id}" class="btn">Update</a>
            <a href="/deleteEvent?eventId=${event.id}" class="btn">Delete</a>
            <a href="/showEvent?eventId=${event.id}" class="btn">Details</a>

        </p>
        </c:forEach>

    <h2>Events where I am invited: ${eventsByUser.size()}</h2>

    <c:forEach items="${eventsByUser}" var="event">
        <p>Name: ${event.title} | Type of event: ${event.eventType}
            <a href="/showEvent?eventId=${event.id}" class="btn">Details</a>
        </p>
    </c:forEach>

    <form action="eventTypeLink" method="post">
       <c:forEach items="${eventsList}" var="eventType">
           <p>${eventType.view()}
               <c:set var="checked" value="0"/>
               <c:forEach items="${userLabels}" var="labels">
                    <c:if test = "${labels==eventType}">
                         <c:set var="checked" value="1"/>
                     </c:if>
               </c:forEach>
               <c:if test = "${checked==1}">
                    <input type="checkbox" name="checkboxName" value="${eventType}" checked/><br>
               </c:if>
               <c:if test = "${checked==0}">
                    <input type="checkbox" name="checkboxName" value="${eventType}" /><br>
               </c:if>
        </c:forEach>
           <p>

           <input type="checkbox" name="checkboxName" value="" checked hidden/><br>
        <input type="submit">
    </form>
</div>

</body>
</html>