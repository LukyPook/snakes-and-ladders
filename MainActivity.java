package com.example.go;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[10][10];

    private boolean P1 = true;

    private TextView textViewPlayer1;
    private TextView textViewplayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewplayer2 = findViewById(R.id.text_view_p2);

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                String btnID = "button_" + i + j;
                int resID = getResources().getIdentifier(btnID,"id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        //Swap first and last values in the 2D board to mimic snakes and ladders
        for(int i = 0; i < 10;  i++) {
            for(int j = 0; j < 10; j++) {
                Button temp = buttons[i][j];
                buttons[i][j] = buttons[9-i][9-j];
                buttons[9-i][9-j] = temp;
            }
        }

        //reset button
        Button btnReset = findViewById(R.id.button_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //do later
           }
        });
    }

    @Override
    public void onClick(View v) {
        //change up later
        if(!((Button)v).getText().toString().equals("")) {
            return;
        }
        if(P1) {
            ((Button)v).setText("Black");
        }
        else {
            ((Button)v).setText("White");
        }
    }
}
