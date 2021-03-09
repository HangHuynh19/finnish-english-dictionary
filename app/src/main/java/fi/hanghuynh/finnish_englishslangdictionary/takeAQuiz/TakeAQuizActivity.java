package fi.hanghuynh.finnish_englishslangdictionary.takeAQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import fi.hanghuynh.finnish_englishslangdictionary.R;
import fi.hanghuynh.finnish_englishslangdictionary.db.AppDatabase;

/** Modeling Take-A-Quiz Activity to display quiz questions and their multiple choices **/
public class TakeAQuizActivity extends AppCompatActivity {
    protected static final String SHARED_PREF_FILE = "my_pref";
    protected static final String USER_PROGRESS = "user_progress";
    protected static final String QUIZ_SCORE = "quiz_score";
    private SharedPreferences prefGet;
    private SharedPreferences prefPut;
    private List<String> finnishWord;
    private List<String> englishTranslation;
    private TakeAQuizUI quiz;
    private ArrayList<String> quizInfo;
    private int userScore = 0;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
        getSupportActionBar().hide();

        finnishWord = AppDatabase.getDbInstance(this.getApplicationContext()).wordDAO().loadFinnishWordArray();
        englishTranslation = AppDatabase.getDbInstance(this.getApplicationContext()).wordDAO().loadEnglishTranslationArray();

        quiz = new TakeAQuizUI(finnishWord, englishTranslation);
        quizInfo = quiz.generateQuiz();
        displayQuestionsAndOptions(quizInfo);

        Button btnNext = findViewById(R.id.nextBtn);
        onClickListener click = new onClickListener();
        btnNext.setOnClickListener(click);

        TextView timer = findViewById(R.id.timer);

        // Implement timer to count down from 1 minute
        // Preference: https://www.youtube.com/watch?v=T_wSEnqGPdo
        // https://developer.android.com/reference/java/util/Timer#schedule(java.util.TimerTask,%20java.util.Date)

        long duration = TimeUnit.MINUTES.toMillis(1);

        countDownTimer = new CountDownTimer(duration, 1000) {
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
                prefGet = getSharedPreferences(SHARED_PREF_FILE, MODE_PRIVATE);
                long userProgress = prefGet.getLong(USER_PROGRESS, 0) + userScore;

                prefPut = getSharedPreferences(SHARED_PREF_FILE, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefPut.edit();
                editor.putLong("user_progress", userProgress);
                editor.apply();

                startActivity(new Intent(TakeAQuizActivity.this, AnnounceQuizResults.class));

                cancel();
                finish();
            }
        }.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        countDownTimer.start();
    }

    /** Displaying quiz questions and their multiple choices
     * @param quiz ArrayList<String> **/
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

    /** Function to check whether the user's answer is correct or not, calculate the user's score
     * on the quiz and save the user score to shared preference
     * @param quiz ArrayList<String> **/
    private int checkUserAnswer(ArrayList<String> quiz) {
        RadioGroup optionGroup = (RadioGroup) findViewById(R.id.optionGroup);
        String userAnswer =
                ((RadioButton)findViewById(optionGroup.getCheckedRadioButtonId()))
                        .getText().toString();

        if (userAnswer.equals(quiz.get(1))) {
            userScore++;
        }

        prefPut = getSharedPreferences("my_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefPut.edit();
        editor.putLong(QUIZ_SCORE, userScore);
        editor.apply();

        return userScore;
    }

    /** Modeling onClickListener class for next button
     * When the user clicks next button, the app will check the user's answer, calculate user score
     * and display new question **/
    private class onClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.nextBtn) {
                userScore = checkUserAnswer(quizInfo);
                quiz = new TakeAQuizUI(finnishWord, englishTranslation);
                quizInfo = quiz.generateQuiz();
                displayQuestionsAndOptions(quizInfo);
            }
        }
    }
}