package com.example.educationCrm.converter;

import com.example.educationCrm.model.entity.Teacher;
import com.example.educationCrm.serviceImp.TeacherServiceImp;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Component
@FacesConverter(value = "teacherConverter")
public class TeacherConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext context, UIComponent component,
                                  String value) {

            if (value == null || value.equals("")) {
                return "";
            }
            TeacherServiceImp teacherServiceImp = context.getApplication()
                    .evaluateExpressionGet(context, "#{teacherServiceImp}"
                            , TeacherServiceImp.class);
            try {
                return teacherServiceImp.withStudents(Long.parseLong(value));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Teacher) {
                return String.valueOf(((Teacher) value).getId());
            }
            return "";
        }
}
