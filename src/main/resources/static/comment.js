let boardId = document.getElementById("boardId");

$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: `/comment/${boardId.value}`,
        success: function (event) {
            console.log(event);
            $("#comment").html(event);
        }
    })
});
