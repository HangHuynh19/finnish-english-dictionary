package fi.hanghuynh.finnish_englishslangdictionary.takeAQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import fi.hanghuynh.finnish_englishslangdictionary.R;

/** Modeling the Activity to announce the user score after finishing the quiz **/
public class AnnounceQuizResults extends AppCompatActivity {
    private SharedPreferences prefGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce_quiz_results);

        TextView userScoreDisplay = findViewById(R.id.scoreAnnouncement);

        prefGet = getSharedPreferences("my_pref", MODE_PRIVATE);
        long userScore = prefGet.getLong("quiz_score", 0);
        userScoreDisplay.setText(Long.toString(userScore));
    }
}