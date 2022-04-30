const ws = new WebSocket("ws://localhost:443")

ws.onopen = function() {
    let toSend = JSON.stringify(new Message("Hello server!"))
    console.log("Connected!")
    console.log( toSend )
    ws.send( toSend )
}

ws.onmessage = function(e) {
    console.log(e.data)
}

ws.onerror = function(e) {
    console.log(e)
    ws.close()
}

class Message {
    text = ""
    date = ""
    img = ""
    sendFrom = ""
    constructor(text) {
        this.text = text;
        this.date = "today"
        this.img = "img.com"
        this.sendFrom = "@user"
    }
}