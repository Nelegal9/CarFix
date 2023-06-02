package com.alekhin.carfix.fragments.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alekhin.carfix.R;
import com.alekhin.carfix.databinding.FragmentCarRepairListBinding;
import com.alekhin.carfix.room.CarRepair;
import com.alekhin.carfix.room.CarRepairViewModel;

public class CarRepairListFragment extends Fragment {
    private FragmentCarRepairListBinding binding;

    SharedPreferences sharedPreferences;
    private static final String KEY = "on_boarding_completed";

    private CarRepairViewModel mCarRepairViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCarRepairListBinding.inflate(inflater);

        CarRepairListAdapter carRepairListAdapter = new CarRepairListAdapter();
        binding.carRepairList.setAdapter(carRepairListAdapter);
        binding.carRepairList.setLayoutManager(new LinearLayoutManager(requireContext()));

        mCarRepairViewModel = new ViewModelProvider(this).get(CarRepairViewModel.class);
        mCarRepairViewModel.readAllData.observe(getViewLifecycleOwner(), carRepairListAdapter::setData);

        firstTimeCheck();

        binding.addCarRepairFloatingActionButton.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_carRepairListFragment_to_carRepairAddFragment));

        return binding.getRoot();
    }

    void firstTimeCheck() {
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        boolean completed = sharedPreferences.getBoolean(KEY, false);
        if (!completed) {
            CarRepair defaultCarRepair = new CarRepair(0, "BMW", "M3 E46", "11N2005Y", "Tech. Inspection", "Alekhin E.A.");
            mCarRepairViewModel.addCarRepair(defaultCarRepair);
            completeOnBoardingProcess();
        }
    }

    void completeOnBoardingProcess() {
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY, true).apply();
    }
}