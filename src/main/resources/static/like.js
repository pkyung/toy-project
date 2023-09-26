const heart = document.querySelector("#isNot");

heart.onclick = function () {
    $.ajax({
        type: "POST",
        url: `/like/${boardId.value}`,
        success: function () {
            console.log("좋아요 완료");
        }
    })
    $("#isNot").removeClass("fa-regular").addClass("fa-solid");
    let cnt = $("#cnt").text();
    $("#cnt").text(cnt++);
}