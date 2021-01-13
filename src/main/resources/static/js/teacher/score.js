window.onload = function () {
    $(".updateScoreButton").click(function () {
        var cid = $(".cid").eq($(".updateScoreButton").index(this)).text()
        var sid = $(".sid").eq($(".updateScoreButton").index(this)).val()
        var usual = $(".usual").eq($(".updateScoreButton").index(this)).val()
        var finall = $(".finall").eq($(".updateScoreButton").index(this)).val()
        if (sid == null) {
            swal("抱歉", "请先选择学生！", "error");
            return;
        }
        if (usual == '' || finall == '') {
            swal("抱歉", "成绩不得为空！", "error");
            return;
        }
        $.ajax({
            url: "/updateScore",
            type: "POST",
            data: {
                cid: cid,
                sid: sid,
                usual: usual,
                finall: finall
            },
            success: function (e) {
                if (e == 'success') {
                    swal("恭喜", "新增/修改成绩成功，1s后刷新界面！", "success")
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000)
                }
                else {
                    swal("抱歉", "新增/修改成绩失败！", "error")
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
    table = document.getElementById("score_table");
    tr = table.getElementsByTagName("tr");

    // 循环表格每一行，查找匹配项
    for (i = 1; i < tr.length; i++) {
        th1 = tr[i].getElementsByTagName("th")[0];
        th2 = tr[i].getElementsByTagName("th")[1];
        if (th1 || th2) {
            if (th1.innerHTML.toUpperCase().indexOf(filter) > -1 || th2.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}