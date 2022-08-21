package com.example.educationCrm.repository;

import com.example.educationCrm.model.dto.SchoolInformationDTO;
import com.example.educationCrm.model.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository
        extends JpaRepository<School,Long> {
    List<SchoolInformationDTO> getSchoolBy();
    School findSchoolByName(String name);
}
