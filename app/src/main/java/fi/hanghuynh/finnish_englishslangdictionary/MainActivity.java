package fi.hanghuynh.finnish_englishslangdictionary;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("method", "onStart");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("method", "onResume");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("method", "onPause");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("method", "onDestroy");
    }
}