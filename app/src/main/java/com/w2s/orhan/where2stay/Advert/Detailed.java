package com.w2s.orhan.where2stay.Advert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.w2s.orhan.where2stay.R;

public class Detailed extends AppCompatActivity {


    public TextView ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        ID = findViewById(R.id.dtl_advertID);
        Intent intent = getIntent();
        String s = intent.getStringExtra("advertID");
        ID.setText(s);
    }
}
