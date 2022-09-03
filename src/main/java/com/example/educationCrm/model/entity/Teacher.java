package com.example.educationCrm.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher extends Person{

    @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    @JoinTable(name = "teachers_students"
            ,joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
            ,uniqueConstraints = { @UniqueConstraint
            (columnNames = { "teacher_id", "student_id" }) })
    private List<Student> students;
    @ManyToOne
    private School school;
    @ManyToOne
    private Lesson lesson;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
