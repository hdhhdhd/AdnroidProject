package com.example.lz.newsbeta.Entity;

import android.widget.Button;

/**
 * Created by lz on 2016/7/20.
 */
public class AddnewsChanged {
    private Button show;
    private Button click;

    public AddnewsChanged(Button show, Button click) {
        this.show = show;
        this.click = click;
    }

    public Button getShow() {
        return show;
    }

    public void setShow(Button show) {
        this.show = show;
    }

    public Button getClick() {
        return click;
    }

    public void setClick(Button click) {
        this.click = click;
    }

}
