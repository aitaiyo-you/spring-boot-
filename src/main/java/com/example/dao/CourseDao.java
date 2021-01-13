package com.example.dao;

import com.example.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseDao {
    public List<Course> queryAll();
    public int addCourse(Course course);
    public int updateCourse(Course course);
    public int deleteCourse(int cid);
    public int deleteAll(List<Integer> ids);
    public List<Course> queryToStudent(int sid);
    public List<Course> queryToTeacher(String tno);
}
