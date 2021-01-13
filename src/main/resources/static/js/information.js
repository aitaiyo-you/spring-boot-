window.onload = function () {
    var Phone = $("input#phone");
    var Birthday = $("input#birthday");
    var Sex = $("select#sex");
    var Province = $("select#province");
    var City = $("select#city");
    var OldPwd = $("input#oldPwd");
    var NewPwd = $("input#newPwd");
    var RePwd = $("input#rePwd");

    $("#update").click(function () {
        var phone = Phone.val();
        var birthday = Birthday.val();
        var sex = Sex.val();
        var province = Province.val();
        var city = City.val();
        if (province.toString() == "none") {
            swal("抱歉", "请选择省份！", "error");
            return;
        }
        if (city.toString() == "none") {
            swal("抱歉", "请选择城市！", "error");
            return;
        }
        if (phone.length != 11) {
            swal("抱歉", "请输入正确的电话号码！", "error");
            return;
        }
        var address = provinceArr[province] + city;
        $.ajax({
            url: "/stu_update",
            type: "POST",
            data: {
                phone: phone,
                birthday: birthday,
                sex: sex,
                address: address
            },
            success: function (e) {
                if (e == "success"){
                    swal("恭喜", "修改信息成功！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    },1000)
                } else {
                    swal("抱歉", "修改信息失败！", "error");
                }
            }
        })
    })
    $("#updatePass").click(function () {
        var oldPwd = OldPwd.val();
        var newPwd = NewPwd.val();
        var rePwd = RePwd.val();
        if (oldPwd.length == 0) {
            swal("抱歉", "原密码不得为空！", "error");
            return ;
        }
        if (newPwd.length <= 4 || newPwd.length >= 20) {
            swal("抱歉", "密码长度应为4~20位！", "error");
            return ;
        }
        if (newPwd != rePwd) {
            swal("抱歉", "两次输入密码必须一致！", "error");
            return ;
        }
        if (oldPwd == newPwd) {
            swal("抱歉", "新密码和原密码不得相同！", "error");
            return ;
        }
        $.ajax({
            url: "/stu_update_pass",
            type: "POST",
            data: {
              oldPwd: oldPwd,
              pass: newPwd
            },
            success: function (e) {
                if (e == "success") {
                    swal("恭喜", "修改密码成功，请重新登录！", "success")
                    setTimeout(function () {
                        stu_exit();
                    },1000)
                } else {
                    swal("抱歉", "原密码输入错误！", "error");
                }
            }
        })
    })
    $(".chooseButton").click(function () {
        var cid = $(".cid").eq($(".chooseButton").index(this)).text()
        var sid = $(".sid").eq($(".chooseButton").index(this)).text()
        $.ajax({
            url: "/chooseClass",
            type: "POST",
            data: {
                cid: cid,
                sid: sid
            },
            success: function (e) {
                if (e == "success"){
                    swal("恭喜", "选课成功！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    },1000)
                } else {
                    swal("抱歉", "选课失败！", "error");
                }
            }
        })
    })
    $(".exitClassButton").click(function () {
        var cid = $(".cid1").eq($(".exitClassButton").index(this)).text()
        var sid = $(".sid1").eq($(".exitClassButton").index(this)).text()
        $.ajax({
            url: "/exitClass",
            type: "POST",
            data: {
                cid: cid,
                sid: sid
            },
            success: function (e) {
                if (e == "success"){
                    swal("恭喜", "退课成功！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    },1000)
                } else {
                    swal("抱歉", "退课失败！", "error");
                }
            }
        })
    })
}
function stu_exit() {
    $.ajax({
        url: "/stu_exit",
        type: "POST",
        success: function () {
            window.location.href = "/student/login";
        }
    })
}
