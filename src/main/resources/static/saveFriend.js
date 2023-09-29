document.querySelectorAll(".save").forEach(function (button) {
    button.addEventListener("click", function (event) {
        let friendId = event.target;
        $.ajax({
            type: "POST",
            url: `/friend/${friendId.value}`,
        })
        button.disabled = true;
    })
});

document.querySelectorAll(".delete").forEach(function (button) {
    button.addEventListener("click", function (event) {
        let friendId = event.target;
        $.ajax({
            type: "DELETE",
            url: `/friend/${friendId.value}`,
        })
        button.disabled = true;
    })
});
