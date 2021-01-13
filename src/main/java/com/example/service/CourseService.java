package com.example.service;

import com.example.entity.Course;

import java.util.List;

public interface CourseService {
    public List<Course> queryAll();
    public int addCourse(Course course);
    public int updateCourse(Course course);
    public int deleteCourse(int cid);
    public int deleteAll(List<Integer> ids);
    public List<Course> queryToStudent(int sid);
    public List<Course> queryToTeacher(String tno);
}
