package com.alibou.security.demo;

import com.alibou.security.Entity.Student;
import com.alibou.security.service.UserService;
import com.alibou.security.service.student.StudentService;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class DemoController {

  @Autowired
  private StudentService studentService;

  @Autowired
  private UserService userService;
  @GetMapping("/a/message")
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("Hello from secured endpoint");
  }

  @GetMapping("/student/{id}")
  public ResponseEntity<Student> getStudent(@PathVariable Long id) {
    try {
//      Long id = Long.valueOf(1);
      Student student = studentService.get(id);
      return new ResponseEntity<Student>(student, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/getStudent")
  public ResponseEntity<Student> getStudentByFirstname(@RequestParam String firstname) {
    try {
      Student student = studentService.getByFirstname(firstname);
      return new ResponseEntity<Student>(student, HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }
  }

}
