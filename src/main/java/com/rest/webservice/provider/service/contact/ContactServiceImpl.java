package com.rest.webservice.provider.service.contact;

import com.rest.webservice.provider.domain.Contact;
import com.rest.webservice.provider.repository.ContactRepository;
import com.rest.webservice.provider.utils.exception.AlreadyExistException;
import com.rest.webservice.provider.utils.exception.NotFoundException;
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
        Optional<Contact> contactOptional = contactRepository.findByPhoneNumber(contact.getPhoneNumber());

        if (contactOptional.isPresent())
            throw new AlreadyExistException(String.format("Contact with phone number: '%s' already exists!", contact.getPhoneNumber()));

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

        if (!contactOptional.isPresent())
            throw new NotFoundException(String.format("Contact with id: '%s' was not found!", id));

        return contactOptional.get();
    }

    @Override
    public List<Contact> getContactsByName(String name) {
        List<Contact> contacts = contactRepository.findByName(name);

        if (contacts.isEmpty())
            throw new NotFoundException(String.format("Contacts with name: '%s' was not found!", name));

        return contacts;
    }

    @Override
    public Contact getContactByPhoneNumber(String phoneNumber) {
        Optional<Contact> contactOptional = contactRepository.findByPhoneNumber(phoneNumber);

        if (!contactOptional.isPresent())
            throw new NotFoundException(String.format("Contact with phone number: '%s' was not found!", phoneNumber));

        return contactOptional.get();
    }

    @Override
    public Contact updateContact(Contact contact) {
        Optional<Contact> contactOptional = contactRepository.findById(contact.getId());

        if (!contactOptional.isPresent())
            throw new NotFoundException(String.format("Contact with id: '%s' was not found!", contact.getId()));

        Contact updatedContact = contactRepository.save(contact);

        return updatedContact;
    }

    @Override
    public void deleteContact(Long id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);

        if (!contactOptional.isPresent())
            throw new NotFoundException(String.format("Contact with id: '%s' was not found!", id));

        contactRepository.deleteById(id);
    }
}
