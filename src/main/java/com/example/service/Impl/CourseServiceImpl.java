package com.example.service.Impl;

import com.example.dao.CourseDao;
import com.example.entity.Course;
import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> queryAll() {
        return courseDao.queryAll();
    }

    @Override
    public int addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    public int updateCourse(Course course) {
        return courseDao.updateCourse(course);
    }

    @Override
    public int deleteCourse(int cid) {
        return courseDao.deleteCourse(cid);
    }

    @Override
    public int deleteAll(List<Integer> ids) {
        return courseDao.deleteAll(ids);
    }

    @Override
    public List<Course> queryToStudent(int sid) {
        return courseDao.queryToStudent(sid);
    }

    @Override
    public List<Course> queryToTeacher(String tno) {
        return courseDao.queryToTeacher(tno);
    }
}
