package com.example.educationCrm.controller;

import com.example.educationCrm.model.entity.School;
import com.example.educationCrm.model.entity.Student;
import com.example.educationCrm.model.entity.StudentClass;
import com.example.educationCrm.model.entity.Teacher;
import com.example.educationCrm.service.SchoolService;
import com.example.educationCrm.service.StudentClassService;
import com.example.educationCrm.service.StudentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.text.ParseException;
import java.util.List;

@Scope(scopeName = "view")
@Controller
@Getter
@Setter
public class StudentController {

    private List<Student> students;
    private List<School> schools;
    private List<StudentClass> classes;
    private Student student;
    private Student selectedStudent;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentClassService studentClassService;
    @Autowired
    private SchoolService schoolService;

    @PostConstruct
    public void init(){
        this.student = new Student();
        this.students = this.studentService.findAllStudent();
        this.schools = this.schoolService.findAll();
        this.classes = this.studentClassService.findAll();
    }

    public void save() throws ParseException {
        this.studentService.save(this.student);
        this.students.add(this.student);
        infoMessage("Kaydedildi.","Kaydedilen Data : ",this.student);
        this.student = new Student();
    }

    public void infoMessage(String summary, String detail, Student student) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null,
                new FacesMessage(summary,
                        detail + student.getName()));
    }
}
