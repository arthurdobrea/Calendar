


<div class="topnavContainer">
    <div class="topnav">
        <div class="appLogo"></div>
        <div class="float-right">
            <div class="float-right-item"><a href="/welcome">HOME</a></div>
            <div class="float-right-item"><a href="/">CALENDAR</a></div>
            <div class="float-right-item"><a onMouseOver="profileDropdownArrowOnMouseOver()" onMouseOut="profileDropdownArrowOnMouseOut()">PROFILE<img src="/resources/ic_arrow_down.png" id="down-arrow" height="24" width="24"></a>
                <div class="sub-menu">
                    <div class="sub-menu-item"><a href="/userPage">My profile</a></div>
                    <div class="sub-menu-item"><a href="/admin">Admin panel</a></div>
                    <div class="sub-menu-item"><a href="#">Add event</a></div>
                    <div class="sub-menu-item"><a href="/logout">Logout</a></div>
                </div>
            </div>
            <div class="no-underline"><a href="#"><img src="/resources/ic_notifications.png" alt="notifications" height="24" width="24"></a></div>
            <div class="no-underline"><a href="javascript:void(0);" style="font-size:16px;" class="icon" onclick="hideShowNavbar()">&#9776;</a></div>
        </div>
    </div>
</div>

<script>
    function profileDropdownArrowOnMouseOver(){
        document.getElementById('down-arrow').src = "/resources/ic_arrow_down_active.png";
    }

    function profileDropdownArrowOnMouseOut() {
        document.getElementById('down-arrow').src = "/resources/ic_arrow_down.png";
    }

    /*do not delete it's for adaptive design'*/
    function hideShowNavbar(){
        var tmp = document.getElementById('topnav');

        if (tmp.className === "topnav") {
            tmp.className += " responsive";
        } else {
            tmp.className = "topnav";
        }
    }
</script>