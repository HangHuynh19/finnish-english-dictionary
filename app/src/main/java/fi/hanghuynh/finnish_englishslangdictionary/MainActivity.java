package fi.hanghuynh.finnish_englishslangdictionary;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import fi.hanghuynh.finnish_englishslangdictionary.db.AppDatabase;
import fi.hanghuynh.finnish_englishslangdictionary.db.Word;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "DICTIONARY";
    protected static final String SHARED_PREF_FILE = "my_pref";
    protected static final String USER_PROGRESS = "user_progress";
    private SharedPreferences prefGet;
    private SharedPreferences prefPut;

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
}
