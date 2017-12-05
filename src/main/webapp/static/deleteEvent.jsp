<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title>Delete event</title>

    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/serghei.css" rel="stylesheet">
    <link href='${contextPath}/resources/css/fullcalendar.css' rel='stylesheet' />
    <link href='${contextPath}/resources/css/calendar.custom.css' rel='stylesheet' />

    <script src='${contextPath}/resources/js/jquery.min.js'></script>
    <script src='${contextPath}/resources/js/jquery-ui.min.js'></script>
    <script src='${contextPath}/resources/js/fullcalendar.js'></script>
    <script src="${contextPath}/resources/js/bootstrapmodal.js"></script>
</head>
<body>

<!-- Modal for deleting an event-->
<div class="modal fade" id="DeleteEvent" role="dialog">
    <div class="modal-dialog" align="center" style="margin-top: 15%">
        <!-- Modal content-->
        <div class="row delete_event_modal">
            <div class="modal-header delete_event_header endava_grey_text">
                <div align="left" class="modal_topic">DELETE EVENT<button type="button" class="close_modal" data-dismiss="modal"></button></div>
            </div>
            <div class="delete_event_body">
                <form:form method="POST" modelAttribute="eventForm">
                    <div class="for_delete_event endava_grey_text" style="float: left;">Are you sure you want to delete this event?</div>
                    <spring:bind path="id">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="hidden" path="id" class="form-control" placeholder="Id of event"
                                        autofocus="true"></form:input>
                            <div class=" col-sm-12" style="padding: 0">
                                <button class="btn_delete_event_cancel" data-dismiss="modal" style="float: left">NOT TODAY</button>
                                <button class="btn_delete_event_submit" type="submit" style="float: right">DELETE</button>
                            </div>
                        </div>
                    </spring:bind>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>



