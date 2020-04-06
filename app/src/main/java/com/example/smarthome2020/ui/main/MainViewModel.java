package com.example.smarthome2020.ui.main;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smarthome2020.ui.device.Device;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<Device> deviceMutableLiveData = new MutableLiveData<>();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userRef = database.getReference("Users");
    private DatabaseReference deviceRef = database.getReference("Devices");
    private ArrayList<Device> allDevices = new ArrayList<>();
    public MutableLiveData<ArrayList<Device>> allDevicesLD = new MutableLiveData<>();

    MutableLiveData<ArrayList<Device>> getAllDevices(){
        deviceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                allDevices.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Device device = dataSnapshot1.getValue(Device.class);
                    allDevices.add(device);
                }
                Log.d("all devices: ", allDevices.size()+"");
                allDevicesLD.setValue(allDevices);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return allDevicesLD;
    }
}
