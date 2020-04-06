package com.example.smarthome2020.ui.login;

import androidx.arch.core.util.Function;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.List;

public class Deserializer<T> implements Function<DataSnapshot, List<T>> {
    private List<T> ts = new ArrayList<>();
    @Override
    public List<T> apply(DataSnapshot dataSnapshot) {
        ts.clear();
        GenericTypeIndicator<ArrayList<T>> t = new GenericTypeIndicator<ArrayList<T>>() {
        };
        ts = dataSnapshot.getValue(t);
        return ts;
    }
}
