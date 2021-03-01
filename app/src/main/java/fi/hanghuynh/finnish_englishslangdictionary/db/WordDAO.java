package fi.hanghuynh.finnish_englishslangdictionary.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface WordDAO {
    @Insert
    public void insertWord(Word... words);

    @Update
    public void updateUsers(Word... word);

    @Query("SELECT * FROM Word")
    public List<Word> getAllWords();

    @Query("DELETE FROM Word")
    public void deleteAll();

    @Query("SELECT * from Word LIMIT 1")
    public Word[] getAnyWord();

    @Query("SELECT * FROM Word WHERE finnish_word LIKE :search")
    public List<Word> findWord(String search);

    @Query("SELECT finnish_word FROM Word")
    public List<String> loadFinnishWordArray();

    @Query("SELECT english_translation FROM Word")
    public List<String> loadEnglishTranslationArray();

    @Query("SELECT finnish_word FROM Word WHERE bookmarked = 0")
    public List<String> loadBookmarkedWords();
}
