package com.rest.webservice.provider.repository;

import com.rest.webservice.provider.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByName(String name);

    Optional<Contact> findByPhoneNumber(String phoneNumber);
}
