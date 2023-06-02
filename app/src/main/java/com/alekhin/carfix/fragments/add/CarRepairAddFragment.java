package com.alekhin.carfix.fragments.add;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.alekhin.carfix.R;
import com.alekhin.carfix.databinding.FragmentCarRepairAddBinding;
import com.alekhin.carfix.room.CarRepair;
import com.alekhin.carfix.room.CarRepairViewModel;

public class CarRepairAddFragment extends Fragment {
    private FragmentCarRepairAddBinding binding;

    private CarRepairViewModel mCarRepairViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCarRepairAddBinding.inflate(inflater);

        mCarRepairViewModel = new ViewModelProvider(this).get(CarRepairViewModel.class);

        binding.addCarRepairButton.setOnClickListener(this::insertDataToDatabase);
        binding.backTextButton.setOnClickListener(this::back);

        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                back(getView());
            }
        });

        return binding.getRoot();
    }

    private void insertDataToDatabase(View v) {
        String carBrand = binding.carBrandTextField.getText().toString();
        String carModel = binding.carModelTextField.getText().toString();
        String carNumber = binding.carNumberTextField.getText().toString();
        String repairType = binding.repairTypeTextField.getText().toString();
        String masterName = binding.masterNameTextField.getText().toString();

        if (inputCheck(carBrand, carModel, carNumber, repairType, masterName)) {
            CarRepair carRepair = new CarRepair(0, carBrand, carModel, carNumber, repairType, masterName);
            mCarRepairViewModel.addCarRepair(carRepair);
            Toast.makeText(requireContext(), R.string.add_success, Toast.LENGTH_LONG).show();
            Navigation.findNavController(v).navigate(R.id.action_carRepairAddFragment_to_carRepairListFragment);
        } else Toast.makeText(requireContext(), R.string.add_failed, Toast.LENGTH_LONG).show();
    }

    private Boolean inputCheck(String carBrand, String carModel, String carNumber, String repairType, String masterName) {
        return !(TextUtils.isEmpty(carBrand) || TextUtils.isEmpty(carModel) || TextUtils.isEmpty(carNumber) || TextUtils.isEmpty(repairType) || TextUtils.isEmpty(masterName));
    }

    private void back(View v) {
        String carBrand = binding.carBrandTextField.getText().toString();
        String carModel = binding.carModelTextField.getText().toString();
        String carNumber = binding.carNumberTextField.getText().toString();
        String repairType = binding.repairTypeTextField.getText().toString();
        String masterName = binding.masterNameTextField.getText().toString();

        if (!carBrand.equals("") || !carModel.equals("") || !carNumber.equals("") || !repairType.equals("") || !masterName.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle(R.string.back_title);
            builder.setMessage(R.string.back_confirm_not_to_add);
            builder.setPositiveButton(R.string.yes, (dialog, which) -> Navigation.findNavController(v).navigate(R.id.action_carRepairAddFragment_to_carRepairListFragment));

            builder.setNegativeButton(R.string.no, (dialog, which) -> {});

            builder.create().show();
        } else Navigation.findNavController(v).navigate(R.id.action_carRepairAddFragment_to_carRepairListFragment);
    }
}