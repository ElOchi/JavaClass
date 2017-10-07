package com.example.dantes.guessinggame;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtGuess;
    private Button btnGuess;
    private TextView lblOutput;

    private int theNumber;

    public void checkGuess() {

        //get the number the user entered
        String theirNumber = txtGuess.getText().toString();
        String message = "";

        try {

            int guess = Integer.parseInt(theirNumber);

            if (guess > theNumber) { //too high
                message = guess + " was too high. Guess again.";
                lblOutput.setText(message);

            }
            else if (guess < theNumber) { //too low
                message = guess + " was too low. Guess again.";
                lblOutput.setText(message);
            }
            else { //correct
                message = guess + " was the righ number. You win! Play again!";
                lblOutput.setText(message);
                newGame();
            }
        }
        catch (Exception ex) {

            message = "Please enter a whole number above.";
            lblOutput.setText(message);
        }
        finally { // highlight the txtGuess text field
            txtGuess.requestFocus();
            txtGuess.selectAll();
        }
    }

    private void newGame(){

        theNumber = (int)(Math.random() * 100 + 1);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtGuess = (EditText) findViewById(R.id.txtGuess);
        btnGuess = (Button) findViewById(R.id.btnGuess);
        lblOutput = (TextView) findViewById(R.id.lblOutput);

        newGame();

        // set up the event listener for our guess buttton
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

         // set up the event listener for our input field
        txtGuess.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
             public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                checkGuess();
                return true;
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
