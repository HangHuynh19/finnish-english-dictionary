package fi.hanghuynh.finnish_englishslangdictionary.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// Preference: https://developer.android.com/reference/android/arch/persistence/room/Query
// https://developer.android.com/codelabs/android-training-livedata-viewmodel/#17

@Dao
public interface WordDAO {
    @Insert // Insert word to database
    public void insertWord(Word... words);

    @Update
    public void updateUsers(Word... word);

    @Update // Update a specific word in database
    public void updateWords(Word word);

    @Query("SELECT * FROM Word") // Select all words in the Word table
    public List<Word> getAllWords();

    @Query("DELETE FROM Word") // Delete all words in database
    public void deleteAll();

    @Query("SELECT * from Word LIMIT 1") // Get a word in database
    public Word[] getAnyWord();

    @Query("SELECT * FROM Word WHERE finnish_word IN (:search)") // Find Word object that has Finnish word equals search word
    public Word findWord(String search);

    @Query("SELECT finnish_word FROM Word") // Get all Finnish words in the database
    public List<String> loadFinnishWordArray();

    @Query("SELECT english_translation FROM Word") // Get al English translation from the database
    public List<String> loadEnglishTranslationArray();

    @Query("SELECT * FROM Word WHERE bookmarked = 1") // Get all the words that have bookmarked = 1
    public List<Word> loadBookmarkedWords();
}
