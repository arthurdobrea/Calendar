


<div class="topnavContainer">
    <div class="appLogo"></div>

    <ul class="topnav"  id="topnav">
        <li><a href="/static/welcome.jsp">HOME</a></li>
        <li><a href="/">CALENDAR</a></li>
        <li><a id="down-arrow" onMouseOver="profileDropdownArrowOnMouseOver()" onMouseOut="profileDropdownArrowOnMouseOut()">PROFILE<img src="/resources/ic_arrow_down.png" alt="notifications" height="24" width="24"></a>
            <ul class="sub-menu">
                <li><a href="/static/userPage.jsp">My profile</a></li>
                <li><a href="#">Admin panel</a></li>
                <li><a href="#">Add event</a></li>
                <li><a href="#">Logout</a></li>
            </ul>
        </li>
        <li class="no-underline"><a href="#"><img src="/resources/ic_notifications.png" alt="notifications" height="24" width="24"></a></li>
        <%--<li class="no-underline"><a href="javascript:void(0);" style="font-size:16px;" class="icon" onclick="hideShowNavbar()">&#9776;</a></li>--%>
    </ul>
</div>



<script>
    function profileDropdownArrowOnMouseOver(){
        document.getElementById('down-arrow').innerHTML = 'PROFILE<img src="/resources/ic_arrow_down_active.png" id="down-arrow" alt="notifications" height="24" width="24">';
    }

    function profileDropdownArrowOnMouseOut() {
        document.getElementById('down-arrow').innerHTML = 'PROFILE<img src="/resources/ic_arrow_down.png" id="down-arrow" alt="notifications" height="24" width="24">';
    }

    /*do not delete it's for adaptive design'*/
//    function hideShowNavbar(){
//        var tmp = document.getElementById('topnav');
//
//        if (tmp.className === "topnav") {
//            tmp.className += " responsive";
//        } else {
//            tmp.className = "topnav";
//        }
//    }
</script>