package com.example.educationCrm.service;

import com.example.educationCrm.model.dto.SchoolDTO;
import com.example.educationCrm.model.dto.SchoolInformationDTO;
import com.example.educationCrm.model.entity.School;

import java.util.List;

public interface SchoolService {
    public void save(School school);
    public void update(SchoolDTO schoolDTO);
    public void delete(School school);

    public List<SchoolInformationDTO> findAllName();

    public List<School> findAll();

}
