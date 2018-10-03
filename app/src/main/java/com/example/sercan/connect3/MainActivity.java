package com.example.sercan.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String activePlayer = "Yellow";

    boolean isGameActive = true;

    int gameState[] = {1, 1, 1, 1, 1, 1, 1, 1, 1};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 1 && isGameActive) {

            gameState[tappedCounter] = 0;

            counter.setTranslationY(-1000);

            if (activePlayer == "Red") {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = "Yellow";
            } else if (activePlayer == "Yellow"){
                counter.setImageResource(R.drawable.red);
                activePlayer = "Red";
            }

            counter.animate().translationYBy(1000).rotation(360).setDuration(1000);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 1) {
                    System.out.println("END... " + gameState[winningPosition[0]]);

                    isGameActive = false;

                    LinearLayout layout = findViewById(R.id.layoutPlayAgain);
                    layout.setVisibility(View.VISIBLE);

                    TextView text = findViewById(R.id.textViewPlayAgain);

                        text.setText(activePlayer + " Won!");

                }
            }
        }
    }

    public void playAgain(View view){
        LinearLayout layout = findViewById(R.id.layoutPlayAgain);
        layout.setVisibility(View.INVISIBLE);

        for (int i = 0; i < gameState.length; i++)
            gameState[i] = 1;

        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++)
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        isGameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
