package com.example.educationCrm.serviceImp;

import com.example.educationCrm.model.dto.StudentClassDTO;
import com.example.educationCrm.model.entity.StudentClass;
import com.example.educationCrm.repository.StudentClassRepository;
import com.example.educationCrm.service.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentClassImp implements StudentClassService {
    @Autowired
    private StudentClassRepository studentClassRepository;
    @Transactional
    @Override
    public void save(StudentClass studentClass) {
        this.studentClassRepository.save(studentClass);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentClass> findAll() {
        return (List<StudentClass>)this.studentClassRepository.findAll();
    }

    @Transactional
    @Override
    public void update(StudentClass studentClass) {
        this.studentClassRepository.save(studentClass);
    }

    @Transactional
    @Override
    public void delete(StudentClass studentClass) {
        this.studentClassRepository.delete(studentClass);
    }
}
