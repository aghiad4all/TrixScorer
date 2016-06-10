package com.example.aghiad.trixscorer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        BackButtonListener();
    }

    public void BackButtonListener(){
        back=(Button)findViewById(R.id.button2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aintent = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(aintent);
                finish();
            }
        });
    }
}
