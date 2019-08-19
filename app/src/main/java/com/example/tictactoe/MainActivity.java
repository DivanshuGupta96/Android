package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean currentlyPlaying=true;
    int [][] winningSlots={ {0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int [] allSlots={2,2,2,2,2,2,2,2,2}; // 2 represents unPlayed
    int currentPlayer=0; //0 represents red

    public void popIn(View view){
        ImageView imgView= (ImageView) view;
        int selectedSlot= Integer.parseInt(imgView.getTag().toString());
        //Log.i("Divanshu", String.valueOf(selectedSlot));
        if(allSlots[selectedSlot]==2 && currentlyPlaying){
            allSlots[selectedSlot]=currentPlayer;
            imgView.setTranslationY(-1000f);
            if(currentPlayer==0){
                imgView.setImageResource(R.drawable.red);
                currentPlayer=1;
            }else{
                imgView.setImageResource(R.drawable.yellow);
                currentPlayer=0;
            }

            imgView.animate().translationYBy(1000f).rotationBy(360).setDuration(300);
        }
        for(int [] checkWinnings: winningSlots){
            if(allSlots[checkWinnings[0]]==allSlots[checkWinnings[1]]
                    && allSlots[checkWinnings[1]]==allSlots[checkWinnings[2]] 
                    && allSlots[checkWinnings[0]]!=2){
                TextView winningTextView= findViewById(R.id.winningText);
                if(allSlots[checkWinnings[0]]==0){
                    winningTextView.setText("Red has won");
                }else{
                    winningTextView.setText("Yellow has won");
                }
                LinearLayout linearLayout= findViewById(R.id.playAgain);
                linearLayout.setVisibility(View.VISIBLE);
                currentlyPlaying=false;
            }else{
                boolean gameIsOver=true;
                for( int slotValue:allSlots){
                    if(slotValue==2){
                        gameIsOver=false;
                    }
                }
                if(gameIsOver){
                    TextView winningTextView= findViewById(R.id.winningText);
                    winningTextView.setText("It's a draw");
                    LinearLayout linearLayout= findViewById(R.id.playAgain);
                    linearLayout.setVisibility(View.VISIBLE);
                    currentlyPlaying=false;
                }
            }
        }

    }
    public void playAgain(View view){
        currentlyPlaying=true;
        LinearLayout linearLayout= findViewById(R.id.playAgain);
        linearLayout.setVisibility(View.INVISIBLE);
        currentPlayer=0;
        for(int i=0; i<allSlots.length;i++){
            allSlots[i]=2;
        }
        GridLayout gridLayout= findViewById(R.id.gridLayout);
        for(int i=0; i<9;i++){
            ((ImageView)(gridLayout.getChildAt(i))).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
