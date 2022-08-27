package com.example.educationCrm.service;

import com.example.educationCrm.model.dto.StudentClassDTO;
import com.example.educationCrm.model.entity.StudentClass;

import java.util.List;

public interface StudentClassService {
    void save(StudentClass studentClass);
    List<StudentClass> findAll();

    void update(StudentClass studentClass);
    void delete(StudentClass studentClass);

    StudentClass findByName(String name);
}
