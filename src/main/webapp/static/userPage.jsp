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

    <title>User Page</title>
    <link href="${contextPath}/resources/css/autocomplete.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
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
    <script src="${contextPath}/resources/scripts/jquery.autocomplete.min.js"></script>
</head>


<body>
<c:import url="header.jsp" />

    <div class="wrap_box_user_page">
        <div class="user_page_left for_shadow">
            <div class="left_block_header">
                <span class="endava_grey_text profile_text">PROFILE</span>
                <span class="endava_red_text edit_user_link cursor_link" onclick="edit_user()">EDIT</span>
            </div>
            <div class="left_block_body" align="center">
                        <img id="avatar" src="data:image/jpeg;base64,${image}" alt="Your avatar"/>
                    <div id="profile_fullname" class="endava_grey_text"><span style="size: 25px">${user.fullName}</span><br>
                        ${user.email}
                        <p class="endava_red_text" style="line-height: 40px">${user.position}</p>
                    </div>
            </div>
        </div>

        <div class="user_page_right for_shadow">
            <div class="right_block_header">
                <div id="text_my_events" class="endava_grey_text">MY EVENTS
                    <span class="endava_grey_text events_toggle">CREATED BY ME
                        <label class="switch">
                            <input type="checkbox" id="switch_events" onclick="if(this.checked) {showEventsInvited();} else {showMyEvents();}">
                            <span class="slider round"></span></label> INVITATIONS</span>
                </div>
                <div class="right_header_2_row">
                    <span class="endava_grey_text for_add_event"><button id="add_event_button" onclick="create_event()">+</button>&nbsp;ADD EVENT</span>
                    <span id="total_events_created" class="endava_grey_text">TOTAL CREATED:
                            <span class="endava_grey_text events_number">${eventsByAuthor.size()}</span>&nbsp;EVENTS</span>
                    <span id="total_events_invited" class="endava_grey_text events_number">INVITED AT:
                            <span class="endava_grey_text events_number">${eventsByUser.size()}</span>&nbsp;EVENTS</span>
                </div>
            </div>

            <%--Created events--%>
            <div id="my_events" align="left" class="scrollable-content">
                <table class="table table-hover">
                    <tbody>
                    <c:forEach items="${eventsByAuthor}" var="event">
                        <script>
                            function delete_event() {
                                $(".delete_event_modalka").load("/deleteEvent?eventId=${event.id} #DeleteEvent", function () {
                                    $("#DeleteEvent").modal();
                                });
                            }
                        </script>

                        <a href="/showEvent?eventId=${event.id}">
                            <tr>
                                <td id="created_event_name" align="left"><span  class="endava_grey_text span_event_title">${event.title}<br></span>
                                    <span  class="endava_grey_text">${event.eventType}</span></td>
                                <td align="right" id="td_edit_btn"><button class="btn_edit_event" onclick="window.location.href='/updateEvent?eventId=${event.id}' "></button></td>
                                <td align="right" id="td_delete_btn"><button class="btn_delete_event" onclick="delete_event() "></button></td>
                            </tr>
                        </a>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <%--Events where I'm invited--%>
            <div id="events_invited" align="left" class="scrollable-content">
                <table class="table table-hover">
                    <tbody>
                    <c:forEach items="${eventsByUser}" var="event">
                        <a href="/showEvent?eventId=${event.id}">
                            <tr>
                                <td align="left" id="invited_event_name"><span  class="endava_grey_text">${event.title}<br></span>
                                    <span  class="endava_red_text span_event_title">${event.eventType}</span></td>
                                <td align="right" id="td_show_event"><a href="/showEvent?eventId=${event.id}"><button class="btn_show_event"></button></a></td>
                            </tr>
                        </a>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="add_event_modal"></div>
    <div class="edit_user_modal"></div>
    <div class="delete_event_modalka"></div>

<script src="${contextPath}/resources/js/jquery.datetimepicker.full.min.js"></script>
<script src="${contextPath}/resources/js/eventValidator.js"></script>
</body>
</html>

