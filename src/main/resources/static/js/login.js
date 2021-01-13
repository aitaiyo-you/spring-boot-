window.onload = function () {
    var name = $("input#username")
    var pass = $("input#password")
    var ver = $("input#verify")

    var tbUserFeedback = $("#tbUserFeedback");
    var tbPassFeedback = $("#tbPassFeedback");
    var tbVerifyFeedback = $("#tbVerifyFeedback");

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

    ver.keyup(function () {
        ver.removeClass("is-valid");
        tbVerifyFeedback.removeClass("valid-feedback");
        ver.removeClass("is-invalid");
        tbVerifyFeedback.removeClass("invalid-feedback");
        tbVerifyFeedback.text("");
    })

    $("#login_button").click(function () {
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

        var TbVerify = ver.val();
        if (TbVerify.length == 0) {
            ver.addClass("is-invalid");
            tbVerifyFeedback.addClass("invalid-feedback");
            tbVerifyFeedback.text("请输入验证码!");
            return;
        }
        // 验证验证码
        else if (verifyCode.validate(TbVerify) == false) {
            ver.addClass("is-invalid");
            tbVerifyFeedback.addClass("invalid-feedback");
            tbVerifyFeedback.text("验证码错误!");
            //刷新验证码
            verifyCode.refresh();
            return;
        }

        $.ajax({
            url: "/stu_login",
            type: "POST",
            data: {
                name: username,
                pass: password
            },
            success: function (e) {
                if (e == "success") {
                    window.location.href = "/student/main"
                }
                else
                    swal("抱歉", "学号或密码错误", "error")
            }
        })
    })

    var verifyCode = new GVerify({
        id: "verify-img",    //容器的ID
        type: "blend"    //图形验证码的类型：blend-数字字母混合类型（默认）、number-纯数字、letter-纯字母
    });
}
