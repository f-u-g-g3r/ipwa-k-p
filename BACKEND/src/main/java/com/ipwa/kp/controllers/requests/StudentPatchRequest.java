package com.ipwa.kp.controllers.requests;

public class StudentPatchRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public StudentPatchRequest() {
    }

    public StudentPatchRequest(String username, String firstName, String lastName, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}