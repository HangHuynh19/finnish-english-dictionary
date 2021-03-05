package fi.hanghuynh.finnish_englishslangdictionary;

import androidx.appcompat.app.AppCompatActivity;
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

/** Modeling Progress Activity for displaying total points and user's bookmarks **/
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
        //Log.d("user progress", Long.toString(userProgress));

        bookmarkedWordList = AppDatabase.getDbInstance(this.getApplicationContext()).wordDAO().loadBookmarkedWords();

        // Preferences:
        // https://github.com/google-developer-training/android-advanced/blob/master/RoomWordsWithDelete/app/src/main/java/com/android/example/roomwordssample/MainActivity.java
        // Set up RecyclerView adapter
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final BookmarkedWordListAdapter adapter = new BookmarkedWordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setWords((List<Word>) bookmarkedWordList);

        context = this.getApplicationContext();

        // Add the functionality to swipe items in the
        // recycler view to delete that item from the array
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

                        myWord.undoBookmark();
                        AppDatabase.getDbInstance(context).wordDAO().updateWords(myWord);

                        // Preference: https://stackoverflow.com/questions/28189371/using-notifyitemremoved-or-notifydatasetchanged-with-recyclerview-in-android
                        bookmarkedWordList.remove(position);
                        adapter.notifyItemRemoved(position);
                        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
                    }

                });

        // Attach the item touch helper to the recycler view
        helper.attachToRecyclerView(recyclerView);
    }
}