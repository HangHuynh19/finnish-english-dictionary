package fi.hanghuynh.finnish_englishslangdictionary;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fi.hanghuynh.finnish_englishslangdictionary.db.AppDatabase;
import fi.hanghuynh.finnish_englishslangdictionary.db.Word;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "DICTIONARY";
    protected static final String SHARED_PREF_FILE = "my_pref";
    protected static final String WORD_OF_THE_DAY_EXECUTION_TIME= "execution_time";
    protected static final String WORD_OF_THE_DAY= "word_of_the_day";
    private SharedPreferences prefGet;
    private SharedPreferences prefPut;
    private Calendar calendar;
    private long executionTimeInMillis;
    private String previousWordOfTheDay;
    private TextView wordOfTheDayTv;

    private View.OnClickListener onClickListener = v -> {
        if (v.getId() == R.id.searchBtn){
            Log.d("button", "search button");
            startActivity(new Intent(MainActivity.this, SearchActivity.class));
        } else if (v.getId() == R.id.quizBtn){
            Log.d("button", "quiz button");
            startActivity(new Intent(MainActivity.this, TakeAQuizActivity.class));
        } else if (v.getId() == R.id.progressBtn){
            Log.d("button", "progress button");
            startActivity(new Intent(MainActivity.this, ProgressActivity.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSearch = findViewById(R.id.searchBtn);
        Button btnTakeQuiz = findViewById(R.id.quizBtn);
        Button btnShowProgress = findViewById(R.id.progressBtn);

        btnSearch.setOnClickListener(onClickListener);
        btnTakeQuiz.setOnClickListener(onClickListener);
        btnShowProgress.setOnClickListener(onClickListener);

        //AppDatabase.getDbInstance(this.getApplicationContext()).wordDAO().deleteAll();
        saveWordToDictionary();

        wordOfTheDayTv = findViewById(R.id.wordOfTheDay);

        prefGet = getSharedPreferences(SHARED_PREF_FILE, MODE_PRIVATE);
        executionTimeInMillis = prefGet.getLong(WORD_OF_THE_DAY_EXECUTION_TIME, 0);
        previousWordOfTheDay = prefGet.getString(WORD_OF_THE_DAY, "No word stored");

        calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        calendar.setTime(currentDate);
        long currentDateInMillis = calendar.getTimeInMillis();

        if(currentDateInMillis - executionTimeInMillis > 86400000) {
            changeWordOfTheDay();

            prefPut = getSharedPreferences("my_pref", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefPut.edit();
            editor.putLong(WORD_OF_THE_DAY_EXECUTION_TIME, currentDateInMillis);
            editor.apply();
        }
        wordOfTheDayTv.setText(previousWordOfTheDay);
    }

    private void saveWordToDictionary() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        if (db.wordDAO().getAnyWord().length < 1) {
            for (int i = 0; i < db.getWordArraySize(); i++) {
                db.wordDAO().insertWord(db.getWord(i));
            }
            finish();
        }
    }

    private void changeWordOfTheDay() {
        List<Word> allWords = AppDatabase.getDbInstance(this.getApplicationContext()).wordDAO().getAllWords();
        Word wordOfTheDay = allWords.get((int)(Math.random() * (allWords.size())));
        String wordOfTheDayContent = wordOfTheDay.finnishWord + " -- " + wordOfTheDay.englishTranslation;

        if (!(wordOfTheDayContent.equals(previousWordOfTheDay))) {
            previousWordOfTheDay = wordOfTheDayContent;
            prefPut = getSharedPreferences("my_pref", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefPut.edit();
            editor.putString(WORD_OF_THE_DAY, previousWordOfTheDay);
            editor.apply();
        }
    }
}
