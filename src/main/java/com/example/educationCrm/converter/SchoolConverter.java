package com.example.educationCrm.converter;

import com.example.educationCrm.model.entity.School;
import com.example.educationCrm.serviceImp.SchoolServiceImp;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Component
@FacesConverter(value = "schoolConverter")
public class SchoolConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext,
                              UIComponent uiComponent,
                              String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        SchoolServiceImp schoolServiceImp = facesContext.getApplication()
                .evaluateExpressionGet(facesContext, "#{schoolServiceImp}"
                        , SchoolServiceImp.class);
        return schoolServiceImp.findByName(s);
    }

    @Override
    public String getAsString(FacesContext facesContext,
                              UIComponent uiComponent,
                              Object o) {
        if (o instanceof School) {
            return ((School) o).getName();
        }
        return "";
    }
}
