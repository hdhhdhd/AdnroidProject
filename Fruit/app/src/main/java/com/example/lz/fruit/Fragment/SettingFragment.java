package com.example.lz.fruit.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lz.fruit.Activity.LogRegActivity;
import com.example.lz.fruit.BaseFragment;
import com.example.lz.fruit.R;

/**
 * Created by lz on 2016/7/7.
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener{
    View view;
    Button change,quit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.settingfragment,null);
        return view;
    }

    @Override
    public void getview() {
        change = (Button) getActivity().findViewById(R.id.setting_change);
        quit = (Button) getActivity().findViewById(R.id.setting_quit);
    }

    @Override
    public void setview() {
        change.setOnClickListener(this);
        quit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.setting_change:
                intent =new Intent(getActivity(), LogRegActivity.class);
                startActivity(intent);
                break;
            case R.id.setting_quit:
                getActivity().finish();
                break;
        }
    }
}
