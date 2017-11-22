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
    <%--<link href="${contextPath}/resources/css/common.css" rel="stylesheet">--%>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</head>

<body style="margin: 0">
<div id="wrapper">
        <div class="panel panel-default for_panel" style="height: 60px; width: 1200px;">
            <div style="width: 400px; height: 100px" class="test_style_for_octopus">
                <nav style="float: right">
                    <a href="/welcome">HOME</a>
                    <a href="/index">CALENDAR</a>
                    <a href="/userControlPanel">PROFILE</a>
                </nav>
            </div>
        </div>

    <aside class="panel panel-default for_shadow" style="padding: 25px">
        <div style="height: 20px;">
            <p class="capital_text" align="left">PROFILE
            <a href="/userControlPanel" style="float: right; color: #DE411B">EDIT</a></p>
        </div>
        <div style="margin-top: 60px">
            <p><img src="${contextPath}/resources/icons/avatar.png" alt="logo.png" align="center" width="150px" height="150px"></p>
        </div>
        <div style="margin-top: 50px">
            <p class="capital_text" style="line-height: 100%;"><span style="size: 25px">Jane Martinas</span><br>
                                     martinas@endava.com</p>
            <p class="capital_text" style="color: #DE411B">JUNIOR AM ENGINEER</p>
        </div>
    </aside>

    <section class="panel panel-default for_shadow" style="padding-top: 25px; padding-left: 20px; padding-right: 20px;">
            <div style="height: 20px; margin-left: 10px; margin-right: 10px;">
                <p class="capital_text" align="left">MY EVENTS
                    <span class="inline_text" style="float: right; color: #48545B">CREATED BY ME <label class="switch" style="margin: 0; margin-top: 3px; height: 12px"><input type="checkbox"><span class="slider round"></span></label> INVITATIONS</span></p>
            </div>

          <div style="margin-top: 30px; margin-left: 10px; margin-right: 10px;">
              <p class="capital_text" align="left"><span class="inline_text" style="color: #DE411B"><a href="/createEvent"><button class="btn_add_event">+</button> ADD EVENT</a></span>
                  <span class="inline_text" style="float: right; color: #48545B">TOTAL NUMBER OF CREATED EVENTS:  <span class="inline_text" style="color: #DE411B">${eventsByAuthor.size()}</span></span></p>
          </div>

<div align="left" class="panel scrollable-content">
    <table class="table table-hover for_table">
        <tbody>
        <c:forEach items="${eventsByAuthor}" var="event">
            <a href="/showEvent?eventId=${event.id}">
                <tr>
                    <td align="left" style="padding-left: 0"><span  class="inline_text" style="color: #48545B; line-height: 100%;">${event.title}<br></span>
                                     <span  class="inline_text">${event.eventType}</span></td>
                    <td align="right" class="td_edit_delete"  style="vertical-align: middle; padding-right: 0"><button class="btn_edit_event" onclick="window.location.href='/updateEvent?eventId=${event.id}' "></button></td>
                <td align="right" class="td_edit_delete"  style="vertical-align: middle; padding-right: 0"><button class="btn_delete_event" onclick="window.location.href='/deleteEvent?eventId=${event.id}' "></button></td>
            </tr>
            </a>
        </c:forEach>
        </tbody>
    </table>
</div>
    </section>
</div>
</body>
</html>




<%--<a href="/userPage" class="btn">User Page</a>--%>
<%--<a href="/events" class="btn">All events</a>--%>
<%--<a href="/logout" class="btn">Logout</a>--%>
<%--<c:if test="${pageContext.request.isUserInRole('ADMIN')}">--%>
<%--<a href="/admin" class="btn">Admin page</a>--%>
<%--</c:if>--%>
<%--<c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">--%>
<%--<a href="/admin" class="btn">Admin page</a>--%>
<%--</c:if>--%>

<%--<div class="container">--%>
<%--<c:if test="${pageContext.request.userPrincipal.name != null}">--%>
<%--<form id="logoutForm" method="POST" action="${contextPath}/logout">--%>
<%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--</form>--%>

<%--<h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>--%>
<%--</h2>--%>

<%--</c:if>--%>

<%--<h2>Avatar</h2> <img src="${imageOfUser}" width="100" height="100" />--%>



<%--<h2>Events where I am invited: ${eventsByUser.size()}</h2>--%>
<%--<form action="eventTypeLink" method="post">--%>
<%--<c:forEach items="${eventsList}" var="eventType">--%>
<%--${eventType.view()}--%>
<%--<c:set var="checked" value="0"/>--%>
<%--<c:forEach items="${userLabels}" var="labels">--%>
<%--<c:if test = "${labels==eventType}">--%>
<%--<c:set var="checked" value="1"/>--%>
<%--</c:if>--%>
<%--</c:forEach>--%>
<%--<c:if test = "${checked==1}">--%>
<%--<input type="checkbox" name="checkboxName" value="${eventType}" checked/>--%>
<%--</c:if>--%>
<%--<c:if test = "${checked==0}">--%>
<%--<input type="checkbox" name="checkboxName" value="${eventType}" />--%>
<%--</c:if>--%>
<%--</c:forEach>--%>

<%--<input type="checkbox" name="checkboxName" value="" checked hidden/>--%>
<%--<input type="submit">--%>
<%--</form>--%>

<%--<table class="table table-hover">--%>
<%--<tr>--%>
<%--<th>Name</th>--%>
<%--<th>Type</th>--%>
<%--</tr>--%>
<%--<c:forEach items="${events}" var="event">--%>
<%--<tr>--%>
<%--<td>${event.title}</td>--%>
<%--<td>${event.eventType}</td>--%>
<%--<td><a href="/showEvent?eventId=${event.id}" class="btn">Details</a></td>--%>
<%--</c:forEach>--%>
<%--</table>--%>