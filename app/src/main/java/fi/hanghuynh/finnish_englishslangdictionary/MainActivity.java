package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private View.OnClickListener onClickListener = v -> {
        if (v.getId() == R.id.startBtn){
            Log.d("button", "start button");
        } else if (v.getId() == R.id.statisticsBtn){
            Log.d("button", "statistics button");
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
}