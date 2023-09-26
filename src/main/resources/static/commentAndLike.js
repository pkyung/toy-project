let boardId = document.getElementById("boardId");

$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: `/comment/${boardId.value}`,
        success: function (event) {
            $("#comment").html(event);
        }
    })
});

$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: `/like/${boardId.value}`,
        success: function (event) {
            $("#like").html(event);
        }
    })
});