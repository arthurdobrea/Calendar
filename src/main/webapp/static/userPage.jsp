<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <link href="${contextPath}/resources/css/event.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/serghei.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/header-style.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Oswald:300' rel='stylesheet' type='text/css'>

    <script src="<c:url value="/resources/js/lib/sockjs-0.3.4.min.js"/>"></script>
    <script src="<c:url value="/resources/js/lib/stomp.js"/>"></script>
    <script src="<c:url value="/resources/js/connectToServer.js"/>"></script>
    <script src='${contextPath}/resources/js/lib/jquery.min.js'></script>
    <script src='${contextPath}/resources/js/lib/jquery-ui.min.js'></script>
    <script src="${contextPath}/resources/js/lib/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/userProfile.js"></script>
    <script src='${contextPath}/resources/js/lib/fullcalendar.js'></script>
    <script src='${contextPath}/resources/js/lib/moment.js'></script>
    <script src="${contextPath}/resources/js/lib/jquery.autocomplete.min.js"></script>
    <script src="${contextPath}/resources/js/eventValidator.js"></script>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <script>
        $(document).ready(function () {
            $('#w-input-search').autocomplete({
                serviceUrl: "/getUserFullName",
                onSelect: function (inp) {
                    console.log(inp.value);
                    if (document.getElementById("t-participants").value.indexOf(inp.value) < 0)
                        document.getElementById("t-participants").value += inp.value + ",";
                    else
                        alert("User " + inp.value + " is in the list ");
                    document.getElementById("w-input-search").value = "";
                },
                paramName: "userFullName",
                delimiter: ",",
                width: "31%",
                transformResult: function (response) {
                    return {
                        suggestions: $.map($.parseJSON(response), function (item) {
                            return {value: item.toString(), data: item.id};
                        })
                    };
                }
            });
        });

        function Checkfiles() {
            var fup = document.getElementById('avatar');
            var fileName = fup.value;
            var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
            if (ext == "gif" || ext == "GIF" || ext == "JPEG" || ext == "jpeg" || ext == "jpg" || ext == "JPG" || ext == "doc") {
                return true;
            }
            else {
                alert("Upload Gif or Jpg images only");
                fup.focus();
                return false;
            }
        }
    </script>
</head>

<body>
<div class="wrap_box_user_page">
    <c:import url="header.jsp"/>
    <div class="user_page_left for_shadow">
        <div class="left_block_header">
            <span class="endava_grey_text profile_text">PROFILE</span>
            <span class="endava_red_text edit_user_link cursor_link" onclick="edit_user()">EDIT</span>
        </div>
        <div class="left_block_body" align="center">
            <img id="avatar" src="data:image/jpeg;base64,${image}"
                 onerror="this.src='/resources/icons/defaultImage.png'" alt="Your avatar"/>
            <div id="profile_fullname" class="endava_grey_text"><span style="size: 25px">${user.fullName}</span><br>
                ${user.email}
                <p class="endava_red_text" style="line-height: 40px">${user.position}</p>
            </div>
        </div>
    </div>

    <div class="user_page_right for_shadow">
        <div class="right_block_header">
            <div id="text_my_events" class="endava_grey_text">MY EVENTS
                <span class="endava_red_text events_toggle">CREATED
                        <label class="switch">
                            <input type="checkbox" id="switch_events"
                                   onclick="if(this.checked) {showEventsInvited();} else {showMyEvents();}">
                            <span class="slider round"></span></label> INVITATIONS</span>
            </div>
            <div class="right_header_2_row">
                <span class="endava_grey_text for_add_event"><button id="add_event_button"
                                                                     onclick="create_event()">+</button>&nbsp;ADD EVENT</span>
                <span id="total_events_created" class="endava_grey_text">EVENTS:
                            <span class="endava_grey_text events_number">${eventsByAuthor.size()}</span></span>
                <span id="total_events_invited" class="endava_grey_text events_number">EVENTS:
                            <span class="endava_grey_text events_number">${eventsByUser.size()}</span></span>
            </div>
        </div>

        <%--Created events--%>

        <div id="my_events" align="left" class="scrollable-content">
            <table class="table table-hover" id="myEventsTable">
                <tbody>
                <c:forEach items="${eventsByAuthor}" var="event">
                    <tr>
                        <td id="created_event_name" align="left" style="padding-left: 5px"><span
                                class="endava_grey_text span_event_title">${event.title}<br></span>
                            <span class="endava_grey_text">${event.eventType}</span></td>
                        <td align="right" style="padding-left: 10px">
                            <input type="hidden" id="${event.id}" value="${event.id}" readonly>
                        </td>

                        <td align="right" id="td_show_event" style="padding: 0">
                            <button class="btn_show_event" onclick="$('.show_event_modal').
                                    load(('/' + 'showEvent?eventId=' + document.getElementById(${event.id}).
                                    getAttribute('value') + ' ' + '#ShowEvent').toString(),
                                    function () {$('#ShowEvent').modal();});">
                            </button>
                        </td>

                        <td align="right" id="td_edit_btn">
                            <button class="btn_edit_event" onclick="$('.edit_event_modalka').
                                    load(('/' + 'editEvent?eventId=' + document.getElementById(${event.id}).
                                    getAttribute('value') + ' ' + '#EditEvent').toString(),
                                    function () {$('#EditEvent').modal();});">
                            </button>
                        </td>

                        <td align="right" id="td_delete_btn">
                            <button class="btn_delete_event" onclick="$('.delete_event_modalka').
                                    load(('/' + 'deleteEvent?eventId=' + document.getElementById(${event.id}).
                                    getAttribute('value') + ' ' + '#DeleteEvent').toString(),
                                    function () {$('#DeleteEvent').modal();});">
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <%--Events where I'm invited--%>
        <div id="events_invited" align="left" class="scrollable-content">
            <table class="table table-hover">
                <tbody>
                <c:forEach items="${eventsByUser}" var="event">
                    <tr>
                        <td align="left" id="invited_event_name" style="padding-left: 5px"><span
                                class="endava_grey_text">${event.title}<br></span>
                            <span class="endava_red_text span_event_title">${event.eventType}</span></td>
                        <td align="right">
                            <input type="hidden" id="${event.id}" value="${event.id}" readonly>
                        </td>
                        <td align="right" id="td_show_event">
                            <button class="btn_show_event" onclick="$('.show_event_modal').
                                    load(('/' + 'showEvent?eventId=' + document.getElementById(${event.id}).
                                    getAttribute('value') + ' ' + '#ShowEvent').toString(),
                                    function () {$('#ShowEvent').modal();});">
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="add_event_modal"></div>
<div class="edit_user_modal"></div>
<div class="show_event_modal"></div>
<div class="delete_event_modalka"></div>
<div class="edit_event_modalka"></div>

</body>
</html>

