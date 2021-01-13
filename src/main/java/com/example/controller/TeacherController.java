package com.example.controller;

import com.example.entity.*;
import com.example.service.*;
import com.example.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ProfessionService professionService;
    @Autowired
    private ScoreService scoreService;

    @PostMapping("/tea_login")
    public String login(
            @RequestParam("tno") String tno,
            @RequestParam("pass") String pwd,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Teacher teacher = new Teacher();
        String pass = Md5Utils.md5(pwd);
        teacher.setTno(tno);
        teacher.setPass(pass);
        Teacher teacher1 = teacherService.login(teacher);
        if (teacher1 != null) {
            session.setAttribute("tno", tno);
            return "success";
        } else {
            return "failed";
        }
    }
    @PostMapping("/tea_update")
    public String update(
            @RequestParam("sex") String sex,
            @RequestParam("birthday") String birthday,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Teacher teacher = new Teacher();
        teacher.setTno((String) session.getAttribute("tno"));
        teacher.setSex(sex);
        teacher.setBirthday(birthday);
        teacher.setPhone(phone);
        teacher.setAddress(address);
        int teacher1 = teacherService.update(teacher);
        if (teacher1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }
    @PostMapping("/tea_update_pass")
    public String updatePass(
            @RequestParam("old") String old,
            @RequestParam("pass") String pwd,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        String oldPwd = Md5Utils.md5(old);
        String pass = Md5Utils.md5(pwd);
        Teacher teacher = new Teacher();
        teacher.setTno((String) session.getAttribute("tno"));
        teacher.setPass(pass);
        teacher.setOldPwd(oldPwd);
        int teacher1 = teacherService.updatePass(teacher);
        if (teacher1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }
    @PostMapping("/add_teacher_course")
    public String add_teacher_course(
            @RequestParam("course") String id,
            @RequestParam("time") String time,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Teacher teacher = teacherService.queryByTno((String) session.getAttribute("tno"));
        Integer tid = teacher.getTid();
        int cid = 0;
        try {
            cid = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Teacher_Course teacher_course = new Teacher_Course();
        teacher_course.setCid(cid);
        teacher_course.setTid(tid);
        int teacher1 = teacherService.addTeacherCourse(teacher_course);
        Course course = new Course();
        course.setCid(cid);
        course.setTime(time);
        int course1 = teacherService.updateCourseTime(course);
        if (teacher1 == 1 && course1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/delete_all_student")
    public String deleteAll(
            @RequestParam("ids[]") List<Integer> ids,
            HttpServletRequest request
    ) {
        System.out.println(ids);
        int student1 = studentService.deleteAll(ids);
        if (student1 != 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/addStudent")
    public String addStudent(
            @RequestParam("sno") String sno,
            @RequestParam("sname") String sname,
            @RequestParam("pid") Integer pid
    ) {
        Student student = new Student();
        student.setSno(sno);
        student.setSname(sname);
        student.setPid(pid);
        int student1 = 0;
        try{
            student1 = studentService.addStudent(student);
        } catch (Exception e){
            e.printStackTrace();
        }
        if (student1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/updateStudentByTeacher")
    public String updateStudent(
            @RequestParam("sid") Integer sid,
            @RequestParam("sno") String sno,
            @RequestParam("sname") String sname,
            @RequestParam("pid") Integer pid
    ) {
        Student student = new Student();
        student.setSid(sid);
        student.setSno(sno);
        student.setSname(sname);
        student.setPid(pid);
        int student1 = 0;
        try{
            student1 = studentService.updateByTeacher(student);
        } catch (Exception e){
            e.printStackTrace();
        }
        if (student1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/deleteStudent")
    public String deleteStudent(
            @RequestParam("sno") String sno
    ) {
        int student1 = studentService.deleteBySno(sno);
        if (student1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/addTeacher")
    public String addTeacher(
            @RequestParam("tno") String tno,
            @RequestParam("tname") String tname,
            @RequestParam("position") String position,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Teacher tea = teacherService.queryByTno((String) session.getAttribute("tno"));
        int cid = tea.getCid();
        Teacher teacher = new Teacher();
        teacher.setCid(cid);
        teacher.setTno(tno);
        teacher.setTname(tname);
        teacher.setPosition(position);
        int teacher1 = 0;
        try {
            teacher1 = teacherService.addTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (teacher1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/delete_all_teacher")
    public String deleteAllTeacher(
            @RequestParam("ids[]") List<Integer> ids
    ) {
        int teacher1 = teacherService.deleteAll(ids);
        if (teacher1 != 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/updateTeacherByTeacher")
    public String updateTeacher(
            @RequestParam("tid") Integer tid,
            @RequestParam("tno") String tno,
            @RequestParam("tname") String tname,
            @RequestParam("position") String position
    ) {
        Teacher teacher = new Teacher();
        teacher.setTid(tid);
        teacher.setTno(tno);
        teacher.setTname(tname);
        teacher.setPosition(position);
        int teacher1 = 0;
        try {
            teacher1 = teacherService.updateByTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (teacher1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/deleteTeacher")
    public String deleteTeacher(
            @RequestParam("tno") String tno
    ) {
        int teacher1 = teacherService.deleteByTno(tno);
        if (teacher1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/addCourse")
    public String addCourse(
            @RequestParam("cname") String cname,
            @RequestParam("credit") Float credit,
            @RequestParam("type") String type,
            @RequestParam("period") Integer period
    ) {
        Course course = new Course();
        course.setCname(cname);
        course.setCredit(credit);
        course.setPeriod(period);
        course.setType(type);
        int course1 = courseService.addCourse(course);
        if (course1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/updateCourse")
    public String updateCourse(
            @RequestParam("cid") Integer cid,
            @RequestParam("cname") String cname,
            @RequestParam("credit") Float credit,
            @RequestParam("type") String type,
            @RequestParam("period") Integer period
    ) {
        Course course = new Course();
        course.setCid(cid);
        course.setCname(cname);
        course.setCredit(credit);
        course.setPeriod(period);
        course.setType(type);
        int course1 = courseService.updateCourse(course);
        if (course1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/deleteCourse")
    public String deleteCourse(
            @RequestParam("cid") Integer cid
    ) {
        int course1 = courseService.deleteCourse(cid);
        if (course1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/delete_all_course")
    public String deleteAllCourse(
            @RequestParam("ids[]") List<Integer> ids
    ) {
        int course1 = courseService.deleteAll(ids);
        if (course1 != 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/addProfession")
    public String addProfession(
            @RequestParam("cid") Integer college,
            @RequestParam("pname") String pname
    ) {
        Profession profession = new Profession();
        profession.setCollege(college);
        profession.setPname(pname);
        int profession1 = 0;
        try {
            profession1 = professionService.addProfession(profession);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (profession1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/updateProfession")
    public String updateProfession(
            @RequestParam("pid") Integer pid,
            @RequestParam("pname") String pname
    ) {
        Profession profession = new Profession();
        profession.setPid(pid);
        profession.setPname(pname);
        int profession1 = 0;
        try {
            profession1 = professionService.updateProfession(profession);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (profession1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/deleteProfession")
    public String deleteProfession(
            @RequestParam("pid") Integer pid
    ) {
        int profession1 = professionService.deleteProfession(pid);
        if (profession1 == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/delete_all_profession")
    public String deleteAllProfession(
            @RequestParam("ids[]") List<Integer> ids
    ) {
        int profession1 = professionService.deleteAll(ids);
        if (profession1 != 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @PostMapping("/updateScore")
    public String updateScore(
            @RequestParam("cid") Integer cid,
            @RequestParam("sid") Integer sid,
            @RequestParam("usual") Float usual,
            @RequestParam("finall") Float finall
    ) {
       Score score = new Score();
       score.setCid(cid);
       score.setSid(sid);
       score.setUsual(usual);
       score.setFinall(finall);
       System.out.println(usual);
       int score1 = scoreService.updateScore(score);
       if (score1 == 1) {
           return "success";
       } else {
           return "failed";
       }
    }

    @PostMapping("/tea_exit")
    public String exit(
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        session.removeAttribute("tno");
        return "exit";
    }
}
