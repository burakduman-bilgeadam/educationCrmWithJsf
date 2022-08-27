package com.example.educationCrm.converter;

import com.example.educationCrm.model.entity.Lesson;
import com.example.educationCrm.serviceImp.LessonServiceImp;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Component
@FacesConverter(value = "lessonConverter")
public class LessonConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext context, UIComponent component,
                                  String value) {

            if (value == null || value.equals("")) {
                return null;
            }
            LessonServiceImp lessonService = context.getApplication()
                    .evaluateExpressionGet(context, "#{lessonServiceImp}"
                            , LessonServiceImp.class);
            return lessonService.findByName(value);
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value instanceof Lesson) {
                return ((Lesson) value).getName();
            }
            return "";
        }
}
