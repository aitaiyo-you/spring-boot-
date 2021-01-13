window.onload = function () {

    $(".teacherItem").click(function () {
        $("#tno2").val($(".tno").eq($(".teacherItem").index(this)).text())
        $("#tname2").val($(".tname").eq($(".teacherItem").index(this)).text())
        $("#tid2").val($(".tid").eq($(".teacherItem").index(this)).text())
        $("#position2").val($(".position").eq($(".teacherItem").index(this)).text());
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
            swal("抱歉", "请先选择教师！", "error");
            return;
        }
        $.ajax({
            url: "/delete_all_teacher",
            type: "POST",
            data: {
                ids: arr,
            },
            success: function (e) {
                if (e == "success") {
                    swal("恭喜", "批量删除教师成功，1s后刷新界面！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000)
                } else {
                    swal("抱歉", "批量删除教师失败！", "error")
                }
            }
        })
    })

    $("#addTeacher").click(function () {
        var tno = $("input#tno1").val();
        var tname = $("input#tname1").val();
        var position = $("select#position1").val();
        if (tno == '' || tname == '') {
            swal("抱歉", "不得有空项！", "error");
            return;
        }
        $.ajax({
            url: "/addTeacher",
            type: "POST",
            data: {
                tno: tno,
                tname: tname,
                position: position,
            },
            success: function (e) {
                if (e == 'success') {
                    swal("恭喜", "新增教师成功，1s后刷新界面！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000)
                }
                else {
                    swal("抱歉", "新增教师失败，可能是教师编号重复导致！", "error")
                }
            }
        })
    })
    $("#updateTeacher").click(function () {
        var tid = $("input#tid2").val();
        var tno = $("input#tno2").val();
        var tname = $("input#tname2").val();
        var position = $("select#position2").val();
        if (tid == '') {
            swal("抱歉", "请先选择教师！", "error");
            return;
        }
        if (tno == '' || tname == '') {
            swal("抱歉", "不得有空项！", "error");
            return;
        }
        $.ajax({
            url: "/updateTeacherByTeacher",
            type: "POST",
            data: {
                tid: tid,
                tno: tno,
                tname: tname,
                position: position,
            },
            success: function (e) {
                if (e == 'success') {
                    swal("恭喜", "修改教师成功，1s后刷新界面！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000)
                }
                else {
                    swal("抱歉", "修改教师失败，可能是教师编号重复导致！", "error")
                }
            }
        })
    })
    $("#deleteTeacher").click(function () {
        var tno = $("input#tno2").val();
        if (tno == '') {
            swal("抱歉", "请先选择教师！", "error");
            return;
        }
        $.ajax({
            url: "/deleteTeacher",
            type: "POST",
            data: {
                tno: tno,
            },
            success: function (e) {
                if (e == 'success') {
                    swal("恭喜", "删除教师成功，1s后刷新界面！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000)
                }
                else {
                    swal("抱歉", "删除教师失败！", "error")
                }
            }
        })
    })
}
function search() {
    // 声明变量
    var input, filter, table, tr, th1, th2, th3, i;
    input = document.getElementById("search");
    filter = input.value.toUpperCase();
    table = document.getElementById("teacher_table");
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