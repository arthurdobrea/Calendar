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

    <link href='${contextPath}/resources/css/fullcalendar.css' rel='stylesheet' />
    <link href="${contextPath}/resources/css/autocomplete.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <link href="${contextPath}/resources/css/event.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/serghei.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>
    <link href="${contextPath}/resources/css/jquery.datetimepicker.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery.datetimepicker.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/calendar.custom.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/header-style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">

    <script src="<c:url value="/resources/scripts/sockjs-0.3.4.min.js"/>"></script>
    <script src="<c:url value="/resources/scripts/stomp.js"/>"></script>
    <script src="<c:url value="/resources/scripts/connectToServer.js"/>"></script>

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
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="${contextPath}/resources/js/userProfile.js"></script>

    <style>
        #calendar {
            max-width: 1500px;
            margin: 0 auto;
        }
    </style>

    <script>
        var calendarInit = false;
        $(document).ready(function() {
            calendarInit = true;
            $('#calendar').fullCalendar({
                customButtons: {
                },
                header: {
                    left: 'prev,today,next',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay,listWeek'
                },

                eventRender: function (event, element) {
                    element.attr('href', 'javascript:void(0);');
                    element.click(function() {
                        $("#eventLocation").html(event.location);
                        $("#eventAuthor").html(event.author);
                        $("#startTime").html(moment(event.start).format('MMM Do h:mm A'));
                        $("#endTime").html(moment(event.end).format('MMM Do h:mm A'));
                        $("#eventInfo").html(event.description);
                        $("#eventContent").dialog({ modal: true, title: event.title, width:350});
                    });
                },

                businessHours: {

                    dow: [ 1, 2, 3, 4, 5 ],

                    start: '10:00',
                    end: '17:00',
                },
                firstDay:1,
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
                timeFormat: 'h:mma',
                timezone: 'local',
                allDay: false,
                events: {url:'/json/allEvents'},
            });
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
                eventRender: function (event, element) {
                    element.attr('href', 'javascript:void(0);');
                    element.click(function() {
                        $("#eventLocation").html(event.location);
                        $("#eventAuthor").html(event.author);
                        $("#startTime").html(moment(event.start).format('MMM Do h:mm A'));
                        $("#endTime").html(moment(event.end).format('MMM Do h:mm A'));
                        $("#eventContent").dialog({ modal: true, title: event.title, width:300});
                    });
                },

                businessHours: {

                    dow: [ 1, 2, 3, 4, 5 ],

                    start: '10:00',
                    end: '17:00',
                },
                timezone: 'local',
                allDay: false,
                firstDay:1,
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
            });
            var container = $('#container');
            var calen = $('#calendar')
            container.append(calen);
        }
    </script>

    <c:import url="header.jsp" />
</head>
<body>
<input type="hidden" id="userId" value="${userId}">

<div class="panel panel-default box_style_shadow", style="padding: 20px;">
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


<div id="container" class="panel panel-default box_style_shadow1" style="padding: 20px;margin-bottom: 20px">

    <div id="calendar"></div>

</div>

<div class="panel panel-default box_style_shadow", style="padding: 0 20px">
    <div class="legend-dot"><span id="meeting-color"></span> Meeting</div>
    <div class="legend-dot"><span id="training-color"></span> Training</div>
    <div class="legend-dot"><span id="stand-up-color"></span> Stand Up</div>
    <div class="legend-dot"><span id="offline-color"></span> Offline</div>
    <div class="legend-dot"><span id="team-building-color"></span> Team Building</div>
    <div class="legend-dot"><span id="workshop-color"></span> Workshop</div>
    <div class="legend-dot"><span id="other-color"></span> Other</div>
</div>



<div class="show_event_modal"></div>

<div id="eventContent" title="Event Details" style="display:none;">
    <table class="pop_up_event">
        <tr>
            <td><strong class="endava_grey_text">Location:</strong></td>
            <td><span id="eventLocation"></span></td>
        </tr>
        <tr>
            <td><strong class="endava_grey_text">Start:</strong></td>
            <td><span id="startTime"></span></td>
        </tr>
        <tr>
            <td><strong class="endava_grey_text">End:</strong></td>
            <td><span id="endTime"></span></td>
        </tr>
        <tr>
            <td><strong class="endava_grey_text">Author:</strong></td>
            <td><span id="eventAuthor"></span></td>
        </tr>
    </table>
</div>

<script src="${contextPath}/resources/js/jquery.datetimepicker.full.min.js"></script>
<script src="${contextPath}/resources/js/eventValidator.js"></script>
</body>
</html>
