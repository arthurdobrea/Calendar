var start;
var notificationIdParsed;

function notificationTimer(startTime, notificationId) {
    start = parseFloat(startTime);
    notificationIdParsed = parseInt(notificationId, 10);
    var refresh = 1000;

    setTimeout('timeCounter(notificationIdParsed)', refresh);
}

function timeCounter(notificationId) {
    var days = Math.floor(start / 86400);
    var hours = Math.floor((start - (days * 86400 )) / 3600);
    var minutes = Math.floor((start - (days * 86400 ) - (hours * 3600 )) / 60);
    var seconds = Math.floor((start - (days * 86400 ) - (hours * 3600 ) - (minutes * 60)));

    var x;
    if (minutes === 0 && hours < 1) {
        if (seconds === 1) {
            x = seconds + " second ago";
        } else {
            x = seconds + " seconds ago";
        }
    } else if (hours === 0) {
        if (minutes === 1) {
            x = minutes + " minute ago";
        } else {
            x = minutes + " minutes ago";
        }
    } else if (days === 0) {
        if (hours === 1) {
            x = hours + " hour ago";
        } else {
            x = hours + " hours ago";
        }
    } else {
        x = days + " day ago";
    }

    document.getElementById("notification" + notificationId).innerHTML = x;
    ++start;

    notificationTimer(start);
}