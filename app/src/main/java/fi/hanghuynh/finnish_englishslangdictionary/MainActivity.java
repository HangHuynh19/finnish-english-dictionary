package fi.hanghuynh.finnish_englishslangdictionary;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import fi.hanghuynh.finnish_englishslangdictionary.db.AppDatabase;
import fi.hanghuynh.finnish_englishslangdictionary.db.Word;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "DICTIONARY";

    private View.OnClickListener onClickListener = v -> {
        if (v.getId() == R.id.startBtn){
            Log.d("button", "start button");
            startActivity(new Intent(MainActivity.this, MainMenu.class));
        } else if (v.getId() == R.id.statisticsBtn){
            Log.d("button", "statistics button");
            startActivity(new Intent(MainActivity.this, UserStatistics.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.startBtn);
        Button btnStats = findViewById(R.id.statisticsBtn);

        btnStart.setOnClickListener(onClickListener);
        btnStats.setOnClickListener(onClickListener);

        //AppDatabase.getDbInstance(this.getApplicationContext()).wordDAO().deleteAll();
        saveWordToDictionary();
        List<Word> wordList = AppDatabase.getDbInstance(this.getApplicationContext()).wordDAO().getAllWords();

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
