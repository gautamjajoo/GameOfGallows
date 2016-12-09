package com.example.sridhar123.hangman;

import android.content.res.Resources;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private String[] words;
    private Random rand;
    private String currWord;
    private LinearLayout wordLayout;
    private TextView[] charViews;
    private GridView gridView;
    private LetterAdapter adapter;
    private String[] letters ={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private ImageView[] bodyParts;
    private Integer numParts=6;
    private Integer currPart;
    private Integer numCorr;
    private Integer numChars;
    private Integer current_count=0;
    private Character current_letter;
    private Integer j=0;
    private Integer no_letter=0;
    private static final String TAG="GameActivity";
    private Integer[] flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Resources resource = getResources();
        words = resource.getStringArray(R.array.words);

        flag = new Integer[26];
        for(int i=0;i<26;i++)
            flag[i]=1;
        rand = new Random();
        currWord="";

        wordLayout = (LinearLayout) findViewById(R.id.word);

        gridView = (GridView) findViewById(R.id.gridview_letters);
        adapter = new LetterAdapter(this , letters);
        gridView.setAdapter(adapter);




        bodyParts = new ImageView[numParts];
        bodyParts[0] = (ImageView)findViewById(R.id.head);
        bodyParts[1] = (ImageView)findViewById(R.id.body);
        bodyParts[2] = (ImageView)findViewById(R.id.left_arm);
        bodyParts[3] = (ImageView)findViewById(R.id.right_arm);
        bodyParts[4] = (ImageView)findViewById(R.id.left_leg);
        bodyParts[5] = (ImageView)findViewById(R.id.right_leg);
        for(int p = 0; p < numParts; p++) {
            Log.i(TAG,"Invisible");
            bodyParts[p].setVisibility(View.INVISIBLE);
        }

        playGame();
    }

    private void playGame(){

        currPart=0;
        numChars=currWord.length();
        numCorr=0;
        String newWord = words[rand.nextInt(words.length)];
        while(newWord.equals(currWord))    //.equals is used to comapare two strings
            newWord = words[rand.nextInt(words.length)];
        currWord = newWord;

        charViews = new TextView[currWord.length()];
        wordLayout.removeAllViews();

        for(int i=0;i<currWord.length();i++){
            Log.i("abc","Inside");
            charViews[i] = new TextView(this);
            charViews[i].setText("" +currWord.charAt(i));
            charViews[i].setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
            charViews[i].setGravity(Gravity.CENTER);
            charViews[i].setTextColor(Color.WHITE);
            charViews[i].setBackgroundResource(R.drawable.letter_bg);
            wordLayout.addView(charViews[i]);

        }


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Log.i(TAG, "Inside onItemCLick");

                if (flag[position] == 1) {
                    view.setEnabled(false);
                    view.setClickable(false);
                    view.setBackgroundResource(R.drawable.letter_up);


                    current_letter = letters[position].charAt(0);
                    for (int i = 0; i < currWord.length(); i++) {
                        if (currWord.charAt(i) == current_letter) {
                            charViews[i].setTextColor(Color.BLACK);
                            current_count++;
                            if (current_count == currWord.length()) {
                                Toast.makeText(GameActivity.this, "Your message", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Log.i("GameActivity", "Inside Else " + no_letter);
                            no_letter++;
                        }

                    }

                    if (no_letter == currWord.length()) {
                        Log.i("GameActivity", "Inside Body Parts " + no_letter);
                        bodyParts[j].setVisibility(View.VISIBLE);
                        j++;

                    }

                    if (j == 6) {
                        Toast.makeText(GameActivity.this, "Game Over Mate", Toast.LENGTH_LONG).show();
                    }

                    no_letter = 0;

                    flag[position]=0;
                }

                         

            }


        });


    }


    }
