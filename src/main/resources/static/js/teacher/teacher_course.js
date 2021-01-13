window.onload = function () {
    $(".courseItem").click(function () {
        $("#TbId").val($(".cid").eq($(".courseItem").index(this)).text())
        $("#TbName").val($(".cname").eq($(".courseItem").index(this)).text())
    })

    var Cid = $("input#TbId");
    var Week_1 = $("select#week_1");
    var Class_1 = $("select#class_1");
    var Week_2 = $("select#week_2");
    var Class_2 = $("select#class_2");

    $("#course_button").click(function () {
        var cid = Cid.val();
        var week_1 = Week_1.val();
        var class_1 = Class_1.val();
        var week_2 = Week_2.val();
        var class_2 = Class_2.val();
        if (cid == '') {
            swal("抱歉", "请先选择课程！", "error");
            return;
        }
        if (week_1 == week_2 && class_1 == class_2) {
            swal("抱歉", "两节课选择时间不能相同！", "error");
            return;
        }
        var time = week_1 + class_1 + '；' + week_2 + class_2;
        $.ajax({
            url: "/add_teacher_course",
            type: "POST",
            data: {
                course: cid,
                time: time
            },
            success: function (e) {
                if (e == "success") {
                    swal("恭喜", "安排授课成功！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    },1000)
                }
                else {
                    swal("抱歉", "安排授课失败！", "error");
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
    table = document.getElementById("teacher_college_table");
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