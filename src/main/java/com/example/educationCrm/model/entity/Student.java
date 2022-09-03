package com.example.educationCrm.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Objects;

@Entity
public class Student extends Person{

    private String number;
    @ManyToOne
    private School school;
    @JsonIgnore
    @ManyToMany(mappedBy = "students", fetch = FetchType.EAGER)
    private List<Teacher> teachers;
    @ManyToMany
    private List<Lesson> lessons;
    @ManyToOne
    private StudentClass studentClass;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getNumber().equals(student.getNumber()) && Objects.equals(getSchool(), student.getSchool()) && Objects.equals(getTeachers(), student.getTeachers()) && Objects.equals(getLessons(), student.getLessons()) && Objects.equals(getStudentClass(), student.getStudentClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getSchool(), getTeachers(), getLessons(), getStudentClass());
    }
}
