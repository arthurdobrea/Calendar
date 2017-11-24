
function connectToServerFunc() {
    var stompClient = null;
        $("#response").empty();
        var socket = new SockJS('/notification');
        stompClient = Stomp.over(socket);
        stompClient.connect('', '', function(frame){
            setConnected(true);
            console.log("Connected: " + frame);
            stompClient.subscribe('/user/queue/reply', function(servermessage) {//Callback when server responds
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
    var serverResponse = document.getElementById("notification");
    //var serverResponseModal = document.getElementById("notification-modal")
    var li = document.createElement('li');
    document.getElementById("bell").src = "/resources/ic_notifications_active.png"

    if (localMessage) {
        li.style.color = '';
        tmp = "";
    } else {
        li.style.color = '';
        tmp = "" + decoded;
    }
    li.innerHTML = tmp;
    serverResponse.appendChild(li);
    //serverResponseModal.appendChild(li);
}
function showServerBroadcastModel(servermessage, localMessage) {
    var decoded = $("<div/>").html(servermessage).text();
    tmp = "<span></span>";
    //var serverResponse = document.getElementById("notification");
    var serverResponseModal = document.getElementById("notification-modal")
    var li = document.createElement('li');
    document.getElementById("bell").src = "/resources/ic_notifications_active.png"

    if (localMessage) {
        li.style.color = '';
        tmp = "";
    } else {
        li.style.color = '';
        tmp = "" + decoded;
    }
    li.innerHTML = tmp;
    //serverResponse.appendChild(li);
    serverResponseModal.appendChild(li);
}

