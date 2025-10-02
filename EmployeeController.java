package com.example.EmployeeCrudOperations.Controller;

import com.example.EmployeeCrudOperations.Entities.Employee;
import com.example.EmployeeCrudOperations.Repository.EmployeeRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repo;

    @PostMapping
    public ResponseEntity<Employee> saveData(@RequestBody Employee emp){

        return new ResponseEntity<>(repo.save(emp) , HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllDetails(){
        return new ResponseEntity<>(repo.findAll() , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateData(@RequestBody Employee em , @PathVariable long id){
        Optional<Employee> saved = repo.findById(id);

        if(saved.isPresent()){
            saved.get().setName(em.getName());
            saved.get().setEmail(em.getEmail());
            saved.get().setGender(em.getGender());
            saved.get().setSalary(em.getSalary());
            saved.get().setPhonenumber(em.getPhonenumber());

            return new ResponseEntity<>(repo.save(saved.get()) , HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteDetails( @PathVariable long id){

        Optional<Employee> std = repo.findById(id);

        if(std.isPresent()){

            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
