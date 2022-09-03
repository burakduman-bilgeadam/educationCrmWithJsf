package com.example.educationCrm.controller;


import com.example.educationCrm.model.entity.Lesson;
import com.example.educationCrm.model.entity.School;
import com.example.educationCrm.model.entity.Student;
import com.example.educationCrm.model.entity.Teacher;
import com.example.educationCrm.service.LessonService;
import com.example.educationCrm.service.SchoolService;
import com.example.educationCrm.service.StudentService;
import com.example.educationCrm.service.TeacherService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Scope(value = "view")
@Getter
@Setter
public class TeacherController {
    private Teacher teacher;
    private Teacher selectedTeacher;
    private Lesson selectedLesson;

    private List<Student> students;
    private LinkedHashSet<Student> selectedStudents;
    private List<Teacher> teacherList;
    private List<Lesson> lessonList;
    private List<School> schoolList;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private LessonService lessonService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private StudentService studentService;

    @PostConstruct
    public void init(){
        this.teacher = new Teacher();
        this.teacherList = teacherService.findAll();
        this.lessonList = lessonService.findAll();
        this.schoolList = schoolService.findAll();
        this.students = studentService.findAllStudent();
    }

    public void save (){
        this.teacherService.save(this.teacher);
        infoMessage("Kaydedildi",
                "Kaydedilen Öğretmen : ",this.teacher);
        this.teacherList.add(this.teacher);
        this.teacher = new Teacher();
    }

    public void delete(Teacher teacher){
        this.teacherService.delete(teacher);
        infoMessage("Silindi.",
                "Silinen Öğretmen : ",teacher);
        this.teacherList.remove(teacher);
    }

    public void update(){
        this.teacherService.update(this.selectedTeacher);
        infoMessage("Güncellendi.",
                "Güncellenen Öğretmen : ",this.selectedTeacher);
        this.selectedTeacher = new Teacher();
    }

    public void selectTeacher(Teacher teacher){
        this.selectedTeacher =teacher;
    }

    public void changeLesson(){
        this.teacherList = this.teacherService.findTeacherByLesson(this.selectedLesson);
    }

    public void changeTeacher(){
        this.selectedStudents =
                (LinkedHashSet<Student>) this.selectedTeacher.getStudents();
    }

    public void assignStudent(){
        List<Student> studentList = new ArrayList<>();
        studentList=this.selectedStudents.stream().map(s -> (Student)s).collect(Collectors.toList());
        this.selectedTeacher.setStudents(studentList);
        this.teacherService.save(this.selectedTeacher);
        infoMessage("Atama Tamamlandı.", "Atama yapılan öğretmen : ",this.selectedTeacher);
        this.selectedTeacher = new Teacher();
        this.selectedLesson = new Lesson();
        this.selectedStudents = new LinkedHashSet<>();
    }

    public void infoMessage(String summary, String detail, Teacher teacher) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null,
                new FacesMessage(summary,
                        detail + teacher.getName()));
    }
}
