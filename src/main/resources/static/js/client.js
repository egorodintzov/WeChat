var stompClient = null;
var connectFlag=false;

$(document).ready(function () {
    connect();
});

function connect() {
    var socket = new SockJS('/message');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        connectFlag=true;
        stompClient.subscribe('/chat/topic', function(response) {
            show(JSON.parse(response.body).value);
        });
    });
}

function disconnect() {
    stompClient.disconnect();
    setConnected(false);
    console.log("Disconnected");
}

function sendMsg() {
    if(connectFlag) {
        var message = document.getElementById('messageField').value;
        stompClient.send('/app/message', {}, JSON.stringify({
            value: message,
        }));
    }
}

function show(message) {
    var element = document.createElement('div');
    element.innerHTML=message;
    document.getElementById('msg-box').appendChild(element);
}