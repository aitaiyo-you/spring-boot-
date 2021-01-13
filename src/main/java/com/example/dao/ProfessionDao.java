package com.example.dao;

import com.example.entity.Profession;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfessionDao {
    public List<Profession> queryAll(String college);
    public int addProfession(Profession profession);
    public int updateProfession(Profession profession);
    public int deleteProfession(int pid);
    public int deleteAll(List<Integer> ids);
}
