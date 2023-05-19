package com.example.final_project.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.final_project.Patient;

import java.util.List;

@Dao
public interface PatientDao {
    @Query("SELECT * FROM patient")
    List<Patient> getAll();

    @Update
    void update(Patient patient);

    @Insert
    void insertAll(Patient... patients);

    @Delete
    void delete(Patient patient);
}
