<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<script src="${contextPath}/resources/js/userProfile.js"></script>

<div class="topnavContainer" align="center">
    <div class="appLogo"></div>

    <ul class="topnav">
        <li><a href="/static/welcome.jsp">HOME</a></li>
        <li><a href="/">CALENDAR</a></li>
        <li id="profile"><a>PROFILE<img src="/resources/icons/ic_arrow_down.png" alt="notifications" height="24" width="24"></a>
            <ul class="sub-menu">
                <li><a href="/userPage">My profile</a></li>
                <li><a href="#">Admin panel</a></li>
                <li><a href="#">Add event</a></li>
                <li><a href="#">Logout</a></li>
            </ul>
        </li>
        <li class="no-underline"><a href="#"><img src="/resources/icons/ic_notifications.png" alt="notifications" height="24" width="24"></a></li>
    </ul>
</div>

<%--<div class="add_event_modal"></div>--%>



