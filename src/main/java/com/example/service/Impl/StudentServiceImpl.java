package com.example.service.Impl;

import com.example.dao.StudentDao;
import com.example.entity.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student login(Student student) {
        return studentDao.login(student);
    }

    @Override
    public List<Student> queryAll() {
        return studentDao.queryAll();
    }

    @Override
    public Student queryById(int id) {
        return studentDao.queryById(id);
    }

    @Override
    public Student queryBySno(String sno) {
        return studentDao.queryBySno(sno);
    }

    @Override
    public int update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public int updatePass(Student student) {
        return studentDao.updatePass(student);
    }

    @Override
    public int updateByTeacher(Student student) {
        return studentDao.updateByTeacher(student);
    }

    @Override
    public int deleteBySno(String sno) {
        return studentDao.deleteBySno(sno);
    }

    @Override
    public int addStudent(Student student) {
        return studentDao.addStudent(student);
    }

    @Override
    public int deleteAll(List<Integer> ids) {
        return studentDao.deleteAll(ids);
    }

}
