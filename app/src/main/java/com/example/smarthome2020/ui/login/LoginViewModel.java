package com.example.smarthome2020.ui.login;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.smarthome2020.common.CommonActivity;
import com.example.smarthome2020.ui.device.Device;
import com.example.smarthome2020.utils.Result;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginViewModel extends ViewModel {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userRef = database.getReference("Users");
    private DatabaseReference deviceRef = database.getReference("Devices");

    public MutableLiveData<String> Email = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();
    public MutableLiveData<ArrayList<User>> usersLD = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Device>> devicesOfUserLD = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Device>> allDevicesLD = new MutableLiveData<>();

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Device> devicesOfUser = new ArrayList<>();
    private ArrayList<Device> allDevices = new ArrayList<>();
    private Result result;
    private User inputUser;

    void setResult(Result result) {
        this.result = result;
    }

    MutableLiveData<ArrayList<User>> getAllUsersLiveData() {
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    User user = dataSnapshot1.getValue(User.class);
                    users.add(user);
                }
                usersLD.setValue(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return usersLD;
    }

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

    ArrayList<Device> getDevicesOfUser(){
        ArrayList<Device> devices = new ArrayList<>();
        devicesOfUser = (ArrayList<Device>) getUser(inputUser).getDevices();
        for (Device device: allDevices){
            for (Device device1: devicesOfUser){
                if (device.getId().equals(device1.getId())){
                    devices.add(device);
                }
            }
        }
        return devices;
    }

    MutableLiveData<ArrayList<Device>> getDevicesOfUserLiveData(){
        devicesOfUserLD.setValue(getDevicesOfUser());
        return devicesOfUserLD;
    }

    public void onClick(View view) {
        inputUser = new User(Email.getValue(), Password.getValue());
        if (isLoginSuccess(inputUser)){
            result.onSuccess(inputUser, "Đăng nhập thành công với "+ inputUser.getEmail());
        }else{
            result.onFailure("Sai tên email hoặc mật khẩu!");
        }
    }

    private boolean isLoginSuccess(User usersMutableLiveData) {
        if (!CommonActivity.isNullOrEmpty(users)) {
            for (User user: users){
                if (user.getEmail().equals(usersMutableLiveData.getEmail())
                        && user.getPassword().equals(usersMutableLiveData.getPassword())){
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    private User getUser(User usersMutableLiveData){
        if (!CommonActivity.isNullOrEmpty(users)) {
            for (User user: users){
                if (user.getEmail().equals(usersMutableLiveData.getEmail())
                        && user.getPassword().equals(usersMutableLiveData.getPassword())){
                    return user;
                }
            }
        }
        return new User();
    }
}
