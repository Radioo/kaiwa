const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/main'  // PROBLEM !!!!!!!!!!!!!!!!!!!!!
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/echo', (message) => {
        showGreeting(JSON.parse(message.body));
    });

    stompClient.subscribe('/topic/history', (message) => {
        const messages = JSON.parse(message.body);
        messages.forEach((message) => {
            showGreeting(message);
        });
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.activate();

    // Wait until client ready
    setTimeout(() => {
        // Get history
        stompClient.publish({
            destination: "/app/history",
        });
    }, 1000);
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.publish({
        destination: "/app/hello",
        body: JSON.stringify({'name': $("#name").val()})
    });
}

function sendMessage() {
    stompClient.publish({
        destination: "/app/hello",
        body: JSON.stringify({
            'name': $("#name").val(),
            'content': $("#message").val()
        })
    });
}

function showGreeting(message) {
    $("#messages-table").append(`<tr><td>${message.name}</td><td>${message.content}</td></tr>`);
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send-button" ).click(() => sendMessage());
});
