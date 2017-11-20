
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

            });
            // stompClient.subscribe("/topic/simplemessagesresponse", function(servermessage) {//Callback when server responds
            //     showServerBroadcast(JSON.parse(servermessage.body).messageContent, false);
            //
            // });
        });
}




function setConnected(connected) {
    $("#connect").prop('disabled', connected);
}

function showServerBroadcast(servermessage, localMessage) {
    var decoded = $("<div/>").html(servermessage).text();
    tmp = "<span></span>";
    var serverResponse = document.getElementById("response");
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';

    if (localMessage) {
        p.style.color = '#006600';
        tmp = "<span ></span>";
    } else {
        p.style.color = '#8A0808';
        tmp = "<span></span> " + decoded;
    }
    p.innerHTML = tmp;
    serverResponse.appendChild(p);
}