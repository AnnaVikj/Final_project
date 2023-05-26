package com.example.final_project;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientRepository {
    public static PatientRepository instance = null;
    private DatabaseReference DataBase;
    private String PATIENT_KEY = "Patient";
    public List<Patient> roomdb;
    public static PatientRepository getInstance(Context context) {
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
        DataBase.push().setValue(patient);
    }

    public void removeByPosition(int position, String street) {
        roomdb.remove(position);
        /*Query streets = DataBase.child("Patient").orderByChild("street").equalTo(street);
        
        streets.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });*/

    }

    /*public void updatePatient(Patient temp) {
        roomdb.patientDao().update(temp);
    }*/

    public void Firebase(){
        DataBase = FirebaseDatabase.getInstance().getReference(PATIENT_KEY);
        if (roomdb != null){
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
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        DataBase.addValueEventListener(listener);
    }
}
