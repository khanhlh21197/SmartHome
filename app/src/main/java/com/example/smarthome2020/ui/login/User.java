package com.example.smarthome2020.ui.login;

import android.util.Patterns;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.smarthome2020.ui.device.Device;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("devices")
    @PropertyName("Devices")
    @Expose
    private List<Device> devices;

    public User(String email, String password, ArrayList<Device> Devices) {
        this.email = email;
        this.password = password;
        this.devices = Devices;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    public boolean isPasswordLengthGreaterThan5() {
        return getPassword().length() > 5;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }

    @NonNull
    @Override
    public String toString() {
        return email + password;
    }
}
