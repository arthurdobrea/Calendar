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
    <link href="${contextPath}/resources/css/header-style.css" rel="stylesheet">

    <style>
        #calendar {
            max-width: 1500px;
            margin: 0 auto;
        }
    </style>

    <script src='${contextPath}/resources/js/moment.min.js'></script>
    <script src='${contextPath}/resources/js/jquery.min.js'></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src='${contextPath}/resources/js/jquery-ui.min.js'></script>
    <script src='${contextPath}/resources/js/fullcalendar.js'></script>
    <script src="${contextPath}/resources/js/ui-bootstrap-tpls-2.5.0.min.js"></script>
    <script src="${contextPath}/resources/js/gcal.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrapmodal.js"></script>
    <script src="${contextPath}/resources/scripts/jquery.autocomplete.min.js"></script>

    <script>
        var calendarInit = false;
        $(document).ready(function() {
            calendarInit = true;
            $('#calendar').fullCalendar({
                customButtons: {
//                    addNew: {
//                        text: 'Add event',
//                        click:
//                                function(event, jsEvent, view) {
//                                    $('#AddEvent').modal();
//                                }
//                    }
                },
                header: {
                    left: 'prev,today,next',
                    center: 'title',
                    right: 'addNew month,agendaWeek,agendaDay,listWeek'
                },
                defaultDate: $('#calendar').fullCalendar('today'),
                weekNumbers: "ISO",
                navLinks: true,
                eventLimit: true,
                views: {
                    agenda: {
                        eventLimit: 3,
                    }
                },
                height:600,
                fixedWeekCount:false,
//                  themeSystem: 'bootstrap3',
                timeFormat: 'h:mma',
                events: {url:'/json/allEvents'},
                eventClick:  function (event, jsEvent, view) {
                    console.log(event);
                    console.log(jsEvent);
                    console.log(view);

                    printEventDataInModal(event.id);
                    $('#eventPage').modal();
                }
            });
//          $('#calendar').fullCalendar( 'gotoDate', currentDate);
            var container=$('#container');
            var calen = $('#calendar')
            container.append(calen);
        });

        function searchEvents() {

            // get selected values from dropdowns (3 values)
            // call a method from json controller and send this 3 params
            // in json method filter by these params

            var inputTag = document.getElementById("searchByTagId");
            var inputType = document.getElementById("searchByTypeId");

            var tag = inputTag.options[inputTag.selectedIndex].value;
            var eventType = inputType.options[inputType.selectedIndex].value;

            var inputUser = document.getElementById("allEventsId");
            var selectedOption = inputUser.options[inputUser.selectedIndex].value;

            var userIdVal = $("#userId").val();

            var authorVal = null;
            var particVal = null;
            if (selectedOption == "EventsCreatedByMe")
            {
                authorVal = userIdVal;
            }
            else if (selectedOption == "EventsWhereIamInvited")
            {
                particVal = userIdVal;
            }


            if (calendarInit == true) {
                $('#calendar').fullCalendar('destroy');
            }

            calendarInit = true;
            $('#calendar').fullCalendar({
                customButtons: {
                },
                header: {
                    left: 'prev,today,next',
                    center: 'title',
                    right: 'addNew month,agendaWeek,agendaDay,listWeek'
                },
                defaultDate: $('#calendar').fullCalendar('today'),
                weekNumbers: "ISO",
                navLinks: true,
                eventLimit: true,
                views: {
                    agenda: {
                        eventLimit: 3,
                    }
                },
                height:650,
                fixedWeekCount:false,
//                themeSystem: 'bootstrap3',
                timeFormat: 'h:mma',
                events:
                {
                    url: '/json/searchEvents/',
                    data: {
                        tag: tag,
                        type: eventType,
                        authorId: authorVal,
                        participantId: particVal
                    }
                },
                eventClick: function (event, jsEvent, view) {
                    console.log(event);
                    console.log(jsEvent);
                    console.log(view);

                    printEventDataInModal(event.id);
                    $('#eventPage').modal();

                }
            });
//                 $('#calendar').fullCalendar( 'gotoDate', currentDate);
            var container = $('#container');
            var calen = $('#calendar')
            container.append(calen);


        }

        function printEventDataInModal(eventId)
        {
            $.get("/json/getEvent", {eventId: eventId}, function(data) {
                console.log(data);

                $("#evName").text(data.title);
                $("#evType").text(data.eventType);
                $("#evLocation").text(data.location);
                $("#evStart").text(data.start);
                $("#evEnd").text(data.end);
                $("#evDescription").text(data.description);
                $("#evCreated").text(data.eventCreated);
                $("#evAuthor").text(data.author.firstname + data.author.lastname);
            });
            $.get("/getParticipantsByEvent", {eventId: eventId}, function(data) {
                console.log(data);
                $("#participantsList").text("");
                $.each(data, function(i, user) {
                    $("#participantsList").append('<li>' + user.firstname + " " + user.lastname + "</li>");
                });
            });
        }
    </script>

    <c:import url="header.jsp" />
