package com.example.service.Impl;

import com.example.dao.ProfessionDao;
import com.example.entity.Profession;
import com.example.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionServiceImpl implements ProfessionService {

    @Autowired
    private ProfessionDao professionDao;

    @Override
    public List<Profession> queryAll(String college) {
        return professionDao.queryAll(college);
    }

    @Override
    public int addProfession(Profession profession) {
        return professionDao.addProfession(profession);
    }

    @Override
    public int updateProfession(Profession profession) {
        return professionDao.updateProfession(profession);
    }

    @Override
    public int deleteProfession(int pid) {
        return professionDao.deleteProfession(pid);
    }

    @Override
    public int deleteAll(List<Integer> ids) {
        return professionDao.deleteAll(ids);
    }
}
