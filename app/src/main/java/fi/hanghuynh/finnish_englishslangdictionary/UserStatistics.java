package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class UserStatistics extends AppCompatActivity {

    private View.OnClickListener onClickListener = v -> {
        if (v.getId() == R.id.backBtn) {
            startActivity(new Intent(UserStatistics.this, MainActivity.class));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_statistics);

        ImageButton btnBack = findViewById(R.id.backBtn);

        btnBack.setOnClickListener(onClickListener);
    }
}