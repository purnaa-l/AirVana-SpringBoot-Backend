package com.postgresql.aqi.service.impl;

import com.postgresql.aqi.entity.ContactUs;
import com.postgresql.aqi.repository.ContactUsRepository;
import com.postgresql.aqi.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactUsImpl implements ContactUsService {

    @Autowired
    private ContactUsRepository contactUsRepository;

    @Override
    public ContactUs saveQuery(ContactUs contactUs) {
        return contactUsRepository.save(contactUs);  // No validation done here
    }

    @Override
    public List<ContactUs> getAllQueries() {
        return contactUsRepository.findAll();
    }

    @Override
    public ContactUs getQueryById(long id) {
        Optional<ContactUs> contactUs = contactUsRepository.findById(id);
        if (contactUs.isPresent()) {
            return contactUs.get();
        } else {
            throw new RuntimeException("Query not found for id :: " + id);
        }
    }

    @Override
    public void deleteQueryById(long id) {
        contactUsRepository.deleteById(id);
    }
}
