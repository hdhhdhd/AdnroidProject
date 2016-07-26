package com.example.lz.student.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.lz.student.R;

/**
 * Created by lz on 2016/6/13.
 */
public class Log extends Fragment {
    View view;
    EditText username,password;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.mainmenu,null);
        username = (EditText) view.findViewById(R.id.log_username_et);
        password = (EditText) view.findViewById(R.id.log_password_et);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
