package com.w2s.orhan.where2stay;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = MainActivity.sharedPreferences.edit();
                editor.putBoolean("isLogin", false);
                editor.commit();
                FirebaseAuth.getInstance().signOut();
                MainActivity.fab.setVisibility(View.INVISIBLE);

                finish();

            }
        });
    }
}
