let button = document.getElementById("list");

button.onclick = function () {
    console.log("클릭")
    $.ajax({
        type: "GET",
        url: `/friend`,
        success: function (event) {
            $("#friend").html(event);
        }
    })
}
