package com.example.final_project.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.final_project.Patient;

@Database(entities = {Patient.class}, version = 1)
public abstract class PatientBase extends RoomDatabase {
    public abstract PatientDao patientDao();
}
