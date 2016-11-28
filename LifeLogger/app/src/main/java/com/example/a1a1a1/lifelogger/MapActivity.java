package com.example.a1a1a1.lifelogger;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by 1a1a1 on 2016-11-25.
 */
public class MapActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_main);
    }

    public void backaway(View v){
        finish();
    }
}
