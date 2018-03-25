package com.w2s.orhan.where2stay.Sign;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.w2s.orhan.where2stay.R;


public class SignUpFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    TextView textEmail;
    TextView textPassword;
    Button btnSignIn;
    View view;
    SignActivity signActivity;
    TabLayout tabLayout;
    TabLayout.Tab tab;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        signActivity = (SignActivity) getActivity();
        mAuth = signActivity.mAuth;
        mAuthListener = signActivity.mAuthListener;

        view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        textEmail = view.findViewById(R.id.txt_Email);
        textPassword = view.findViewById(R.id.txt_Password);
        btnSignIn = view.findViewById(R.id.btn_signIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        tabLayout = signActivity.findViewById(R.id.tabs);
        tab = tabLayout.getTabAt(0);

        return view;
    }

    public void signUp() {
        mAuth.createUserWithEmailAndPassword(textEmail.getText().toString(), textPassword.getText().toString())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getActivity(), "kayıt başarılı", Toast.LENGTH_SHORT).show();
                        tab.select();
                    }
                }).addOnFailureListener(getActivity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "kayıt başarısız", Toast.LENGTH_SHORT).show();
                    }
        });
    }

}
