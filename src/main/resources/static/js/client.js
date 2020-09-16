let stompClient=null;
let isConnected=false;


window.onload = function() {
    connect();
};


function connect() {
    let socket = new SockJS("/message");
    stompClient=Stomp.over(socket);
    stompClient.connect({},function (frame) {
        isConnected=true;
        // подсоединяемся к топику , где хранятся сообщения
        stompClient.subscribe('/chat/topic',function (response) {
            // парсим объект и получаем его поле message
            showMessage(JSON.parse(response.body).message);
        });
    });
}

function sendMessage() {
    if(isConnected) {
        // получаем значения с поля
        let message = document.getElementById('input').value;
        // отправляем сообщение на урл , который принимает сообщения
        stompClient.send('/app/message',{},JSON.stringify({
            message:message
        }));
    }
}

// выводим сообщения
// p.s я не юзаю реакт , потому что мне лень его подключать
function showMessage(message) {
    let element = document.createElement('div');
    element.innerHTML=message;
    document.body.appendChild(element);
}
