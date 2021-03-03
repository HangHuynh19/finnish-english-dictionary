package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import fi.hanghuynh.finnish_englishslangdictionary.db.AppDatabase;

public class MatchingActivity extends AppCompatActivity {

    private  AppDatabase db;
    private ArrayList<Integer> randNumbers;
    private ArrayList<String> finnWords, engWords, engWordsShuffled;
    private ArrayList<ImageView> finnCards, engCards;
    private ImageView finnTL, finnTR, finnML, finnMR, finnBL, finnBR;
    private ImageView engTL, engTR, engML, engMR, engBL, engBR;
    private TextView fTextTL, fTextTR, fTextML, fTextMR, fTextBL, fTextBR;
    private TextView eTextTL, eTextTR, eTextML, eTextMR, eTextBL, eTextBR;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);

        db = AppDatabase.getDbInstance(this.getApplicationContext());

        generateRandom();
        Log.d("test", "numbers: " + randNumbers);
        Log.d("test", "finnWords: " + finnWords);
        Log.d("test", "engWords: " + engWords);
        loadGameView();

        //Creating a duplicate of the english words array with a different order
        engWordsShuffled = engWords;
        Collections.shuffle(engWordsShuffled);
        Log.d("test", "engWords: " + engWords);
        setCardText();
    }

    //Generating the index of 6 random words from the database
    public void generateRandom() {

        randNumbers = new ArrayList<>();
        finnWords = new ArrayList<>();
        engWords = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(db.getWordArraySize());
                if (!randNumbers.contains(randomNumber)){
                    randNumbers.add(randomNumber);
                    finnWords.add(db.getWord(randomNumber).finnishWord);
                    engWords.add(db.getWord(randomNumber).englishTranslation);
                } else {
                    i--;
                }
        }
    }


    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        int secondCardClicked = 0, firstCardEnglish = 0, firstCardFinnish = 0;
        int finnWordsIndex = 0, engWordsIndex = 0;

        switch (v.getId()) {
            case R.id.finnishCardTL:            //Corresponds to finnWords(0)
                finnCards.get(0).setAlpha((float) 0.0);
                break;
            case R.id.finnishCardTR:            //Corresponds to finnWord(1)
                finnCards.get(1).setAlpha((float) 0.0);
                break;
            case R.id.finnishCardML:            //Corresponds to finnWord(2)
                finnCards.get(2).setAlpha((float) 0.0);
                break;
            case R.id.finnishCardMR:            //Corresponds to finnWord(3)
                finnCards.get(3).setAlpha((float) 0.0);
                break;
            case R.id.finnishCardBL:            //Corresponds to finnWord(4)
                finnCards.get(4).setAlpha((float) 0.0);
                break;
            case R.id.finnishCardBR:            //Corresponds to finnWord(5)
                finnCards.get(5).setAlpha((float) 0.0);
                break;
        }
    }

    public void loadGameView() {

        eTextTL = findViewById(R.id.englishTextTL);
        eTextTR = findViewById(R.id.englishTextTR);
        eTextML = findViewById(R.id.englishTextML);
        eTextMR = findViewById(R.id.englishTextMR);
        eTextBL = findViewById(R.id.englishTextBL);
        eTextBR = findViewById(R.id.englishTextBR);

        fTextTL = findViewById(R.id.finnishTextTL);
        fTextTR = findViewById(R.id.finnishTextTR);
        fTextML = findViewById(R.id.finnishTextML);
        fTextMR = findViewById(R.id.finnishTextMR);
        fTextBL = findViewById(R.id.finnishTextBL);
        fTextBR = findViewById(R.id.finnishTextBR);


        //Assigning the card images into an array and setting an onClickListener
        finnCards = new ArrayList<>();
        engCards = new ArrayList<>();

        finnCards.add(findViewById(R.id.finnishCardTL));
        finnCards.add(findViewById(R.id.finnishCardTR));
        finnCards.add(findViewById(R.id.finnishCardML));
        finnCards.add(findViewById(R.id.finnishCardMR));
        finnCards.add(findViewById(R.id.finnishCardBL));
        finnCards.add(findViewById(R.id.finnishCardBR));

        engCards.add(findViewById(R.id.englishCardTL));
        engCards.add(findViewById(R.id.englishCardTR));
        engCards.add(findViewById(R.id.englishCardML));
        engCards.add(findViewById(R.id.englishCardMR));
        engCards.add(findViewById(R.id.englishCardBL));
        engCards.add(findViewById(R.id.englishCardBR));

        for (int i = 0; i < 6; i++) {
            finnCards.get(i).setOnClickListener(this::onClick);
            engCards.get(i).setOnClickListener(this::onClick);
        }
    }

    public void setCardText() {
        eTextTL.setText(engWordsShuffled.get(0));
        eTextTR.setText(engWordsShuffled.get(1));
        eTextML.setText(engWordsShuffled.get(2));
        eTextMR.setText(engWordsShuffled.get(3));
        eTextBL.setText(engWordsShuffled.get(4));
        eTextBR.setText(engWordsShuffled.get(5));

        fTextTL.setText(finnWords.get(0));
        fTextTR.setText(finnWords.get(1));
        fTextML.setText(finnWords.get(2));
        fTextMR.setText(finnWords.get(3));
        fTextBL.setText(finnWords.get(4));
        fTextBR.setText(finnWords.get(5));
    }
}