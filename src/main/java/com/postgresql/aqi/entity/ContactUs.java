package com.postgresql.aqi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contact_us")
public class ContactUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate primary key values
    @Column(name = "contact_id")
    private long contactId;

    @Column(name = "name", nullable = false, length = 100) // Custom column mapping
    private String name;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "mobile_number", nullable = false, length = 15)
    private String mobileNumber;

    @Column(name = "query", nullable = false, columnDefinition = "TEXT")
    private String query;

    // No-args constructor
    public ContactUs() {
    }

    // All-args constructor
    public ContactUs(long contactId, String name, String email, String mobileNumber, String query) {
        this.contactId = contactId;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.query = query;
    }

    // Getter and Setter for contactId
    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for mobileNumber
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    // Getter and Setter for query
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
