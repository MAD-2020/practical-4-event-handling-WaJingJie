package sg.edu.np.WhackAMole;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Whack-a-mole";
    CountDownTimer molerepeater;
    TextView txt;
    Integer scoreCount;
    String score;
    int i;
    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 8.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The functions readTimer() and placeMoleTimer() are to inform the user X seconds before starting and loading new mole.
        - Feel free to modify the function to suit your program.
    */
    private void readyTimer(){
        /*  HINT:
            The "Get Ready" Timer.
            Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
            Toast message -"Get Ready In X seconds"
            Log.v(TAG, "Ready CountDown Complete!");
            Toast message - "GO!"
            belongs here.
            This timer countdown from 10 seconds to 0 seconds and stops after "GO!" is shown.
         */
        Toast.makeText(getApplicationContext(),"Get ready in 10 seconds!", Toast.LENGTH_LONG).show();

        new CountDownTimer(10000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);

            }

            @Override
            public void onFinish() {
                Log.v(TAG, "Ready CountDown Complete!");
                placeMoleTimer();
                Toast.makeText(getApplicationContext(),"GO!", Toast.LENGTH_LONG).show();

            }
        }.start();
    }
    private void placeMoleTimer(){
        /* HINT:
           Creates new mole location each second.
           Log.v(TAG, "New Mole Location!");
           setNewMole();
           belongs here.
           This is an infinite countdown timer.
         */

        molerepeater = new CountDownTimer(1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v(TAG, "New Mole Location!");
                setNewMole();
            }

            @Override
            public void onFinish() {
                molerepeater.start();
            }
        }.start();
    }
    private static final int[] BUTTON_IDS = {
        /* HINT:
            Stores the 9 buttons IDs here for those who wishes to use array to create all 9 buttons.
            You may use if you wish to change or remove to suit your codes.*/
        R.id.button1,
        R.id.button2,
        R.id.button3,
        R.id.button4,
        R.id.button5,
        R.id.button6,
        R.id.button7,
        R.id.button8,
        R.id.button9,
    };
    private Button[] buttons = new Button[BUTTON_IDS.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*Hint:
            This starts the countdown timers one at a time and prepares the user.
            This also prepares the existing score brought over.
            It also prepares the button listeners to each button.
            You may wish to use the for loop to populate all 9 buttons with listeners.
         */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txt =findViewById(R.id.textView);
        Intent previousscore = getIntent();
        txt.setText(previousscore.getStringExtra("Score"));
        score = txt.getText().toString();

        //Log.v(TAG, "Current User Score: " + String.valueOf(advancedScore));


        for(final int id : BUTTON_IDS){
            /*  HINT:
            This creates a for loop to populate all 9 buttons with listeners.
            You may use if you wish to remove or change to suit your codes.
            */
            final Button button = (Button)findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doCheck(button);
                }
            });

        }
    }
    @Override
    protected void onStart(){
        super.onStart();
        readyTimer();
    }
    private void doCheck(Button checkButton)
    {
        /* Hint:
            Checks for hit or miss
            Log.v(TAG, "Hit, score added!");
            Log.v(TAG, "Missed, point deducted!");
            belongs here.
        */

        String buttonText = checkButton.getText().toString();
        int prevscore = Integer.parseInt(txt.getText().toString());
        if(buttonText =="*"){
            Log.v(TAG, "Hit, score added!");
            scoreCount = prevscore + 1;
            prevscore = scoreCount;
            txt.setText(Integer.toString(scoreCount));
        }
        else if(buttonText =="O"){
            Log.v(TAG, "Missed, point deducted!");
            scoreCount -=  1;
            txt.setText(Integer.toString(scoreCount));
        }
        setNewMole();

    }

    public void setNewMole()
    {
        /* Hint:
            Clears the previous mole location and gets a new random location of the next mole location.
            Sets the new location of the mole.
         */
        Random ran = new Random();
        int randomLocation = ran.nextInt(9);
        for(i = 0; i<BUTTON_IDS.length;i++){
            if(i==randomLocation){
                buttons[i] = (Button)findViewById(BUTTON_IDS[i]);
                buttons[i].setText("*");
            }
            else{
                buttons[i] = (Button)findViewById(BUTTON_IDS[i]);
                buttons[i].setText("O");
            }
        }
    }
}

