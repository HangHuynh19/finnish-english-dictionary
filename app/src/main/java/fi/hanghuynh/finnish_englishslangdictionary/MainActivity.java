package fi.hanghuynh.finnish_englishslangdictionary;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fi.hanghuynh.finnish_englishslangdictionary.db.AppDatabase;
import fi.hanghuynh.finnish_englishslangdictionary.db.Word;
import fi.hanghuynh.finnish_englishslangdictionary.progressActivity.ProgressActivity;
import fi.hanghuynh.finnish_englishslangdictionary.searchActivity.SearchActivity;
import fi.hanghuynh.finnish_englishslangdictionary.takeAQuiz.TakeAQuizActivity;
import fi.hanghuynh.finnish_englishslangdictionary.wordOfTheDayFeature.AlarmsetActivity;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "DICTIONARY";
    protected static final String SHARED_PREF_FILE = "my_pref";
    private static final String WORD_OF_THE_DAY_EXECUTION_TIME= "execution_time";
    private static final String WORD_OF_THE_DAY= "word_of_the_day";
    private SharedPreferences prefGet;
    private SharedPreferences prefPut;

    // Create the onClickListener for 3 buttons: Search, Take A Quiz and Check Progress and Bookmarks
    // After the user clicks the the button, it will lead them to the assigned Activity
    private View.OnClickListener onClickListener = v -> {
        if (v.getId() == R.id.searchBtn){
            startActivity(new Intent(MainActivity.this, SearchActivity.class));
        } else if (v.getId() == R.id.quizBtn){
            startActivity(new Intent(MainActivity.this, TakeAQuizActivity.class));
        } else if (v.getId() == R.id.progressBtn){
            startActivity(new Intent(MainActivity.this, ProgressActivity.class));
        } else if (v.getId() == R.id.matchBtn){
            startActivity(new Intent(MainActivity.this, MatchingActivity.class));
        } else if (v.getId() == R.id.settingBtn) {
            startActivity(new Intent(MainActivity.this, AlarmsetActivity.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Button btnSearch = findViewById(R.id.searchBtn);
        Button btnTakeQuiz = findViewById(R.id.quizBtn);
        Button btnShowProgress = findViewById(R.id.progressBtn);
        Button btnMatch = findViewById(R.id.matchBtn);
        Button wordBtn = findViewById(R.id.settingBtn);

        btnSearch.setOnClickListener(onClickListener);
        btnTakeQuiz.setOnClickListener(onClickListener);
        btnShowProgress.setOnClickListener(onClickListener);
        btnMatch.setOnClickListener(onClickListener);
        wordBtn.setOnClickListener(onClickListener);

        //AppDatabase.getDbInstance(this.getApplicationContext()).wordDAO().deleteAll();
        saveWordToDictionary();
        changeWordOfTheDay();
    }

    protected void onResume() {
        super.onResume();
        changeWordOfTheDay();
    }

    protected void onRestart() {
        super.onRestart();
        changeWordOfTheDay();
    }

    /** Load data to the database if the database is null **/
    private void saveWordToDictionary() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
            if (db.wordDAO().getAnyWord().length < 1) {
                for (int i = 0; i < db.getWordArraySize(); i++) {
                    db.wordDAO().insertWord(db.getWord(i));
                }
                finish();
            }
        }

    /** Generate a new word-of-the-day
     * @param previousWordOfTheDay String */
    private void generateWordOfTheDay(String previousWordOfTheDay) {
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

    /** Check if 1 day has passed or not. If yes, change the content of the word-of-the-day text view */
     private void changeWordOfTheDay() {
        TextView wordOfTheDayTv = findViewById(R.id.wordOfTheDay);

        prefGet = getSharedPreferences(SHARED_PREF_FILE, MODE_PRIVATE);
        long executionTimeInMillis = prefGet.getLong(WORD_OF_THE_DAY_EXECUTION_TIME, 0);
        String previousWordOfTheDay = prefGet.getString(WORD_OF_THE_DAY, "No word stored");

        // Preference: https://www.tutorialspoint.com/java/util/calendar_gettime.htm
        // https://developer.android.com/reference/java/util/Date
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        calendar.setTime(currentDate);
        long currentDateInMillis = calendar.getTimeInMillis();

        if((currentDateInMillis - executionTimeInMillis) > 86400000) {
            generateWordOfTheDay(previousWordOfTheDay);
            prefPut = getSharedPreferences("my_pref", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefPut.edit();
            editor.putLong(WORD_OF_THE_DAY_EXECUTION_TIME, currentDateInMillis);
            editor.apply();
        }

        wordOfTheDayTv.setText("WORD OF THE DAY\n" + previousWordOfTheDay);
    }
}



