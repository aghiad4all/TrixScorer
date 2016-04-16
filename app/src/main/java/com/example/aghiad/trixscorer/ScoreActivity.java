package com.example.aghiad.trixscorer;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    //action id
    private static final int ID_KING     = 1;
    private static final int ID_DIAMONDS = 2;
    private static final int ID_QUEENS   = 3;
    private static final int ID_TREX     = 4;
    private static final int ID_LAT      = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        TextView name1=(TextView)findViewById(R.id.textView);
        TextView name2=(TextView)findViewById(R.id.textView2);
        TextView name3=(TextView)findViewById(R.id.textView3);
        TextView name4=(TextView)findViewById(R.id.textView4);

        Intent intent = getIntent();
        String player1=intent.getStringExtra("name1");
        String player2=intent.getStringExtra("name2");
        String player3=intent.getStringExtra("name3");
        String player4=intent.getStringExtra("name4");

        name1.setText(player1);
        name2.setText(player2);
        name3.setText(player3);
        name4.setText(player4);


        OnClickRestartButtonListner();
        OnClickAddButtonListner();



    }

    public void OnClickRestartButtonListner(){
        Button restart=(Button)findViewById(R.id.button3);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.pick_color)
                .setItems(R.array.colors_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                    }
                });
        return builder.create();
    }
    public void OnClickAddButtonListner(){
        Button add=(Button)findViewById(R.id.button2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
