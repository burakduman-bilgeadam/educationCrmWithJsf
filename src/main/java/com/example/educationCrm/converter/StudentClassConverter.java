package com.example.educationCrm.converter;

import com.example.educationCrm.model.entity.StudentClass;
import com.example.educationCrm.serviceImp.SchoolServiceImp;
import com.example.educationCrm.serviceImp.StudentClassImp;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Component
@FacesConverter(value = "studentClassConverter")
public class StudentClassConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext,
                              UIComponent uiComponent, String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        StudentClassImp studentClassImp = facesContext.getApplication()
                .evaluateExpressionGet(facesContext, "#{studentClassImp}"
                        , StudentClassImp.class);
        return studentClassImp.findByName(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o instanceof StudentClass){
            return ((StudentClass) o).getName();
        }
        return "";
    }
}
