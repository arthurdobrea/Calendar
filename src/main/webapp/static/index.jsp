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
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href='${contextPath}/resources/css/fullcalendar.css' rel='stylesheet' />
    <%--<link href='${contextPath}/resources/css/fullcalendar.print.css' rel='stylesheet' media='print' />--%>
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
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrapmodal.js"></script>
    <script src="${contextPath}/resources/scripts/jquery.autocomplete.min.js"></script>

    <script>
        var calendarInit = false;
        $(document).ready(function() {
            calendarInit = true;
            $('#calendar').fullCalendar({
                timezone: 'local',
                customButtons: {
                },
                header: {
                    left: 'prev,today,next',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay,listWeek'
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
//                    console.log(event);
//                    console.log(jsEvent);
//                    console.log(view);

                    window.location.replace("/showEvent?eventId=" + event.id);
                }
            });
//          $('#calendar').fullCalendar( 'gotoDate', currentDate);
            var container=$('#container');
            var calen = $('#calendar')
            container.append(calen);
        });

        function searchEvents() {
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

                    window.location.replace("/showEvent?eventId=" + event.id);

                }
            });
//                 $('#calendar').fullCalendar( 'gotoDate', currentDate);
            var container = $('#container');
            var calen = $('#calendar')
            container.append(calen);
        }
    </script>

    <c:import url="header.jsp" />
</head>
<body>
<input type="hidden" id="userId" value="${userId}">

<div class="panel panel-default box_style_shadow", style="padding-top:30px; padding-left: 30px; padding-right: 30px; padding-bottom: 30px">
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


<div id="container" class="panel panel-default box_style_shadow1" style="padding-top: 30px; padding-left: 30px; padding-right: 30px; padding-bottom: 30px; margin-bottom: 20px">

    <div id="calendar"></div>

</div>

<div class="panel panel-default box_style_shadow", style="padding-top:0px; padding-left: 30px; padding-right: 30px; padding-bottom: 0px; margin-bottom: 30px">
    <div class="legend-dot"><span id="meeting-color"></span> Meeting</div>
    <div class="legend-dot"><span id="training-color"></span> Training</div>
    <div class="legend-dot"><span id="stand-up-color"></span> Stand Up</div>
    <div class="legend-dot"><span id="offline-color"></span> Offline</div>
    <div class="legend-dot"><span id="team-building-color"></span> Team Building</div>
    <div class="legend-dot"><span id="workshop-color"></span> Workshop</div>
    <div class="legend-dot"><span id="other-color"></span> Other</div>
</div>
</div>

<script src="${contextPath}/resources/js/jquery.datetimepicker.full.min.js"></script>
<script src="${contextPath}/resources/js/eventValidator.js"></script>
<script src="${contextPath}/resources/scripts/jquery.autocomplete.min.js"></script>
</body>
</html>
