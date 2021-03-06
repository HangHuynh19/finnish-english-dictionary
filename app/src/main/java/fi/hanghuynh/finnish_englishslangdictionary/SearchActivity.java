package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import fi.hanghuynh.finnish_englishslangdictionary.db.AppDatabase;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

private String searchedWord;
private  AppDatabase db;
private EditText searchBar;
private int[] wordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();

        db = AppDatabase.getDbInstance(this.getApplicationContext());
        searchBar = findViewById(R.id.searchBar);

        ImageButton btnBack = findViewById(R.id.backBtn);
        Button btnSearch = findViewById(R.id.searchBtn);

        btnBack.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    /*
    onClickListener set for SearchActivity. Search button will run the function search()
    and load the SearchResultsActivity
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backBtn) {
            startActivity(new Intent(SearchActivity.this, MainActivity.class));
        } else if (v.getId() == R.id.searchBtn) {
            searchedWord = searchBar.getText().toString();
            search();

            Intent searchResults = new Intent(SearchActivity.this, SearchResultsActivity.class);
            searchResults.putExtra("wordIds", wordId);
            startActivity(searchResults);
        }
    }

    /*
    search function searches database for words containing the inputted text into
    the editText box
     */
    public void search() {
        int dbSize = db.getWordArraySize(), j = 0;
        wordId = new int[dbSize];
        for (int i = 0; i < dbSize; i++) {
            if (db.getWord(i).finnishWord.contains(searchedWord)){
                Log.d("test", db.getWord(i).finnishWord);
                wordId[j] = i;
                j++;
            }
        }
    }
}