package fi.hanghuynh.finnish_englishslangdictionary.progressActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fi.hanghuynh.finnish_englishslangdictionary.R;
import fi.hanghuynh.finnish_englishslangdictionary.db.Word;

// Preference: https://github.com/google-developer-training/android-advanced/blob/master/RoomWordsWithDelete/app/src/main/java/com/android/example/roomwordssample/WordListAdapter.java
/** Modeling list adapter for bookmarked word list **/
public class BookmarkedWordListAdapter extends RecyclerView.Adapter<BookmarkedWordListAdapter.WordViewHolder>{

    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words
    private SelectedWord selectedWord;

    /** Class constructor
     * @param context Context **/
    public BookmarkedWordListAdapter(Context context, SelectedWord selectedWord) {
        mInflater = LayoutInflater.from(context);
        this.selectedWord = selectedWord;
    }

    /** Put layout of individual bookmarked word to parent view group
     * @param parent ViewGroup
     * @param viewType int
     * @return view holder of individual word **/
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.bookmarked_word_layout, parent, false);
        return new WordViewHolder(itemView);
    }

    /** Update the view holder to reflect the bookmark word at a specific position
     * @param holder WordViewHolder
     * @param position int **/
    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mWords != null) {
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getFinnishWord());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }

    /** Associate a list of words with this adapter
     * @param words List<Word>**/
    public void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    /** Count numbers of items in the array
     * @return integer number of array size **/
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    /**
     * Get the word at a given position.
     * This method is useful for identifying which word
     * was clicked or swiped in methods that handle user events.
     * @param position
     * @return The word at the given position
     **/
    public Word getWordAtPosition(int position) {
        return mWords.get(position);
    }

    /** declare an interface for selected bookmarked word **/
    // Preference: https://www.youtube.com/watch?v=ujKDN_ZtGHQ
    public interface SelectedWord {
        void selectedWord(Word word);
    }

    /** Set the place for the adapter to display individual bookmarked word **/
    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        /** View holder constructor
         * @param itemView View **/
        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.bookmarkedWordTv);

            wordItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedWord.selectedWord(mWords.get(getAdapterPosition()));
                }
            });
        }
    }
}
