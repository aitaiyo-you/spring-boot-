package com.example.service.Impl;

import com.example.dao.ScoreDao;
import com.example.entity.Score;
import com.example.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDao scoreDao;

    @Override
    public List<Score> queryToTeacher(String tno) {
        return scoreDao.queryToTeacher(tno);
    }

    @Override
    public List<Score> queryToStudent(String sno) {
        return scoreDao.queryToStudent(sno);
    }

    @Override
    public List<Score> queryByCourse(int cid) {
        return scoreDao.queryByCourse(cid);
    }

    @Override
    public int updateScore(Score score) {
        return scoreDao.updateScore(score);
    }

    @Override
    public int chooseClass(Score score) {
        return scoreDao.chooseClass(score);
    }

    @Override
    public int exitClass(Score score) {
        return scoreDao.exitClass(score);
    }
}
