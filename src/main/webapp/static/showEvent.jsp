<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ShowEvent</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/event.css" rel="stylesheet">
    <%--<link href="${contextPath}/resources/css/header-style.css" rel="stylesheet">--%>
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>
    <link href="${contextPath}/resources/css/jquery.datetimepicker.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery.datetimepicker.min.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src='${contextPath}/resources/js/jquery.min.js'></script>
    <script src='${contextPath}/resources/js/jquery-ui.min.js'></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/userProfile.js"></script>
    <script src='${contextPath}/resources/js/moment.js'></script>
    <script src='${contextPath}/resources/js/fullcalendar.js'></script>
    <script src='${contextPath}/resources/js/userProfile.js'></script>

    <script src="/resources/js/jquery.min.js"></script>
</head>
<body>

<div class="modal fade" id="EditUser" role="dialog">
    <div class="modal-dialog" align="center" style="margin-top: 100px">
        <!-- Modal content-->
        <div class="edit_profile_modal">
            <div class="modal-header edit_profile_header capital_text">
                <p align="left" class="modal_topic">EVENT<button type="button" class="close_modal" data-dismiss="modal"></button></p>
            </div>
            <div class="modal-body edit_profile_body">

                <form:form method="POST" modelAttribute="eventForm" class="form-signin">
                    <h2 class="form-signin-heading"></h2>

                    <spring:bind path="id">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="hidden" path="id" class="form-control eventId" placeholder="Id of event"
                                        autofocus="true"></form:input>
                        </div>
                    </spring:bind>
                </form:form>

                <p>
                    Name: ${eventForm.title} <br>
                    Type: ${eventForm.eventType}<br>
                    Location: ${eventForm.location}<br>
                    Start time: ${eventForm.start}<br>
                    End time: ${eventForm.end}<br>
                    Description:${eventForm.description}<br>
                    Created at: ${eventForm.eventCreated}<br>
                    Created by: ${eventForm.author.fullName}<br>
                    Will be attended by:<br>
                <ul id="participantsList"></ul>

                <script>
                    $.get("/getParticipantsByEvent", {eventId: $(".eventId").attr("value")}, function(data) {
                        console.log(data);

                        $.each(data, function(i, user) {
                            $("#participantsList").append('<li>' + user.firstname + " " + user.lastname + "</li>");
                        });
                        $(document).ready(function(){
                        });
                    });
                </script>

                <form>
                    <input type="button" value="Back to User Page"
                           onclick="window.location.href='/userPage'" />
                </form>

            </div>
        </div>
    </div>
</div>



</body>
</html>

