package com.alibou.security.service.student;

import com.alibou.security.Entity.Student;

public interface StudentService {
    public Student get(Long id);

    public Student getByFirstname(String firstname);
}
