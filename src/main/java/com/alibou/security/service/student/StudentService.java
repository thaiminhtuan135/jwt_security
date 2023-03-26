package com.alibou.security.service.student;

import com.alibou.security.Entity.Student;

import java.util.List;

public interface StudentService {
    public Student get(Long id);

    public Student getByFirstname(String firstname);

    public List<Student> listStudent();
}
