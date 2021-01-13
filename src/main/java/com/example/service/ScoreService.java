package com.example.service;

import com.example.entity.Score;

import java.util.List;

public interface ScoreService {
    public List<Score> queryToTeacher(String tno);
    public List<Score> queryToStudent(String sno);
    public List<Score> queryByCourse(int cid);
    public int updateScore(Score score);
    public int chooseClass(Score score);
    public int exitClass(Score score);
}
