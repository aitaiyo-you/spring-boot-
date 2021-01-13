package com.example.service;

import com.example.entity.Profession;

import java.util.List;

public interface ProfessionService {
    public List<Profession> queryAll(String college);
    public int addProfession(Profession profession);
    public int updateProfession(Profession profession);
    public int deleteProfession(int pid);
    public int deleteAll(List<Integer> ids);
}
