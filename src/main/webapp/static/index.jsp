<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

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
    <link href='${contextPath}/resources/css/fullcalendar.css' rel='stylesheet'/>
    <link href='${contextPath}/resources/css/fullcalendar.print.css' rel='stylesheet' media='print'/>

    <script src='${contextPath}/resources/js/moment.min.js'></script>
    <script src='${contextPath}/resources/js/jquery.min.js'></script>
    <script src='${contextPath}/resources/js/fullcalendar.min.js'></script>
    <script src='${contextPath}/resources/js/calendar.js'></script>
    <script src="${contextPath}/resources/js/angular.min.js"></script>
    <script src="${contextPath}/resources/js/ui-bootstrap-tpls-2.5.0.min.js"></script>
    <script src="${contextPath}/resources/js/gcal.min.js"></script>

    <script>
        $(document).ready(function () {

            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay,listWeek'
                },
                defaultDate: '2017-10-12',
                navLinks: true, // can click day/week names to navigate views
                editable: true,
                eventLimit: true, // allow "more" link when too many events
                events: [
                    {
                        title: 'All Day Event',
                        start: '2017-10-02',
                    },
                    {
                        title: 'Making this project for Endava',
                        start: '2017-10-03',
                        end: '2017-10-6'
                    },
                    {
                        title: 'Long Event',
                        start: '2017-10-07',
                        end: '2017-10-10'
                    },
                    {
                        id: 999,
                        title: 'Repeating Event',
                        start: '2017-10-09T16:00:00'
                    },
                    {
                        id: 999,
                        title: 'Repeating Event',
                        start: '2017-10-16T16:00:00'
                    },
                    {
                        title: 'Conference',
                        start: '2017-10-11',
                        end: '2017-10-13'
                    },
                    {
                        title: 'Meeting',
                        start: '2017-10-12T10:30:00',
                        end: '2017-10-12T12:30:00'
                    },
                    {
                        title: 'Lunch',
                        start: '2017-10-12T12:00:00'
                    },
                    {
                        title: 'Meeting',
                        start: '2017-10-12T14:30:00'
                    },
                    {
                        title: 'Happy Hour',
                        start: '2017-10-12T17:30:00'
                    },
                    {
                        title: 'Dinner',
                        start: '2017-10-12T20:00:00'
                    },
                    {
                        title: 'Birthday Party',
                        start: '2017-10-13T07:00:00'
                    },
                    {
                        title: 'Click for Google',
                        url: 'http://google.com/',
                        start: '2017-10-28'
                    }
                ]
            });

        });

    </script>
    <style>
        ul {

        }

        #calendar {
            max-width: 900px;
            margin: 0 auto;
        }

    </style>
</head>
<body>
<a href="/welcome" class="btn">Home</a>
<a href="/index" class="btn">Calendar</a>
<a href="/userControlPanel" class="btn">User Panel</a>
<a href="/create-event" class="btn">Create new event</a>
<a href="/events" class="btn">All events</a>
<a href="/userPage" class="btn">User Page</a>
<a href="/logout" class="btn">Logout</a>

<div id='calendar'></div>
</body>
</html>
