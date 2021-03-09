package fi.hanghuynh.finnish_englishslangdictionary.searchActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fi.hanghuynh.finnish_englishslangdictionary.R;
import fi.hanghuynh.finnish_englishslangdictionary.db.AppDatabase;
import fi.hanghuynh.finnish_englishslangdictionary.db.Word;

public class SearchResultsActivity extends AppCompatActivity {

    private AppDatabase db;
    private List<String> searchedWords;
    private ArrayList<Integer> wordIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        ListView lvSearchedWords = findViewById(R.id.searchedWords);
        Bundle b = getIntent().getExtras();

        wordIds = b.getIntegerArrayList("wordIds");
        db = AppDatabase.getDbInstance(this.getApplicationContext());

        ArrayList<String> results = (ArrayList<String>) getSearchedWords();

        if (wordIds.size() == 0) {
            setContentView(R.layout.activity_search_results2);
        } else {
            lvSearchedWords.setAdapter(new ListAdapter(
                    this,
                    R.layout.searched_word_layout,
                    results
            ));
        }
    }

    /** Creates an array to store the String that shows the Finnish Word and Translation **/
    public List<String> getSearchedWords() {
        searchedWords = new ArrayList<>();

        for (int i = 0; i < wordIds.size(); i++) {
            searchedWords.add(db.getWord(wordIds.get(i)).finnishWord + " -- " + db.getWord(wordIds.get(i)).englishTranslation);
        }

        return searchedWords;
    }

    // Preference: https://github.com/jonndavis1993/Android-Tutorials/blob/master/app/src/main/java/com/simpleware/jonathan/listviewexample/MainActivity.java
    /** Create list adapter for displaying array of searchedWords **/
    private class ListAdapter extends ArrayAdapter<String> {
        private int layout;
        private ArrayList<String> list;

        /** List adapter constructor
         * @param context Context
         * @param resources int
         * @param objects ArrayList **/
        private ListAdapter(Context context, int resources, ArrayList<String> objects) {
            super(context, resources, objects);
            this.layout = resources;
            this.list = objects;
        }

        @Override
        public View getView(int position, View converterView, ViewGroup parent) {
            ViewHolder mainViewHolder = null;

            if (converterView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                converterView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.result = converterView.findViewById(R.id.searchedWordTxt);
                viewHolder.bookmark = converterView.findViewById(R.id.bookmarkBtn);
                converterView.setTag(viewHolder);
            }

            mainViewHolder = (ViewHolder) converterView.getTag();

            mainViewHolder.bookmark.setOnClickListener(view -> {
                String[] keyword = getItem(position).split(" -- ");
                Word updatedWord = AppDatabase.getDbInstance(this.getContext()).wordDAO().findWord(keyword[0]);
                updatedWord.bookmark();
                Toast.makeText(getContext(), keyword[0] + " bookmarked successfully!", Toast.LENGTH_SHORT).show();
                db.getDbInstance(this.getContext()).wordDAO().updateWords(updatedWord);
            });

            mainViewHolder.result.setText(getItem(position));

            return converterView;
        }
    }

    /** Modeling ViewHolder for individual item on ListView **/
    public class ViewHolder {
        private TextView result;
        private ImageButton bookmark;
    }
}

