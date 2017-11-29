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
    <link href="${contextPath}/resources/css/event.css" rel="stylesheet">
    <%--<link href="${contextPath}/resources/css/header-style.css" rel="stylesheet">--%>
    <link href='${contextPath}/resources/css/fullcalendar.css' rel='stylesheet' />
    <link href='${contextPath}/resources/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
    <link href='${contextPath}/resources/css/calendar.custom.css' rel='stylesheet' />
    <link href="${contextPath}/resources/css/jquery.datetimepicker.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery.datetimepicker.min.css" rel="stylesheet">

    <script src='${contextPath}/resources/js/moment.js'></script>
    <script src='${contextPath}/resources/js/jquery.min.js'></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src='${contextPath}/resources/js/jquery-ui.min.js'></script>
    <script src='${contextPath}/resources/js/fullcalendar.js'></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
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
                    timezone: 'local',
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

    <div id='calendar'></div>

    <script src="${contextPath}/resources/js/jquery.datetimepicker.full.js"></script>
    <script src="${contextPath}/resources/js/eventValidator.js"></script>
</body>
</html>
