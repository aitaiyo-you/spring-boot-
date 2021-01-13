package com.example.dao;

import com.example.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherDao {
    public Teacher login(Teacher teacher);
    public List<Teacher> queryAll(String college);
    public Teacher queryById(int id);
    public Teacher queryByTno(String tno);
    public int update(Teacher teacher);
    public int updatePass(Teacher teacher);
    public List<Course> queryAllCourse();
    public List<Course> queryCourseByTno(String tno);
    public int addTeacherCourse(Teacher_Course teacher_course);
    public int updateCourseTime(Course course);
    public List<Student> queryAllStudent(String college);
    public List<Profession> queryAllClass(String college);
    public int addTeacher(Teacher teacher);
    public int deleteAll(List<Integer> ids);
    public int updateByTeacher(Teacher teacher);
    public int deleteByTno(String tno);
}
