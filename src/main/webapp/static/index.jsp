<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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

    <title>Main Page</title>

    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href='${contextPath}/resources/css/fullcalendar.css' rel='stylesheet' />
    <link href='${contextPath}/resources/css/fullcalendar.print.css' rel='stylesheet' media='print' />
    <link href='${contextPath}/resources/css/calendar.custom.css' rel='stylesheet' />
    <link href="${contextPath}/resources/css/jquery.datetimepicker.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery.datetimepicker.min.css" rel="stylesheet">

    <script src='${contextPath}/resources/js/moment.js'></script>
    <script src='${contextPath}/resources/js/jquery.min.js'></script>
    <script src='${contextPath}/resources/js/jquery-ui.min.js'></script>
    <script src='${contextPath}/resources/js/fullcalendar.js'></script>
    <script src="${contextPath}/resources/js/ui-bootstrap-tpls-2.5.0.min.js"></script>
    <script src="${contextPath}/resources/js/gcal.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrapmodal.js"></script>

<script>
        $(document).ready(function() {
            $('#calendar').fullCalendar({
//                customButtons: {
//                    addNew: {
//                        text: 'Add event',
//                        click:
//                            function(event, jsEvent, view) {
//                                $('#AddEvent').modal();
//                            }
//                    }
//                },
                header: {
                    left: 'prev,today,next',
                    center: 'title',
                    right: 'addNew month,agendaWeek,agendaDay,listWeek'
                },
                defaultDate: $('#calendar').fullCalendar('today'),
                weekNumbers: "ISO",
                navLinks: true,
                eventLimit: false,
//                themeSystem: 'bootstrap3',
                allDaySlot: true,
                timeFormat: 'h:mma',
                events: {url:'/json/allEvents'},
                timezone: 'local',
                eventClick:  function(event, jsEvent, view) {
                    console.log(event);
                    console.log(jsEvent);
                    console.log(view);

                    printEventDataInModal(event.id);
                    $('#eventPage').modal();

                }
        });
         // $('#calendar').fullCalendar( 'gotoDate', currentDate);
        });
    </script>
    <style>
        #calendar {
            max-width: 900px;
            margin: 0 auto;
        }
    </style>
</head>
<body>

    <c:import url="header.jsp" />
    <%--<jsp:include page="header.jsp"/>--%>

<a href="/welcome" class="btn">Home</a>
<a href="/index" class="btn">Calendar</a>
<a href="/events" class="btn">All events</a>
<a href="/tags" class="btn">Tags</a>
<a href="/mailing" class="btn">Mail to all</a>
<a href="/userPage" class="btn">User Page</a>
<c:if test="${pageContext.request.isUserInRole('ADMIN')}">
    <a href="/admin" class="btn">Admin page</a>
</c:if>
<c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">
    <a href="/admin" class="btn">Admin page</a>
</c:if>
<a href="/userControlPanel" class="btn">User Panel</a>
<a href="/logout" class="btn">Logout</a>
<p>
<p>
    <!-- Modal -->
<%--<div class="modal fade" id="eventPage" role="dialog">--%>
    <%--<div class="modal-dialog">--%>
        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title">Event page</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
<%--<h1> You will see event page right here </h1>--%>
                <%--<form:form method="POST" modelAttribute="eventForm" class="form-signin">--%>
                <%--<h2 class="form-signin-heading"></h2>--%>

                <%--<spring:bind path="id">--%>
                <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                    <%--<form:input type="hidden" path="id" class="form-control eventId" placeholder="Id of event"--%>
                                <%--autofocus="true"></form:input>--%>
                <%--</div>--%>
                <%--</spring:bind>--%>
                <%--</form:form>--%>

                <%--Show event details modal--%>
                <div id="fullCalModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span> <span class="sr-only">close</span></button>
                                <h4 id="modalTitle" class="modal-title"></h4>
                            </div>
                            <div align="left" id="modalBody" class="modal-body">
                                <div id="eventStart"></div>
                                <div id="eventEnd"></div>
                                <div id="eventLocation"></div>
                                <div id="eventType"></div>
                                <div id="eventAuthor"></div>
                                <div id="eventCreated"></div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
<%--<div class="modal fade" id="eventPage" role="dialog">--%>
    <%--<div class="modal-dialog">--%>
        <%--<!-- Modal content-->--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                <%--<h4 class="modal-title">Event page</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<form:form modelAttribute="eventForm" class="form-signin">--%>
                <%--<h2 class="form-signin-heading"></h2>--%>

                <%--<spring:bind path="id">--%>
                <%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
                    <%--<form:input type="hidden" path="id" class="form-control eventId" placeholder="Id of event"--%>
                                <%--autofocus="true"></form:input>--%>
                <%--</div>--%>
                <%--</spring:bind>--%>
                <%--</form:form>--%>

<%--<p>--%>
    <%--Name: <span id="evName"></span> <br>--%>
    <%--Type: <span id="evType"></span><br>--%>
    <%--Location: <span id="evLocation"></span> <br>--%>
    <%--Start time: <span id="evStart"></span> <br>--%>
    <%--End time: <span id="evEnd"></span> <br>--%>
    <%--Description:<span id="evDescription"></span> <br>--%>
    <%--Created at: <span id="evCreated"></span> <br>--%>
    <%--Created by: <span id="evAuthor"></span> <br>--%>
    <%--Will be attended by:<br>--%>
    <%--<ul style = "list-style: none"; id="participantsList"></ul>--%>
<%--</p>--%>

<%--<script>--%>
    <%--function printEventDataInModal(eventId)--%>
    <%--{--%>
        <%--$.get("/json/getEvent", {eventId: eventId}, function(data) {--%>
        <%--console.log(data);--%>

        <%--$("#evName").text(data.title);--%>
        <%--$("#evType").text(data.eventType);--%>
        <%--$("#evLocation").text(data.location);--%>
        <%--$("#evStart").text(data.start);--%>
        <%--$("#evEnd").text(data.end);--%>
        <%--$("#evDescription").text(data.description);--%>
        <%--$("#evCreated").text(data.eventCreated);--%>
        <%--$("#evAuthor").text(data.author.firstname + data.author.lastname);--%>
    <%--});--%>
        <%--$.get("/getParticipantsByEvent", {eventId: eventId}, function(data) {--%>
        <%--console.log(data);--%>
        <%--$("#participantsList").text("");--%>
        <%--$.each(data, function(i, user) {--%>
            <%--$("#participantsList").append('<li>' + user.firstname + " " + user.lastname + "</li>");--%>
        <%--});--%>
    <%--});--%>
    <%--}--%>
<%--</script>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<div id='calendar'></div>

<%--<script src="${contextPath}/resources/js/jquery.datetimepicker.full.min.js"></script>--%>
<%--<script src="${contextPath}/resources/js/eventValidator.js"></script>--%>
</body>
</html>
