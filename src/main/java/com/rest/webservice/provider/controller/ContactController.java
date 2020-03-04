package com.rest.webservice.provider.controller;

import com.rest.webservice.provider.domain.Contact;
import com.rest.webservice.provider.domain.Message;
import com.rest.webservice.provider.service.contact.ContactService;
import com.rest.webservice.provider.utils.exception.AlreadyExistException;
import com.rest.webservice.provider.utils.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping
    public List<Contact> retrieveAllContacts() {
        log.info("Retrieving all contacts");
        return contactService.retrieveAllContacts();
    }

    @GetMapping("{id}")
    public Contact getContactById(@PathVariable Long id) {
        log.info("Getting contact by id: '{id}'", id);
        return contactService.getContactById(id);
    }

    @GetMapping(params = {"name"})
    public Contact getContactByName(@RequestParam String name) {
        log.info("Getting contact by name: '{name}'", name);
        Contact contact = contactService.getContactByName(name);

        if (contact == null)
            throw new NotFoundException(String.format("Contact with name: '%s' was not found!", name));

        return contact;
    }

    @GetMapping(params = {"phoneNumber"})
    public Contact getContactByPhoneNumber(@RequestParam String phoneNumber) {
        log.info("Getting contact by phone number: '{phoneNumber}'", phoneNumber);
        Contact contact = contactService.getContactByPhoneNumber(phoneNumber);

        if (contact == null)
            throw new NotFoundException(String.format("Contact with phone number: '%s' was not found!", phoneNumber));

        return contact;
    }

    @PostMapping
    public ResponseEntity<?> saveContact(@Valid @RequestBody Contact contact) {
        log.info("Saving new contact: {contact}", contact);
        Contact contactFromDb = contactService.getContactByPhoneNumber(contact.getPhoneNumber());

        if (contactFromDb != null)
            throw new AlreadyExistException(String.format("Contact with phone number: '%s' already exists!", contact.getPhoneNumber()));

        Contact savedContact = contactService.saveContact(contact);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedContact.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    @PutMapping("{id}")
    public Contact updateContact(
            @PathVariable Long id,
            @Valid @RequestBody Contact contact) {
        log.info("Updating contact by id: {id}. New values: {contact}", id, contact);
        contact.setId(id);
        Contact contactFromDb = contactService.getContactById(contact.getId());

        if (contactFromDb == null)
            throw new NotFoundException(String.format("Contact with id: '%s' was not found!", contact.getId()));

        Contact updatedContact = contactService.updateContact(contact);
        return updatedContact;
    }

    @DeleteMapping("{id}")
    public void deleteContact(@PathVariable Long id) {
        log.info("Deleting contact by id: '%s'", id);
        Contact contactFromDb = contactService.getContactById(id);

        if (contactFromDb == null)
            throw new NotFoundException(String.format("Contact with id: '%s' was not found!", id));

        contactService.deleteContact(contactFromDb);
    }

    @PostMapping("{id}/message")
    public void sendMessageToContact(@PathVariable Long id, @Valid @RequestBody Message message) {
        log.info("Sending message: '{message}' to contact by id: '{id}'", message, id);
        Contact contactFromDb = contactService.getContactById(id);
    }
}
