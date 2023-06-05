package com.alekhin.carfix.room;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CarRepairRepository {
    private final CarRepairDao carRepairDao;

    public CarRepairRepository(@NonNull CarRepairDao carRepairDao) {
        this.carRepairDao = carRepairDao;
    }

    void addCarRepair(CarRepair carRepair) {
        carRepairDao.addCarRepair(carRepair);
    }

    void updateCarRepair(CarRepair carRepair) {
        carRepairDao.updateCarRepair(carRepair);
    }

    void deleteCarRepair(CarRepair carRepair) {
        carRepairDao.deleteCarRepair(carRepair);
    }

    LiveData<List<CarRepair>> searchDatabase(String searchQuery) {
        return carRepairDao.searchDatabase(searchQuery);
    }
}