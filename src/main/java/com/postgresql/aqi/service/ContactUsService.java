package com.postgresql.aqi.service;

import com.postgresql.aqi.entity.ContactUs;

import java.util.List;

public interface ContactUsService {
    ContactUs saveQuery(ContactUs contactUs);
    List<ContactUs> getAllQueries();
    ContactUs getQueryById(long id);
    void deleteQueryById(long id);
}
