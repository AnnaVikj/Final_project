package com.example.final_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.final_project.databinding.DetailFragmentBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailFragment extends Fragment {
    private final PatientRepository repository = PatientRepository.getInstance(getContext());
    private static Patient data;
    private static String body_name = "";
    private static String status2 = "";
    private static int flag1 = 0;

    public static DetailFragment newInstance(PatientRepository repository, int position, int flag) {
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        flag1 = flag;
        if (position >= 0) {
            data = repository.getPatients().get(position);
            bundle.putString("street", data.getStreet());
            bundle.putString("description", data.getDescription());
            bundle.putString("fio", data.getFio());
            bundle.putString("body", data.getBody());
            bundle.putString("condition", data.getCondition());
        } else {
            bundle.putString("street", "");
            bundle.putString("description", "");
            bundle.putString("fio", "");
            bundle.putString("body", "");
            bundle.putString("condition", "");
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    private DetailFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String StreetText = getArguments().getString("street");
        String descriptionText = getArguments().getString("description");
        String fioText = getArguments().getString("fio");
        String bodyclick = getArguments().getString("body");
        String status1 = getArguments().getString("condition");
        binding.street.setText(StreetText);
        binding.description.setText(descriptionText);
        binding.fio.setText(fioText);
        body_name = bodyclick;
        status2 = status1;

        if (bodyclick.equals("head")){
            binding.head.setChecked(true);
        }
        if(bodyclick.equals("hands")){
            binding.hands.setChecked(true);
        }
        if(bodyclick.equals("foot")){
            binding.foots.setChecked(true);
        }
        if(bodyclick.equals("lower_torso")){
            binding.lowerTorso.setChecked(true);
        }
        if(bodyclick.equals("high_torso")){
            binding.highTorso.setChecked(true);
        }
        if(bodyclick.equals("all")){
            binding.all.setChecked(true);
        }

        if(status1.equals("Состояние тяжелое")){
            binding.high.setChecked(true);
        }else if(status1.equals("Состояние среднее")){
            binding.middle.setChecked(true);
        }

        binding.back.setOnClickListener(view1 -> {
                    getParentFragmentManager()
                            .beginTransaction()
                            .replace(R.id.rootContainer, new MainFragment())
                            .commit();
                body_name = data.getBody();
                status2 = data.getCondition();}
        );

        binding.head.setOnClickListener(v -> {
            body_name = "head";
        });
        binding.foots.setOnClickListener(v -> {
            body_name = "foot";
        });
        binding.hands.setOnClickListener(v -> {
            body_name = "hands";
        });
        binding.lowerTorso.setOnClickListener(v -> {
            body_name = "lower_torso";
        });
        binding.highTorso.setOnClickListener(v -> {
            body_name = "high_torso";
        });
        binding.all.setOnClickListener(v -> {
            body_name = "all";
        });

        binding.high.setOnClickListener(v -> {
            status2 = "Состояние тяжелое";
        });
        binding.middle.setOnClickListener(v -> {
            status2 = "Состояние среднее";
        });


        String[] extern = {getResources().getString(R.string.save), getResources().getString(R.string.add)};
        binding.saveoradd.setText(flag1 == 1 ? extern[0] : extern[1]);
        binding.saveoradd.setOnClickListener(view1 -> {
                    if (binding.street.getText().toString().equals("")) {
                        Toast.makeText(getContext(), "Улица не заполнена!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (flag1 == 1) {
                            data.setStreet(binding.street.getText().toString());
                            data.setDescription(binding.description.getText().toString());
                            data.setFio(binding.fio.getText().toString());
                            data.setBody(body_name);
                            data.setCondition(status2);
                            //repository.updatePatient(data);
                        } else {
                            repository.addPatient(new Patient(
                                    binding.street.getText().toString(),
                                    binding.description.getText().toString(),
                                    binding.fio.getText().toString(),
                                    body_name,
                                    status2
                            ));
                        }

                        getParentFragmentManager()
                                .beginTransaction()
                                .replace(R.id.rootContainer, new MainFragment())
                                .commit();
                    }
                }
        );
    }
}
