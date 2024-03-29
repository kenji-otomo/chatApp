let stompClient = null;
let roomId;
let userId;
let imageUrl;

$(function () {
	// APIと接続
	connect();
	roomId = $('#roomIdNow').data('value');
    userId = $('#userId').val();
    // 最下部までスクロール
    scroll();

    $("#sendChat").on('submit', function (e) {
        e.preventDefault();
        sendMessage();
    });

    $("#upload_file").on('change', function (e) {
        preview(e);
    })
});

function connect() {
    let socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
		console.log('connect');
        stompClient.subscribe('/topics/greetings/'+roomId, function (chat) {
            showGreeting(chat);
            scroll();
        });
    },onError);
    stompClient.debug = function(str) {};
}

function sendMessage() {

    stompClient.send("/send/chat/"+roomId, {},
    JSON.stringify({'name' : $("#name").val(),'message' : $("#message").val(),
    'roomId' : roomId, 'userId' : userId, 'image' : imageUrl, 'imageName' : $('#upload_file').val()})
    );
    $("#message").val('');
    $("#upload_file").val('');
    let previewImg = document.getElementById('preview');
    if (previewImg.hasChildNodes()) {
        previewImg.removeChild(previewImg.firstChild);
    }
    imageUrl = null;
};

function showGreeting(chat) {
    let chatContent = '<span>' +
     JSON.parse(chat.body).content + '</span>';
    if (JSON.parse(chat.body).picturePath != null) {
        chatContent += '<div><img src=\"'
        + JSON.parse(chat.body).picturePath +'\" width=\"200\"></div>'
    }
    $("#greetings").append('<tr><td>' + chatContent + '</td></tr>');
    if ($('#no-message').length) {
        $('#no-message').remove();
    }
}

function scroll(){
    let maincontent = document.getElementById('greetings');
    maincontent.scrollIntoView(false);
}

function preview(img) {
        let preview = document.getElementById('preview');
		let file_reader = new FileReader();
	    file_reader.readAsDataURL(img.target.files[0]);
		file_reader.addEventListener('load', function(e) {
		
            imageUrl = e.target.result;
            let img = document.createElement("img");
            img.src = imageUrl;
            preview.replaceChildren(img);
      	});
}

function onError() {
    let result = confirm('接続が切断されました。お手数ですが再ログインをお願い致します。\nログイン画面に遷移しますか？');
    if (result) {
        window.location.href = 'http://localhost:8080/';
    }
}