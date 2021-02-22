package fi.hanghuynh.finnish_englishslangdictionary.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

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
}
