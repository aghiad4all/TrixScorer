package com.example.aghiad.trixscorer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Process;

import java.util.ArrayList;
import java.util.Collections;

public class Final extends AppCompatActivity {

    public String player1=null;
    public String player2=null;
    public String player3=null;
    public String player4=null;

    public String score1=null;
    public String score2=null;
    public String score3=null;
    public String score4=null;

    public TextView p1_score;
    public TextView p2_score;
    public TextView p3_score;
    public TextView p4_score;
    public TextView name1;
    public TextView name2;
    public TextView name3;
    public TextView name4;

    Button restart;
    Button exit;

    ArrayList<Integer> scores = new ArrayList<Integer>();
    ArrayList<String> orderedNames= new ArrayList<String>();

    boolean name1Flag=true;
    boolean name2Flag=true;
    boolean name3Flag=true;
    boolean name4Flag=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        //***Intent
        Intent finalintent =getIntent();
        player1=finalintent.getStringExtra("pname1");
        player2=finalintent.getStringExtra("pname2");
        player3=finalintent.getStringExtra("pname3");
        player4=finalintent.getStringExtra("pname4");
        score1=finalintent.getStringExtra("pscore1");
        score2=finalintent.getStringExtra("pscore2");
        score3=finalintent.getStringExtra("pscore3");
        score4=finalintent.getStringExtra("pscore4");

        //**************************************************
        //***id elements
        name1=(TextView)findViewById(R.id.textView6);
        name2=(TextView)findViewById(R.id.textView7);
        name3=(TextView)findViewById(R.id.textView8);
        name4=(TextView)findViewById(R.id.textView9);
        p1_score=(TextView)findViewById(R.id.textView10);
        p2_score=(TextView)findViewById(R.id.textView11);
        p3_score=(TextView)findViewById(R.id.textView12);
        p4_score=(TextView)findViewById(R.id.textView13);
        restart=(Button)findViewById(R.id.button5);
        exit=(Button)findViewById(R.id.button6);
        //**************************************************

        scores.add(Integer.parseInt(score1));
        scores.add(Integer.parseInt(score2));
        scores.add(Integer.parseInt(score3));
        scores.add(Integer.parseInt(score4));

        Collections.sort(scores);
        Collections.reverse(scores);
        for(int i=0; i<scores.size(); i++){
            if((scores.get(i)==Integer.parseInt(score1)) && name1Flag){
                orderedNames.add(player1);
                name1Flag=false;
            }
            if((scores.get(i)==Integer.parseInt(score2)) && name2Flag){
                orderedNames.add(player2);
                name2Flag=false;
            }
            if((scores.get(i)==Integer.parseInt(score3)) && name3Flag){
                orderedNames.add(player3);
                name3Flag=false;
            }
            if((scores.get(i)==Integer.parseInt(score4)) && name4Flag){
                orderedNames.add(player4);
                name4Flag=false;
            }
        }

        name1.setText(orderedNames.get(0));
        p1_score.setText(scores.get(0).toString());
        name2.setText(orderedNames.get(1));
        p2_score.setText(scores.get(1).toString());
        name3.setText(orderedNames.get(2));
        p3_score.setText(scores.get(2).toString());
        name4.setText(orderedNames.get(3));
        p4_score.setText(scores.get(3).toString());
        //**************************************************
        final MediaPlayer mpvictory = MediaPlayer.create(this, R.raw.victory);
        mpvictory.start();
        OnClickRestartButtonListner();
        OnClickExitButtonListner();
    }

    public void OnClickRestartButtonListner(){
        Button restart=(Button)findViewById(R.id.button5);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
    }

    public void OnClickExitButtonListner(){
        Button exit=(Button)findViewById(R.id.button6);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }
}
