package com.example.controller;

import com.example.entity.*;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ProfessionService professionService;
    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/")
    public String index(
            Model model,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        if (session.getAttribute("sno") != null) {
            model.addAttribute("sno", session.getAttribute("sno"));
        }
        if (session.getAttribute("tno") != null) {
            model.addAttribute("tno", session.getAttribute("tno"));
        }
        return "index";
    }

    @RequestMapping("/student/login")
    public String stu_login() {
        return "student/login";
    }

    @RequestMapping("/student/main")
    public String stu_information(
            Model model,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Student student1 = studentService.queryBySno((String) session.getAttribute("sno"));
        int sid = student1.getSid();
        List<Course> courses = courseService.queryToStudent(sid);
        List<Score> scores = scoreService.queryToStudent((String) session.getAttribute("sno"));
        model.addAttribute("student1", student1);
        model.addAttribute("courses", courses);
        model.addAttribute("scores", scores);
        return "/student/main";
    }
    @RequestMapping("/teacher/login")
    public String tea_login() {
        return "teacher/login";
    }

    @RequestMapping("/teacher/main")
    public String tea_main(
            Model model,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Teacher teacher1 = teacherService.queryByTno((String)session.getAttribute("tno"));
        model.addAttribute("teacher1",teacher1);
        return "teacher/index";
    }

    @RequestMapping("/teacher/information")
    public String tea_information(
            Model model,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Teacher teacher1 = teacherService.queryByTno((String)session.getAttribute("tno"));
        model.addAttribute("teacher1",teacher1);
        return "teacher/information";
    }

    @RequestMapping("/teacher/main-teacher-course")
    public String tea_teacher_course(
            Model model,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Teacher teacher1 = teacherService.queryByTno((String)session.getAttribute("tno"));
        List<Course> course1 = teacherService.queryAllCourse();
        model.addAttribute("teacher1",teacher1);
        model.addAttribute("course1", course1);
        return "teacher/teacher_course";
    }

    @RequestMapping("/teacher/main-student")
    public String tea_student(
            Model model,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Teacher teacher1 = teacherService.queryByTno((String)session.getAttribute("tno"));
        String college = teacher1.getName();
        List<Student> student1 = teacherService.queryAllStudent(college);
        List<Profession> professions = teacherService.queryAllClass(college);
        model.addAttribute("teacher1", teacher1);
        model.addAttribute("student1", student1);
        model.addAttribute("classes", professions);
        return "teacher/student";
    }

    @RequestMapping("/teacher/main-teacher")
    public String tea_teacher(
            Model model,
            HttpServletRequest request
    ){
        HttpSession session = request.getSession();
        Teacher teacher1 = teacherService.queryByTno((String)session.getAttribute("tno"));
        String college = teacher1.getName();
        List<Teacher> teachers = teacherService.queryAll(college);
        model.addAttribute("teacher1", teacher1);
        model.addAttribute("teachers", teachers);
        return "/teacher/teacher";
    }

    @RequestMapping("/teacher/main-course")
    public String tea_course(
            Model model,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Teacher teacher1 = teacherService.queryByTno((String)session.getAttribute("tno"));
        List<Course> courses = courseService.queryAll();
        model.addAttribute("teacher1", teacher1);
        model.addAttribute("courses", courses);
        return "teacher/course";
    }

    @RequestMapping("/teacher/main-profession")
    public String tea_profession(
            Model model,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Teacher teacher1 = teacherService.queryByTno((String)session.getAttribute("tno"));
        String college = teacher1.getName();
        List<Profession> professions = professionService.queryAll(college);
        model.addAttribute("teacher1", teacher1);
        model.addAttribute("professions", professions);
        return "teacher/profession";
    }

    @RequestMapping("/teacher/main-score")
    public String tea_score(
            Model model,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Teacher teacher1 = teacherService.queryByTno((String)session.getAttribute("tno"));
        List<Course> courses = courseService.queryToTeacher((String) session.getAttribute("tno"));
        List<Score> scores = scoreService.queryToTeacher((String) session.getAttribute("tno"));
        model.addAttribute("teacher1", teacher1);
        model.addAttribute("courses", courses);
        model.addAttribute("scores", scores);
        return "teacher/score";
    }
}