</head>
<body>
<input type="hidden" id="userId" value="${userId}">

<div class="panel panel-default box_style_shadow", style="padding-top: 30px; padding-left: 30px; padding-right: 30px; padding-bottom: 30px">
    <%--<div style="border: none">--%>
    <%--<input type="text" id="key-word-search" onkeyup="" placeholder="Enter key word...">--%>
    <%--</div>--%>

    <div class="row">
        <div class="col-md-4" style="border: none">
            <select id="allEventsId" onchange="searchEvents()" class="roles_button_style">
                <option value="">All Events</option>
                <option value="EventsCreatedByMe">Events created by me</option>
                <option value="EventsWhereIamInvited">Events where I am Invited</option>
            </select>
        </div>

        <div class="col-md-4" style="border: none">
            <select id="searchByTagId" onchange="searchEvents()" class="roles_button_style">
                <option value="">Search by Tag</option>
                <option value="APPLICATION_MANAGEMENT">Application Management</option>
                <option value="DEVELOPMENT">Development</option>
                <option value="TESTING">Testing</option>
                <option value="TOWER">Tower</option>
                <option value="NBC">NBC</option>
                <option value="ALL_STAFF">All Staff</option>
            </select>
        </div>

        <div class="col-md-4" style="border: none">
            <select id="searchByTypeId" onchange="searchEvents()" class="roles_button_style">
                <option value="">Search by Type</option>
                <option value="MEETING">Meeting</option>
                <option value="TRAINING">Training</option>
                <option value="STANDUP">Stand up</option>
                <option value="OFFLINE">Offline</option>
                <option value="TEAM_BUILDING">Team building</option>
                <option value="WORKSHOP">Workshop</option>
                <option value="OTHER">Other</option>
            </select>
        </div>
    </div>

</div>


<div id="container" class="panel panel-default box_style_shadow" style="padding-top: 30px; padding-left: 30px; padding-right: 30px; padding-bottom: 30px; margin-bottom: -50px">

    <div id="calendar"></div>

</div>

<div class="container-fluid" style="margin-top: -10px;">
    <ul id="legend">
        <li style="font-size: 15px">Meeting: <span class="glyphicon glyphicon-one-fine-dot" style=" color: #C71585"></span></li>
        <li style="font-size: 15px">Training:  <span class="glyphicon glyphicon-one-fine-dot" style="color: #00008B"></span>  </li>
        <li style="font-size: 15px">Stand Up:  <span class="glyphicon glyphicon-one-fine-dot" style="color:#2E8B57 "></span>  </li>
        <li style="font-size: 15px">Offline:   <span class="glyphicon glyphicon-one-fine-dot" style="color: #48D1CC"></span>  </li>
        <li style="font-size: 15px">Team Building:<span class="glyphicon glyphicon-one-fine-dot" style="color: #FF4500"></span></li>
        <li style="font-size: 15px">Workshop:   <span class="glyphicon glyphicon-one-fine-dot" style="color: #FF00FF"></span>  </li>
        <li style="font-size: 15px">Other:     <span class="glyphicon glyphicon-one-fine-dot" style="color: #F08080"></span>   </li>
    </ul>
</div>

<p>
<p>
    <!-- Modal -->
    <div class="modal fade" id="eventPage" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Event page</h4>
                </div>
                <div class="modal-body">
                    <form:form modelAttribute="eventForm" class="form-signin">
                    <h2 class="form-signin-heading"></h2>

                    <spring:bind path="id">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="hidden" path="id" class="form-control eventId" placeholder="Id of event"
                                    autofocus="true"></form:input>
                    </div>
                    </spring:bind>
                    </form:form>

<p>
    Name: <span id="evName"></span> <br>
    Type: <span id="evType"></span><br>
    Location: <span id="evLocation"></span> <br>
    Start time: <span id="evStart"></span> <br>
    End time: <span id="evEnd"></span> <br>
    Description:<span id="evDescription"></span> <br>
    Created at: <span id="evCreated"></span> <br>
    Created by: <span id="evAuthor"></span> <br>
    Will be attended by:<br>
