let ws = new WebSocket("wss://"+ window.location.hostname +":443")

ws.onopen = function(e) {
    console.log("Connected!")
    ws.send("Hello server!")
}

ws.onmessage = function(e) {
    console.log(e.data)
}

ws.onerror = function(e) {
    console.log(e)
    ws.close()
}
