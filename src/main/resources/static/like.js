const heart = document.querySelector("#isNot");

heart.onclick = function () {
    $.ajax({
        type: "POST",
        url: `/like/${boardId.value}`,
    })
    $("#isNot").removeClass("fa-regular").addClass("fa-solid");
    let cnt = $("#cnt").text();
    $("#cnt").text(++cnt);
}