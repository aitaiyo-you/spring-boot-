package com.example.service;

import com.example.entity.Student;

import java.util.List;

public interface StudentService {
    public Student login(Student student);
    public List<Student> queryAll();
    public Student queryById(int id);
    public Student queryBySno(String sno);
    public int update(Student student);
    public int updatePass(Student student);
    public int updateByTeacher(Student student);
    public int deleteBySno(String sno);
    public int addStudent(Student student);
    public int deleteAll(List<Integer> ids);
}
