package com.example.demo.controller;


import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;
 

    
    
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/")
    public List<Contact> getAllContacts() {
        // Retrieve all contacts from the database
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id) {
        // Retrieve a contact by its ID from the database
        return contactService.getContactById(id).orElse(null);
    }

    @PostMapping("/submit")
    public Contact submitContactForm(@RequestBody Contact contact) {
        // Save the contact form data to the database
        return contactService.saveContact(contact);
    }

    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {
        // Update an existing contact by ID with the provided data
        return contactService.updateContact(id, updatedContact);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        // Delete a contact by its ID from the database
        contactService.deleteContact(id);
    }


       
}

