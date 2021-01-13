package com.example.dao;

import com.example.entity.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreDao {
    public List<Score> queryToTeacher(String tno);
    public List<Score> queryToStudent(String sno);
    public List<Score> queryByCourse(int cid);
    public int updateScore(Score score);
    public int chooseClass(Score score);
    public int exitClass(Score score);
}
