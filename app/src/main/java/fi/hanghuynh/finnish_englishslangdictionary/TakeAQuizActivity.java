package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


import fi.hanghuynh.finnish_englishslangdictionary.db.AppDatabase;

public class TakeAQuizActivity extends AppCompatActivity {
    private List<String> finnishWord;
    private List<String> englishTranslation;
    private TakeAQuizUI quiz;
    private ArrayList<String> quizInfo;
    private RadioGroup optionGroup;
    private int userScore = 0;
    private TextView timer;
    private long userProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

        finnishWord = AppDatabase.getDbInstance(this.getApplicationContext()).wordDAO().loadFinnishWordArray();
        //Log.d("finnish array", Integer.toString(finnishWord.size()));
        englishTranslation = AppDatabase.getDbInstance(this.getApplicationContext()).wordDAO().loadEnglishTranslationArray();
        //Log.d("english array", Integer.toString(englishTranslation.size()));

        quiz = new TakeAQuizUI(finnishWord, englishTranslation);
        quizInfo = quiz.generateQuiz();
        displayQuestionsAndOptions(quizInfo);

        Button btnNext = findViewById(R.id.nextBtn);
        onClickListener click = new onClickListener();
        btnNext.setOnClickListener(click);

        timer = findViewById(R.id.timer);

        long duration = TimeUnit.MINUTES.toMillis(1);

        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisecondsTillFinish) {
                String sDuration = String.format(Locale.ENGLISH, "%02d:%02d"
                        , TimeUnit.MILLISECONDS.toMinutes(millisecondsTillFinish)
                        , TimeUnit.MILLISECONDS.toSeconds(millisecondsTillFinish) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisecondsTillFinish)));
                timer.setText(sDuration);
            }

            @Override
            public void onFinish() {
                userScore = checkUserAnswer(quizInfo);
                SharedPreferences prefGet = getSharedPreferences("my_pref", MODE_PRIVATE);
                userProgress = prefGet.getLong("user_progress", 0) + userScore;
                Log.d("user progress", Long.toString(userProgress));

                SharedPreferences prefPut = getSharedPreferences("my_pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefPut.edit();
                editor.putLong("user_progress", userProgress);
                editor.apply();

                setContentView(R.layout.activity_take_quiz2);

                TextView userScoreDisplay = findViewById(R.id.scoreAnnouncement);

                userScoreDisplay.setText(Integer.toString(userScore));
            }
        }.start();
    }

    private void displayQuestionsAndOptions(ArrayList<String> quiz) {
        TextView question = findViewById(R.id.question);
        RadioButton option1 = findViewById(R.id.option1);
        RadioButton option2 = findViewById(R.id.option2);
        RadioButton option3 = findViewById(R.id.option3);
        String questionSentence = "What is the meaning of " + quiz.get(0) + "?";

        question.setText(questionSentence);
        option1.setText(quiz.get(2));
        option2.setText(quiz.get(3));
        option3.setText(quiz.get(4));
    }

    private int checkUserAnswer(ArrayList<String> quiz) {
        optionGroup = (RadioGroup) findViewById(R.id.optionGroup);
        String userAnswer =
                ((RadioButton)findViewById(optionGroup.getCheckedRadioButtonId()))
                        .getText().toString();

        if (userAnswer.equals(quiz.get(1))) {
            userScore++;
        }

        return userScore;
    }

    private class onClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.nextBtn) {
                userScore = checkUserAnswer(quizInfo);
                Log.d("score", Integer.toString(userScore));
                quiz = new TakeAQuizUI(finnishWord, englishTranslation);
                quizInfo = quiz.generateQuiz();
                displayQuestionsAndOptions(quizInfo);
            }
        }
    };
}