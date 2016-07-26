package com.example.lz.fruit.Tools;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lz.fruit.R;

/**
 * Created by lz on 2016/7/5.
 */
public class  Tools {

    public static void setTitle(View v, final Activity activity) {
        ImageView iv = (ImageView) v.findViewById(R.id.title_back);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }
}
