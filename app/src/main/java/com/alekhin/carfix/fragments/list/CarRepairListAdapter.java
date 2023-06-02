package com.alekhin.carfix.fragments.list;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.alekhin.carfix.databinding.CardCarRepairBinding;
import com.alekhin.carfix.room.CarRepair;

import java.util.Collections;
import java.util.List;

public class CarRepairListAdapter extends RecyclerView.Adapter<CarRepairListAdapter.CarRepairListViewHolder> {
    List<CarRepair> carRepairList = Collections.emptyList();

   public static class CarRepairListViewHolder extends RecyclerView.ViewHolder {
       CardCarRepairBinding binding;
       public CarRepairListViewHolder(@NonNull CardCarRepairBinding binding) {
           super(binding.getRoot());
           this.binding = binding;
       }
   }

    @NonNull
    @Override
    public CarRepairListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarRepairListViewHolder(CardCarRepairBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull CarRepairListViewHolder holder, int position) {
        CarRepair currentCarRepair = carRepairList.get(position);
        holder.binding.carRepairNumber.setText(String.valueOf(currentCarRepair.id));
        holder.binding.carBrand.setText(currentCarRepair.carBrand);
        holder.binding.carModel.setText(currentCarRepair.carModel);
        holder.binding.carNumber.setText(currentCarRepair.carNumber);
        holder.binding.repairType.setText(currentCarRepair.repairType);
        holder.binding.masterName.setText(currentCarRepair.masterName);

        holder.binding.card.setOnClickListener(v -> {
            NavDirections action = CarRepairListFragmentDirections.actionCarRepairListFragmentToCarRepairUpdateFragment(currentCarRepair);
            Navigation.findNavController(v).navigate(action);
        });
    }

    @Override
    public int getItemCount() {
        return carRepairList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    void setData(List<CarRepair> carRepairList) {
       this.carRepairList = carRepairList;
       notifyDataSetChanged();
    }
}