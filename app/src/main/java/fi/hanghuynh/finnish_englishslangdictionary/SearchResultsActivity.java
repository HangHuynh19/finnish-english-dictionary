package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import fi.hanghuynh.finnish_englishslangdictionary.db.AppDatabase;

public class SearchResultsActivity extends AppCompatActivity {

    private  AppDatabase db;
    private List<String> searchedWords;
    private int[] wordIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        getSupportActionBar().hide();

        ListView lvSearchedWords = findViewById(R.id.searchedWords);
        Bundle b =getIntent().getExtras();

        wordIds = b.getIntArray("wordIds");
        db = AppDatabase.getDbInstance(this.getApplicationContext());

        //Displays the return words in a listView
        lvSearchedWords.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                getSearchedWords()
        ));

    }
    //Creates an array to store the String that shows the Finnish Word and Translation
    public List<String> getSearchedWords() {
        searchedWords = new ArrayList<>();
        int size = 0;
        for (int i = 0; i < wordIds.length; i++) {
            if (wordIds[i] > 0) {
                searchedWords.add(db.getWord(wordIds[i]).finnishWord + " -- " + db.getWord(wordIds[i]).englishTranslation);
                size++;
            } else {
                break;
            }
        }
        return searchedWords;
    }
}