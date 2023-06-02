package com.alekhin.carfix.room;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CarRepairRepository {
    private final CarRepairDao carRepairDao;
    LiveData<List<CarRepair>> readAllData;

    public CarRepairRepository(@NonNull CarRepairDao carRepairDao) {
        this.carRepairDao = carRepairDao;
        readAllData = carRepairDao.readAllData();
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
}