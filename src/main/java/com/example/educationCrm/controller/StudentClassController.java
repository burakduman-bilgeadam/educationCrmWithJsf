package com.example.educationCrm.controller;

import com.example.educationCrm.model.entity.StudentClass;
import com.example.educationCrm.service.StudentClassService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

@Controller
@Scope(value = "view")
@Getter
@Setter
public class StudentClassController {
    private StudentClass studentClass;
    private StudentClass selectedClass;
    private List<StudentClass> classList;
    @Autowired
    private StudentClassService studentClassService;

    @PostConstruct
    public void init(){
        this.studentClass = new StudentClass();
        this.classList = studentClassService.findAll();
    }

    public void save (){
        this.studentClassService.save(this.studentClass);
        infoMessage("Kaydedildi",
                "Kaydedilen Sınıf : ",this.studentClass);
        this.classList.add(studentClass);
        this.studentClass = new StudentClass();
    }

    public void delete(StudentClass studentClass){
        this.studentClassService.delete(studentClass);
        infoMessage("Silindi.",
                "Silinen Ders : ",studentClass);
        this.classList.remove(studentClass);
    }

    public void update(){
        this.studentClassService.update(this.selectedClass);
        infoMessage("Güncellendi.",
                "Güncellenen Ders : ",this.selectedClass);
        this.selectedClass = new StudentClass();
    }

    public void selectStudentClass(StudentClass studentClass){
        this.selectedClass =studentClass;
    }

    public void infoMessage(String summary, String detail, StudentClass studentClass) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null,
                new FacesMessage(summary,
                        detail + studentClass.getName()));
    }
}
