window.onload = function () {

    $(".studentItem").click(function () {
        $("#sno2").val($(".sno").eq($(".studentItem").index(this)).text())
        $("#sname2").val($(".sname").eq($(".studentItem").index(this)).text())
        $("#sid2").val($(".sid").eq($(".studentItem").index(this)).text())
        $("#classes2").val($(".cid").eq($(".studentItem").index(this)).text())
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
                arr[arr.length] = $(".sid").eq(i).text()
            }
        }
        if (arr == '') {
            swal("抱歉", "请先选择学生！", "error");
            return;
        }
        $.ajax({
            url: "/delete_all_student",
            type: "POST",
            data: {
                ids: arr,
            },
            success: function (e) {
                if (e == "success") {
                    swal("恭喜", "批量删除学生成功，1s后刷新界面！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000)
                } else {
                    swal("抱歉", "批量删除学生失败！", "error")
                }
            }
        })
    })

    $("#addStudent").click(function () {
        var sno = $("input#sno1").val();
        var sname = $("input#sname1").val();
        var pid = $("select#classes1").val();
        if (sno == '' || sname == '') {
            swal("抱歉", "不得有空项！", "error");
            return;
        }
        $.ajax({
            url: "/addStudent",
            type: "POST",
            data: {
                sno: sno,
                sname: sname,
                pid: pid
            },
            success: function (e) {
                if (e == 'success') {
                    swal("恭喜", "新增学生成功，1s后刷新界面！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000)
                }
                else {
                    swal("抱歉", "新增学生失败，可能是学号重复导致！", "error")
                }
            }
        })
    })

    $("#updateStudent").click(function () {
        var sid = $("input#sid2").val();
        var sno = $("input#sno2").val();
        var sname = $("input#sname2").val();
        var pid = $("select#classes2").val();
        if (sid == '') {
            swal("抱歉", "请先选择学生！", "error");
            return;
        }
        if (sno == '' || sname == '') {
            swal("抱歉", "不得有空项！", "error");
            return;
        }
        $.ajax({
            url: "/updateStudentByTeacher",
            type: "POST",
            data: {
                sid: sid,
                sno: sno,
                sname: sname,
                pid: pid
            },
            success: function (e) {
                if (e == 'success') {
                    swal("恭喜", "修改学生成功，1s后刷新界面！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000)
                }
                else {
                    swal("抱歉", "修改学生失败，可能是学号重复导致！", "error")
                }
            }
        })
    })

    $("#deleteStudent").click(function () {
        var sno = $("input#sno2").val();
        if (sno == '') {
            swal("抱歉", "请先选择学生！", "error");
            return;
        }
        $.ajax({
            url: "/deleteStudent",
            type: "POST",
            data: {
                sno: sno,
            },
            success: function (e) {
                if (e == 'success') {
                    swal("恭喜", "删除学生成功，1s后刷新界面！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000)
                } else {
                    swal("抱歉", "删除学生失败！", "error")
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
    table = document.getElementById("student_table");
    tr = table.getElementsByTagName("tr");

    // 循环表格每一行，查找匹配项
    for (i = 1; i < tr.length; i++) {
        th1 = tr[i].getElementsByTagName("th")[1];
        th2 = tr[i].getElementsByTagName("th")[3];
        th3 = tr[i].getElementsByTagName("th")[4];
        if (th1 || th2 || th3) {
            if (th1.innerHTML.toUpperCase().indexOf(filter) > -1 || th2.innerHTML.toUpperCase().indexOf(filter) > -1 || th3.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}