package com.example.final_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.databinding.ActivityMarkUpDocBinding;

public class MarcDocFragment extends Fragment {
    private final PatientRepository repository = PatientRepository.getInstance(getContext());
    private MarkUpDoc markUpDoc = new MarkUpDoc();

    PatientAdapterDoc.OnPatientDataClickListener patientClickListener = new PatientAdapterDoc.OnPatientDataClickListener() {
        @Override
        public void onPatientClick(RecyclerView.ViewHolder holder) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), Typeface.BOLD_ITALIC);
            View dialogView = null;
            String body = repository.getPatients().get(holder.getAdapterPosition()).getBody();
            if (body.equals("head")){
                dialogView = getLayoutInflater().inflate(R.layout.head_for, null);
            }else if(body.equals("foot")){
                dialogView = getLayoutInflater().inflate(R.layout.leg_for, null);
            }else if(body.equals("hands")){
                dialogView = getLayoutInflater().inflate(R.layout.hand_for, null);
            }else if(body.equals("lower_torso")){
                dialogView = getLayoutInflater().inflate(R.layout.lower_for, null);
            }else if(body.equals("high_torso")){
                dialogView = getLayoutInflater().inflate(R.layout.high_for, null);
            }else if(body.equals("all")){
                dialogView = getLayoutInflater().inflate(R.layout.all_for, null);
            }
            builder.setTitle(repository.getPatients().get(holder.getAdapterPosition()).getStreet());
            builder.setView(dialogView);
            builder.setMessage(repository.getPatients().get(holder.getAdapterPosition()).getDescription() + "\n"
                    + repository.getPatients().get(holder.getAdapterPosition()).getFio() + "\n"
                    + repository.getPatients().get(holder.getAdapterPosition()).getCondition() + "\n" + "\n"
                    + "Принять пациента?");
            builder.setNeutralButton("Нет", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Patient patient = repository.getPatients().get(holder.getAdapterPosition());
                    repository.removeByPosition(holder.getAdapterPosition(), patient.getStreet());
                    adapter.removeItemByPosition(holder.getAdapterPosition());
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    };
    private final PatientAdapterDoc adapter = new PatientAdapterDoc(patientClickListener);
    ActivityMarkUpDocBinding markUpDocBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        markUpDocBinding = ActivityMarkUpDocBinding.inflate(inflater, container, false);
        markUpDocBinding.containerDoc.setAdapter(adapter);
        adapter.setData(repository.getPatients());
        return markUpDocBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fm = getParentFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.ContainerMarc);
        if (fragment == null) {
            fragment = new MainFragment();
            fm.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.ContainerMarc, fragment, String.valueOf(false))
                    .commit();
        }
    }
}
