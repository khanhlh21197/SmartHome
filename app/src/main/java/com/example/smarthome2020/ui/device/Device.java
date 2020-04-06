package com.example.smarthome2020.ui.device;

import androidx.annotation.NonNull;

import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.smarthome2020.ui.login.HHA000002;

import java.io.Serializable;

public class Device implements Serializable {
    @SerializedName("HHA000001")
    @PropertyName("HHA000001")
    @Expose
    private HHA000001 hHA000001;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("HHA000002")
    @PropertyName("HHA000002")
    @Expose
    private HHA000002 hHA000002;
    @SerializedName("name")
    @Expose
    private String name;

    public Device() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HHA000001 getHHA000001() {
        return hHA000001;
    }

    public void setHHA000001(HHA000001 hHA000001) {
        this.hHA000001 = hHA000001;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public HHA000002 getHHA000002() {
        return hHA000002;
    }

    public void setHHA000002(HHA000002 hHA000002) {
        this.hHA000002 = hHA000002;
    }

    @NonNull
    @Override
    public String toString() {
        return id + name;
    }
}
