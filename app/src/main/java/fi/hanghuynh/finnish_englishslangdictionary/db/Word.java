package fi.hanghuynh.finnish_englishslangdictionary.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "finnish_word")
    public String finnishWord;

    @ColumnInfo(name = "english_translation")
    public String englishTranslation;

    public boolean bookmarked;

    public Word(int id, String finnishWord, String englishTranslation) {
        this.id = id;
        this.finnishWord = finnishWord;
        this.englishTranslation = englishTranslation;
        this.bookmarked = false;
    }

}
