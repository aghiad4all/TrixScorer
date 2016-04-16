package com.example.aghiad.trixscorer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

   private static Button button_start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClickButtonListner();
    }

    public void OnClickButtonListner(){
        final EditText name1=(EditText)findViewById (R.id.editText);
        final EditText name2=(EditText)findViewById (R.id.editText2);
        final EditText name3=(EditText)findViewById (R.id.editText3);
        final EditText name4=(EditText)findViewById (R.id.editText4);
        button_start=(Button)findViewById(R.id.button);
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.example.aghiad.trixscorer.ScoreActivity");
                intent.putExtra("name1",name1.getText().toString());
                intent.putExtra("name2",name2.getText().toString());
                intent.putExtra("name3",name3.getText().toString());
                intent.putExtra("name4",name4.getText().toString());
                startActivity(intent);
            }
        });
    }
}
