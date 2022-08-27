package com.example.educationCrm.serviceImp;

import com.example.educationCrm.model.dto.StudentDTO;
import com.example.educationCrm.model.dto.StudentInformationDTO;
import com.example.educationCrm.model.entity.School;
import com.example.educationCrm.model.entity.Student;
import com.example.educationCrm.model.entity.StudentClass;
import com.example.educationCrm.repository.SchoolRepository;
import com.example.educationCrm.repository.StudentClassRepository;
import com.example.educationCrm.repository.StudentRepository;
import com.example.educationCrm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private StudentClassRepository studentClassRepository;
    /*private final SimpleDateFormat formatter
            = new SimpleDateFormat("dd-MM-yyyy"
            , Locale.ENGLISH);*/

    @Transactional
    @Override
    public void save(Student student) throws ParseException {
        this.studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    @Override
    public StudentInformationDTO getNameSurnameById(Long id) {
        return this.studentRepository.getStudentById(id);
    }

    @Transactional
    @Override
    public void update(StudentDTO studentDTO) {
        Optional<Student> studentOptional = this.studentRepository
                .findById(studentDTO.getId());
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            student.setSurname(studentDTO.getSurname());
            student.setName(studentDTO.getName());
            student.setBirthDate(studentDTO.getBirthDate());
            student.setNumber(studentDTO.getNumber());
            StudentClass studentClass = this.studentClassRepository
                    .findById(studentDTO.getStudentClassId())
                    .orElse(null);
            School school = this.schoolRepository
                    .findById(studentDTO.getSchoolId())
                    .orElse(null);
            student.setSchool(school);
            student.setStudentClass(studentClass);
            this.studentRepository.save(student);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        this.studentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentInformationDTO> getBySchoolName(String schoolName) {
        return this.studentRepository.findStudentBySchoolName(schoolName);
    }

    @Transactional(readOnly = true)
    @Override
    public Student findById(Long id) {
        return this.studentRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> findAllStudent() {
        return this.studentRepository.findAll();
    }
}
