package com.example.educationCrm.repository;

import com.example.educationCrm.model.entity.Lesson;
import com.example.educationCrm.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository
        extends JpaRepository<Teacher,Long> {


    @Query(value = "select i from Teacher i where i.lesson=:lesson")
    List<Teacher> teacherByLesson(Lesson lesson);
}
