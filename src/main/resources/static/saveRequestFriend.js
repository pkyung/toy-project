let button = document.getElementById("request");
let friendId = document.getElementById("request");
console.log("리쿠ㅞ스트")
button.onclick = function () {
    console.log("클릭")
    $.ajax({
        type: "POST",
        url: `/friend/request/${friendId.value}`,
    })
    button.disabled = true;
}