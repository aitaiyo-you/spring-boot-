package com.example.dao;

import com.example.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentDao {
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
