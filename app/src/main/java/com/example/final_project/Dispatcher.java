package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.final_project.databinding.ActivityDispatcherBinding;

public class Dispatcher extends AppCompatActivity {
ActivityDispatcherBinding binding;
private PatientRepository repository = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDispatcherBinding.inflate(getLayoutInflater());
        String s = getIntent().getStringExtra("dispatcher");
        if (repository == null) repository = PatientRepository.getInstance(getApplicationContext());
        setContentView(binding.getRoot());

        repository.Firebase();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.rootContainer);
        if (fragment == null) {
            fragment = new MainFragment();
            fm.beginTransaction()
                    // .addToBackStack(null)
                    .replace(R.id.rootContainer, fragment, String.valueOf(false))
                    .commit();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}