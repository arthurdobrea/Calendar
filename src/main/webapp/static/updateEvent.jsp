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
    <meta name="description">
    <meta name="author">

    <title>Create an event</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery.datetimepicker.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery.datetimepicker.min.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="${contextPath}/resources/js/eventValidator.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</head>

<body onload="allDayUnchecked()">
<div class="container">
    <form:form method="POST" modelAttribute="eventForm" class="form-signin">
        <h2 class="form-signin-heading">Update your event</h2>

        <spring:bind path="id">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="hidden" path="id" class="form-control" autofocus="true" required="true"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="title">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="title" class="form-control" autofocus="true" required="true"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="eventType">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:select  path="eventType" class="form-control" required="true" >

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
                <form:input type="text" path="location" class="form-control" placeholder="Location of the event" autofocus="true" required="true"></form:input>
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

        <label><input type="checkbox" id="all-day" onclick="if(this.checked) {allDayChecked();} else {allDayUnchecked();}">All day event</label>

        <spring:bind path="description">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:textarea type="textarea" rows="7" path="description" class="form-control" placeholder="Description" autofocus="true"></form:textarea>
            </div>
        </spring:bind>

        <spring:bind path="participants">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:select path = "participants" itemLabel="fullName" itemValue="id" items = "${eventForm.participants}" multiple="true" />
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit" onmouseover ="eventDateTime()">Update</button>
    </form:form>
</div>

<script src="${contextPath}/resources/js/jquery.datetimepicker.full.min.js"></script>
</body>
</html>
