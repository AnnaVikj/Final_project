package com.example.final_project;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.final_project.data.PatientBase;

import java.util.ArrayList;
import java.util.List;

public class PatientRepository {
    public static PatientRepository instance = null;
    public PatientBase roomdb;
    public static PatientRepository getInstance(Context context) {
        if (instance == null) instance = new PatientRepository(context);
        return instance;
    }

    public PatientRepository(Context context){
        roomdb = Room.databaseBuilder(context, PatientBase.class, "patient_room.db")
                .allowMainThreadQueries().build();
    }
    public List<Patient> getPatients(){
        return roomdb.patientDao().getAll();
    }

    public void addPatient(Patient patient){
        roomdb.patientDao().insertAll(patient);
    }

    public void removeByPosition(Patient patient) {
        roomdb.patientDao().delete(patient);
    }

    public void updatePatient(Patient temp) {
        roomdb.patientDao().update(temp);
    }
}
