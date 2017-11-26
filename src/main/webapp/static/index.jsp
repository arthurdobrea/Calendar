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
    <link href='${contextPath}/resources/css/fullcalendar.css' rel='stylesheet'/>
    <link href='${contextPath}/resources/css/fullcalendar.print.css' rel='stylesheet' media='print'/>
    <link href="${contextPath}/resources/css/autocomplete.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/event.css" rel="stylesheet">
    <script src='${contextPath}/resources/js/moment.min.js'></script>
    <script src='${contextPath}/resources/js/jquery.min.js'></script>
    <script src='${contextPath}/resources/js/jquery-ui.min.js'></script>
    <script src='${contextPath}/resources/js/fullcalendar.js'></script>
    <script src="${contextPath}/resources/js/ui-bootstrap-tpls-2.5.0.min.js"></script>
    <script src="${contextPath}/resources/js/gcal.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrapmodal.js"></script>
    <script src='${contextPath}/resources/js/jquery-ui.min.js'></script>
    <%--<script src="${contextPath}/resources/scripts/jquery.1.10.2.min.js"></script>--%>

    <script src="${contextPath}/resources/scripts/jquery.autocomplete.min.js"></script>
        <script>


            $(document).ready(function () {
                $('#calendar').fullCalendar({
                    customButtons: {
                        addNew: {
                            text: 'Add event',
                            click:
                                function (event, jsEvent, view) {
                                    $('#AddEvent').modal();
                                }
                        }
                    },
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'addNew month,agendaWeek,agendaDay,listWeek'
                    },
                    defaultDate: $('#calendar').fullCalendar('today'),
                    weekNumbers: "ISO",
                    navLinks: true,
                    eventLimit: false,
                    timeFormat: 'h:mma',
                    events:
                        {url: '/json/allEvents'},
                    eventClick: function (event, jsEvent, view) {
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


<!-- Button HTML (to Trigger Modal) -->

<a href="remote.html" role="button" class="btn btn-large btn-primary" data-toggle="modal" data-target="#myModal">Launch Demo Modal</a>




<c:import url="header.jsp" />

<p>

<div id='calendar'></div>

</body>
</html>
