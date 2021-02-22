package fi.hanghuynh.finnish_englishslangdictionary.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private List<Word> words;
    public abstract WordDAO wordDAO();
    private static AppDatabase INSTANCE;

    public AppDatabase() {
        words = new ArrayList<>();
        words.add(new Word(1, "Bemari", "BMW"));
        words.add(new Word(2, "äijä", "dude"));
        words.add(new Word(3, "bileet", "party"));
        words.add(new Word(4, "bodari", "bodybuilder"));
        words.add(new Word(5, "botski", "boat or ship"));
        words.add(new Word(6, "Cittari", "K-citymarket"));
        words.add(new Word(7, "dekkari", "detective story"));
        words.add(new Word(8, "demarit", "social democrats"));
        words.add(new Word(9, "divari", " antique store; also means division on ice hockey"));
        words.add(new Word(10, "dödö", "deodorant"));
        words.add(new Word(11, "dösä", "bus"));
        words.add(new Word(12, "dumari", "referee"));
        words.add(new Word(13, "duunari", "manual worker"));
        words.add(new Word(14, "enskari", "premiere or opening night"));
        words.add(new Word(15, "eskari", "preschool"));
        words.add(new Word(16, "festarit", "music festival"));
        words.add(new Word(17, "fillari", "bicycle"));
        words.add(new Word(18, "Fotari", "Photoshop"));
        words.add(new Word(19, "hamppari", "hamburger"));
        words.add(new Word(20, "henkari", "coat hanger"));
        words.add(new Word(21, "henkkari", "ID "));
        words.add(new Word(22, "Hesari", "Helsingin Sanomat newspaper"));
        words.add(new Word(23, "hevari", "heavy metal musician/enthusiast"));
        words.add(new Word(24, "hiilari", "carbohydrates"));
        words.add(new Word(25, "hodari", "hot dog"));
        words.add(new Word(26, "hokkarit", "hockey skates"));
        words.add(new Word(27, "iskari", "shock absorber"));
        words.add(new Word(28, "jälkkäri", "dessert;also means detention"));
        words.add(new Word(29, "järkkäri", "security guy"));
        words.add(new Word(30, "kaasari", "carburetor"));
        words.add(new Word(31, "kalsari", "long johns"));
        words.add(new Word(32, "kännykä", "cellphone"));
        words.add(new Word(33, "kirppis", "flea market or thrift shop"));
        words.add(new Word(34, "klemmari", "paperclip"));
        words.add(new Word(35, "kokkarit", "cocktail party"));
        words.add(new Word(36, "kommari", "communist"));
        words.add(new Word(37, "Kraiskeri", "Chrysler"));
        words.add(new Word(38, "kumpparit", "rain boots"));
        words.add(new Word(39, "kunnari", "home run in baseball"));
        words.add(new Word(40, "kuohari", "sparkling wine"));
        words.add(new Word(41, "kylppäri", "bathroom"));
        words.add(new Word(42, "lande", "countryside"));
        words.add(new Word(43, "läppäri", "laptop"));
        words.add(new Word(44, "leffa", "movie"));
        words.add(new Word(45, "leggarit", "leggings"));
        words.add(new Word(46, "lenkkarit", "sneakers"));
        words.add(new Word(47, "limppari", "lemonade"));
        words.add(new Word(48, "limu", "soft drink "));
        words.add(new Word(49, "linkkari", "pocket knife"));
        words.add(new Word(50, "lokari", "mudguard"));
        words.add(new Word(51, "lukkari", "school timetable"));
        words.add(new Word(52, "luuseri", "loser"));
        words.add(new Word(53, "maiharit", "combat boots"));
        words.add(new Word(54, "Maikkari", "TV-station MTV3"));
        words.add(new Word(55, "makkari", "bedroom"));
        words.add(new Word(56, "Mäkkäri", "McDonald"));
        words.add(new Word(57, "merkkari", "pirate"));
        words.add(new Word(58, "molari", "goal keeper"));
        words.add(new Word(59, "motskari", "motorcycle"));
        words.add(new Word(60, "muskari", "musical school"));
        words.add(new Word(61, "naamari", "mask"));
        words.add(new Word(62, "näkkäri", "cripsbread"));
        words.add(new Word(63, "neukkari", "meeting room"));
        words.add(new Word(64, "nilkkarit", "ankle boots"));
        words.add(new Word(65, "nimpparit", "name day"));
        words.add(new Word(66, "nyyttärit", "potluck gathering "));
        words.add(new Word(67, "olkkari", "living room"));
        words.add(new Word(68, "ostari", "shopping mall"));
        words.add(new Word(69, "päättäri", "the last stop of a railway or other transport route"));
        words.add(new Word(70, "pannari", "pancakes"));
        words.add(new Word(71, "penkkarit", "school graduation day"));
        words.add(new Word(72, "piikkarit", "soccer shoes"));
        words.add(new Word(73, "piilarit", "contact lenses"));
        words.add(new Word(74, "pikkari", "panties or briefs"));
        words.add(new Word(75, "Pleikkari", "PlayStation "));
        words.add(new Word(76, "pokkari", "paperback"));
        words.add(new Word(77, "polttarit", "bachelor party"));
        words.add(new Word(78, "portsari", "bouncer"));
        words.add(new Word(79, "pukkari", "changing room"));
        words.add(new Word(80, "rankkari", "penalty kick"));
        words.add(new Word(81, "rekkari", "license plate"));
        words.add(new Word(82, "rööki", "cigarette"));
        words.add(new Word(83, "roudari", "roadie"));
        words.add(new Word(84, "ruuvari", "screw driver"));
        words.add(new Word(85, "sivari", "police in civilian clothes"));
        words.add(new Word(86, "skeittari", "skater"));
        words.add(new Word(87, "snägäri", "(hot dog) stand or kiosk"));
        words.add(new Word(88, "spora", "tram"));
        words.add(new Word(89, "Stadi", "Helsinki"));
        words.add(new Word(90, "stoge", "train"));
        words.add(new Word(91, "sytkäri", "lighter"));
        words.add(new Word(92, "talkkari", "janitor"));
        words.add(new Word(93, "tekstari", "text message"));
        words.add(new Word(94, "telkkari", "television"));
        words.add(new Word(95, "tennarit", "tennis shoes"));
        words.add(new Word(96, "terkkari", "nurse"));
        words.add(new Word(97, "termari", "thermal bottle"));
        words.add(new Word(98, "tikkari", "lollipop"));
        words.add(new Word(99, "toppari", "midfield player in soccer "));
        words.add(new Word(100, "tuparit", "house-warming party"));
        words.add(new Word(101, "uikkarit", "swimming suit"));
        words.add(new Word(102, "välkkäri", "break time at school"));
        words.add(new Word(103, "vapari", "free kick in soccer"));
        words.add(new Word(104, "vekkari", "alarm clock"));
        words.add(new Word(105, "verkkarit", "sweatpants"));
        words.add(new Word(106, "vetskari", "zipper"));
        words.add(new Word(107, "vinkkari", "car blinker"));
        words.add(new Word(108, "voikkari", "sandwich"));
        words.add(new Word(109, "Volkkari", "Volkswagen "));
    }

    public static AppDatabase getDbInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "APP_DB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public Word getWord(int index) {
        return words.get(index);
    }

    public int getWordArraySize() {
        return words.size();
    }

}
