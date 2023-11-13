package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.DTO.UserLoginDTO;
import com.example.demo.DTO.UserRegistrationDTO;
import com.example.demo.Repo.UserRepository;
import com.example.demo.model.User;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getId())) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        User newUser = new User(userDTO.getId(), userDTO.getName(), userDTO.getPassword(), userDTO.getEmail(),
                userDTO.getPhone(), userDTO.getCountry(), userDTO.getAddress(), userDTO.getGender());

        userRepository.save(newUser);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody UserLoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());

        if (user != null && user.getPassword().equals(loginDTO.getPassword())) {
            // Authentication successful
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            // Authentication failed
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        
        if (user != null) {
            userRepository.delete(user);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{username}")
    public ResponseEntity<String> editUserByUsername(@PathVariable String username, @RequestBody UserRegistrationDTO userDTO) {
        User existingUser = userRepository.findByUsername(username);

        if (existingUser != null) {
            // Update the user's information
            existingUser.setName(userDTO.getName());
            existingUser.setEmail(userDTO.getEmail());
            existingUser.setPhone(userDTO.getPhone());
            existingUser.setCountry(userDTO.getCountry());
            existingUser.setAddress(userDTO.getAddress());
            existingUser.setGender(userDTO.getGender());
            
            userRepository.save(existingUser);

            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

}