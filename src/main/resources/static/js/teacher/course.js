window.onload = function () {

    $(".courseItem").click(function () {
        $("#cname2").val($(".cname").eq($(".courseItem").index(this)).text())
        $("#cid2").val($(".cid").eq($(".courseItem").index(this)).text())
        $("#type2").val($(".type").eq($(".courseItem").index(this)).text())
        $("#credit2").val($(".credit").eq($(".courseItem").index(this)).text())
        $("#period2").val($(".period").eq($(".courseItem").index(this)).text())
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
                arr[arr.length] = $(".cid").eq(i).text()
            }
        }
        if (arr == '') {
            swal("抱歉", "请先选择课程！", "error");
            return;
        }
        $.ajax({
            url: "/delete_all_course",
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
    $("#addCourse").click(function () {
        var cname = $("input#cname1").val();
        var credit = $("input#credit1").val();
        var type = $("select#type1").val();
        var period = $("input#period1").val();
        if (cname == '' || credit == '' || period == '') {
            swal("抱歉", "不得有空项！", "error");
            return;
        }
        $.ajax({
            url: "/addCourse",
            type: "POST",
            data: {
                cname: cname,
                credit: credit,
                type: type,
                period: period,
            },
            success: function (e) {
                if (e == 'success') {
                    swal("恭喜", "新增课程成功，1s后刷新界面！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000)
                }
                else {
                    swal("抱歉", "新增课程失败！", "error")
                }
            }
        })
    })
    $("#updateCourse").click(function () {
        var cid = $("input#cid2").val();
        var cname = $("input#cname2").val();
        var credit = $("input#credit2").val();
        var type = $("select#type2").val();
        var period = $("input#period2").val();
        if (cid == '') {
            swal("抱歉", "请先选择课程！", "error");
            return;
        }
        if (cname == '' || credit == '' || period == '') {
            swal("抱歉", "不得有空项！", "error");
            return;
        }
        $.ajax({
            url: "/updateCourse",
            type: "POST",
            data: {
                cid: cid,
                cname: cname,
                credit: credit,
                type: type,
                period: period,
            },
            success: function (e) {
                if (e == 'success') {
                    swal("恭喜", "修改课程成功，1s后刷新界面！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000)
                }
                else {
                    swal("抱歉", "修改课程失败！", "error")
                }
            }
        })
    })
    $("#deleteCourse").click(function () {
        var cid = $("input#cid2").val();
        if (cid == ''){
            swal("抱歉", "请先选择课程！", "error");
            return;
        }
        $.ajax({
            url: "/deleteCourse",
            type: "POST",
            data: {
                cid: cid,
            },
            success: function (e) {
                if (e == 'success') {
                    swal("恭喜", "修删除课程成功，1s后刷新界面！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000)
                }
                else {
                    swal("抱歉", "删除课程失败！", "error")
                }
            }
        })
    })
}
function search() {
    // 声明变量
    var input, filter, table, tr, th1, th2, th3, th4, i;
    input = document.getElementById("search");
    filter = input.value.toUpperCase();
    table = document.getElementById("course_table");
    tr = table.getElementsByTagName("tr");

    // 循环表格每一行，查找匹配项
    for (i = 1; i < tr.length; i++) {
        th1 = tr[i].getElementsByTagName("th")[1];
        th2 = tr[i].getElementsByTagName("th")[3];
        th3 = tr[i].getElementsByTagName("th")[4];
        th4 = tr[i].getElementsByTagName("th")[5];
        if (th1 || th2 || th3) {
            if (th1.innerHTML.toUpperCase().indexOf(filter) > -1 || th2.innerHTML.toUpperCase().indexOf(filter) > -1 ||
                th3.innerHTML.toUpperCase().indexOf(filter) > -1 || th4.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}