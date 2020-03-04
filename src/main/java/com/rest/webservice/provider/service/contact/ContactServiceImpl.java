package com.rest.webservice.provider.service.contact;

import com.rest.webservice.provider.domain.Contact;
import com.rest.webservice.provider.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact saveContact(Contact contact) {
        Contact savedContact = contactRepository.save(contact);
        return savedContact;
    }

    @Override
    public List<Contact> retrieveAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts;
    }

    @Override
    public Contact getContactById(Long id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        return contactOptional.get();
    }

    @Override
    public Contact getContactByName(String name) {
        Contact contact = contactRepository.findByName(name);
        return contact;
    }

    @Override
    public Contact getContactByPhoneNumber(String phoneNumber) {
        Contact contact = contactRepository.findByPhoneNumber(phoneNumber);
        return contact;
    }

    @Override
    public Contact updateContact(Contact contact) {
        Contact updatedContact = contactRepository.save(contact);
        return updatedContact;
    }

    @Override
    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }
}
