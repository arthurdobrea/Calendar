<!DOCTYPE html>
<html>

<head>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/bootstrapmodal.js"></script>
</head>

<body>



<div class="topnavContainer">
    <div class="appLogo"></div>

    <ul class="topnav">
        <li><a href="/welcome">HOME</a></li>
        <li><a href="/">CALENDAR</a></li>
        <li><a href="#">PROFILE<img src="/resources/ic_arrow_down.png" alt="notifications" height="24" width="24"></a>
            <ul class="sub-menu">
                <li><a href="/userPage">My profile</a></li>
                <li><a href="#">Admin panel</a></li>
                <li><a href="/createEvent" data-toggle="modal"  data-toggle="#AddEvent">Add event</a></li>
                <li><a href="#">Logout</a></li>
            </ul>
        </li>
        <li class="no-underline"><a href="#"><img src="/resources/ic_notifications.png" alt="notifications" height="24" width="24"></a></li>
    </ul>
</div>

<div class="modal fade" id="AddEvent" role="dialog"></div>

</body>
<html>






