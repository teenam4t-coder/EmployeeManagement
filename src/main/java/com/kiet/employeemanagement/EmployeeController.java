package com.kiet.employeemanagement;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    // Store employees in HashMap
    private Map<Integer, Employee> employees =
            new HashMap<>();

    // (b) Add new employee - POST
    @PostMapping
    public ResponseEntity<Employee> addEmployee(
            @RequestBody Employee employee) {

        employees.put(employee.getId(), employee);

        return new ResponseEntity<>(
                employee, HttpStatus.CREATED);
    }

    // (c) Get all employees - GET
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {

        return ResponseEntity.ok(
                new ArrayList<>(employees.values()));
    }

    // (c) Get employee by ID - GET
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(
            @PathVariable int id) {

        Employee employee = employees.get(id);

        if (employee == null) {
            return ResponseEntity.status(
                            HttpStatus.NOT_FOUND)
                    .body("Employee not found");
        }

        return ResponseEntity.ok(employee);
    }

    // (d) Update employee - PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable int id,
            @RequestBody Employee employee) {

        if (!employees.containsKey(id)) {
            return ResponseEntity.status(
                            HttpStatus.NOT_FOUND)
                    .body("Employee not found");
        }

        employee.setId(id);
        employees.put(id, employee);

        return ResponseEntity.ok(employee);
    }

    // (e) Delete employee - DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(
            @PathVariable int id) {

        if (!employees.containsKey(id)) {
            return ResponseEntity.status(
                            HttpStatus.NOT_FOUND)
                    .body("Employee not found");
        }

        employees.remove(id);

        return ResponseEntity.ok(
                "Employee deleted successfully");
    }
}
