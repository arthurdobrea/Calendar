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

    <title>Create an event</title>

    <link href="${contextPath}/resources/css/autocomplete.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/serghei.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/event.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery-ui.min.css" rel="stylesheet">

    <script src="${contextPath}/resources/js/bootstrapmodal.js"></script>
    <script src="${contextPath}/resources/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/resources/scripts/jquery.autocomplete.min.js"></script>
    <script src="${contextPath}/resources/js/userProfile.js"></script>

    <script>
        $('form').submit(function(){
            // Блокируем кнопки при отправке формы
            $('input[type=submit]', $(this)).prop( 'disabled', true );
            e.preventDefault();
        });$('form').submit(function(){
            // Блокируем кнопки при отправке формы
            $('input[type=submit]', $(this)).prop( 'disabled', true );
            e.preventDefault();
        });
    </script>
</head>
</head>

<body>
<%--edit event modal--%>
<div class="modal fade" id="EditEvent" role="dialog">
    <div class="modal-dialog modal-lg" align="center" style="margin-top: 50px">
        <div class="modal-content" style="border-radius: 0;">
            <div class="modal-header" style="margin-left: 15px; margin-right: 15px">
                <div class="create_event_header">
                    <p align="left" class="modal_topic endava_grey_text">EDIT EVENT<button type="button" class="close_modal" data-dismiss="modal"></button></p>
                </div>
            </div>
            <div class="modal-body" style="padding-left: 30px; padding-right: 30px">
                <form action="${contextPath}/editEvent" method="POST">
                    <div class="event-form">

                        <div class="row" id="leftblock" style="padding-right: 15px">
                            <div class="col-sm-6" style="text-align: left">
                                <div class="form-group">
                                    <label for="ev-title">TITLE</label>
                                    <input type="text" name="title" class="form-control" id="ev-title"
                                           placeholder="Enter title" required="true" value="${event.title}" maxlength="30">
                                </div>
                                <div class="form-group">
                                    <label for="ev-location">LOCATION</label>
                                    <input type="text" name="location" class="form-control" id="ev-location"
                                           placeholder="Enter Location" required="true" value="${event.location}" maxlength="30">
                                </div>
                                <div class="form-group">
                                    <label class="label_add_event" for="ev-type">EVENT TYPE</label>
                                    <select class="event_add_form_type_select_box" id="ev-type" name="eventType" required="true" >
                                        <option style="font-size: 14px" value="">Select event type</option>
                                        <c:forEach items="${eventTypes}" var="et">
                                            <option style="font-size: 14px" value=${et}>${et.view()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6" >
                                        <label for="datetimepicker1">START DATE</label>
                                        <div class="form-group">
                                            <input type="text" name="start" class="form-control" id="datetimepicker1" readonly
                                                   onclick="changeDateTimeForm()" value="${event.start.toString().replace("T"," ").replace("-","/")}" >
                                        </div>
                                    </div>
                                    <div class="col-sm-6" >
                                        <label for="datetimepicker2">END DATE</label>
                                        <div class="form-group">
                                            <input type="text" name="end" class="form-control" id="datetimepicker2" readonly
                                                   onclick="changeDateTimeForm()" value="${event.end.toString().replace("T"," ").replace("-","/")}" >
                                        </div>
                                    </div>

                                        <div class="col-sm-6" id="startBlock" hidden>
                                            <input type="date" class="form-control" id="datepicker1">
                                            <input type="time" class="form-control" id="timepicker1">
                                        </div>

                                        <div class="col-sm-6" id="endBlock" hidden>
                                            <input type="date" class="form-control" id="datepicker2" value="${event.start}" onchange="changeColor()">
                                            <input type="time" class="form-control" id="timepicker2" onchange="changeColor()">
                                        </div>

                                        <%--<div class="col-sm-6" id="allDayBlock" hidden>--%>
                                            <%--<div class="form-group" id="alldaydiv" style="padding-bottom:5px; padding-top: 0; text-align: left;">--%>
                                                <%--<div align="right" style="width: 70px"><label id="alldaylabel" class="modal-header edit_profile_header">--%>
                                                    <%--<input type="checkbox" id="all-day" value="${event.allDay}"--%>
                                                           <%--onclick="if(this.checked) {allDayChecked();} else {allDayUnchecked();}">--%>
                                                    <%--<span class="endava_red_text" style="font-size: 12px">&nbsp;All day</span>--%>
                                                <%--</label></div>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>

                                </div>
                            </div>

                            <div class="row" id="rightblock">
                                <div class="col-sm-6">
                                    <div class="form-group textarea-group">
                                        <label for="ev-description">DESCRIPTION</label>
                                        <textarea name="description" class="form-control" rows="3"
                                                  id="ev-description" required="true"
                                                  value="${event.description}" maxlength="300">${event.description} </textarea>
                                    </div>
                                </div>

                                <div class="col-sm-6">
                                    <div class="form-group participant-group">
                                        <div class="input-group">
                                            <input type="text" id="w-input-search" value=""
                                                   class="form-control" placeholder="Enter name...">
                                            <span class="input-group-btn" style="text-align: right">
                                                    <button class="btn btn-secondary" type="button"
                                                            id="span-btn-search">&#128269</button>
                                                </span>

                                        </div>
                                        <label for="t-participants">PARTICIPANTS</label>
                                        <textarea class="form-control" name="participants" id="t-participants"
                                                  rows="3"
                                                  value="${event.getParticipantsToString()}">${event.getParticipantsToString()}</textarea>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12" style="text-align: center; bottom: 20px">
                                            <div class="checkbox-group"  id="subs-checkbox">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox"  name="checkParticipants"/>Send emails to
                                                    participants</label>
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name="checkSubscribe"/>Send emails to
                                                    subscribers
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">
                            <label for="tag-checkbox-edit" id="tag-box-label-edit">TAGS</label>
                            <div class="checkbox-group" style="text-align: center" id="tag-checkbox-edit">
                                <c:forEach items="${tags}" var="tag">
                                    <c:set var="checked" value="0"/>
                                    <c:forEach items="${event.getEventTagsAsEnum()}" var="eventTag">
                                        <c:if test="${tag.tag==eventTag}">
                                            <c:set var="checked" value="1"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${checked==1}">
                                        <label class="checkbox-inline" style="color:${tag.tag.color()}">
                                            <input type="checkbox" name="checkboxTags"
                                                   id="checkboxTag" checked/> ${tag.tag.view()}
                                        </label>
                                    </c:if>
                                    <c:if test="${checked==0}">
                                        <label class="checkbox-inline" style="color:${tag.tag.color()}">
                                            <input type="checkbox" name="checkboxTags"
                                                   id="checkboxTag"/> ${tag.tag.view()}
                                        </label>
                                    </c:if>
                                </c:forEach>
                                <label class="checkbox-inline" style="color:${tag.tag.color()}">
                                    <input type="checkbox" name="checkboxTags"
                                           hidden value="hidden" checked/>
                                </label>
                                <label class="checkbox-inline" style="color:${tag.tag.color()}">
                                    <input type="checkbox" name="checkboxTags"
                                           hidden value="hidden" checked/>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12" style="text-align: center">
                            <input type="text" name="event-id" id="ev-id" value="${event.id}" hidden>
                            <input type="submit" class="btn_login_submit" value="SAVE"
                                   style="margin-bottom: 15px" onmousedown="checkHiddenInput()" >
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/eventValidator.js"></script>
</body>
</html>