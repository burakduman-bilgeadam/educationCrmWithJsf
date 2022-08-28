package com.example.educationCrm.converter;

import com.example.educationCrm.model.entity.Student;
import com.example.educationCrm.serviceImp.StudentServiceImp;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Component
@FacesConverter(value = "studentConverter",managed = true, forClass = Student.class)
public class StudentConverter implements Converter<Student> {

        @Override
        public Student getAsObject(FacesContext context, UIComponent component,
                                  String value) {

            if (value == null || value.equals("")) {
                return null;
            }
            StudentServiceImp studentServiceImp = context.getApplication()
                    .evaluateExpressionGet(context, "#{studentServiceImp}"
                            , StudentServiceImp.class);
            try {
                return studentServiceImp.findById(Long.parseLong(value));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Student student) {
        return String.valueOf(student.getId());
    }
}
