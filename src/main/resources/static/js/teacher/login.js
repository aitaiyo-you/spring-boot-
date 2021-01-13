window.onload = function () {
    var name = $("input#username");
    var pass = $("input#password");

    var tbUserFeedback = $("#tbUserFeedback");
    var tbPassFeedback = $("#tbPassFeedback");

    name.keyup(function () {
        name.removeClass("is-valid");
        tbUserFeedback.removeClass("valid-feedback");
        name.removeClass("is-invalid");
        tbUserFeedback.removeClass("invalid-feedback");
        tbUserFeedback.text("");
    })

    pass.keyup(function () {
        pass.removeClass("is-valid");
        tbPassFeedback.removeClass("valid-feedback");
        pass.removeClass("is-invalid");
        tbPassFeedback.removeClass("invalid-feedback");
        tbPassFeedback.text("");
    })

    $(".login-button").click(function () {
        var username = name.val();
        if (username.length == 0) {
            name.addClass("is-invalid");
            tbUserFeedback.addClass("invalid-feedback");
            tbUserFeedback.text("您的登录名不能为空!");
            return;
        }

        var password = pass.val()
        if (password.length == 0) {
            pass.addClass("is-invalid");
            tbPassFeedback.addClass("invalid-feedback");
            tbPassFeedback.text("您的密码不能为空!");
            return;
        }

        $.ajax({
            url: "/tea_login",
            type: "POST",
            data: {
                tno: username,
                pass: password
            },
            success: function (e) {
                if (e == "success") {
                    window.location.href = "/teacher/main"
                } else {
                    swal("抱歉", "用户名或密码错误", "error")
                }
            }
        })
    })
}