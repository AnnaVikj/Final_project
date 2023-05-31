package com.example.final_project;

import android.content.Context;

import androidx.annotation.NonNull;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientRepository {
    public static PatientRepository instance = null;
    private DatabaseReference DataBase;
    public boolean flag = true;
    private String PATIENT_KEY = "Patient";
    public List<Patient> roomdb;


    public static PatientRepository init(Context context) {
        if (instance == null) instance = new PatientRepository(context);
        return instance;
    }

    public PatientRepository(Context context){
        DataBase = FirebaseDatabase.getInstance().getReference(PATIENT_KEY);
        roomdb = new ArrayList<>();
    }
    public List<Patient> getPatients(){
        return roomdb;
    }

    public void addPatient(Patient patient){

        if(patient.getCondition() == null){
            patient.setCondition("");
        }
        if(patient.getBody() == null){
            patient.setBody("");
        }
        if(patient.getFio() == null){
            patient.setFio("");
        }
        if(patient.getDescription() == null){
            patient.setDescription("");
        }

        roomdb.add(patient);
        DataBase.push().setValue(patient);
    }


    public void clear(){
        roomdb.clear();
    }

    public void Firebase(){
        DataBase = FirebaseDatabase.getInstance().getReference(PATIENT_KEY);

        if(roomdb != null){
            roomdb.clear();
        }
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds: snapshot.getChildren()) {
                    String street = ds.getValue(Patient.class).getStreet();
                    String description = ds.getValue(Patient.class).getDescription();
                    String fio = ds.getValue(Patient.class).getFio();
                    String body = ds.getValue(Patient.class).getBody();
                    String status = ds.getValue(Patient.class).getCondition();
                    Patient patient = new Patient(street, description, fio, body, status);
                    roomdb.add(patient);
                    flag = false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        DataBase.addValueEventListener(listener);
    }
}
