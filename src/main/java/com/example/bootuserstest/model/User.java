package com.example.bootuserstest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "User")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false, columnDefinition = "TEST")
    @NotBlank
    @NotNull
    private String firstName;

    @Column(name = "second_name", nullable = false, columnDefinition = "TEST")
    @NotBlank
    @NotNull
    private String secondName;

    @Column(name = "phone_number", nullable = false, columnDefinition = "TEST")
    @NotBlank
    @NotNull
    @Pattern(regexp = "^380\\d{9}$")
    private String phoneNumber;

    @Column(name = "email", nullable = false, columnDefinition = "TEST")
    @Email
    @NotBlank
    @NotNull
    private String email;

    @Column(name = "app", nullable = false, columnDefinition = "TEST")
    @NotBlank
    @NotNull
    private String app;

    @Column(name = "date_record", columnDefinition = "DATE")
    @DateTimeFormat(style = "yyyy/MM/dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime dateRecord;

    public User() {
        System.out.println("creating");
    }

    public User(String firstName, String secondName, String phoneNumber, String email, String app, LocalDateTime dateRecord) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.app = app;
        this.dateRecord = dateRecord;
    }

    public User(String firstName, String secondName, String phoneNumber, String email, String app) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.app = app;
        this.dateRecord = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public LocalDateTime getDateRecord() {
        return dateRecord;
    }

    public void setDateRecord(LocalDateTime dateRecord) {
        this.dateRecord = dateRecord;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", firstName='" + firstName + '\''
                + ", secondName='" + secondName + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + " email='" + email + '\''
                + ", app='" + app + '\''
                + ", dateRecord=" + dateRecord
                + '}';
    }
}
