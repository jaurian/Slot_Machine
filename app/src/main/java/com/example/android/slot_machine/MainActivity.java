package com.example.android.slot_machine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
private SeekBar seek;
private Button play;
private Button rules;
private TextView scorev;
private Drawable sleepy;
private Drawable exis;
private Drawable leafy;
private Handler handler;
private Drawable ball;
private ImageView rect1;
private ImageView rect2;
private ImageView rect3;
private Drawable[] a;
private Update update1;
private Update update2;
private Update update3;
private long speed;
private long time;
int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a=new Drawable[4];
        seek=findViewById(R.id.scale);
        handler=new Handler();
        score=0;
        play=findViewById(R.id.play);
        rules=findViewById(R.id.rules);
        scorev=findViewById(R.id.score);
        exis =getResources().getDrawable(R.drawable.existential_kitty);
        sleepy=getResources().getDrawable(R.drawable.sleepy_kitty);
        leafy = getResources().getDrawable(R.drawable.leafy_kitty);
        ball=getResources().getDrawable(R.drawable.ball);
        rect1=findViewById(R.id.rect1);
        rect2=findViewById(R.id.rect2);
        rect3=findViewById(R.id.rect3);
        a[0]=exis;
        a[1]=sleepy;
        a[2]=leafy;
        a[3]=ball;
        rect1.setImageDrawable(a[0]);
        rect2.setImageDrawable(a[1]);
        rect3.setImageDrawable(a[2]);





        seek.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        speed=seek.getProgress();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });





    }
 private class Update implements Runnable{
    ImageView view;
    int current;
     public  Update(ImageView v, int x) {
            view=v;
            current=x;

     }
        public void run(){
         ranTime();
         current++;
         view.setImageDrawable(a[current%4]);
         handler.postDelayed(this, time);
     }





 }

public void ranTime(){
        Random ran=new Random();
        int x=ran.nextInt(500)+100;
        time=x/(speed+1);
}

public void buttonPressed(View v) {
    if (play.getText() == "STOP") {
        handler.removeCallbacks(update1);
        handler.removeCallbacks(update2);
        handler.removeCallbacks(update3);
        play.setText("START");
        updateScore();
        Toast.makeText(this, "You won:" + scorev.getText() + "", Toast.LENGTH_LONG).show();

    } else {
        update1 = new Update(rect1, 0);
        update2 = new Update(rect2, 1);
        update3 = new Update(rect3, 2);
        handler.postDelayed(update1, 100);
        handler.postDelayed(update2, 200);
        handler.postDelayed(update3, 300);
        play.setText("STOP");

    }
}
    public void rulePressed(View v){
        Intent x = new Intent(this, rules.class);
        startActivity(x);
    }


public void updateScore(){
    if(rect1.getDrawable().equals(exis)&&rect2.getDrawable().equals(exis)&&rect3.getDrawable().equals(exis)){
        score+=200;
    }
    else{
        for(int i=0;i<a.length;i++){
        if(a[i].equals(leafy)){
            score+=10;
        }
        else if(a[i].equals(ball)){
            score+=20;
        }
        else if(a[i].equals(exis)){
            score+=50;
        }
    }}
    scorev.setText("Score: "+score+"");
}
@Override
protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt("Score", score);
}

protected void onRestoreInstanceState(Bundle savedInstanceState){
    savedInstanceState.getInt("Score");
}
}


