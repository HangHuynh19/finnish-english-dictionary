package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class InitialScreenActivity extends AppCompatActivity {

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_screen);
        getSupportActionBar().hide();
    }

    private final Runnable runnable = () -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
    };

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}