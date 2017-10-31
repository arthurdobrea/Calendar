<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an event</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>

<body>

<link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>

<a href="/welcome" class="btn">Home</a>
<a href="/index" class="btn">Calendar</a>
<a href="/userControlPanel" class="btn">User Panel</a>
<a href="/createEvent" class="btn">Create new event</a>
<a href="/events" class="btn">All events</a>
<a href="/logout" class="btn">Logout</a>
 <p><p>

<div class="container">

    <form:form method="POST" modelAttribute="eventForm" class="form-signin">
        <h2 class="form-signin-heading">Create your event</h2>
        <spring:bind path="eventName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="eventName" class="form-control" placeholder="Event name"
                            autofocus="true"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="eventType">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:select  path="eventType" class="form-control" >

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

        <spring:bind path="startTime">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="datetime-local" path="startTime" class="form-control" autofocus="true"
                            placeholder="Start time"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="endTime">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="datetime-local" path="endTime" class="form-control" autofocus="true"
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
                          multiple="true" />
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
