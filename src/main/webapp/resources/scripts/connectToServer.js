function connectToServer() {
    var stompClient = null;
    $("#response").empty();
    var socket = new SockJS('/notification');
    stompClient = Stomp.over(socket);
    stompClient.connect('', '', function (frame) {
        setConnected(true);
        console.log("Connected: " + frame);
        stompClient.subscribe('/user/queue/reply', function (servermessage) {//Callback when server responds
            showServerBroadcast(JSON.parse(servermessage.body).messageContent, false);
            showServerBroadcastModel(JSON.parse(servermessage.body).messageContent, false);
        });
    });
}

function setConnected(connected) {
    $("#connect").prop('disabled', connected);
}

function showServerBroadcast(servermessage, localMessage) {
    var decoded = $("<div/>").html(servermessage).text();

    tmp = "<span></span>";
    document.getElementById("bell").src = "resources/icons/ic_notifications_active.png";

    var serverResponse = document.getElementById("notification");
    var row = document.createElement("tr");
    var notification_time_date = document.createElement("td");
    var notification_time = document.createElement("p");
    var notification_date = document.createElement("p");
    var notification_message = document.createElement("td");

    notification_time_date.id = "notification_time_date";
    notification_time.id = "notification_time";
    notification_date.id = "notification_date";
    notification_message.id = "notification_message";

    if (localMessage) {
        tmp = "";
    } else {
        tmp = "" + decoded;
    }

    var temp = document.createTextNode(tmp);

    notification_time.innerHTML = temp.nodeValue.substr(0, 5);
    notification_date.innerHTML = temp.nodeValue.substr(6, 9);
    notification_message.innerHTML = "<a href='#'>" + temp.nodeValue.substr(15, temp.length) + "</a>";

    notification_time_date.appendChild(notification_time);
    notification_time_date.appendChild(notification_date);
    row.appendChild(notification_time_date);
    row.appendChild(notification_message);

    var tbody = serverResponse.getElementsByTagName("tbody")[0];
    tbody.insertBefore(row, tbody.children[0]);

    tbody.removeChild(tbody.children[3]);
}

function showServerBroadcastModel(servermessage, localMessage) {
    var decoded = $("<div/>").html(servermessage).text();

    tmp = "<span></span>";
    document.getElementById("bell").src = "resources/icons/ic_notifications_active.png";

    var serverResponseModal = document.getElementById("modal_table");
    var row = document.createElement("tr");
    var modal_time_date = document.createElement("td");
    var modal_time = document.createElement("p");
    var modal_date = document.createElement("p");
    var modal_message = document.createElement("td");

    row.id = "modal_line";
    modal_time_date.id = "modal_time_date";
    modal_time.id = "modal_time";
    modal_date.id = "modal_date";
    modal_message.id = "modal_message";

    if (localMessage) {
        tmp = "";
    } else {
        tmp = "" + decoded;
    }

    var temp = document.createTextNode(tmp);

    modal_time.innerHTML = temp.nodeValue.substr(0, 5);
    modal_date.innerHTML = temp.nodeValue.substr(6, 9);
    modal_message.innerHTML = "<a href='#'>" + temp.nodeValue.substr(15, temp.length) + "</a>";

    modal_time_date.appendChild(modal_time);
    modal_time_date.appendChild(modal_date);
    row.appendChild(modal_time_date);
    row.appendChild(modal_message);

    var tbody = serverResponseModal.getElementsByTagName("tbody")[0];
    tbody.insertBefore(row, tbody.children[0]);
}

