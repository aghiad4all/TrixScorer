package com.example.aghiad.trixscorer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    //action id
    public String game=null;
    public Button choose;
    public EditText p1_score;
    public EditText p2_score;
    public EditText p3_score;
    public EditText p4_score;
    public TextView name1;
    public TextView name2;
    public TextView name3;
    public TextView name4;
    public TextView subGameString;

    ArrayList<String> subGame = new ArrayList<String>();
    ArrayList<Integer> p1_history = new ArrayList<Integer>();
    ArrayList<Integer> p2_history = new ArrayList<Integer>();
    ArrayList<Integer> p3_history = new ArrayList<Integer>();
    ArrayList<Integer> p4_history = new ArrayList<Integer>();
    Context context;
    public int pgameCounter=0;
    public String player1=null;
    public String player2=null;
    public String player3=null;
    public String player4=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        name1=(TextView)findViewById(R.id.textView);
        name2=(TextView)findViewById(R.id.textView2);
        name3=(TextView)findViewById(R.id.textView3);
        name4=(TextView)findViewById(R.id.textView4);
        subGameString=(TextView)findViewById(R.id.textView5);

        p1_score=(EditText)findViewById(R.id.editText5);
        p2_score=(EditText)findViewById(R.id.editText6);
        p3_score=(EditText)findViewById(R.id.editText7);
        p4_score=(EditText)findViewById(R.id.editText8);
        p1_score.setText(Integer.toString(0));
        p2_score.setText(Integer.toString(0));
        p3_score.setText(Integer.toString(0));
        p4_score.setText(Integer.toString(0));

        p1_history.add(0);
        p2_history.add(0);
        p3_history.add(0);
        p4_history.add(0);
        //System.out.println(Integer.toString(p1_history.size()));
        //System.out.println(Integer.toString(p1_history.get(p1_history.size())));
        Intent intent = getIntent();
        player1=intent.getStringExtra("name1");
        player2=intent.getStringExtra("name2");
        player3=intent.getStringExtra("name3");
        player4=intent.getStringExtra("name4");

        name1.setText(player1);
        name2.setText(player2);
        name3.setText(player3);
        name4.setText(player4);

        name1.setTextColor(Color.YELLOW);
        p1_score.setTextColor(Color.YELLOW);
        //choose = (Button)findViewById(R.id.button2);
       // choose.setOnClickListener(this);
        context=this;
        OnClickRestartButtonListner();
        OnClickAddButtonListner();
        OnClickUndoButtonListner();


    }

    public void OnClickRestartButtonListner(){
        Button restart=(Button)findViewById(R.id.button3);
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

    public void OnClickUndoButtonListner(){
        Button undo=(Button)findViewById(R.id.button4);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p1_history.size()>1) {
                    p1_history.remove(p1_history.size() - 1);
                    p2_history.remove(p2_history.size() - 1);
                    p3_history.remove(p3_history.size() - 1);
                    p4_history.remove(p4_history.size() - 1);
                    if (subGame.size()>=1) {
                        subGame.remove(subGame.size() - 1);
                        String s = createSubGameString();
                        subGameString.setText(s);
                    }
                    p1_score.setText(Integer.toString(p1_history.get(p1_history.size() - 1)));
                    p2_score.setText(Integer.toString(p2_history.get(p2_history.size() - 1)));
                    p3_score.setText(Integer.toString(p3_history.get(p3_history.size() - 1)));
                    p4_score.setText(Integer.toString(p4_history.get(p4_history.size() - 1)));

                    pgameCounter=p1_history.size()-1;

                    if (pgameCounter < 5) {
                        name1.setTextColor(Color.YELLOW);
                        p1_score.setTextColor(Color.YELLOW);
                        name2.setTextColor(Color.WHITE);
                        p2_score.setTextColor(Color.WHITE);
                    } else if (pgameCounter >= 5 && pgameCounter < 10) {
                        name2.setTextColor(Color.YELLOW);
                        p2_score.setTextColor(Color.YELLOW);
                        name3.setTextColor(Color.WHITE);
                        p3_score.setTextColor(Color.WHITE);
                    } else if (pgameCounter >= 10 && pgameCounter < 15) {
                        name3.setTextColor(Color.YELLOW);
                        p3_score.setTextColor(Color.YELLOW);
                        name4.setTextColor(Color.WHITE);
                        p4_score.setTextColor(Color.WHITE);
                    } else if (pgameCounter >= 15) {
                        name4.setTextColor(Color.YELLOW);
                        p4_score.setTextColor(Color.YELLOW);
                    }
                }
            }
        });
    }

    public void OnClickAddButtonListner(){
        Button add=(Button)findViewById(R.id.button2);
        final MediaPlayer mpfail = MediaPlayer.create(this, R.raw.fail);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {
                        "L", "T", "K", "Q", "D"
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Make your selection");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        //mDoneButton.setText(items[item]);

                        game = items[item].toString();
                        System.out.println(items[item]);

                        if (subGame.size() == 5) {
                            subGame.clear();
                        }

                        if ((!subGame.contains(game))) {
                            showDialog(0);
                        } else {
                            mpfail.start();
                            String value = "Game Already Chosen ...";
                            Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                        }



                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }

        });
    }

    public void applyGame (String game, int[] t){
        int player1=Integer.parseInt(p1_score.getText().toString());
        int player2=Integer.parseInt(p2_score.getText().toString());
        int player3=Integer.parseInt(p3_score.getText().toString());
        int player4=Integer.parseInt(p4_score.getText().toString());
        int[] p=new int[]{player1,player2,player3,player4};

            switch (game) {
                case "L":
                    for (int i = 0; i < 4; i++) {
                        p[i] = p[i] - (t[i] * 15);
                    }
                    break;
                case "Q":
                    for (int i = 0; i < 4; i++) {
                        p[i] = p[i] - (t[i] * 25);
                    }
                    break;
                case "K":
                    for (int i = 0; i < 4; i++) {
                        p[i] = p[i] - (t[i] * 75);
                    }
                    break;
                case "D":
                    for (int i = 0; i < 4; i++) {
                        p[i] = p[i] - (t[i] * 10);
                    }
                    break;
                case "T":
                    for (int i = 0; i < 4; i++) {
                        p[i] = p[i] + (t[i]);
                    }
                    break;
                default:
                    String value = "Error: No score changes ";
                    Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                    break;
            }

            p1_score.setText(Integer.toString(p[0]));
            p2_score.setText(Integer.toString(p[1]));
            p3_score.setText(Integer.toString(p[2]));
            p4_score.setText(Integer.toString(p[3]));

            p1_history.add(p[0]);
            p2_history.add(p[1]);
            p3_history.add(p[2]);
            p4_history.add(p[3]);
            pgameCounter=p1_history.size()-1;

            if (pgameCounter < 5) {
                name1.setTextColor(Color.YELLOW);
                p1_score.setTextColor(Color.YELLOW);

            } else if (pgameCounter >= 5 && pgameCounter < 10) {
                name1.setTextColor(Color.WHITE);
                p1_score.setTextColor(Color.WHITE);
                name2.setTextColor(Color.YELLOW);
                p2_score.setTextColor(Color.YELLOW);
            } else if (pgameCounter >= 10 && pgameCounter < 15) {
                name2.setTextColor(Color.WHITE);
                p2_score.setTextColor(Color.WHITE);
                name3.setTextColor(Color.YELLOW);
                p3_score.setTextColor(Color.YELLOW);
            } else if (pgameCounter >= 15) {
                name3.setTextColor(Color.WHITE);
                p3_score.setTextColor(Color.WHITE);
                name4.setTextColor(Color.YELLOW);
                p4_score.setTextColor(Color.YELLOW);
            }
        if(pgameCounter==20){
            Intent finalintent = new Intent("com.example.aghiad.trixscorer.Final");
            finalintent.putExtra("pname1",name1.getText().toString());
            finalintent.putExtra("pname2",name2.getText().toString());
            finalintent.putExtra("pname3",name3.getText().toString());
            finalintent.putExtra("pname4",name4.getText().toString());
            finalintent.putExtra("pscore1",p1_score.getText().toString());
            finalintent.putExtra("pscore2",p2_score.getText().toString());
            finalintent.putExtra("pscore3",p3_score.getText().toString());
            finalintent.putExtra("pscore4",p4_score.getText().toString());
            startActivity(finalintent);
            finish();


        }
    }

    protected Dialog onCreateDialog(int id)
    {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LinearLayout lila1= new LinearLayout(this);
        lila1.setOrientation(LinearLayout.VERTICAL);
        final EditText input = new EditText(this);
        final EditText input1 = new EditText(this);
        final EditText input2 = new EditText(this);
        final EditText input3 = new EditText(this);

        //***Number keyboard only
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input1.setInputType(InputType.TYPE_CLASS_NUMBER);
        input2.setInputType(InputType.TYPE_CLASS_NUMBER);
        input3.setInputType(InputType.TYPE_CLASS_NUMBER);

        final TextView p1=new TextView(this);
        p1.setText(player1 + ":");
        final TextView p2=new TextView(this);
        p2.setText(player2 + ":");
        final TextView p3=new TextView(this);
        p3.setText(player3 + ":");
        final TextView p4=new TextView(this);
        p4.setText(player4 + ":");
        lila1.addView(p1);
        lila1.addView(input);
        lila1.addView(p2);
        lila1.addView(input1);
        lila1.addView(p3);
        lila1.addView(input2);
        lila1.addView(p4);
        lila1.addView(input3);
        alert.setView(lila1);

        //alert.setIcon(R.drawable.);
        alert.setTitle("Fill the Scores:");
        final MediaPlayer mpsuccess = MediaPlayer.create(this, R.raw.success);
        final MediaPlayer mpfail = MediaPlayer.create(this, R.raw.fail);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String val1 = input.getText().toString().trim();
                String val2 = input1.getText().toString().trim();
                String val3 = input2.getText().toString().trim();
                String val4 = input3.getText().toString().trim();

                boolean validStrings = checkStrings(val1, val2, val3, val4);

                if (validStrings) {
                    int t1 = Integer.parseInt(input.getText().toString());
                    int t2 = Integer.parseInt(input1.getText().toString());
                    int t3 = Integer.parseInt(input2.getText().toString());
                    int t4 = Integer.parseInt(input3.getText().toString());
                    int[] t = new int[]{t1, t2, t3, t4};
                    boolean validInts = checkForNumbers(t);
                    if (validInts) {
                        applyGame(game, t);
                        input.setText("");
                        input1.setText("");
                        input2.setText("");
                        input3.setText("");
                        mpsuccess.start();
                        subGame.add(game);
                        String s=createSubGameString();
                        subGameString.setText(s);
                        String value = "Game Added Succesfully ! ...";
                        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                        if (pgameCounter==20){

                        }
                    } else {
                        mpfail.start();
                        String value = "Values Entered are invalid or incomplete...";
                        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mpfail.start();
                    String value = "Values Entered are invalid or incomplete...";
                    Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                }
            }
        });
        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        input.setText("");
                        input1.setText("");
                        input2.setText("");
                        input3.setText("");
                        dialog.cancel();
                    }
                });
        return alert.create();
    }

    boolean checkStrings (String p1, String p2, String p3, String p4){
        boolean valid=false;
        if((p1.isEmpty() || p1.length() == 0 || p1.equals("") || p1 == null)||(p2.isEmpty() || p2.length() == 0 || p2.equals("") || p2 == null) || (p3.isEmpty() || p3.length() == 0 || p3.equals("") || p3 == null)|| (p4.isEmpty() || p4.length() == 0 || p4.equals("") || p4 == null))
        {
            valid=false;
        }
        else
        {
            valid=true;
        }
        return valid;
    }

    boolean checkForNumbers (int[] t){
        boolean validInt=false;
        if(game.equals("L")){
            if(((t[0]+t[1]+t[2]+t[3])==13)&&(t[0]>=0)&&(t[0]<=13)&&(t[1]>=0)&&(t[1]<=13)&&(t[2]>=0)&&(t[2]<=13)&&(t[3]>=0)&&(t[3]<=13)){
                validInt=true;
            }
        }
        else if(game.equals("D")){
            if(((t[0]+t[1]+t[2]+t[3])==13)&&(t[0]>=0)&&(t[0]<=13)&&(t[1]>=0)&&(t[1]<=13)&&(t[2]>=0)&&(t[2]<=13)&&(t[3]>=0)&&(t[3]<=13)){
                validInt=true;
            }
        }
        else if(game.equals("Q")){
            if(((t[0]+t[1]+t[2]+t[3])==4)&&(t[0]>=0)&&(t[0]<=4)&&(t[1]>=0)&&(t[1]<=4)&&(t[2]>=0)&&(t[2]<=4)&&(t[3]>=0)&&(t[3]<=4)){
                validInt=true;
            }
        }
        else if(game.equals("K")){
            if(((t[0]+t[1]+t[2]+t[3])==1)&&(t[0]>=0)&&(t[0]<=1)&&(t[1]>=0)&&(t[1]<=1)&&(t[2]>=0)&&(t[2]<=1)&&(t[3]>=0)&&(t[3]<=1)){
                validInt=true;
            }
        }
        else if(game.equals("T")){
            if(((t[0]+t[1]+t[2]+t[3])==500)&&((t[0]==50)||(t[0]==100)||(t[0]==150)||(t[0]==200))&&((t[1]==50)||(t[1]==100)||(t[1]==150)||(t[1]==200))&&((t[2]==50)||(t[2]==100)||(t[2]==150)||(t[2]==200))&&((t[3]==50)||(t[3]==100)||(t[3]==150)||(t[3]==200))){
                validInt=true;
            }
        }
        return validInt;
    }

    String createSubGameString(){
        String s="";
        for (String st:subGame){
            s=s+st+" / ";
        }
        return s;
    }
}
