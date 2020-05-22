package sg.edu.np.WhackAMole;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button buttonLeft;
    Button buttonMiddle;
    Button buttonRight;
    Integer scoreCount;
    Integer correctbtn;

    private static final String TAG = "Whack-a-mole";
    TextView score;
    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The function doCheck() also decides if the user qualifies for the advance level and triggers for a dialog box to ask for user to decide.
        - The function nextLevelQuery() builds the dialog box and shows. It also triggers the nextLevel() if user selects Yes or return to normal state if user select No.
        - The function nextLevel() launches the new advanced page.
        - Feel free to modify the function to suit your program.
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = (TextView) findViewById(R.id.score);
        buttonLeft = findViewById(R.id.lefthole);
        buttonMiddle = findViewById(R.id.centerhole);
        buttonRight = findViewById(R.id.righthole);
        scoreCount = 0;
        correctbtn = 0;
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Button 1 Clicked");
                if (buttonLeft.getText() == "*") {
                    scoreCount = scoreCount + 1;
                    correctbtn = correctbtn + 1;
                    doCheck(buttonLeft);
                } else {
                    scoreCount = scoreCount - 1;

                }
                score.setText(Integer.toString(scoreCount));
                onStart();
                doCheck(buttonLeft);
            }
        });
        buttonMiddle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Button 2 Clicked");
                if (buttonMiddle.getText() == "*") {
                    scoreCount = scoreCount + 1;
                    correctbtn = correctbtn + 1;
                    doCheck(buttonMiddle);
                } else {
                    scoreCount = scoreCount - 1;
                }
                score.setText(Integer.toString(scoreCount));
                onStart();
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Button 3 Clicked");
                if (buttonRight.getText() == "*") {
                    scoreCount = scoreCount + 1;
                    correctbtn = correctbtn + 1;
                    doCheck(buttonRight);
                } else {
                    scoreCount = scoreCount - 1;

                }
                score.setText(Integer.toString(scoreCount));
                onStart();
            }
        });


        Log.v(TAG, "Finished Pre-Initialisation!");
    }
    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        Log.v(TAG, "Starting GUI!");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAG, "Paused Whack-A-Mole!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAG, "Stopped Whack-A-Mole!");
        finish();
    }

    private void doCheck(Button checkButton) {
        /* Checks for hit or miss and if user qualify for advanced page.
            Triggers nextLevelQuery().
         */
        if (correctbtn%10 == 0){
            nextLevelQuery();
        }
    }

    private void nextLevelQuery(){
        /*
        Builds dialog box here.
        Log.v(TAG, "User accepts!");
        Log.v(TAG, "User decline!");
        Log.v(TAG, "Advance option given to user!");
        belongs here*/
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setTitle("Warning! Insane Whack-A-Mole Incoming!");
        alertbox.setMessage("Would you like to advance to adcanced mode?");
        alertbox.setCancelable(true);
        alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nextLevel();
            }
        });
        alertbox.create().show();
    }

    private void nextLevel(){
        /* Launch advanced page */
        Intent activity_main2 = new Intent(MainActivity.this, Main2Activity.class);
        activity_main2.putExtra("Score", Integer.toString(scoreCount));
        startActivity(activity_main2);
    }

    private void setNewMole() {
        Random ran = new Random();
        int randomLocation = ran.nextInt(3);
        if (randomLocation == 0) {
            buttonLeft.setText("*");
            buttonMiddle.setText("O");
            buttonRight.setText("O");
        } else if (randomLocation == 1) {
            buttonLeft.setText("O");
            buttonMiddle.setText("*");
            buttonRight.setText("O");
        } else {
            buttonLeft.setText("O");
            buttonMiddle.setText("O");
            buttonRight.setText("*");
        }
    }
}