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
    <script src='${contextPath}/resources/js/moment.min.js'></script>
    <script src='${contextPath}/resources/js/jquery.min.js'></script>
    <script src='${contextPath}/resources/js/jquery-ui.min.js'></script>
    <script src='${contextPath}/resources/js/fullcalendar.js'></script>
    <script src="${contextPath}/resources/js/ui-bootstrap-tpls-2.5.0.min.js"></script>
    <script src="${contextPath}/resources/js/gcal.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrapmodal.js"></script>

<script>
        $(document).ready(function() {
            $('#calendar').fullCalendar({
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
                    {url:'/json/allEvents'},
                eventClick:  function(event, jsEvent, view) {
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
<a href="/welcome" class="btn">Home</a>
<a href="/index" class="btn">Calendar</a>
<a href="/userControlPanel" class="btn">User Panel</a>
<a href="/events" class="btn">All events</a>
<a href="/tags" class="btn">Tags</a>
<a href="/mailing" class="btn">Mail to all</a>
<a href="/userPage" class="btn">User Page</a>
<a href="/logout" class="btn">Logout</a>
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
<h1> You will see event page right here </h1>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="AddEvent" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Add new event</h4>
            </div>
            <div class="modal-body">
                    <form:form method="POST" action="${contextPath}/index" modelAttribute="eventForm" class="form-signin">
                        <spring:bind path="title">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input type="text" path="title" class="form-control" placeholder="Event name"
                                            autofocus="true"></form:input>
                            </div>
                        </spring:bind>
                        <spring:bind path="eventType">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:select  path="eventType" class="form-control" >
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
                                            autofocus="true"></form:input>
                            </div>
                        </spring:bind>
                        <spring:bind path="start">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input type="datetime-local" path="start" class="form-control" autofocus="true"
                                            placeholder="Start time"></form:input>
                            </div>
                        </spring:bind>
                        <spring:bind path="end">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input type="datetime-local" path="end" class="form-control" autofocus="true"
                                            placeholder="End time"></form:input>
                            </div>
                        </spring:bind>
                        <spring:bind path="description">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:textarea type="textarea" rows="7" path="description" class="form-control" placeholder="Description"
                                               autofocus="true"></form:textarea>
                            </div>
                        </spring:bind>
                        <spring:bind path="participants">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:select path = "participants" cssClass="form-control" itemLabel="fullName" itemValue="id" items = "${eventForm.participants}"
                                             multiple="true" required="true"/>
                            </div>
                        </spring:bind>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                    </form:form>
            </div>
        </div>
    </div>
</div>
<div id='calendar'></div>
</body>
</html>
