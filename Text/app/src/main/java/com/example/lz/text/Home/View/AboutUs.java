package com.example.lz.text.Home.View;

import android.view.View;
import android.widget.ImageView;

import com.example.lz.text.BaseActivity;
import com.example.lz.text.Tools;
import com.example.lz.text.R;

/**
 * Created by lz on 2016/5/22.
 */
public class AboutUs extends BaseActivity {
    ImageView iv;
    View view;
    @Override
    public void getView() {
        setContentView(R.layout.aboutus);
        iv = (ImageView) findViewById(R.id.aboutus_iv);
        view =findViewById(R.id.aboutustitle_lo);
    }

    @Override
    public void setView() {
          Tools.setTitle(view,"关于我们",this);
    }
}
