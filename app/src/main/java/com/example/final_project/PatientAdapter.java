package com.example.final_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.databinding.ItemPatientBinding;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.viewHolder> {
private final ArrayList<Patient> data = new ArrayList<>();

    public PatientAdapter(OnPatientDataClickListener clickListener) {
        this.clickListener = clickListener;
    }

    interface OnPatientDataClickListener {
        void onPatientClick(RecyclerView.ViewHolder holder);
    }

    private final OnPatientDataClickListener clickListener;

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(ItemPatientBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false).getRoot());
    }

    public void setData(List<Patient> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.bind(data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onPatientClick(holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void removeItemByPosition(int adapterPosition) {
        data.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        private final ItemPatientBinding Itembinding;
        public viewHolder(@NonNull View viewItem) {
            super(viewItem);
            this.Itembinding = ItemPatientBinding.bind(viewItem);
        }

        public void bind(Patient patient) {
            Itembinding.street.setText(patient.getStreet());
            String description = patient.getDescription();
            if (description.isEmpty()) {
                Itembinding.ill.setVisibility(View.GONE);
            } else {
                Itembinding.ill.setVisibility(View.VISIBLE);
                Itembinding.ill.setText(patient.getDescription());
            }
            Itembinding.fio.setText(patient.getFio());
        }
    }
}
