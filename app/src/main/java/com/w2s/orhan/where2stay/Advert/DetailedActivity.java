package com.w2s.orhan.where2stay.Advert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.w2s.orhan.where2stay.Chat.Conversation;
import com.w2s.orhan.where2stay.R;

import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {


    private TextView ID;
    private ImageView imageView;
    private TextView title;
    private TextView cost;
    private TextView owner;
    private Button message;
    public SharedPreferences sharedPreferences;
    public DatabaseReference mDatabaseRef;
    String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        ID = findViewById(R.id.dtl_advertID);
        imageView = findViewById(R.id.dtl_image);
        title = findViewById(R.id.dtl_başlık);
        cost = findViewById(R.id.dtl_fiyat);
        owner = findViewById(R.id.dtl_ilanSahibi);
        message = findViewById(R.id.dtl_btn_mesaj);


        final Intent intent = getIntent();

        final String s = intent.getStringExtra("advertID");
        ID.setText(s);


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Posts").child("kiralık");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if (ds.getKey().equals(s)){
                        HashMap<String, String> hashMap = (HashMap<String, String>) ds.getValue();
                        title.setText(hashMap.get("title"));
                        cost.setText(hashMap.get("cost"));
                        owner.setText(hashMap.get("user_id"));
                        user_id = hashMap.get("user_id");
                        Picasso.with(getApplicationContext()).load(hashMap.get("downloadurl")).into(imageView);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sharedPreferences.getBoolean("isLogin", false)){
                    Intent intent = new Intent(DetailedActivity.this, Conversation.class);
                    intent.putExtra("user_id", user_id);
                    startActivity(intent);
                } else {
                    Toast.makeText(DetailedActivity.this, "Please Log In.", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
