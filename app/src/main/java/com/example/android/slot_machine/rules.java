package com.example.android.slot_machine;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by android on 4/3/18.
 */

public class rules extends AppCompatActivity {
    private TextView scoret;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules);
        scoret=findViewById(R.id.score2);
        //scoret.setText("Score:"+x.getIntExtra("Score")+"");


    }
}
