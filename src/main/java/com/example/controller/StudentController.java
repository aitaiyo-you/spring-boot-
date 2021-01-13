package com.example.controller;

import com.example.entity.Score;
import com.example.entity.Student;
import com.example.service.ScoreService;
import com.example.service.StudentService;
import com.example.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ScoreService scoreService;

    @PostMapping("/stu_login")
    public String login(
            @RequestParam("name") String sno,
            @RequestParam("pass") String pass,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        String password = Md5Utils.md5(pass);
        Student student = new Student();
        student.setSno(sno);
        student.setPass(password);
        Student student1 = studentService.login(student);
        if (student1 != null) {
            session.setAttribute("sno", sno);
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("stu_update")
    public String update(
            @RequestParam("sex") String sex,
            @RequestParam("phone") String phone,
            @RequestParam("birthday") String birthday,
            @RequestParam("address") String address,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Student student = new Student();
        student.setSno((String) session.getAttribute("sno"));
        student.setAddress(address);
        student.setBirthday(birthday);
        student.setSex(sex);
        student.setPhone(phone);
        int student1 = studentService.update(student);
        if (student1 == 1){
            return "success";
        } else  {
            return "failed";
        }
    }

    @PostMapping("stu_update_pass")
    public String updatePass(
            @RequestParam("oldPwd") String oldPwd,
            @RequestParam("pass") String pass,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        String old = Md5Utils.md5(oldPwd);
        String newPwd = Md5Utils.md5(pass);
        Student student = new Student();
        student.setSno((String) session.getAttribute("sno"));
        student.setPass(newPwd);
        student.setOldPass(old);
        int student1 = studentService.updatePass(student);
        if (student1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/chooseClass")
    public String chooseClass(
            @RequestParam("cid") Integer cid,
            @RequestParam("sid") Integer sid
    ) {
        Score score = new Score();
        score.setCid(cid);
        score.setSid(sid);
        int score1 = scoreService.chooseClass(score);
        if (score1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/exitClass")
    public String exitClass(
            @RequestParam("cid") Integer cid,
            @RequestParam("sid") Integer sid
    ) {
        Score score = new Score();
        score.setCid(cid);
        score.setSid(sid);
        int score1 = scoreService.exitClass(score);
        if (score1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("stu_exit")
    public String exit(
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        session.removeAttribute("sno");
        return "exit";
    }
}
