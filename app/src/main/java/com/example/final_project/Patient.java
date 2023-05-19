package com.example.final_project;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;
@Entity
public class Patient {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "street")
    private String street;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "fio")
    private String fio;
    @ColumnInfo(name = "body")
    private String body;
    @ColumnInfo(name = "condition")
    private String condition;

    public Patient(String street, String description, String fio, String body, String condition) {
        this.street = street;
        this.description = description;
        this.fio = fio;
        this.body = body;
        this.condition = condition;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
