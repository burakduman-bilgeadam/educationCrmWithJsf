package com.example.educationCrm.controller;

import com.example.educationCrm.model.entity.School;
import com.example.educationCrm.service.SchoolService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Scope(value = "view")
@Controller
@Getter
@Setter
public class SchoolController {
    private School school;
    @Autowired
    private SchoolService schoolService;

    @PostConstruct
    public void init(){
        this.school = new School();
    }

    public void save(){
        this.schoolService.save(this.school);
        saveMessage();
        this.school = new School();
    }

    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null,
                new FacesMessage("Kaydedildi",
                        "Kaydedilen Okul : " + school.getName()));
    }

    public List<School> getList(){
        return this.schoolService.findAll();
    }

    public void delete(School school){
        this.schoolService.delete(school);
    }

    public void update(School school){
        this.school = school;
    }
}
