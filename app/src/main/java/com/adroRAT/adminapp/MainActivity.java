package com.adroRAT.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import static com.adroRAT.adminapp.FirebaseInitialize.MY_REF;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseInitialize.FirebaseInitialize();
   //     MY_REF.setValue("anfaas is great");
   //     MY_REF.getDatabase().getReference("love").setValue("yes");

    }
}
