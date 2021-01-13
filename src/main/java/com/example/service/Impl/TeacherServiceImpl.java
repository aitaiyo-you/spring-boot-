package com.example.service.Impl;

import com.example.dao.TeacherDao;
import com.example.entity.*;
import com.example.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher login(Teacher teacher) {
        return teacherDao.login(teacher);
    }

    @Override
    public List<Teacher> queryAll(String college) {
        return teacherDao.queryAll(college);
    }

    @Override
    public Teacher queryById(int id) {
        return teacherDao.queryById(id);
    }

    @Override
    public Teacher queryByTno(String tno) {
        return teacherDao.queryByTno(tno);
    }

    @Override
    public int update(Teacher teacher) {
        return teacherDao.update(teacher);
    }

    @Override
    public int updatePass(Teacher teacher) {
        return teacherDao.updatePass(teacher);
    }

    @Override
    public List<Course> queryAllCourse() {
        return teacherDao.queryAllCourse();
    }

    @Override
    public List<Course> queryCourseByTno(String tno) {
        return teacherDao.queryCourseByTno(tno);
    }

    @Override
    public int addTeacherCourse(Teacher_Course teacher_course) {
        return teacherDao.addTeacherCourse(teacher_course);
    }

    @Override
    public int updateCourseTime(Course course) {
        return teacherDao.updateCourseTime(course);
    }

    @Override
    public List<Student> queryAllStudent(String college) {
        return teacherDao.queryAllStudent(college);
    }

    @Override
    public List<Profession> queryAllClass(String college) {
        return teacherDao.queryAllClass(college);
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherDao.addTeacher(teacher);
    }

    @Override
    public int deleteAll(List<Integer> ids) {
        return teacherDao.deleteAll(ids);
    }

    @Override
    public int updateByTeacher(Teacher teacher) {
        return teacherDao.updateByTeacher(teacher);
    }

    @Override
    public int deleteByTno(String tno) {
        return teacherDao.deleteByTno(tno);
    }
}
