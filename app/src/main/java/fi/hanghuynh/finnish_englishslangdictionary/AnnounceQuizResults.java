package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class AnnounceQuizResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce_quiz_results);

        TextView userScoreDisplay = findViewById(R.id.scoreAnnouncement);

        SharedPreferences prefGet = getSharedPreferences("my_pref", MODE_PRIVATE);
        long userScore = prefGet.getLong("user_progress", 0);
        userScoreDisplay.setText(Long.toString(userScore));
    }
}