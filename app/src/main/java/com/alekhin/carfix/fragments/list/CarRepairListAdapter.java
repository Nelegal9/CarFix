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
       private final CardCarRepairBinding binding;

       public CarRepairListViewHolder(@NonNull CardCarRepairBinding binding) {
           super(binding.getRoot());
           this.binding = binding;
       }

       private void bind(CarRepair carRepair) {
           binding.carRepairNumber.setText(String.valueOf(carRepair.id));
           binding.carBrand.setText(carRepair.carBrand);
           binding.carModel.setText(carRepair.carModel);
           binding.carNumber.setText(carRepair.carNumber);
           binding.repairType.setText(carRepair.repairType);
           binding.masterName.setText(carRepair.masterName);

           binding.card.setOnClickListener(v -> {
               NavDirections action = CarRepairListFragmentDirections.actionCarRepairListFragmentToCarRepairUpdateFragment(carRepair);
               Navigation.findNavController(v).navigate(action);
           });
       }
   }

    @NonNull
    @Override
    public CarRepairListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarRepairListViewHolder(CardCarRepairBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CarRepairListViewHolder holder, int position) {
        CarRepair currentCarRepair = carRepairList.get(position);
        holder.bind(currentCarRepair);
    }

    @Override
    public int getItemCount() {
        return carRepairList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<CarRepair> carRepairList) {
       this.carRepairList = carRepairList;
       notifyDataSetChanged();
    }
}