package fi.hanghuynh.finnish_englishslangdictionary.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface WordDAO {
    @Query("SELECT * FROM Word")
    List<Word> getAllWords();

    @Query("DELETE FROM Word")
    void deleteAll();

    @Query("SELECT * from Word LIMIT 1")
    Word[] getAnyWord();

    @Insert
    void insertWord(Word... words);

    @Query("SELECT * FROM Word WHERE finnish_word LIKE :search")
    List<Word> findWord(String search);

    @Query("SELECT finnish_word FROM Word")
    List<String> loadFinnishWordArray();

    @Query("SELECT english_translation FROM Word")
    List<String> loadEnglishTranslationArray();
}
