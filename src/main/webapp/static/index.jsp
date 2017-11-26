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
    <link href="${contextPath}/resources/css/event.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/event.css" rel="stylesheet">
    <%--<link href="${contextPath}/resources/css/header-style.css" rel="stylesheet">--%>
    <link href='${contextPath}/resources/css/fullcalendar.css' rel='stylesheet' />
    <link href='${contextPath}/resources/css/fullcalendar.print.css' rel='stylesheet' media='print' />
    <link href='${contextPath}/resources/css/calendar.custom.css' rel='stylesheet' />
    <link href="${contextPath}/resources/css/jquery.datetimepicker.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery.datetimepicker.min.css" rel="stylesheet">

    <script src='${contextPath}/resources/js/moment.js'></script>
    <script src='${contextPath}/resources/js/jquery.min.js'></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src='${contextPath}/resources/js/jquery-ui.min.js'></script>
    <script src='${contextPath}/resources/js/fullcalendar.js'></script>
    <script src="${contextPath}/resources/js/ui-bootstrap-tpls-2.5.0.min.js"></script>
    <script src="${contextPath}/resources/js/gcal.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrapmodal.js"></script>

    <c:import url="header.jsp" />

    <div class="panel panel-default box_style_shadow", style="padding-top: 30px; padding-left: 30px; padding-right: 30px; padding-bottom: 30px">
        <%--<div style="border: none">--%>
            <%--<input type="text" id="key-word-search" onkeyup="" placeholder="Enter key word...">--%>
        <%--</div>--%>

        <div class="row">
            <div class="col-md-4" style="border: none">
                <select id="allEventsId" onchange="searchEvents()" class="roles_button_style select">
                    <option value="">All Events</option>
                    <option value="">Events created by me</option>
                    <option value="">Events where I am Invited</option>
                </select>
            </div>

            <div class="col-md-4" style="border: none">
                <select id="searchByTagId" onchange="searchByTag()" class="roles_button_style select ">
                        <option value="">Search by Tag</option>
                        <option value="AM_STREAM">AM Stream</option>
                        <option value="DEVELOPMENT">Development</option>
                        <option value="TESTING">Testing</option>
                        <option value="TOWER">Tower</option>
                        <option value="NBC">NBC</option>
                        <option value="ALL_STAFF">All Staff</option>
                </select>
            </div>

            <div class="col-md-4" style="border: none">
                <select id="searchByTypeId" onchange="searchByType()" class="roles_button_style select">
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


    <div id="container" class="panel panel-default box_style_shadow", style="padding-top: 30px; padding-left: 30px; padding-right: 30px; padding-bottom: 30px; margin-bottom: 20px">

        <div id="calendar"></div>
        <script>
            var calendarInit = false;
        $(document).ready(function() {
            calendarInit = true;
            $('#calendar').fullCalendar({
                timezone: 'local',
                customButtons: {
                    addNew: {
                        text: 'Add event',
                        click:
                            function(event, jsEvent, view) {
                                $('#AddEvent').modal();
                            }
                    }
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
        </script>

    <script>
        function searchEvents() {
            var input = document.getElementById("allEventsId");
//            var x = '/json/getEventsByTag?tag=' + $(input).val();
//            console.log(x);
//            var calendar = $('#calendar');

            if (calendarInit == true)
            {
                $('#calendar').fullCalendar('destroy');
            }

            calendarInit = true;
            $(document).ready(function () {
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
                    events: {url: '/json/getEventsByAuthor?id=' + $(input).val()},
//                    paramName: "TOWER",
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
            });
        }
    </script>


        <script>
        function searchByTag() {
            var input = document.getElementById("searchByTagId");
            var x = '/json/getEventsByTag?tag=' + $(input).val();
            console.log(x);
//            var calendar = $('#calendar');

            if (calendarInit == true)
            {
                $('#calendar').fullCalendar('destroy');
            }

            calendarInit = true;
            $(document).ready(function () {
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
                    events: {url: '/json/getEventsByTag?tag=' + $(input).val()},
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
                var calen = $('#calendar');
                container.append(calen);
            });
        }
        </script>

    <script>
        function searchByType() {
            var input1 = document.getElementById("searchByTypeId");
            var x = '/json/getEventsByType?type=' + $(input1).val();
           console.log(x);
//            var calendar = $('#calendar');

                if (calendarInit == true)
                {
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
                    events: {url: '/json/getEventsByType?type=' + $(input1).val()},
                    eventClick: function (event, jsEvent, qview) {
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
    </script>

        </div>

    <style>
        #calendar {
            max-width: 1500px;
            margin: 0 auto;
        }
    </style>
</head>
<body>

    <%--<c:import url="header.jsp" />--%>
    <%--&lt;%&ndash;<jsp:include page="header.jsp"/>&ndash;%&gt;--%>

<%--<a href="/welcome" class="btn">Home</a>--%>
<%--<a href="/index" class="btn">Calendar</a>--%>
<%--<a href="/events" class="btn">All events</a>--%>
<%--<a href="/tags" class="btn">Tags</a>--%>
<%--<a href="/mailing" class="btn">Mail to all</a>--%>
<%--<a href="/userPage" class="btn">User Page</a>--%>
<%--<c:if test="${pageContext.request.isUserInRole('ADMIN')}">--%>
    <%--<a href="/admin" class="btn">Admin page</a>--%>
<%--</c:if>--%>
<%--<c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">--%>
    <%--<a href="/admin" class="btn">Admin page</a>--%>
<%--</c:if>--%>
<%--<a href="/userControlPanel" class="btn">User Panel</a>--%>
<%--<a href="/logout" class="btn">Logout</a>--%>
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

    <script>
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
            </div>
        </div>
    </div>
</div>

    <%--<!-- Modal -->--%>
    <%--<div class="modal fade" id="AddEvent" role="dialog">--%>
        <%--<div class="modal-dialog modal-lg">--%>
            <%--<!-- Modal content-->--%>
            <%--<div class="modal-content">--%>
                <%--<div class="modal-header">--%>
                    <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                    <%--<h4 class="modal-title">ADD EVENT</h4>--%>
                <%--</div>--%>
                <%--<div class="modal-body">--%>


                    <%--<div class="container">--%>
                        <%--<form action="${contextPath}/createEvent" method="POST" htmlEscape="true">--%>
                            <%--<div class="event-form">--%>

                                <%--<div class="row" id="leftblock" style="padding-right: 15px">--%>
                                    <%--<div class="col-sm-6">--%>
                                        <%--<div class="form-group">--%>
                                            <%--<label for="ev-title">TITLE</label>--%>
                                            <%--<input type="text" class="form-control" id="ev-title"--%>
                                                   <%--placeholder="Enter title">--%>
                                        <%--</div>--%>
                                        <%--<div class="form-group">--%>
                                            <%--<label for="ev-location">LOCATION</label>--%>
                                            <%--<input type="text" class="form-control" id="ev-location"--%>
                                                   <%--placeholder="Enter Location">--%>
                                        <%--</div>--%>
                                        <%--<div class="form-group">--%>
                                            <%--<label for="ev-type">EVENT TYPE</label>--%>
                                            <%--<select class="form-control" id="ev-type">--%>
                                                <%--<option value="">Select Event type</option>--%>
                                                <%--<c:forEach items="${eventTypes}" var="et">--%>
                                                    <%--<option value="${et}">${et.view()}</option>--%>
                                                <%--</c:forEach>--%>
                                            <%--</select>--%>
                                        <%--</div>--%>
                                        <%--<div class="row">--%>
                                            <%--<div class="col-sm-6">--%>
                                                <%--<div class="form-group">--%>
                                                    <%--<label for="ev-start-date">START DATE</label>--%>
                                                    <%--<input type="date" class="form-control" id="ev-start-date"--%>
                                                           <%--placeholder="Choose date... ">--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                            <%--<div class="col-sm-6">--%>
                                                <%--<div class="form-group">--%>
                                                    <%--<label for="ev-end-date">END DATE</label>--%>
                                                    <%--<input type="date" class="form-control" id="ev-end-date"--%>
                                                           <%--placeholder="Choose date... ">--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>

                                        <%--<div class="row">--%>
                                            <%--<div class="col-sm-12" style="text-align: center; bottom:10px">--%>
                                                <%--<div class="checkbox-group" id="subs-checkbox">--%>
                                                    <%--<label class="checkbox-inline">--%>
                                                        <%--<input type="checkbox" name="checkParticipants" checked/>Send--%>
                                                        <%--emails to--%>
                                                        <%--participants</label>--%>
                                                    <%--<label class="checkbox-inline">--%>
                                                        <%--<input type="checkbox" name="checkSubscribe" checked/>Send--%>
                                                        <%--emails to subscribers--%>
                                                    <%--</label>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>

                                    <%--</div>--%>


                                    <%--<div class="row" id="rightblock">--%>
                                        <%--<div class="col-sm-6">--%>
                                            <%--<div class="form-group textarea-group">--%>
                                                <%--<label for="ev-description">DESCRIPTION</label>--%>
                                                <%--<textarea class="form-control" rows="3" id="ev-description"></textarea>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="col-sm-6">--%>
                                            <%--<div class="form-group participant-group">--%>
                                                <%--<div class="input-group">--%>

                                                    <%--<input type="text" id="w-input-search" value=""--%>
                                                           <%--class="form-control" placeholder="Enter name...">--%>
                                                    <%--<span class="input-group-btn" style="text-align: right">--%>
                            <%--<button class="btn btn-secondary" type="button" id="span-btn-search">&#128269</button>--%>
                            <%--</span>--%>

                                                <%--</div>--%>
                                                <%--<label for="t-participants">PARTICIPANTS</label>--%>
                                                <%--<textarea class="form-control" name="participants" id="t-participants"--%>
                                                          <%--rows="3" required></textarea>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>


                                <%--<div class="row">--%>
                                    <%--<div class="col-sm-12">--%>
                                        <%--<label for="tag-checkbox" id="tag-box-label">TAGS</label>--%>
                                        <%--<div class="checkbox-group" style="text-align: center" id="tag-checkbox">--%>
                                            <%--<c:forEach items="${tags}" var="tag">--%>
                                                <%--<label class="checkbox-inline" style="color:${tag.tag.color()}">--%>
                                                    <%--<input type="checkbox" name="checkboxTags"--%>
                                                           <%--id="checkboxTag"> ${tag.tag.view()}--%>
                                                <%--</label>--%>
                                            <%--</c:forEach>--%>

                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>


                                <%--<div class="row">--%>
                                    <%--<div class="col-sm-12" style="text-align: center">--%>
                                        <%--<input type="submit" id="sendButton" value="ADD">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                        <%--</form>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>


    <!-- Modal -->
    <!-- Modal for adding a new event-->
    <div class="modal fade" id="AddEvent" role="dialog">
        <div class="modal-dialog modal-lg">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Add new event</h4>
                </div>
                <div class="modal-body">
                    <form:form method="POST" action="${contextPath}/index" modelAttribute="eventForm" class="form-signin" htmlEscape="true">
                        <spring:bind path="title">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input type="text" path="title" class="form-control" placeholder="Event name"
                                            autofocus="true" required="true"></form:input>
                            </div>
                        </spring:bind>
                        <spring:bind path="eventType">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:select  path="eventType" class="form-control" required="true">
                                    <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
                                        <a href="/admin" class="btn">Admin page</a>
                                    </c:if>
                                    <c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">
                                        <a href="/admin" class="btn">Admin page</a>
                                    </c:if>

                                    <option value="">Select Event Type</option>
                                    <option value="MEETING">Meeting</option>
                                    <option value="TRAINING">Training</option>
                                    <option value="STANDUP">Stand up</option>
                                    <option value="OFFLINE">Offline</option>
                                    <option value="TEAM_BUILDING">Team building</option>
                                    <option value="WORKSHOP">Workshop</option>
                                    <option value="OTHER">Other</option>
                                </form:select>
                            </div>
                        </spring:bind>
                        <spring:bind path="location">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input type="text" path="location" class="form-control"
                                            placeholder="Location of the event"
                                            autofocus="true" required="true"></form:input>
                            </div>
                        </spring:bind>
                        <spring:bind path="start">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input id="datetimepicker1h" type="hidden" path="start"></form:input>

                            </div>
                        </spring:bind>
                        <input type="text" id="datetimepicker1" class="form-control" required="true">

                        <spring:bind path="end">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input id="datetimepicker2h" type="hidden" path="end"></form:input>
                            </div>
                        </spring:bind>
                        <input type="text" id="datetimepicker2" class="form-control" required="true">



                        <spring:bind path="allDay">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input id="all-dayh" type="hidden" path="allDay"></form:input>
                            </div>
                        </spring:bind>
                        <label><input type="checkbox" id="all-day" path="allDay" onclick="if(this.checked) {allDayChecked();} else {allDayUnchecked();}">All day event</label>


                        <spring:bind path="description">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:textarea type="textarea" rows="7" path="description" class="form-control" placeholder="Description"
                                               autofocus="true"></form:textarea>
                            </div>
                        </spring:bind>
                        <spring:bind path="participants">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:select path = "participants" cssClass="form-control" itemLabel="fullName" itemValue="id" items = "${eventForm.participants}"
                                             multiple="true" required="true" />
                            </div>
                        </spring:bind>

                        <div align="left">
                            <label><input type="checkbox" name="checkParticipants" checked/>Send emails to participants</label><br>
                            <label><input type="checkbox" name="checkSubscribe" checked/>Send emails to subscribers</label>
                        </div>

                        <button class="btn btn-lg btn-primary btn-block" type="submit" onmouseover ="eventDateTime()">Submit</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
    <div id='calendar'></div>

    <script src="${contextPath}/resources/js/jquery.datetimepicker.full.min.js"></script>
    <script src="${contextPath}/resources/js/eventValidator.js"></script>
</body>
</html>
