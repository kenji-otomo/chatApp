var stompClient = null;

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/topics/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
            scroll();
        });
    });
}

function sendMessage() {
    stompClient.send("/send/chat", {}, JSON.stringify({'name': $("#name").val(),'message': $("#message").val()}));
    $("#message").val('');
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
	connect();
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage();});
});

function scroll(){
    var maincontent = document.getElementById('conversation');
    maincontent.scrollIntoView(false);
}