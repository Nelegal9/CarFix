package com.alekhin.carfix.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CarRepairViewModel extends AndroidViewModel {
    public LiveData<List<CarRepair>> readAllData;
    private final CarRepairRepository carRepairRepository;

    public CarRepairViewModel(@NonNull Application application) {
        super(application);

        CarRepairDao carRepairDao = CarRepairDatabase.getDatabase(application).carRepairDao();
        carRepairRepository = new CarRepairRepository(carRepairDao);
        readAllData = carRepairDao.readAllData();
    }

    public void addCarRepair(CarRepair carRepair) {
        carRepairRepository.addCarRepair(carRepair);
    }

    public void updateCarRepair(CarRepair carRepair) {
        carRepairRepository.updateCarRepair(carRepair);
    }

    public void deleteCarRepair(CarRepair carRepair) {
        carRepairRepository.deleteCarRepair(carRepair);
    }

    public LiveData<List<CarRepair>> searchDatabase(String searchQuery) {
        return carRepairRepository.searchDatabase(searchQuery);
    }
}