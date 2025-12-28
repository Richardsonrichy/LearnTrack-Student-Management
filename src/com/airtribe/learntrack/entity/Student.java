package com.airtribe.learntrack.entity;

public class Student extends Person {

    private String batch;
    private boolean active;

    // Default constructor
    public Student() {
        super(); // calls Person()
    }
    // Constructor Overloading Example
    public Student(int id, String firstName, String lastName) {

        // calling parent constructor but email default
        super(id, firstName, lastName, "not-provided@email.com");

        this.batch = "Not Assigned";
        this.active = true;
    }


    // Parameterized constructor
    public Student(int id, String firstName, String lastName, String email,
                   String batch, boolean active) {

        super(id, firstName, lastName, email); // calling parent constructor
        this.batch = batch;
        this.active = active;
    }

    // Getter and Setter
    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Method overriding

    @Override
    public String toString() {
        return  "\n---------------------------" +
                "\nStudent ID   : " + getId() +
                "\nName         : " + getFirstName() + " " + getLastName() +
                "\nEmail        : " + getEmail() +
                "\nBatch        : " + batch +
                "\nStatus      : " + (active ? "ACTIVE" : "INACTIVE") +

                "\n---------------------------";
    }

}

