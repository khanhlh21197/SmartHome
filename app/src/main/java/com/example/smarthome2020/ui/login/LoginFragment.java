package com.example.smarthome2020.ui.login;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.smarthome2020.R;
import com.example.smarthome2020.common.CommonActivity;
import com.example.smarthome2020.common.ReplaceFragment;
import com.example.smarthome2020.databinding.LoginFragmentBinding;
import com.example.smarthome2020.ui.device.Device;
import com.example.smarthome2020.ui.main.MainFragment;
import com.example.smarthome2020.utils.Result;

import java.util.ArrayList;
import java.util.List;

public class LoginFragment extends Fragment implements Result {
    private LoginViewModel loginViewModel;
    private Activity mActivity;
    private LoginFragmentBinding binding;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Device> devicesOfUser = new ArrayList<>();

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        unit(view);
        return view;
    }

    private void unit(View view) {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(mActivity, R.layout.login_fragment);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);
//        users = loginViewModel.getUsersFromFirebase();
        loginViewModel.getAllUsersLiveData().observe((LifecycleOwner) mActivity, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                LoginFragment.this.users = (ArrayList<User>) users;
                Log.d("users", users.size() + "observe");
                for (User user: users){
                    Log.d("email", user.getEmail());
                }
            }
        });
        loginViewModel.setResult(this);

        loginViewModel.getAllDevices().observe((LifecycleOwner) mActivity, new Observer<ArrayList<Device>>() {
            @Override
            public void onChanged(ArrayList<Device> devices) {

            }
        });
    }

    @Override
    public void onFailure(String message) {
        CommonActivity.showConfirmValidate(mActivity, message);
    }

    @Override
    public void onSuccess(Object o, String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
//        ReplaceFragment.replaceFragment(mActivity, MainFragment.newInstance(), true);
        loginViewModel.getAllDevices();
        ReplaceFragment.replaceFragment(mActivity, MainFragment.newInstance(), true);
    }

    public void displayAlertDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.enter_firebase_url_dialog, null);


        AlertDialog.Builder alert = new AlertDialog.Builder(mActivity);
        alert.setTitle("Login");
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mActivity, "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Login", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // code for matching password
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}
