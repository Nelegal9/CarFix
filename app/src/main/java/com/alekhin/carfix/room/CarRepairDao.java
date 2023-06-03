package com.alekhin.carfix.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CarRepairDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addCarRepair(CarRepair carRepair);

    @Update
    void updateCarRepair(CarRepair carRepair);

    @Delete
    void deleteCarRepair(CarRepair carRepair);

    @Query("SELECT * FROM carrepair ORDER BY id ASC")
    LiveData<List<CarRepair>> readAllData();

    @Query("SELECT * FROM carrepair WHERE carBrand LIKE :searchQuery OR carModel LIKE :searchQuery OR carNumber LIKE :searchQuery OR repairType LIKE :searchQuery OR masterName LIKE :searchQuery")
    LiveData<List<CarRepair>> searchDatabase(String searchQuery);
}