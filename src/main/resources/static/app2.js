var stompClient = null;

function connect() {
    var socket = new SockJS('https://localhost:443')
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        console.log('Connected: ' + frame);
        stompClient.subscribe('/', function (msg) {
            showMsg(JSON.parse(msg.body).text);
        });
    });
}

function sendNewMsg(msg) {
    stompClient.send("/", {}, msg);
}

function showMsg(msg) {
    console.log(msg);
}

connect();