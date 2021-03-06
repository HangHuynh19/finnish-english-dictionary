package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        getSupportActionBar().hide();

        SharedPreferences prefGet = getSharedPreferences("my_pref", MODE_PRIVATE);
        long userProgress = prefGet.getLong("user_progress", 0);

        TextView totalPointDisplay = findViewById(R.id.totalPointAnnouncement);
        totalPointDisplay.setText(Long.toString(userProgress));
        Log.d("user progress", Long.toString(userProgress));

    }

}