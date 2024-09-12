package com.example.csvoperations.utlity;

import com.example.csvoperations.entity.Employee;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSVHelper {
    public static void writeEmployeesToCsv(List<Employee> employees, Writer writer) throws IOException {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Name", "Email", "Department"))) {
            for (Employee employee : employees) {
                csvPrinter.printRecord(employee.getId(), employee.getName(), employee.getEmail(), employee.getDepartment());
            }
        }
    }
}