package com.w2s.orhan.where2stay.Advert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.w2s.orhan.where2stay.MainActivity;
import com.w2s.orhan.where2stay.R;

public class AddAdvert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advert);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        TextView textView = findViewById(R.id.txt_Type);
        textView.setText(type);

    }
}
