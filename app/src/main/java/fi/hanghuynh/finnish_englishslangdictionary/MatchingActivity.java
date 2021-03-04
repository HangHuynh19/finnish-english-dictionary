package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import fi.hanghuynh.finnish_englishslangdictionary.db.AppDatabase;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MatchingActivity extends AppCompatActivity {

    private  AppDatabase db;
    private ArrayList<Integer> randNumbers;
    private ArrayList<String> finnWords, engWords, engWordsShuffled;
    private ArrayList<ImageView> finnCards, engCards;
    private ArrayList<TextView> finnText, engText;
    private Boolean noCardsClick, firstCardEnglish, firstCardFinnish;
    private int finnWordsIndex, engWordsIndex, totalPairs, totalLives, engWordsShuffledIndex;
    private Button btnReset;
    Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);

        db = AppDatabase.getDbInstance(this.getApplicationContext());
        noCardsClick = TRUE;
        firstCardFinnish = FALSE;
        firstCardEnglish = FALSE;

        generateRandom();
        Log.d("test", "numbers: " + randNumbers);
        Log.d("test", "finnWords: " + finnWords);
        Log.d("test", "engWords: " + engWords);


        //Creating a duplicate of the english words array with a different order
        engWordsShuffled = new ArrayList<>(engWords);
        Collections.shuffle(engWordsShuffled);
        loadGameView();

        Log.d("test", "engWordsShuffled: " + engWordsShuffled);
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
            switch (v.getId()) {
                case R.id.finnishCardTL:            //Corresponds to finnWords(0)
                    if (noCardsClick == TRUE || firstCardEnglish == TRUE) {
                        finnWordsIndex = 0;
                    }
                    if (noCardsClick == TRUE && finnWordsIndex == 0) {                      //No cards clicked
                        firstFinnishCard();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex == engWordsIndex) {      //The first card clicked was English
                        correctGuessFinnish();                                                                              //and the cards match
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex != engWordsIndex) {      //The first card clicked was English
                        incorrectGuessFinnish();                                                                            //and does not match
                    }
                    break;
                case R.id.finnishCardTR:            //Corresponds to finnWord(1)
                    if (noCardsClick == TRUE || firstCardEnglish == TRUE) {              //Ensures value of finnWordsIndex does not
                        finnWordsIndex = 1;                                             //change until an english card is pressed
                    }
                    if (noCardsClick == TRUE) {                  //No cards clicked
                        firstFinnishCard();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex == engWordsIndex) {      //The first card clicked was English
                        correctGuessFinnish();

                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex != engWordsIndex) {                                  //The first card clicked was Finnish
                        incorrectGuessFinnish();
                    }
                    break;
                case R.id.finnishCardML:            //Corresponds to finnWord(2)
                    if (noCardsClick == TRUE || firstCardEnglish == TRUE) {
                        finnWordsIndex = 2;
                    }
                    if (noCardsClick == TRUE && finnWordsIndex == 2) {                  //No cards clicked
                        firstFinnishCard();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex == engWordsIndex) {        //The first card clicked was English
                        correctGuessFinnish();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex != engWordsIndex) {                                  //The first card clicked was Finnish
                        incorrectGuessFinnish();
                    }
                    break;
                case R.id.finnishCardMR:            //Corresponds to finnWord(3)
                    if (noCardsClick == TRUE || firstCardEnglish == TRUE) {
                        finnWordsIndex = 3;
                    }
                    if (noCardsClick == TRUE && finnWordsIndex == 3) {                  //No cards clicked
                        firstFinnishCard();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex == engWordsIndex) {        //The first card clicked was English
                        correctGuessFinnish();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex != engWordsIndex) {                                  //The first card clicked was Finnish
                        incorrectGuessFinnish();
                    }
                    break;
                case R.id.finnishCardBL:            //Corresponds to finnWord(4)
                    if (noCardsClick == TRUE || firstCardEnglish == TRUE) {
                        finnWordsIndex = 4;
                    }
                    if (noCardsClick == TRUE && finnWordsIndex == 4) {                                         //No cards clicked
                        firstFinnishCard();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex == engWordsIndex) {        //The first card clicked was English
                        correctGuessFinnish();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex != engWordsIndex) {                                  //The first card clicked was Finnish
                        incorrectGuessFinnish();
                    }
                    break;
                case R.id.finnishCardBR:            //Corresponds to finnWord(5)
                    if (noCardsClick == TRUE || firstCardEnglish == TRUE) {
                        finnWordsIndex = 5;
                    }
                    if (noCardsClick == TRUE && finnWordsIndex == 5) {                                         //No cards clicked
                        firstFinnishCard();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex == engWordsIndex) {        //The first card clicked was English
                        correctGuessFinnish();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex != engWordsIndex) {                                  //The first card clicked was Finnish
                        incorrectGuessFinnish();
                    }
                    break;
                case R.id.englishCardTL:            //Corresponds to engWordsShuffled(0)
                    if (noCardsClick == TRUE || firstCardFinnish == TRUE) {
                        engWordsShuffledIndex = 0;
                        engWordsIndex = engWords.indexOf(engWordsShuffled.get(engWordsShuffledIndex));   //gives the index of the word in the original array
                    }
                    if (noCardsClick == TRUE && engWordsShuffledIndex == 0) {
                        firstEnglishCard();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex == engWordsIndex) {
                        correctGuessEnglish();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex != engWordsIndex) {
                        incorrectGuessEnglish();
                    }
                    break;
                case R.id.englishCardTR:            //Corresponds to engWordsShuffled(1)
                    if (noCardsClick == TRUE || firstCardFinnish == TRUE) {
                        engWordsShuffledIndex = 1;
                        engWordsIndex = engWords.indexOf(engWordsShuffled.get(engWordsShuffledIndex));   //gives the index of the word in the original array
                    }
                    if (noCardsClick == TRUE && engWordsShuffledIndex == 1) {
                        firstEnglishCard();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex == engWordsIndex) {
                        correctGuessEnglish();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex != engWordsIndex) {
                        incorrectGuessEnglish();
                    }
                    break;
                case R.id.englishCardML:            //Corresponds to engWordsShuffled(2)
                    if (noCardsClick == TRUE || firstCardFinnish == TRUE) {
                        engWordsShuffledIndex = 2;
                        engWordsIndex = engWords.indexOf(engWordsShuffled.get(engWordsShuffledIndex));   //gives the index of the word in the original array
                    }
                    if (noCardsClick == TRUE && engWordsShuffledIndex == 2) {
                        firstEnglishCard();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex == engWordsIndex) {
                        correctGuessEnglish();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex != engWordsIndex) {
                        incorrectGuessEnglish();
                    }
                    break;
                case R.id.englishCardMR:            //Corresponds to engWordsShuffled(3)
                    if (noCardsClick == TRUE || firstCardFinnish == TRUE) {
                        engWordsShuffledIndex = 3;
                        engWordsIndex = engWords.indexOf(engWordsShuffled.get(engWordsShuffledIndex));   //gives the index of the word in the original array
                    }
                    if (noCardsClick == TRUE && engWordsShuffledIndex == 3) {
                        firstEnglishCard();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex == engWordsIndex) {
                        correctGuessEnglish();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex != engWordsIndex) {
                        incorrectGuessEnglish();
                    }
                    break;
                case R.id.englishCardBL:            //Corresponds to engWordsShuffled(4)
                    if (noCardsClick == TRUE || firstCardFinnish == TRUE) {
                        engWordsShuffledIndex = 4;
                        engWordsIndex = engWords.indexOf(engWordsShuffled.get(engWordsShuffledIndex));   //gives the index of the word in the original array
                    }
                    if (noCardsClick == TRUE && engWordsShuffledIndex == 4) {
                        firstEnglishCard();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex == engWordsIndex) {
                        correctGuessEnglish();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex != engWordsIndex) {
                        incorrectGuessEnglish();
                    }
                    break;
                case R.id.englishCardBR:            //Corresponds to engWordsShuffled(5)
                    if (noCardsClick == TRUE || firstCardFinnish == TRUE) {
                        engWordsShuffledIndex = 5;
                        engWordsIndex = engWords.indexOf(engWordsShuffled.get(engWordsShuffledIndex));   //gives the index of the word in the original array
                    }
                    if (noCardsClick == TRUE && engWordsShuffledIndex == 5) {
                        firstEnglishCard();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex == engWordsIndex) {
                        correctGuessEnglish();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex != engWordsIndex) {
                        incorrectGuessEnglish();
                    }
                    break;
            }
            if (totalPairs == 0) {
                endGameWin();
            } else if (totalLives == 0) {
                endGameLoss();
            }
        }

    private final View.OnClickListener onClickListener = v -> {
        if (v.getId() == R.id.resetBtn) {
            resetGame();
            btnReset.setOnClickListener(null);
            btnReset.setVisibility(View.INVISIBLE);
        }
    };

    public void loadGameView() {
        totalPairs = 6;
        totalLives = 5;
        //Assigning all textView into an array
        finnText = new ArrayList<>();
        engText = new ArrayList<>();

        finnText.add(findViewById(R.id.finnishTextTL));
        finnText.add(findViewById(R.id.finnishTextTR));
        finnText.add(findViewById(R.id.finnishTextML));
        finnText.add(findViewById(R.id.finnishTextMR));
        finnText.add(findViewById(R.id.finnishTextBL));
        finnText.add(findViewById(R.id.finnishTextBR));

        engText.add(findViewById(R.id.englishTextTL));
        engText.add(findViewById(R.id.englishTextTR));
        engText.add(findViewById(R.id.englishTextML));
        engText.add(findViewById(R.id.englishTextMR));
        engText.add(findViewById(R.id.englishTextBL));
        engText.add(findViewById(R.id.englishTextBR));

        for (int i = 0; i < 6; i++) {
            engText.get(i).setText(engWordsShuffled.get(i));
            finnText.get(i).setText(finnWords.get(i));
        }

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
        btnReset = findViewById(R.id.resetBtn);

    }


    //Methods for actions to happen when cards are clicked
    public void firstFinnishCard() {
        finnCards.get(finnWordsIndex).setAlpha((float) 0.0);
        finnText.get(finnWordsIndex).setVisibility(View.VISIBLE);
        noCardsClick = FALSE;
        firstCardFinnish = TRUE;
    }

    public void firstEnglishCard() {
        engCards.get(engWordsShuffledIndex).setAlpha((float) 0.0);
        engText.get(engWordsShuffledIndex).setVisibility(View.VISIBLE);
        noCardsClick = FALSE;
        firstCardEnglish = TRUE;
    }

    public void correctGuessFinnish() {
        finnCards.get(finnWordsIndex).setAlpha((float) 0.0);                                                             //and matches
        finnText.get(finnWordsIndex).setVisibility(View.VISIBLE);
        noCardsClick = TRUE;
        engCards.get(engWordsShuffledIndex).setOnClickListener(null);
        finnCards.get(finnWordsIndex).setOnClickListener(null);
        totalPairs--;
    }

    public void correctGuessEnglish() {
        engCards.get(engWordsShuffledIndex).setAlpha((float) 0.0);
        engText.get(engWordsShuffledIndex).setVisibility(View.VISIBLE);
        noCardsClick = TRUE;
        engCards.get(engWordsShuffledIndex).setOnClickListener(null);
        finnCards.get(finnWordsIndex).setOnClickListener(null);
        totalPairs--;
    }

    public void incorrectGuessFinnish() {
        finnCards.get(finnWordsIndex).setAlpha((float) 0.0);                                                             //and does not match
        finnText.get(finnWordsIndex).setVisibility(View.VISIBLE);
        //delay function
        noCardsClick = TRUE;
        firstCardFinnish = FALSE;
        firstCardEnglish = FALSE;
        finnCards.get(finnWordsIndex).setAlpha((float) 1.0);
        engCards.get(engWordsShuffledIndex).setAlpha((float) 1.0);
        finnText.get(finnWordsIndex).setVisibility(View.INVISIBLE);
        engText.get(engWordsShuffledIndex).setVisibility(View.INVISIBLE);
        totalLives--;
    }

    public void incorrectGuessEnglish(){
        engCards.get(engWordsShuffledIndex).setAlpha((float) 0.0);
        engText.get(engWordsShuffledIndex).setVisibility(View.VISIBLE);
        //delay function
        noCardsClick = TRUE;
        firstCardFinnish = FALSE;
        firstCardEnglish = FALSE;
        finnCards.get(finnWordsIndex).setAlpha((float) 1.0);
        engCards.get(engWordsShuffledIndex).setAlpha((float) 1.0);
        finnText.get(finnWordsIndex).setVisibility(View.INVISIBLE);
        engText.get(engWordsShuffledIndex).setVisibility(View.INVISIBLE);
        totalLives--;
    }

    public void endGameLoss() {
        for (int i = 0; i < 6; i++) {
            finnCards.get(i).setOnClickListener(null);
            engCards.get(i).setOnClickListener(null);
        }
        Button btnReset = findViewById(R.id.resetBtn);
        btnReset.setOnClickListener(onClickListener);
        btnReset.setVisibility(View.VISIBLE);
    }

    public void endGameWin() {
        Button btnReset = findViewById(R.id.resetBtn);
        btnReset.setOnClickListener(onClickListener);
        btnReset.setVisibility(View.VISIBLE);
    }

    public void resetGame() {
        for (int i = 0; i < 6; i++) {
            finnCards.get(i).setAlpha((float) 1.0);
            engCards.get(i).setAlpha((float) 1.0);
            finnText.get(i).setVisibility(View.INVISIBLE);
            engText.get(i).setVisibility(View.INVISIBLE);
        }
        generateRandom();
        engWordsShuffled = new ArrayList<>(engWords);
        Collections.shuffle(engWordsShuffled);
        Log.d("test2", "" + finnWords);
        Log.d("test2", "" + engWords);
        Log.d("test2", "" + engWordsShuffled);
        loadGameView();
    }
}