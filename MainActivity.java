package com.example.go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[10][10];

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    private int indexP1x;
    private int indexP1y;
    private int indexP2x;
    private int indexP2y;

    private ArrayList<BoardIndex> boardIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SnakesandLadders SNL = new SnakesandLadders();

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        //Assign all ID's to 2D array
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                String btnID = "btn_" + i + j;
                int resID = getResources().getIdentifier(btnID,"id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        //swap buttns to mimic snl board
        for(int j = 1; j < 10; j+=2){
            for(int i = 0; i < 10 / 2; i++) {
                Button temp = buttons[j][i];
                buttons[j][i] = buttons[j][buttons[j].length - i - 1];
                buttons[j][buttons[j].length - i - 1] = temp;
            }
        }
        //Swap each row
        for(int i = 0; i < 5; i++) {
            int index = 9-i;
            for(int j = 0; j < 10; j++) {
                Button temp = buttons[i][j];
                buttons[i][j] = buttons[index][j];
                buttons[index][j] = temp;
            }
        }

        //reverse all rows again
        for(int j = 0; j < 10; j++){
            for(int i = 0; i < 10 / 2; i++) {
                Button temp = buttons[j][i];
                buttons[j][i] = buttons[j][buttons[j].length - i - 1];
                buttons[j][buttons[j].length - i - 1] = temp;
            }
        }
        //call convert method
        assign1Dto2D();

        //roll button
        Button btnRoll = findViewById(R.id.button_roll);
        btnRoll.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int dice1 = (int) (Math.random() * (6-1)) + 1;
               int dice2 = (int) (Math.random() * (6-1)) + 1;
               boolean P1;

               P1 = SNL.getTurn();
               if(P1) {
                   buttons
                   String msg = (SNL.play(dice1, dice2));
                   textViewPlayer1.setText(msg);
               }
               else {
                   String msg = (SNL.play(dice1, dice2));
                   textViewPlayer2.setText(msg);
               }
           }
        });
    }

    //assign event handler to each button upon roll
    @Override
    public void onClick(View v) {
        //change up later
        if(!((Button)v).getText().toString().equals("")) {
            return;
        }
    }
    public void assign1Dto2D() {
        boardIndex = new ArrayList<>();
        for(int i = 9; i >= 0; i--) {
            if(!(i%2==0)) {
                for(int j = 9; j >= 0; j--) {
                    boardIndex.add(new BoardIndex(i,j));
                }
            }
            else {
                for (int j = 0; j < 10; j++) {
                    boardIndex.add(new BoardIndex(i,j));
                }
            }
        }
    }
}
