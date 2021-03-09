package fi.hanghuynh.finnish_englishslangdictionary.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/** Modeling a Word object **/
@Entity
public class Word implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "finnish_word")
    public String finnishWord;

    @ColumnInfo(name = "english_translation")
    public String englishTranslation;

    public boolean bookmarked;

    /** Constructor
     * @param id int
     * @param finnishWord String
     * @param englishTranslation String
     */
    public Word(int id, String finnishWord, String englishTranslation) {
        this.id = id;
        this.finnishWord = finnishWord;
        this.englishTranslation = englishTranslation;
        this.bookmarked = false;
    }

    /** Get Finnish word from the object
     * @return String type of Finnish word
     */
    public String getFinnishWord() {
        return this.finnishWord;
    }

    /** Get ID of a Word object
     * @return interger number representing the ID
     */
    public int getId() {
        return this.id;
    }

    /** Method to bookmark a word
     * @return the state true for the property bookmarked of the object Word */
    public void bookmark() {
        this.bookmarked = true;
    }

    /** Method to undo a already bookmarked words
     * @return the state false for the property bookmarked of the object Word */
    public void undoBookmark() {
        this.bookmarked = false;
    }
}
