package fi.hanghuynh.finnish_englishslangdictionary.wordOfTheDayFeature;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fi.hanghuynh.finnish_englishslangdictionary.R;
import fi.hanghuynh.finnish_englishslangdictionary.db.AppDatabase;
import fi.hanghuynh.finnish_englishslangdictionary.db.Word;


public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        TextView textViewWord = findViewById(R.id.txt_notification);

        int rand = (int) (Math.random() * AppDatabase.getDbInstance(this.getApplicationContext()).wordDAO().getAllWords().size() + 1);

        AppDatabase dbword = AppDatabase.getDbInstance(this.getApplicationContext());
        Word word =  dbword.getWord(rand);
        Log.d("array of words: ", String.valueOf(word.finnishWord));
        textViewWord.setText((CharSequence) (word.finnishWord +" = "+ word.englishTranslation));
    }
}