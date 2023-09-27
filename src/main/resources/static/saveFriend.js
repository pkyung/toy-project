let button = document.getElementById("request");
let friendId = document.getElementById("request");

button.onclick = function () {
    $.ajax({
        type: "POST",
        url: `/friend/${friendId.value}`,
    })
    button.disabled = true;
}