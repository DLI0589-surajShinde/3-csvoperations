package com.example.csvoperations.service;

import com.example.csvoperations.entity.Employee;
import com.example.csvoperations.repository.EmployeeRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<String> uploadCSV(MultipartFile file) {
        List<String> log = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line.length != 3) {
                    log.add("Invalid record: " + String.join(",", line));
                    continue;
                   }
                Employee employee = new Employee();
                employee.setName(line[0]);
                employee.setEmail(line[1]);
                employee.setDepartment(line[2]);
                employeeRepository.save(employee);
                log.add("Successfully processed: " + String.join(",", line));
            }
        } catch (Exception e) {
            log.add("Error processing file: " + e.getMessage());
        }
        return log;
    }


    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }
}