<ul style = "list-style: none"; id="participantsList"></ul>
</p>
</div>
</div>
</div>
</div>
<%--<!-- Modal -->--%>
<%--<div class="modal fade" id="AddEvent" role="dialog">--%>
<%--<div class="modal-dialog">--%>
<%--<!-- Modal content-->--%>
<%--<div class="modal-content">--%>
<%--<div class="modal-header">--%>
<%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
<%--<h4 class="modal-title">Add new event</h4>--%>
<%--</div>--%>
<%--<div class="modal-body">--%>
<%--<form:form method="POST" action="${contextPath}/index" modelAttribute="eventForm" class="form-signin" htmlEscape="true">--%>
<%--<spring:bind path="title">--%>
<%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
<%--<form:input type="text" path="title" class="form-control" placeholder="Event name"--%>
<%--autofocus="true" required="true"></form:input>--%>
<%--</div>--%>
<%--</spring:bind>--%>
<%--<spring:bind path="eventType">--%>
<%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
<%--<form:select  path="eventType" class="form-control" required="true">--%>
<%--<c:if test="${pageContext.request.isUserInRole('ADMIN')}">--%>
<%--<a href="/admin" class="btn">Admin page</a>--%>
<%--</c:if>--%>
<%--<c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">--%>
<%--<a href="/admin" class="btn">Admin page</a>--%>
<%--</c:if>--%>

<%--<option value="">Select Event Type</option>--%>
<%--<option value="MEETING">Meeting</option>--%>
<%--<option value="TRAINING">Training</option>--%>
<%--<option value="STANDUP">Stand up</option>--%>
<%--<option value="OFFLINE">Offline</option>--%>
<%--<option value="TEAM_BUILDING">Team building</option>--%>
<%--<option value="WORKSHOP">Workshop</option>--%>
<%--<option value="OTHER">Other</option>--%>
<%--</form:select>--%>
<%--</div>--%>
<%--</spring:bind>--%>
<%--<spring:bind path="location">--%>
<%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
<%--<form:input type="text" path="location" class="form-control"--%>
<%--placeholder="Location of the event"--%>
<%--autofocus="true" required="true"></form:input>--%>
<%--</div>--%>
<%--</spring:bind>--%>
<%--<spring:bind path="start">--%>
<%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
<%--<form:input id="datetimepicker1h" type="hidden" path="start"></form:input>--%>

<%--</div>--%>
<%--</spring:bind>--%>
<%--<input type="text" id="datetimepicker1" class="form-control" required="true">--%>

<%--<spring:bind path="end">--%>
<%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
<%--<form:input id="datetimepicker2h" type="hidden" path="end"></form:input>--%>
<%--</div>--%>
<%--</spring:bind>--%>
<%--<input type="text" id="datetimepicker2" class="form-control" required="true">--%>

<%--<label><input type="checkbox" id="all-day" onclick="if(this.checked) {allDayChecked();} else {allDayUnchecked();}">All day event</label>--%>

<%--<spring:bind path="description">--%>
<%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
<%--<form:textarea type="textarea" rows="7" path="description" class="form-control" placeholder="Description"--%>
<%--autofocus="true"></form:textarea>--%>
<%--</div>--%>
<%--</spring:bind>--%>
<%--<spring:bind path="participants">--%>
<%--<div class="form-group ${status.error ? 'has-error' : ''}">--%>
<%--<form:select path = "participants" cssClass="form-control" itemLabel="fullName" itemValue="id" items = "${eventForm.participants}"--%>
<%--multiple="true" required="true"/>--%>
<%--</div>--%>
<%--</spring:bind>--%>
<%--<button class="btn btn-lg btn-primary btn-block" type="submit" onmouseover ="eventDateTime()">Submit</button>--%>
<%--</form:form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div id="container1" class="panel panel-default box_style_shadow", style="padding-top: 30px; padding-left: 30px; padding-right: 30px; padding-bottom: 30px; margin-bottom: 20px">--%>
<%----%>
<%--</div>--%>


<script src="${contextPath}/resources/js/jquery.datetimepicker.full.min.js"></script>
<script src="${contextPath}/resources/js/eventValidator.js"></script>
<script src="${contextPath}/resources/scripts/jquery.autocomplete.min.js"></script>
</body>
</html>
