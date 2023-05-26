package com.example.final_project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.databinding.ActivityDispatcherBinding;
import com.example.final_project.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {
    private final PatientRepository repository = PatientRepository.getInstance(getContext());
    PatientAdapter.OnPatientDataClickListener productClickListener = new PatientAdapter.OnPatientDataClickListener() {
        @Override
        public void onPatientClick(RecyclerView.ViewHolder holder) {

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rootContainer, DetailFragment.newInstance(repository, holder.getAdapterPosition(),1))
                    .commit();
        }
    };
    private final PatientAdapter adapter = new PatientAdapter(productClickListener);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMainBinding binding = FragmentMainBinding.inflate(inflater, container, false);
        binding.container.setAdapter(adapter);
        binding.update.setOnClickListener(v -> {
            adapter.setData(repository.getPatients());
        });
        binding.add.setOnClickListener(v -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.rootContainer, DetailFragment.newInstance(repository,-1,0))
                    .commit();

            //adapter.setData(repository.getPatients());
        });
        //adapter.setData(repository.getPatients());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter.setData(repository.getPatients());
        FragmentManager fm = getParentFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.rootContainer);
        if (fragment == null) {
            fragment = new MainFragment();
            fm.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.rootContainer, fragment, String.valueOf(false))
                    .commit();
        }
    }
}
