package com.exam.myapplication.ui.features.list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exam.myapplication.R;
import com.exam.myapplication.data.model.SickUserModel;
import com.exam.myapplication.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItemViewHolder> {

    private List<SickUserModel> list = new ArrayList<>();
    private OnAdapterItemClickListener listener;

    public List<SickUserModel> getList() {
        return list;
    }

    public void setListener(OnAdapterItemClickListener listener) {
        this.listener = listener;
    }

    public void submitItems(List<SickUserModel> items) {
        list = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListItemViewHolder(
                ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ListItemViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding binding;

        public ListItemViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(SickUserModel sickUserModel) {
            if (listener != null) {
                binding.getRoot().setOnClickListener(v -> listener.onClick(sickUserModel));
            }
            Context context = binding.getRoot().getContext();
            binding.itemFio.setText(sickUserModel.getFio());
            binding.itemDoctorFio.setText(
                    sickUserModel.getDoctorFio() != null ?
                            sickUserModel.getDoctorFio() :
                            binding.getRoot().getContext().getString(R.string.no_doctor)
            );
            binding.itemIsInHospital.setText(
                    sickUserModel.getInHospital() ?
                            context.getString(R.string.in_hospital) :
                            context.getString(R.string.not_in_hospital)
            );
        }
    }
}

