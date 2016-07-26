package com.example.lz.fruit;

import android.support.v4.app.Fragment;

/**
 * Created by lz on 2016/7/5.
 */
public abstract class BaseFragment extends Fragment {
    @Override
    public void onStart() {
        super.onStart();
        getview();
    }

    @Override
    public void onResume() {
        super.onResume();
        setview();
    }

    abstract public void getview();
    abstract public void setview();
}
