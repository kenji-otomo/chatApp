let stompClient = null;
let roomId;
let userId;

function connect() {
    let socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/topics/greetings/'+roomId, function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
            scroll();
        });
    });
}

function sendMessage() {
    stompClient.send("/send/chat/"+roomId, {}, JSON.stringify({'name' : $("#name").val(),'message' : $("#message").val(),
    	'roomId' : roomId, 'userId' : userId}));
    $("#message").val('');
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
	connect();
	roomId = $('#roomIdNow').text();
	console.log(roomId);
    userId = $('#userName').data('value');
    console.log(userId);
    scroll();

    $("#sendChat").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage();});
});

function scroll(){
    let maincontent = document.getElementById('greetings');
    maincontent.scrollIntoView(false);
}