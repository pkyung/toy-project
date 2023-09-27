let list = document.getElementById("list");
let mail = document.getElementById("mail");
let search = document.getElementById("search");

list.onclick = function () {
    $.ajax({
        type: "GET",
        url: `/friend`,
        success: function (event) {
            $("#friend").html(event);
        }
    })
}

search.onclick = function () {
    let keyword = document.getElementById("keyword").value;
    $.ajax({
        type: "GET",
        data: {
            keyword: keyword
        },
        contentType: 'application/x-www-form-urlencoded;charset=euc-kr',
        url: `/friend/search`,
        success: function (event) {
            $("#friend").html(event);
        }
    })
}

mail.onclick = function () {
    $.ajax({
        type: "GET",
        url: `/friend/request`,
        success: function (event) {
            $("#friend").html(event);
        }
    })
}