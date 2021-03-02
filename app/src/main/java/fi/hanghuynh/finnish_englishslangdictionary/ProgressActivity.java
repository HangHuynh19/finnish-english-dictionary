package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import java.util.Observable;

import fi.hanghuynh.finnish_englishslangdictionary.adapters.BookmarkedWordListAdapter;
import fi.hanghuynh.finnish_englishslangdictionary.db.AppDatabase;
import fi.hanghuynh.finnish_englishslangdictionary.db.Word;

public class ProgressActivity extends AppCompatActivity {

    private List<Word> bookmarkedWordList;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        SharedPreferences prefGet = getSharedPreferences("my_pref", MODE_PRIVATE);
        long userProgress = prefGet.getLong("user_progress", 0);

        TextView totalPointDisplay = findViewById(R.id.totalPointAnnouncement);
        totalPointDisplay.setText(Long.toString(userProgress));
        Log.d("user progress", Long.toString(userProgress));

        bookmarkedWordList = AppDatabase.getDbInstance(this.getApplicationContext()).wordDAO().loadBookmarkedWords();

        //Set up RecyclerView adapter
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final BookmarkedWordListAdapter adapter = new BookmarkedWordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setWords((List<Word>) bookmarkedWordList);

        context = this.getApplicationContext();

        // Add the functionality to swipe items in the
        // recycler view to delete that item
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    // We are not implementing onMove() in this app
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    // When the use swipes a word,
                    // delete that word from the database.
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Word myWord = adapter.getWordAtPosition(position);

                        /**if (myWord.equals(bookmarkedWordList.get(position))) {
                            bookmarkedWordList.get(position).undoBookmark();
                            AppDatabase.getDbInstance(context).wordDAO().updateWords(bookmarkedWordList.get(position));
                        }**/

                        myWord.undoBookmark();
                        AppDatabase.getDbInstance(context).wordDAO().updateWords(myWord);
                    }

                });
        // Attach the item touch helper to the recycler view
        helper.attachToRecyclerView(recyclerView);
        adapter.notifyDataSetChanged();
    }
}