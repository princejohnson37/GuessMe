package com.example.guessme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class StartActivity extends AppCompatActivity {
    public EditText number;
    public TextView clue;
    public Button btnOk;
    int guess = 0;
    int gotIt=0;
    int random_number = genRandomNumber();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //System.out.println("It worked");
        number = findViewById(R.id.editTextNumber);
        clue = findViewById(R.id.txtClue);
        btnOk = findViewById(R.id.btnOk);
        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnOk.setEnabled(!s.toString().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        guess = 1;
        gotIt = 0;
    }
    public int genRandomNumber(){
        Random random = new Random();
        return random.nextInt(21);
    }

    public void btnOkClicked(View view) {

        int guessed_number = Integer.parseInt(number.getText().toString());

        if(guess < 5 && gotIt != 1) {
            if (random_number == guessed_number) {
                Intent i = new Intent(this, won.class);
                gotIt = 1;
                startActivity(i);
            } else if (random_number > guessed_number) {
                clue.setText("guess higher");
                guess++;

            } else {
                clue.setText("guess lower");
                guess++;
            }
        }
        else if(guess>=5){
            Intent i = new Intent(this,loss.class);
            startActivity(i);
        }
    }

}