package com.alibou.security.demo;

import com.alibou.security.Entity.Student;
import com.alibou.security.service.UserService;
import com.alibou.security.service.student.StudentService;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.List;
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

  @GetMapping("/student/export")
  public void exportToCSV(HttpServletResponse response) throws IOException {
    response.setContentType("text/csv");
    String fileName = "student.csv";
    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=" + fileName;
    response.setHeader(headerKey, headerValue);
    List<Student> listUser = studentService.listStudent();
    ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

    String[] csvHeader = {"ID", "firstname", "lastname", "email", "role"};
    String[] nameMapping = {"id", "firstname", "lastname", "email", "role"};
    csvWriter.writeHeader(csvHeader);
    for (Student student : listUser) {
      csvWriter.write(student,nameMapping);
    }
    csvWriter.close();
  }
}
