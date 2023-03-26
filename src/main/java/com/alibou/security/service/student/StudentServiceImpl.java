package com.alibou.security.service.student;

import com.alibou.security.Entity.Student;

import com.alibou.security.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Student get(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student getByFirstname(String firstname) {
        return studentRepository.findStudentByFirstname(firstname).get();
    }

    @Override
    public List<Student> listStudent() {
        return studentRepository.findAll();
    }

}
