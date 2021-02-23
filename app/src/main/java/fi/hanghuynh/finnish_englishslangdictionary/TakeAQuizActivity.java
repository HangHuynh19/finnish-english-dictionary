package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TakeAQuizActivity extends AppCompatActivity {

    private View.OnClickListener onClickListener = v -> {
        if (v.getId() == R.id.backBtn) {
            startActivity(new Intent(TakeAQuizActivity.this, MainActivity.class));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

        ImageButton btnBack = findViewById(R.id.backBtn);

        btnBack.setOnClickListener(onClickListener);
    }
}