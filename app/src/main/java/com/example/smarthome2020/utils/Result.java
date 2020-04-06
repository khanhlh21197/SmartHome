package com.example.smarthome2020.utils;

public interface Result<T>  {
    void onFailure(String message);
    void onSuccess(Object o, String message);
}
