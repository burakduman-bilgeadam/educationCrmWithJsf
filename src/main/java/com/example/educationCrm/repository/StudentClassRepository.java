package com.example.educationCrm.repository;

import com.example.educationCrm.model.entity.StudentClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentClassRepository
        extends CrudRepository<StudentClass,Long> {

    /*@EntityGraph(value = "StudentClass.detail",
            type = EntityGraph.EntityGraphType.LOAD)
    StudentClass getById(Long Id);*/
    StudentClass findStudentClassByName(String name);
}
