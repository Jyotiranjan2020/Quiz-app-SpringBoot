package com.example.demo.service;

import com.example.demo.Repo.ContactRepository;
import com.example.demo.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAllContacts() {
        // Retrieve all contacts from the database
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        // Retrieve a contact by its ID from the database
        return contactRepository.findById(id);
    }

    public Contact saveContact(Contact contact) {
        // Save the contact form data to the database
        return contactRepository.save(contact);
    }

    public Contact updateContact(Long id, Contact updatedContact) {
        // Update an existing contact by ID with the provided data
        // First, check if the contact with the given ID exists
        Optional<Contact> existingContact = contactRepository.findById(id);
        if (existingContact.isPresent()) {
            Contact contactToUpdate = existingContact.get();
            contactToUpdate.setName(updatedContact.getName());
            contactToUpdate.setEmail(updatedContact.getEmail());
            contactToUpdate.setMessage(updatedContact.getMessage());

            // Save the updated contact
            return contactRepository.save(contactToUpdate);
        } else {
            // Contact not found
            return null;
        }
    }

    public void deleteContact(Long id) {
        // Delete a contact by its ID from the database
        contactRepository.deleteById(id);
    }
}
