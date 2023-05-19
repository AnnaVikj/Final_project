package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.final_project.databinding.ActivityMarkUpDocBinding;
import com.example.final_project.databinding.ContainerMarkDocBinding;

public class MarkUpDoc extends AppCompatActivity {
    ContainerMarkDocBinding binding;
    private PatientRepository repository = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String name = getIntent().getStringExtra("name2");
        binding = ContainerMarkDocBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (repository == null) PatientRepository.getInstance(getApplicationContext());

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.ContainerMarc);
        if (fragment == null) {
            fragment = new MarcDocFragment();
            fm.beginTransaction()
                    // .addToBackStack(null)
                    .replace(R.id.ContainerMarc, fragment, String.valueOf(false))
                    .commit();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}