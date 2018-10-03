package com.example.sercan.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int YELLOW = 293;
    private static final int RED = 924;
    int activePlayer = YELLOW;
    int moveCount = 0;
    boolean isGameActive = true;
    int gameState[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 0 && isGameActive) {
            counter.setTranslationY(-1000);
            moveCount++;
            System.out.println("moveCount: " + moveCount);
            if (activePlayer == RED) {
                counter.setImageResource(R.drawable.yellow);
                gameState[tappedCounter] = YELLOW;
                activePlayer = YELLOW;
            } else if (activePlayer == YELLOW){
                counter.setImageResource(R.drawable.red);
                gameState[tappedCounter] = RED;
                activePlayer = RED;
            }
            counter.animate().translationYBy(1000).rotation(360).setDuration(1000);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 0 && gameState[winningPosition[1]] != 0 && gameState[winningPosition[2]] != 0) {
                    if (activePlayer == YELLOW)
                        endGame("Yellow Won!");
                    else
                        endGame("Red Won!");
                }
            }
        }
        if (moveCount == 9) {
            endGame("Tie!");
        }
    }

    public void playAgain(View view){
        LinearLayout layout = findViewById(R.id.layoutPlayAgain);
        layout.setVisibility(View.INVISIBLE);
        moveCount = 0;
        for (int i = 0; i < gameState.length; i++)
            gameState[i] = 0;
        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++)
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        isGameActive = true;
    }

    public void endGame(String message) {
        isGameActive = false;
        TextView text = findViewById(R.id.textViewPlayAgain);
        LinearLayout layout = findViewById(R.id.layoutPlayAgain);
        layout.setVisibility(View.VISIBLE);
        text.setText(message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
