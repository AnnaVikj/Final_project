package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.final_project.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PatientRepository repository = null;
    public ActivityMainBinding binding;
    private final String dispatcher = "dis";
    private final String doctor = "doc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        if (repository == null) repository = PatientRepository.init(getApplicationContext());
        repository.clear();
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        if (repository.flag){
        repository.Firebase();
        }
        binding.login.setOnClickListener(view -> check());
    }


    private void check() {
        String name = binding.input.getText().toString();
        if (!name.isEmpty()) {
            if (name.equals(dispatcher)) {
                Intent intent = new Intent(this, Dispatcher.class);
                intent.putExtra("name1", "dispatcher");
                startActivity(intent);
            } else if (name.equals(doctor)){

                Intent intent = new Intent(this, MarkUpDoc.class);
                intent.putExtra("name2", "doctor");
                startActivity(intent);
            } else {
                Toast.makeText(this, "Неверный пароль", Toast.LENGTH_SHORT).show();
            }
        }

    }

}