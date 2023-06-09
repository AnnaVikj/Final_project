package com.example.final_project;


import java.util.Objects;

public class Patient {

    private String id;
    private String street;
    private String description;
    private String fio;
    private String body;
    private String condition;

    public Patient(){

    }

    public Patient(String street, String description, String fio, String body, String condition) {
        this.street = street;
        this.description = description;
        this.fio = fio;
        this.body = body;
        this.condition = condition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }


    public String getStreet() {
        return street;
    }

    public String getDescription() {
        return description;
    }

    public String getFio() {
        return fio;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(street, patient.street) && Objects.equals(description, patient.description) && Objects.equals(fio, patient.fio) && Objects.equals(body, patient.body) && Objects.equals(condition, patient.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, description, fio, body, condition);
    }
}
