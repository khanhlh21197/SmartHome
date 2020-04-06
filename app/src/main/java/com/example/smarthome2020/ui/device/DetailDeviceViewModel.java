package com.example.smarthome2020.ui.device;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.smarthome2020.data.FirebaseQueryLiveData;
import com.example.smarthome2020.ui.login.Deserializer;

import java.util.ArrayList;
import java.util.List;

public class DetailDeviceViewModel extends BaseObservable {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRootRef = database.getReference("Devices");

    public LiveData<DetailDevice> detailDeviceLiveData = new MutableLiveData<>();
    ArrayList<DetailDevice> detailDevices = new ArrayList<>();

    LiveData<List<DetailDevice>> getDevices(){
        FirebaseQueryLiveData mLiveData = new FirebaseQueryLiveData(myRootRef);
        return Transformations.map(mLiveData, new Deserializer<DetailDevice>());
    }
}
