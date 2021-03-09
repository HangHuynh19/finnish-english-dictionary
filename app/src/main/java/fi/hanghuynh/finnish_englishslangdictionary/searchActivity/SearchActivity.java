package fi.hanghuynh.finnish_englishslangdictionary.searchActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

import fi.hanghuynh.finnish_englishslangdictionary.R;
import fi.hanghuynh.finnish_englishslangdictionary.db.AppDatabase;
import fi.hanghuynh.finnish_englishslangdictionary.db.Word;

/** Modeling Search Activity that gets user input **/
public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private String searchedWord;
    private  AppDatabase db;
    private EditText searchBar;
    private ArrayList<Integer> wordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();

        db = AppDatabase.getDbInstance(this.getApplicationContext());
        searchBar = findViewById(R.id.searchBar);

        Button btnSearch = findViewById(R.id.searchBtn);

        btnSearch.setOnClickListener(this);

        // Fulfill the intent from Progress Activity. A clicked bookmarked Finnish word will be
        // copied to the search for the user to search its meaning
        Intent intent = getIntent();

        if(intent.getExtras() != null) {
            Word word = (Word) intent.getSerializableExtra("bookmarked word");
            searchBar.setText(word.getFinnishWord());
        }
    }

    /*
    onClickListener set for SearchActivity. Search button will run the function search()
    and load the SearchResultsActivity
     */
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.searchBtn) {
            searchedWord = searchBar.getText().toString().trim().toLowerCase();
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
        int dbSize = db.getWordArraySize();
        wordId = new ArrayList<>();
        for (int i = 0; i < dbSize; i++) {
            if (db.getWord(i).finnishWord.contains(searchedWord)){
                wordId.add(i);
            }
        }
    }
}