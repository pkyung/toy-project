let button = document.getElementById("request");
let friendId = document.getElementById("request");

button.onclick = function () {
    $.ajax({
        type: "POST",
        url: `/friend/${friendId.value}`,
        success: function () {
            console.log("좋아요 완료");
        }
    })
    button.disabled = true;
}