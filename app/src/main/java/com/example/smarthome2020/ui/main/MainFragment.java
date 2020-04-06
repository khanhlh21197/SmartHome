package com.example.smarthome2020.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.smarthome2020.common.BaseBindingAdapter;
import com.example.smarthome2020.R;
import com.example.smarthome2020.databinding.MainFragmentBinding;
import com.example.smarthome2020.ui.device.Device;
import com.example.smarthome2020.ui.login.LoginViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private LoginViewModel loginViewModel;
    private BaseBindingAdapter<Device> adapter;
    private MainFragmentBinding mainFragmentBinding;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        initData();
        initView();
        return view;
    }

    @SuppressLint("NewApi")
    private void initView() {
        mainFragmentBinding = DataBindingUtil.setContentView(Objects.requireNonNull(getActivity()), R.layout.main_fragment);
        adapter = new BaseBindingAdapter<>(getActivity(), R.layout.item_device);
        mainFragmentBinding.listDevice.setAdapter(adapter);
        mainFragmentBinding.listDevice.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    }

    @SuppressLint("NewApi")
    private void initData() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.allDevicesLD.observe(Objects.requireNonNull(getActivity()), new Observer<ArrayList<Device>>() {
            @Override
            public void onChanged(ArrayList<Device> devices) {
                adapter.setData(devices);
                for (Device device : devices) {
                    Log.d("devices", device.getId() + "");
                }
                Log.d("devices", devices.size() + "");
            }
        });
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
        // Read from the database
        loginViewModel.allDevicesLD.observe(getActivity(), new Observer<ArrayList<Device>>() {
            @Override
            public void onChanged(ArrayList<Device> devices) {
                mViewModel.allDevicesLD.setValue(devices);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
