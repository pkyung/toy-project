document.querySelectorAll(".btn").forEach(function (button) {
    button.addEventListener("click", function (event) {
        let friendId = event.target;
        $.ajax({
            type: "POST",
            url: `/friend/request/${friendId.value}`,
        })
        button.disabled = true;
    })
})