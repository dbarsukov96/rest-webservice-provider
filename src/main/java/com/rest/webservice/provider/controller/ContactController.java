package com.rest.webservice.provider.controller;

import com.rest.webservice.provider.domain.Contact;
import com.rest.webservice.provider.domain.Message;
import com.rest.webservice.provider.service.contact.ContactService;
import com.rest.webservice.provider.service.message.MessageService;
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
    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<Contact> retrieveAllContacts() {
        log.info("Retrieving all contacts");
        return contactService.retrieveAllContacts();
    }

    @GetMapping("{id}")
    public Contact getContactById(@PathVariable Long id) {
        log.info("Getting contact by id: {}", id);
        Contact contact = contactService.getContactById(id);

        return contact;
    }

    @GetMapping(params = {"name"})
    public List<Contact> getContactsByName(@RequestParam String name) {
        log.info("Getting contacts by name: {}", name);
        List<Contact> contacts = contactService.getContactsByName(name);

        return contacts;
    }

    @GetMapping(params = {"phoneNumber"})
    public Contact getContactByPhoneNumber(@RequestParam String phoneNumber) {
        log.info("Getting contact by phone number: {}", phoneNumber);
        Contact contact = contactService.getContactByPhoneNumber(phoneNumber);

        return contact;
    }

    @PostMapping
    public ResponseEntity<?> saveContact(@Valid @RequestBody Contact contact) {
        log.info("Saving new contact: {}", contact);
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
        log.info("Updating contact by id: {}. New values: {}", id, contact);
        contact.setId(id);
        Contact updatedContact = contactService.updateContact(contact);

        return updatedContact;
    }

    @DeleteMapping("{id}")
    public void deleteContact(@PathVariable Long id) {
        log.info("Deleting contact by id: {}", id);
        contactService.deleteContact(id);
    }

    @PostMapping("{id}/message")
    public void sendMessageToContact(@PathVariable Long id, @Valid @RequestBody Message message) {
        log.info("Sending message: {} to contact by id: {}", message.getText(), id);
        Contact contactFromDb = contactService.getContactById(id);

        messageService.sendMessage(contactFromDb, message);
    }
}
