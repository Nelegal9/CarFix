package com.alekhin.carfix.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CarRepair.class}, version = 1, exportSchema = false)
public abstract class CarRepairDatabase extends RoomDatabase {
    public abstract CarRepairDao carRepairDao();
    private static final String DATABASE_NAME = "car_repair_database";
    private static volatile CarRepairDatabase INSTANCE = null;

    public synchronized static CarRepairDatabase getDatabase(Context context) {
        if (INSTANCE == null) INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CarRepairDatabase.class, DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        return INSTANCE;
    }
}