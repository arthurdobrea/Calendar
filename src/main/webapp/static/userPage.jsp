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
    <link href='${contextPath}/resources/css/calendar.custom.css' rel='stylesheet' />
    <link href="${contextPath}/resources/css/jquery.datetimepicker.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery.datetimepicker.min.css" rel="stylesheet">

    <script src='${contextPath}/resources/js/moment.min.js'></script>
    <script src='${contextPath}/resources/js/jquery.min.js'></script>
    <script src='${contextPath}/resources/js/moment.js'></script>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/userProfile.js"></script>

</head>

<body>
<c:import url="header.jsp" />
<div id="wrapper">
    <aside class="panel panel-default for_shadow" style="padding: 25px">
        <div style="height: 20px;">
            <p class="capital_text" align="left">PROFILE
                <button id="edit_user" onclick="edit_user()">EDIT</button></p>
        </div>
        <div id="avatar" style="margin-top: 60px">
            <img alt="img" src="data:image/jpeg;base64,${image}"/>
        </div>
        <div style="margin-top: 50px">
            <p class="capital_text" style="line-height: 100%;"><span style="size: 25px">${user.fullName}</span><br>
                                     ${user.email}</p>
            <p class="capital_text" style="color: #DE411B">JUNIOR AM ENGINEER</p>
        </div>
    </aside>

    <section class="panel panel-default for_shadow" style="padding-top: 25px; padding-left: 20px; padding-right: 20px;">
            <div style="height: 20px; margin-left: 10px; margin-right: 10px;">
                <p class="capital_text" align="left">MY EVENTS
                    <span class="inline_text" style="float: right; color: #48545B">CREATED BY ME
                        <label class="switch" style="margin: 0; margin-top: 3px; height: 12px">
                            <input type="checkbox" id="switch_events" onclick="if(this.checked) {showEventsInvited();} else {showMyEvents();}">
                            <span class="slider round"></span></label> INVITATIONS</span></p>
            </div>

          <div style="margin-top: 30px; margin-left: 10px; margin-right: 10px;">
              <p class="capital_text" align="left"><span class="inline_text" style="color: #DE411B"><button id="add_event_button" class="btn_add_event" onclick="create_event()">+</button> ADD EVENT</span>
                  <span id="total_events_created" class="inline_text" style="float: right; color: #48545B;">TOTAL NUMBER OF CREATED EVENTS:
                      <span class="inline_text" style="color: #DE411B">${eventsByAuthor.size()}</span></span>
                  <span id="total_events_invited" class="inline_text" style="float: right; color: #48545B;">TOTAL NUMBER OF EVENTS I AM INVITED:
                      <span class="inline_text" style="color: #DE411B">${eventsByUser.size()}</span></span></p>
          </div>


        <%--Created events--%>
<div id="my_events" align="left" class="panel scrollable-content">
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

        <%--Events where I'm invited--%>
        <div id="events_invited" align="left" class="panel scrollable-content">
            <table class="table table-hover for_table">
                <tbody>
                <c:forEach items="${eventsByUser}" var="event">
                    <a href="/showEvent?eventId=${event.id}">
                        <tr>
                            <td align="left" style="padding-left: 0"><span  class="inline_text" style="color: blue; line-height: 100%;">${event.title}<br></span>
                                <span  class="inline_text">${event.eventType}</span></td>
                            <td align="right" class="td_edit_delete"  style="vertical-align: middle; padding-right: 0"><a href="/showEvent?eventId=${event.id}"><button class="btn_unsubscribe"></button></a></td>
                        </tr>
                    </a>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </section>
</div>



<div class="add_event_modal"></div>
<div class="edit_user_modal"></div>

<script src="${contextPath}/resources/js/jquery.datetimepicker.full.min.js"></script>
<script src="${contextPath}/resources/js/eventValidator.js"></script>

</body>
</html>


