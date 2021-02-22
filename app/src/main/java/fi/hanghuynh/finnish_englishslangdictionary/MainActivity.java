package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import fi.hanghuynh.finnish_englishslangdictionary.db.AppDatabase;
import fi.hanghuynh.finnish_englishslangdictionary.db.Word;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "DICTIONARY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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