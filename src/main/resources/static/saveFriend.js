let save = document.getElementById("save");
let deleteButton = document.getElementById("delete");
let friendId = document.getElementById("save");

save.onclick = function () {
    $.ajax({
        type: "POST",
        url: `/friend/${friendId.value}`,
    })
    save.disabled = true;
}

deleteButton.onclick = function () {
    $.ajax({
        type: "DELETE",
        url: `/friend/${friendId.value}`,
    })
    deleteButton.disabled = true;
}