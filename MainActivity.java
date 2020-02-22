package com.example.go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[10][10];

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

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
        
        //roll button
        Button btnRoll = findViewById(R.id.button_roll);
        btnRoll.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int dice1 = (int) (Math.random() * (6-1)) + 1;
               int dice2 = (int) (Math.random() * (6-1)) + 1;
               boolean P1;
               
               //set visibility test
//               buttons[0][0].setVisibility(View.VISIBLE);
//               buttons[0][1].setVisibility(View.VISIBLE);
//               buttons[1][7].setVisibility(View.VISIBLE);
//               buttons[2][5].setVisibility(View.VISIBLE);
//               buttons[3][8].setVisibility(View.VISIBLE);
//               buttons[5][7].setVisibility(View.VISIBLE);
//               buttons[4][7].setVisibility(View.VISIBLE);
//               buttons[8][3].setVisibility(View.VISIBLE);
//               buttons[9][7].setVisibility(View.VISIBLE);

               
               P1 = SNL.getTurn();
               if(P1) {
                   textViewPlayer1.setText(SNL.play(dice1, dice2));
               }
               else {
                   textViewPlayer2.setText(SNL.play(dice1, dice2));
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
}
