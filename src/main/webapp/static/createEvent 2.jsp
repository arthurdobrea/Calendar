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

    <link href="${contextPath}/resources/css/autocomplete.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href='${contextPath}/resources/css/fullcalendar.css' rel='stylesheet'/>
    <link href='${contextPath}/resources/css/fullcalendar.print.css' rel='stylesheet' media='print'/>
    <link href="${contextPath}/resources/css/event.css" rel="stylesheet">
    <script src='${contextPath}/resources/js/moment.min.js'></script>
    <script src='${contextPath}/resources/js/jquery.min.js'></script>
    <script src='${contextPath}/resources/js/jquery-ui.min.js'></script>
    <script src='${contextPath}/resources/js/fullcalendar.js'></script>
    <script src="${contextPath}/resources/js/ui-bootstrap-tpls-2.5.0.min.js"></script>
    <script src="${contextPath}/resources/js/gcal.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrapmodal.js"></script>
    <script src="${contextPath}/resources/scripts/jquery.autocomplete.min.js"></script>

    <script>
        $(document).ready(function() {
            $('#w-input-search').autocomplete({
                serviceUrl: "/getUserFullName",
                onSelect: function(inp){
                    console.log(inp.value);
                    if (document.getElementById("t-participants").value.indexOf(inp.value)<0)
                        document.getElementById("t-participants").value+=inp.value+",";
                    else
                        alert("User "+ inp.value+" is in the list ");
                    document.getElementById("w-input-search").value="";
                },
                paramName: "userFullName",
                delimiter: ",",
                width: "32%",
                transformResult: function(response) {
                    return {
                        suggestions: $.map($.parseJSON(response), function(item) {
                            return { value: item.fullName, data: item.id};
                        })
                    };
                }
            });
        });
    </script>
</head>
<body onload="eventStartValidation()">
<a href="/welcome" class="btn_calendar">Home</a>
<a href="/index" class="btn_calendar">Calendar</a>
<a href="/userControlPanel" class="btn_calendar">User Panel</a>
<a href="/createEvent" class="btn_calendar">Create new event</a>
<a href="/userPage" class="btn_calendar">User Page</a>
<a href="/events" class="btn_calendar">All events</a>
<a href="/logout" class="btn_calendar">Logout</a>
<c:if test="${pageContext.request.isUserInRole('ADMIN')}">
    <a href="/admin" class="btn_calendar">Admin page</a>
</c:if>
<c:if test="${pageContext.request.isUserInRole('SUPREME_ADMIN')}">
    <a href="/admin" class="btn_calendar">Admin page</a>
</c:if>
<button type="button"  class="btn" data-toggle="modal" data-target="#AddEvent" ></button>

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
    <div class="modal-dialog modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">ADD EVENT</h4>
            </div>
            <div class="modal-body">


                <div class="container">
                    <form action="${contextPath}/createEvent" method="POST" htmlEscape="true">
                        <div class="event-form">

                            <div class="row" id="leftblock" style="padding-right: 15px">
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label for="ev-title">TITLE</label>
                                        <input type="text" class="form-control" id="ev-title"
                                               placeholder="Enter title">
                                    </div>
                                    <div class="form-group">
                                        <label for="ev-location">LOCATION</label>
                                        <input type="text" class="form-control" id="ev-location"
                                               placeholder="Enter Location">
                                    </div>
                                    <div class="form-group">
                                        <label for="ev-type">EVENT TYPE</label>
                                        <select class="form-control" id="ev-type">
                                            <option value="">Select Event type</option>
                                            <c:forEach items="${eventTypes}" var="et">
                                                <option value="${et}">${et.view()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="ev-start-date">START DATE</label>
                                                <input type="date" class="form-control" id="ev-start-date"
                                                       placeholder="Choose date... ">
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="ev-end-date">END DATE</label>
                                                <input type="date" class="form-control" id="ev-end-date"
                                                       placeholder="Choose date... ">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-sm-12" style="text-align: center; bottom:10px">
                                            <div class="checkbox-group" id="subs-checkbox">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name="checkParticipants" checked/>Send
                                                    emails to
                                                    participants</label>
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name="checkSubscribe" checked/>Send
                                                    emails to subscribers
                                                </label>
                                            </div>
                                        </div>
                                    </div>

                                </div>


                                <div class="row" id="rightblock">
                                    <div class="col-sm-6">
                                        <div class="form-group textarea-group">
                                            <label for="ev-description">DESCRIPTION</label>
                                            <textarea class="form-control" rows="3" id="ev-description"></textarea>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group participant-group">
                                            <div class="input-group">

                                                <input type="text" id="w-input-search" value=""
                                                       class="form-control" placeholder="Enter name...">
                                                <span class="input-group-btn" style="text-align: right">
                            <button class="btn btn-secondary" type="button" id="span-btn-search">&#128269</button>
                            </span>

                                            </div>
                                            <label for="t-participants">PARTICIPANTS</label>
                                            <textarea class="form-control" name="participants" id="t-participants"
                                                      rows="3"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-sm-12">
                                    <label for="tag-checkbox" id="tag-box-label">TAGS</label>
                                    <div class="checkbox-group" style="text-align: center" id="tag-checkbox">
                                        <c:forEach items="${tags}" var="tag">
                                            <label class="checkbox-inline" style="color:${tag.tag.color()}">
                                                <input type="checkbox" name="checkboxTags"
                                                       id="checkboxTag"> ${tag.tag.view()}
                                            </label>
                                        </c:forEach>

                                    </div>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-sm-12" style="text-align: center">
                                    <input type="submit" id="sendButton" value="ADD">
                                </div>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
