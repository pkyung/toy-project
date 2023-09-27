let button = document.getElementById("list");
let search = document.getElementById("search");

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
