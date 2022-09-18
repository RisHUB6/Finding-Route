package com.rishabh.route;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText start, end;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.start);
        end = findViewById(R.id.end);
        search = findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String initial = start.getText().toString().trim();
                String ending = end.getText().toString().trim();

                //checking condition...

                if (initial.isEmpty() && ending.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter The Values", Toast.LENGTH_SHORT).show();
                }
                else{
                    DisplayTrack(initial , ending);
                }
            }
        });
    }

    private void DisplayTrack(String initial , String ending) {
        //If device doesn't have the maps
        try {
            Uri uri = Uri.parse("https://www.google.com.in/maps/dir/"+ initial + "/" + ending);
            //initialize intent with action view

            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            //set pkg
            intent.setPackage("com.google.android.apps.maps");
            //set flag

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);

        }catch(ActivityNotFoundException e){
            //When google maps is not install.
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            //initialize Uri with action view

            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            //Set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start Activity.
            startActivity(intent);
        }
    }
}