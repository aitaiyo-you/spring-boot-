window.onload = function () {

    $(".professionItem").click(function () {
        $("#pname2").val($(".pname").eq($(".professionItem").index(this)).text())
        $("#pid2").val($(".pid").eq($(".professionItem").index(this)).text())
    })

    $("#select-all").change(function () {
        if ($("#select-all").prop("checked")) {
            $(".checkbox-user").prop("checked", true)
        } else {
            $(".checkbox-user").prop("checked", false)
        }
    })

    $(".checkbox-user").change(function () {
        let num = 0;
        for (let i = 0; i < $(".checkbox-user").length; i++){
            if ($(".checkbox-user").eq(i).prop("checked")) {
                num ++;
            }
        }
        if (num == $(".checkbox-user").length) {
            $("#select-all").prop("checked", true)
        }
    })

    $(".deleteAllButton").click(function () {
        let arr = [];
        for (let i = 0; i < $(".checkbox-user").length; i++) {
            if ($(".checkbox-user").eq(i).prop("checked")) {
                arr[arr.length] = $(".tid").eq(i).text()
            }
        }
        if (arr == '') {
            swal("抱歉", "请先选择班级！", "error");
            return;
        }
        $.ajax({
            url: "/delete_all_profession",
            type: "POST",
            data: {
                ids: arr,
            },
            success: function (e) {
                if (e == "success") {
                    swal("恭喜", "批量删除班级成功，1s后刷新界面！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000)
                } else {
                    swal("抱歉", "批量删除班级失败！", "error")
                }
            }
        })
    })
    $("#addProfession").click(function () {
        var cid = $("input#cid1").val();
        var pname = $("input#pname1").val();
        if (pname == '') {
            swal("抱歉", "班级名不得为空！", "error");
            return;
        }
        $.ajax({
            url: "/addProfession",
            type: "POST",
            data: {
                cid: cid,
                pname: pname
            },
            success: function (e) {
                if (e == "success") {
                    swal("恭喜", "新增班级成功，1s后刷新界面！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000)
                } else {
                    swal("抱歉", "新增班级失败，可能是班级名重复导致！", "error")
                }
            }
        })
    })
    $("#updateProfession").click(function () {
        var pid = $("input#pid2").val();
        var pname = $("input#pname2").val();
        if (pid == '') {
            swal("抱歉", "请先选择班级！", "error");
            return;
        }
        if (pname == '') {
            swal("抱歉", "班级名不得为空！", "error");
            return;
        }
        $.ajax({
            url: "/updateProfession",
            type: "POST",
            data: {
                pid: pid,
                pname: pname
            },
            success: function (e) {
                if (e == "success") {
                    swal("恭喜", "修改班级成功，1s后刷新界面！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000)
                } else {
                    swal("抱歉", "修改班级失败，可能是班级名重复导致！", "error")
                }
            }
        })
    })
    $("#deleteProfession").click(function () {
        var pid = $("input#pid2").val();
        if (pid == '') {
            swal("抱歉", "请先选择班级！", "error");
            return;
        }
        $.ajax({
            url: "/deleteProfession",
            type: "POST",
            data: {
                pid: pid,
            },
            success: function (e) {
                if (e == "success") {
                    swal("恭喜", "删除班级成功，1s后刷新界面！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000)
                } else {
                    swal("抱歉", "删除班级失败！", "error")
                }
            }
        })
    })
}
function search() {
    // 声明变量
    var input, filter, table, tr, th1, th2, i;
    input = document.getElementById("search");
    filter = input.value.toUpperCase();
    table = document.getElementById("profession_table");
    tr = table.getElementsByTagName("tr");

    // 循环表格每一行，查找匹配项
    for (i = 1; i < tr.length; i++) {
        th1 = tr[i].getElementsByTagName("th")[1];
        th2 = tr[i].getElementsByTagName("th")[3];
        if (th1 || th2) {
            if (th1.innerHTML.toUpperCase().indexOf(filter) > -1 || th2.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}