package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import fi.hanghuynh.finnish_englishslangdictionary.MainActivity;
import fi.hanghuynh.finnish_englishslangdictionary.R;

public class InitialScreenActivity extends AppCompatActivity {

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_screen);
        getSupportActionBar().hide();
    }

    /*
    Switches to MainActivity, finish() ensures you can't revisit this activity
     */

    private final Runnable runnable = () -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
    };


    /*
    Set a 3 second delay when the initial screen is loaded, before the next
    activity is run
     */

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

     /*
    reference: https://www.youtube.com/watch?v=GtE8NwOQoos
    Image: https://vectr.com/dfallow95/b2j5qxrBG.png?width=640&height=640&select=b2j5qxrBGpage0
     */
}