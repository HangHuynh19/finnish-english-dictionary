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

    public String getFinnishWord() {
        return this.finnishWord;
    }

    public int getId() {
        return this.id;
    }

    public boolean bookmark() {
        this.bookmarked = true;
        return bookmarked;
    }

    public boolean undoBookmark() {
        this.bookmarked = false;
        return bookmarked;
    }

}
