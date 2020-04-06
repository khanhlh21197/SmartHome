package com.example.smarthome2020.ui.device;

import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DetailDevice implements Serializable {
    @SerializedName("NCL")
    @PropertyName("NCL")
    @Expose
    private String nCL;
    @SerializedName("NCLT")
    @PropertyName("NCLT")
    @Expose
    private String nCLT;
    @SerializedName("ND")
    @PropertyName("ND")
    @Expose
    private String nD;
    @SerializedName("NDU")
    @PropertyName("NDU")
    @Expose
    private String nDU;
    @SerializedName("NG")
    @PropertyName("NG")
    @Expose
    private String nG;
    @SerializedName("NO")
    @PropertyName("NO")
    @Expose
    private String nO;

    public String getNCL() {
        return nCL;
    }

    public void setNCL(String nCL) {
        this.nCL = nCL;
    }

    public String getNCLT() {
        return nCLT;
    }

    public void setNCLT(String nCLT) {
        this.nCLT = nCLT;
    }

    public String getND() {
        return nD;
    }

    public void setND(String nD) {
        this.nD = nD;
    }

    public String getNDU() {
        return nDU;
    }

    public void setNDU(String nDU) {
        this.nDU = nDU;
    }

    public String getNG() {
        return nG;
    }

    public void setNG(String nG) {
        this.nG = nG;
    }

    public String getNO() {
        return nO;
    }

    public void setNO(String nO) {
        this.nO = nO;
    }
}
