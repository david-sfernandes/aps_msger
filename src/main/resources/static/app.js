var stompClient = null;

function connect() {
    var socket = new SockJS('/fallback')
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        console.log('Connected: ' + frame);
        stompClient.subscribe('/to/all', function (msg) {
            showMsg(JSON.parse(msg.body).text);
        });
    });
}

function sendNewMsg(msg) {
    stompClient.send("/app/s", {}, JSON.stringify({'text': msg}));
}

function showMsg(msg) {
    console.log(msg);
}

connect();
