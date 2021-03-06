package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import fi.hanghuynh.finnish_englishslangdictionary.db.AppDatabase;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MatchingActivity extends AppCompatActivity {

    private  AppDatabase db;
    private ArrayList<String> finnWords, engWords, engWordsShuffled;
    private ArrayList<ImageView> finnCards, engCards, lives;
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

        //Creating a duplicate of the english words array with a different order
        engWordsShuffled = new ArrayList<>(engWords);
        Collections.shuffle(engWordsShuffled);
        loadGameView();

        //Log.d("words", "finnishWords: " + finnWords);
        //Log.d("words", "engWords: " + engWords);
        //Log.d("words", "engWordsShuffled: " + engWordsShuffled);
    }

    /*
    Generating the index of 6 random words from the database
    */
    public void generateRandom() {

        ArrayList<Integer> randNumbers = new ArrayList<>();
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
    /*
    On click listener responsible fo all of the image cards
    Each case for the finnishCards is the same apart from finnWordsIndex
    Each case for the englishCards is the same apart from engWordsShuffledIndex
     */
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
            switch (v.getId()) {
                /*
                finnishCard cases
                First if statement in each case notes which cards is clicked with finnWordsIndex
                Each if/elseif statement after are use to determine how the game plays
                 */
                case R.id.finnishCardTL:    //Corresponds to finnWords(0)
                    if (noCardsClick == TRUE || firstCardEnglish == TRUE) {
                        finnWordsIndex = 0;
                    }
                    if (noCardsClick == TRUE && finnWordsIndex == 0) {  //No cards clicked
                        firstFinnishCard();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex == engWordsIndex) {  //Successful Guess
                        correctGuessFinnish();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex != engWordsIndex) {  //Unsuccessful Guess
                        incorrectGuessFinnish();
                    }
                    break;
                case R.id.finnishCardTR:            //Corresponds to finnWord(1)
                    if (noCardsClick == TRUE || firstCardEnglish == TRUE) {
                        finnWordsIndex = 1;
                    }
                    if (noCardsClick == TRUE) { //No cards clicked
                        firstFinnishCard();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex == engWordsIndex) {  //Successful Guess
                        correctGuessFinnish();

                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex != engWordsIndex) {  //Unsuccessful Guess
                        incorrectGuessFinnish();
                    }
                    break;
                case R.id.finnishCardML:            //Corresponds to finnWord(2)
                    if (noCardsClick == TRUE || firstCardEnglish == TRUE) {
                        finnWordsIndex = 2;
                    }
                    if (noCardsClick == TRUE && finnWordsIndex == 2) {                  //No cards clicked
                        firstFinnishCard();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex == engWordsIndex) {  //Successful Guess
                        correctGuessFinnish();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex != engWordsIndex) {  //Unsuccessful Guess
                        incorrectGuessFinnish();
                    }
                    break;
                case R.id.finnishCardMR:            //Corresponds to finnWord(3)
                    if (noCardsClick == TRUE || firstCardEnglish == TRUE) {
                        finnWordsIndex = 3;
                    }
                    if (noCardsClick == TRUE && finnWordsIndex == 3) {                  //No cards clicked
                        firstFinnishCard();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex == engWordsIndex) {  //Successful Guess
                        correctGuessFinnish();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex != engWordsIndex) {  //Unsuccessful Guess
                        incorrectGuessFinnish();
                    }
                    break;
                case R.id.finnishCardBL:            //Corresponds to finnWord(4)
                    if (noCardsClick == TRUE || firstCardEnglish == TRUE) {
                        finnWordsIndex = 4;
                    }
                    if (noCardsClick == TRUE && finnWordsIndex == 4) {                                         //No cards clicked
                        firstFinnishCard();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex == engWordsIndex) {  //Successful Guess
                        correctGuessFinnish();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex != engWordsIndex) {  //Unsuccessful Guess
                        incorrectGuessFinnish();
                    }
                    break;
                case R.id.finnishCardBR:            //Corresponds to finnWord(5)
                    if (noCardsClick == TRUE || firstCardEnglish == TRUE) {
                        finnWordsIndex = 5;
                    }
                    if (noCardsClick == TRUE && finnWordsIndex == 5) {                                         //No cards clicked
                        firstFinnishCard();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex == engWordsIndex) {  //Successful Guess
                        correctGuessFinnish();
                    } else if (noCardsClick == FALSE && firstCardEnglish == TRUE && finnWordsIndex != engWordsIndex) {  //Unsuccessful Guess
                        incorrectGuessFinnish();
                    }
                    break;
                /*
                englishCard cases
                First if statement in each case states the current card clicked
                using engWordsShuffledIndex. Also acquires the index of the
                english word from the original array (before it was shuffled)
                Next three if/elseif statements are conditions to determine how the game plays
                 */
                case R.id.englishCardTL:            //Corresponds to engWordsShuffled(0)
                    if (noCardsClick == TRUE || firstCardFinnish == TRUE) {
                        engWordsShuffledIndex = 0;
                        engWordsIndex = engWords.indexOf(engWordsShuffled.get(engWordsShuffledIndex));   //gives the index of the word in the original array
                    }
                    if (noCardsClick == TRUE && engWordsShuffledIndex == 0) {
                        firstEnglishCard();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex == engWordsIndex) {  //Successful Guess
                        correctGuessEnglish();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex != engWordsIndex) {  //Unsuccessful Guess
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
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex == engWordsIndex) {  //Successful Guess
                        correctGuessEnglish();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex != engWordsIndex) {  //Unsuccessful Guess
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
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex == engWordsIndex) {  //Successful Guess
                        correctGuessEnglish();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex != engWordsIndex) {  //Unsuccessful Guess
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
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex == engWordsIndex) {  //Successful Guess
                        correctGuessEnglish();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex != engWordsIndex) {  //Unsuccessful Guess
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
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex == engWordsIndex) {  //Successful Guess
                        correctGuessEnglish();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex != engWordsIndex) {  //Unsuccessful Guess
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
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex == engWordsIndex) {  //Successful Guess
                        correctGuessEnglish();
                    } else if (noCardsClick == FALSE && firstCardFinnish == TRUE && finnWordsIndex != engWordsIndex) {  //Unsuccessful Guess
                        incorrectGuessEnglish();
                    }
                    break;
            }
            if (totalPairs == 0) {          //Checks to see if there are anymore cards left,
                endGameWin();               //if not game ends as a win

            } else if (totalLives == 0) {   //Checks to see if all lives are gone,
                endGameLoss();              //if so game ends as a loss

            }
        }

    /*
    onClickListener for the reset button when game ends
    will reset the game and allow the user to play again with new words
     */
    private final View.OnClickListener onClickListener = v -> {
        if (v.getId() == R.id.resetBtn) {
            resetGame();
            btnReset.setOnClickListener(null);
            btnReset.setVisibility(View.INVISIBLE);
        }
    };

    /*
    loadGameView() is run on onCreate() and resetGame()
    It will update the cards and words for the game
    Text and images are stored in array's
     */
    public void loadGameView() {
        totalPairs = 6;
        totalLives = 5;

        //Assigning all textViews into an array
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

        //Assigning the card images and lives images into an array and setting an onClickListener
        finnCards = new ArrayList<>();
        engCards = new ArrayList<>();
        lives = new ArrayList<>();

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

        lives.add(findViewById(R.id.heart1));
        lives.add(findViewById(R.id.heart2));
        lives.add(findViewById(R.id.heart3));
        lives.add(findViewById(R.id.heart4));
        lives.add(findViewById(R.id.heart5));

        for (int i = 0; i < 6; i++) {
            finnCards.get(i).setOnClickListener(this::onClick);
            engCards.get(i).setOnClickListener(this::onClick);
        }
        btnReset = findViewById(R.id.resetBtn);

    }


    /*
    The following functions are called when cards are clicked,
    depending on the order
     */
    public void firstFinnishCard() {    //The first card out of two click is a finnishCard
        finnCards.get(finnWordsIndex).setAlpha((float) 0.0);
        finnText.get(finnWordsIndex).setVisibility(View.VISIBLE);
        noCardsClick = FALSE;
        firstCardFinnish = TRUE;
    }

    public void firstEnglishCard() {    //The first card out of two click is a englishCard
        engCards.get(engWordsShuffledIndex).setAlpha((float) 0.0);
        engText.get(engWordsShuffledIndex).setVisibility(View.VISIBLE);
        noCardsClick = FALSE;
        firstCardEnglish = TRUE;
    }

    /*
    Second card clicked is Finnish and the words match
     */
    public void correctGuessFinnish() {
        finnCards.get(finnWordsIndex).setAlpha((float) 0.0);
        finnText.get(finnWordsIndex).setVisibility(View.VISIBLE);
        noCardsClick = TRUE;
        engCards.get(engWordsShuffledIndex).setOnClickListener(null);
        finnCards.get(finnWordsIndex).setOnClickListener(null);
        totalPairs--;
        //
        //Toast Message
        //
    }

    /*
    Second card clicked is English and the words match
     */
    public void correctGuessEnglish() {
        engCards.get(engWordsShuffledIndex).setAlpha((float) 0.0);
        engText.get(engWordsShuffledIndex).setVisibility(View.VISIBLE);
        noCardsClick = TRUE;
        engCards.get(engWordsShuffledIndex).setOnClickListener(null);
        finnCards.get(finnWordsIndex).setOnClickListener(null);
        totalPairs--;
        //
        //Toast Message
        //
    }

    /*
    Second card clicked is finnish and the words do not match
     */
    public void incorrectGuessFinnish() {
        finnCards.get(finnWordsIndex).setAlpha((float) 0.0);
        finnText.get(finnWordsIndex).setVisibility(View.VISIBLE);
        updateLives();
        //
        //Toast Message Here
        //
        /*
        Timer to delay when cards disappear. Code in run() will start once the
        delay timer finishes
         */
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        noCardsClick = TRUE;
                        firstCardFinnish = FALSE;
                        firstCardEnglish = FALSE;
                        finnCards.get(finnWordsIndex).setAlpha((float) 1.0);
                        engCards.get(engWordsShuffledIndex).setAlpha((float) 1.0);
                        finnText.get(finnWordsIndex).setVisibility(View.INVISIBLE);
                        engText.get(engWordsShuffledIndex).setVisibility(View.INVISIBLE);
                    }
                }, 2000 );
    }

    /*
    Second card clicked is English and the words do no match
     */
    public void incorrectGuessEnglish(){
        engCards.get(engWordsShuffledIndex).setAlpha((float) 0.0);
        engText.get(engWordsShuffledIndex).setVisibility(View.VISIBLE);
        updateLives();
        //
        //Toast Message Here
        //
        /*
        Timer to delay when cards disappear. Code in run() will start once the
        delay timer finishes
         */
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        noCardsClick = TRUE;
                        firstCardFinnish = FALSE;
                        firstCardEnglish = FALSE;
                        finnCards.get(finnWordsIndex).setAlpha((float) 1.0);
                        engCards.get(engWordsShuffledIndex).setAlpha((float) 1.0);
                        finnText.get(finnWordsIndex).setVisibility(View.INVISIBLE);
                        engText.get(engWordsShuffledIndex).setVisibility(View.INVISIBLE);

                    }
                }, 2000);
    }

    /*
    Called when totalLives == 0, reveals resetButton
     */
    public void endGameLoss() {
        for (int i = 0; i < 6; i++) {
            finnCards.get(i).setOnClickListener(null);
            engCards.get(i).setOnClickListener(null);
        }
        Button btnReset = findViewById(R.id.resetBtn);
        btnReset.setOnClickListener(onClickListener);
        btnReset.setVisibility(View.VISIBLE);
    }

    /*
    Called when totalPairs == 0, reveals resetButton
     */
    public void endGameWin() {
        Button btnReset = findViewById(R.id.resetBtn);
        btnReset.setOnClickListener(onClickListener);
        btnReset.setVisibility(View.VISIBLE);
    }

    /*
    Resets game and allow the user to play again with new words
     */
    public void resetGame() {
        for (int i = 0; i < 6; i++) {
            finnCards.get(i).setAlpha((float) 1.0);
            engCards.get(i).setAlpha((float) 1.0);
            finnText.get(i).setVisibility(View.INVISIBLE);
            engText.get(i).setVisibility(View.INVISIBLE);
            resetLives();
        }
        Toast.makeText(this, "This is my message", Toast.LENGTH_SHORT).show();
        generateRandom();
        engWordsShuffled = new ArrayList<>(engWords);
        Collections.shuffle(engWordsShuffled);
        loadGameView();
    }

    /*
    When an incorrect guess is made, one of the hearts will become empty
     */
    public void updateLives() {

        lives.get(totalLives - 1).setImageResource(R.drawable.empty_heart_icon);
        //lives.get(totalLives).setVisibility(View.INVISIBLE);
        totalLives--;
    }

    /*
    During resetGame() the hearts in the top right will be refreshed
     */
    public void resetLives() {
        for (int i = 0; i < 5; i++) {
            lives.get(i).setImageResource(R.drawable.full_heart_icon);
        }
    }
}