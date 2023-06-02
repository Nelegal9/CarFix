package com.alekhin.carfix.room;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CarRepair implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String carBrand;
    public String carModel;
    public String carNumber;
    public String repairType;
    public String masterName;

    public CarRepair(int id, String carBrand, String carModel, String carNumber, String repairType, String masterName) {
        this.id = id;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carNumber = carNumber;
        this.repairType = repairType;
        this.masterName = masterName;
    }

    protected CarRepair(Parcel in) {
        id = in.readInt();
        carBrand = in.readString();
        carModel = in.readString();
        carNumber = in.readString();
        repairType = in.readString();
        masterName = in.readString();
    }

    public static final Creator<CarRepair> CREATOR = new Creator<CarRepair>() {
        @Override
        public CarRepair createFromParcel(Parcel in) {
            return new CarRepair(in);
        }

        @Override
        public CarRepair[] newArray(int size) {
            return new CarRepair[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(carBrand);
        dest.writeString(carModel);
        dest.writeString(carNumber);
        dest.writeString(repairType);
        dest.writeString(masterName);
    }
}