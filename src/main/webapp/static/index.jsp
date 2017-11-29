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
                height:500,
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

<div class="panel panel-default box_style_shadow1", style="padding-top: 30px; padding-left: 30px; padding-right: 30px; padding-bottom: 30px">
    <%--<div style="border: none">--%>
    <%--<input type="text" id="key-word-search" onkeyup="" placeholder="Enter key word...">--%>
    <%--</div>--%>

    <div class="row">
        <div class="col-md-4" style="border: none">
            <select id="allEventsId" onchange="searchEvents()" class="roles_button_style1">
                <option value=""style="font-size: 15px">All Events</option>
                <option value="EventsCreatedByMe" style="font-size: 15px">Events created by me</option>
                <option value="EventsWhereIamInvited" style="font-size: 15px">Events where I am Invited</option>
            </select>
        </div>

        <div class="col-md-4" style="border: none">
            <select id="searchByTagId" onchange="searchEvents()" class="roles_button_style1">
                <option value="" style="font-size: 15px">Search by Tag</option>
                <option value="APPLICATION_MANAGEMENT" style="font-size: 15px">Application Management</option>
                <option value="DEVELOPMENT" style="font-size: 15px">Development</option>
                <option value="TESTING" style="font-size: 15px">Testing</option>
                <option value="TOWER" style="font-size: 15px">Tower</option>
                <option value="NBC" style="font-size: 15px">NBC</option>
                <option value="ALL_STAFF" style="font-size: 15px">All Staff</option>
            </select>
        </div>

        <div class="col-md-4" style="border: none">
            <select id="searchByTypeId" onchange="searchEvents()" class="roles_button_style1">
                <option value="" style="font-size: 15px">Search by Type</option>
                <option value="MEETING" style="font-size: 15px">Meeting</option>
                <option value="TRAINING" style="font-size: 15px">Training</option>
                <option value="STANDUP" style="font-size: 15px">Stand up</option>
                <option value="OFFLINE" style="font-size: 15px">Offline</option>
                <option value="TEAM_BUILDING" style="font-size: 15px">Team building</option>
                <option value="WORKSHOP" style="font-size: 15px">Workshop</option>
                <option value="OTHER" style="font-size: 15px">Other</option>
            </select>
        </div>
    </div>

</div>


<div id="container" class="panel panel-default box_style_shadow1" style="padding-top: 30px; padding-left: 30px; padding-right: 30px; padding-bottom: 30px; margin-bottom: -50px">

    <div id="calendar"></div>

</div>

<div class="container-fluid" style="margin-top: -10px;">
    <ul id="legend">
        <li style="font-size: 15px">Meeting: <span class="glyphicon glyphicon-one-fine-dot" style=" color: #b61667"></span></li>
        <li style="font-size: 15px">Training:  <span class="glyphicon glyphicon-one-fine-dot" style="color: #00897b"></span>  </li>
        <li style="font-size: 15px">Stand Up:  <span class="glyphicon glyphicon-one-fine-dot" style="color:#992f99"></span>  </li>
        <li style="font-size: 15px">Offline:   <span class="glyphicon glyphicon-one-fine-dot" style="color: #1a5a8f"></span>  </li>
        <li style="font-size: 15px">Team Building:<span class="glyphicon glyphicon-one-fine-dot" style="color: #b61616"></span></li>
        <li style="font-size: 15px">Workshop:   <span class="glyphicon glyphicon-one-fine-dot" style="color: #1bb7de"></span>  </li>
        <li style="font-size: 15px">Other:     <span class="glyphicon glyphicon-one-fine-dot" style="color: #13A04C"></span>   </li>
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

<script src="${contextPath}/resources/js/jquery.datetimepicker.full.min.js"></script>
<script src="${contextPath}/resources/js/eventValidator.js"></script>
<script src="${contextPath}/resources/scripts/jquery.autocomplete.min.js"></script>
</body>
</html>
