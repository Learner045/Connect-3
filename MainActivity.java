package com.example.volleyapp.com.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //yellow
    int activePlayer=0;
    Boolean activeGame=true;

    int winningPosition[][]={ {0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    int drawchecker=0;

    //unplayed slot
    int gameState[]={2,2,2,2,2,2,2,2,2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view){

        ImageView counter=(ImageView)view;

        int tappedCounter=Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && activeGame) {

            drawchecker++;
            gameState[tappedCounter]=activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }


            counter.animate().translationYBy(1000f).setDuration(500);

            for(int[]winningPosition1:winningPosition){

                if(gameState[winningPosition1[0]]==gameState[winningPosition1[1]] && gameState[winningPosition1[1]]==gameState[winningPosition1[2]] && gameState[winningPosition1[0]]!=2)
                {
                    //someone has won
                    activeGame=false;
                    String playerwon="red";

                    if(gameState[winningPosition1[0]]==0){

                        playerwon="yellow";
                    }


                    TextView winner=(TextView)findViewById(R.id.winner);
                    winner.setText(playerwon+" has won yeahhh");
                    LinearLayout linearLayout=(LinearLayout)findViewById(R.id.playAgainLayout);
                    linearLayout.setVisibility(View.VISIBLE);


                }else{

                    if(drawchecker>8){

                        TextView winner=(TextView)findViewById(R.id.winner);
                        winner.setText("DRAW");
                        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.playAgainLayout);
                        linearLayout.setVisibility(View.VISIBLE);

                    }


                }


            }


        }


    }

    public void playAgain(View view){

        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.playAgainLayout);
        linearLayout.setVisibility(View.INVISIBLE);
        drawchecker=0;

        activeGame=true;
        activePlayer=0;

        for(int i=0;i<gameState.length;i++)
            gameState[i]=2;

        GridLayout gridLayout=(GridLayout)findViewById(R.id.GridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){

            ( (ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }

    }


}
