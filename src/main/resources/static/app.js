const ws = new WebSocket("ws://localhost:443")
let username = null

ws.onopen = function() {
    console.log("Connected!")
}

ws.onmessage = function(e) {
    let message = JSON.parse(e.data)
    if(message.author === 'SERVER'){
        showMessage(message, "server")
    } else {
        showMessage(message, "greybox")
    }
}

ws.onerror = function(e) {
    ws.close()
}

function sendMessage() {
    let input = document.getElementById("write")
    if (input.value !== '') {
        let msg = new Message(input.value, username, new Date().toLocaleString("pt-br"))
        let newMsg = JSON.stringify(msg)
        ws.send(newMsg)
        showMessage(msg, "user")
    }
}

function showMessage(message, author) {
    let p = document.createElement("p")
    let h4 = document.createElement("h4")
    let newbox = document.createElement("div")
    let h5 = document.createElement("h5")

    h4.innerText = message.author
    h5.innerText = message.date
    p.innerText =  message.text
    newbox.className = "message " + author

    newbox.appendChild(h4)
    newbox.appendChild(p)
    newbox.appendChild(h5)

    document.getElementById('messages').appendChild(newbox)
    write.value = ""
}

function saveUsername() {
    let cover = document.getElementById("cover")
    let input = document.getElementById('username').value
    let usernameSpace = document.getElementById("name")

    if (input){
        username = input;
        usernameSpace.innerText = input;
        cover.style.display = 'none';
    }
}

class Message {
    text = ""
    date = ""
    img = ""
    author = ""
    constructor(text, username, date) {
        this.text = text;
        this.date = date;
        this.img = "img.com";
        this.author = username;
    }
}

function closeWs() {
    ws.close()
}