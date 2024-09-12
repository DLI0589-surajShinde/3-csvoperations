package com.example.csvoperations.controller;

import com.example.csvoperations.entity.Employee;
import com.example.csvoperations.service.EmployeeService;
import com.example.csvoperations.utlity.CSVHelper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFile(@RequestParam("file") MultipartFile file) {
        List<String> log = employeeService.uploadCSV(file);
        return ResponseEntity.ok(log);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.FOUND);
    }

    @GetMapping("/download")
    public void downloadCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=employees.csv");
        List<Employee> employees = employeeService.getAllEmployee();
        CSVHelper.writeEmployeesToCsv(employees, response.getWriter());
    }
}