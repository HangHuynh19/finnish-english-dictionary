package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        onClickListener click = new onClickListener();

        Button searchActivityBtn = findViewById(R.id.searchActivityBtn);

        searchActivityBtn.setOnClickListener(click);
    }

    private class onClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.searchActivityBtn) {

            }
        }
    };
}