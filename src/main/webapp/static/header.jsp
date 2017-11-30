<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<script>
    $(function() {
        var currentLocation = window.location.href;

        if(~currentLocation.indexOf('welcome') == false){
            $("#imageId").show();
        }
    });

</script>
<script src="${contextPath}/resources/js/jquery.datetimepicker.full.min.js"></script>
<script src="${contextPath}/resources/js/eventValidator.js"></script>

<div class="topnavContainer">
    <script>
        connectToServer();
    </script>
    <div class="topnav" id="topnav">
        <div class="appLogo" id = "imageId" style="display: none" ></div>
        <div class="float-right">
            <div class="float-right-item"><a href="/welcome" id="welcome">HOME</a></div>
            <div class="float-right-item"><a href="/">CALENDAR</a></div>
            <div class="float-right-item"><a onMouseOver="profileDropdownArrowOnMouseOver()"
                                             onMouseOut="profileDropdownArrowOnMouseOut()">PROFILE<img
                    src="/resources/icons/ic_arrow_down.png" id="down-arrow" height="24" width="24"></a>
                <div class="sub-menu">
                    <div class="sub-menu-item"><a href="/userPage">My profile</a></div>
                    <div class="sub-menu-item"><a href="/admin">Admin panel</a></div>
                    <div class="sub-menu-item"><a href="/createEvent" data-toggle="modal" data-toggle="#AddEvent">Add
                        event</a></div>
                    <div class="sub-menu-item"><a href="/logout">Logout</a></div>
                </div>
            </div>
            <div class="no-underline">
                <a href="#">
                    <img src="/resources/icons/ic_notifications.png" id="bell" alt="notifications" height="24"
                         width="24">
                </a>

                <div class="notifications-list">
                    <p id="notification-title">Notifications</p>
                    <table id="notification">
                        <%
                            int counter = 0;
                        %>
                        <c:forEach items="${uncheckedNotifications}" var="notification">
                            <%
                                if (counter < 3) {
                                    ++counter;
                                } else {
                                    break;
                                }
                            %>
                            <tr>
                                <td id="notification_time_date">
                                    <p id="notification_time"><javatime:format value="${notification.event.eventCreated}"
                                                                        pattern="HH:mm"/></p>
                                    <p id="notification_date"><javatime:format value="${notification.event.eventCreated}"
                                                                        pattern="MM/dd/yy"/></p>
                                </td>
                                <td id="notification_message"><a
                                        href="${contextPath}/showEvent?eventId=${notification.event.id}">${notification.event.title}</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:forEach items="${checkedNotifications}" var="notification">
                            <%
                                if (counter < 3) {
                                    ++counter;
                                } else {
                                    break;
                                }
                            %>
                            <tr>
                                <td id="notification_time_date">
                                    <p id="notification_time"><javatime:format value="${notification.event.eventCreated}"
                                                                        pattern="HH:mm"/></p>
                                    <p id="notification_date"><javatime:format value="${notification.event.eventCreated}"
                                                                        pattern="MM/dd/yy"/></p>
                                </td>
                                <td id="notification_message"><a
                                        href="${contextPath}/showEvent?eventId=${notification.event.id}">${notification.event.title}</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table id="notification">
                    <p id="notification-bottom"><a href="#" id="notification_window">Show all</a></p>
                </div>
            </div>
            <div class="no-underline"><a href="javascript:void(0);" style="font-size:16px;" class="icon"
                                         onclick="hideShowNavbar()">&#9776;</a></div>
        </div>
    </div>
</div>

<div class="modal fade" id="AddEvent" role="dialog"></div>

<div class="add_event_modal"></div>

<div id="modal_form">
    <span id="modal_title">NOTIFICATIONS</span>
    <span id="modal_close"></span>

    <div id="modal_content">
        <table id="modal_table">
            <c:forEach items="${uncheckedNotifications}" var="notification">
                <tr id="modal_line">
                    <td id="modal_time_date">
                        <p id="modal_time"><javatime:format value="${notification.event.eventCreated}" pattern="HH:mm"/></p>
                        <p id="modal_date"><javatime:format value="${notification.event.eventCreated}" pattern="MM/dd/yy"/></p>
                    </td>
                    <td id="modal_message"><a
                            href="${contextPath}/showEvent?eventId=${notification.event.id}">${notification.event.title}</a>
                    </td>
                </tr>
            </c:forEach>
            <c:forEach items="${checkedNotifications}" var="notification">
                <tr id="modal_line">
                    <td id="modal_time_date">
                        <p id="modal_time"><javatime:format value="${notification.event.eventCreated}" pattern="HH:mm"/></p>
                        <p id="modal_date"><javatime:format value="${notification.event.eventCreated}" pattern="MM/dd/yy"/></p>
                    </td>
                    <td id="modal_message"><a
                            href="${contextPath}/showEvent?eventId=${notification.event.id}">${notification.event.title}</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div id="overlay"></div>
<!-- Пoдлoжкa -->
<script>
    $(document).ready(function () { // вся мaгия пoсле зaгрузки стрaницы
        $('a#notification_window').click(function (event) { // лoвим клик пo ссылки с id="go"
            event.preventDefault(); // выключaем стaндaртную рoль элементa
            $('#overlay').fadeIn(10, // снaчaлa плaвнo пoкaзывaем темную пoдлoжку
                function () { // пoсле выпoлнения предъидущей aнимaции
                    $('#modal_form')
                        .css('display', 'block') // убирaем у мoдaльнoгo oкнa display: none;
                        .animate({opacity: 1}, 200); // плaвнo прибaвляем прoзрaчнoсть oднoвременнo сo съезжaнием вниз
                });
        });
        /* Зaкрытие мoдaльнoгo oкнa, тут делaем тo же сaмoе нo в oбрaтнoм пoрядке */
        $('#modal_close, #overlay').click(function () { // лoвим клик пo крестику или пoдлoжке
            $('#modal_form')
                .animate({opacity: 0}, 200,  // плaвнo меняем прoзрaчнoсть нa 0 и oднoвременнo двигaем oкнo вверх
                    function () { // пoсле aнимaции
                        $(this).css('display', 'none'); // делaем ему display: none;
                        $('#overlay').fadeOut(400); // скрывaем пoдлoжку
                    }
                );
        });
    });

    function profileDropdownArrowOnMouseOver() {
        document.getElementById('down-arrow').src = "/resources/icons/ic_arrow_down_active.png";
    }

    function profileDropdownArrowOnMouseOut() {
        document.getElementById('down-arrow').src = "/resources/icons/ic_arrow_down.png";
    }

    function hideShowNavbar() {
        var topnav = document.getElementById('topnav');

        if (topnav.className === "topnav") {
            topnav.className += " responsive";
        } else {
            topnav.className = "topnav";
        }
    }
</script>