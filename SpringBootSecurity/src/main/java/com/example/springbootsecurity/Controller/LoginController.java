package com.example.springbootsecurity.Controller;

import com.example.springbootsecurity.CustomerRepository;
import com.example.springbootsecurity.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        Customer newCustomer = null;
        ResponseEntity responseEntity = null;
        try{
            String hashPassword = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(hashPassword);
            newCustomer = customerRepository.save(customer);
            if(newCustomer.getId() > 0){
                responseEntity = ResponseEntity.status(HttpStatus.CREATED).body("Successfully created");
            }
        }catch (Exception ex){
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception occurred due to creating an user " + ex.getMessage());
        }
        return responseEntity;
    }
}
