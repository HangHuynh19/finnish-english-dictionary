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
        words.add(new Word(1, "affa", "sunrise"));
        words.add(new Word(2, "äijä", "dude"));
        words.add(new Word(3, "aikarauta", "time"));
        words.add(new Word(4, "asiallinen", "good"));
        words.add(new Word(5, "assa", "station"));
        words.add(new Word(6, "bai", "hey"));
        words.add(new Word(7, "baidu", "sauna"));
        words.add(new Word(8, "bailut", "party"));
        words.add(new Word(9, "bamari", "police"));
        words.add(new Word(10, "banaani", "cell phone"));
        words.add(new Word(11, "bandi", "band(music)"));
        words.add(new Word(12, "Bemari", "BMW"));
        words.add(new Word(13, "bestis", "best friend"));
        words.add(new Word(14, "bileet", "party"));
        words.add(new Word(15, "bodari", "bodybuilder"));
        words.add(new Word(16, "botski", "boat or ship"));
        words.add(new Word(17, "broidi", "brother"));
        words.add(new Word(18, "brsta", "beer"));
        words.add(new Word(19, "citi", "center"));
        words.add(new Word(20, "Cittari", "K-citymarket"));
        words.add(new Word(21, "daa", "eat"));
        words.add(new Word(22, "daagis", "nursery"));
        words.add(new Word(23, "datsa", "summer cottage"));
        words.add(new Word(24, "dekkari", "detective story"));
        words.add(new Word(25, "demarit", "social democrats"));
        words.add(new Word(26, "denari", "money"));
        words.add(new Word(27, "divari", "antique store"));
        words.add(new Word(28, "dödö", "deoderant"));
        words.add(new Word(29, "dogi", "dog"));
        words.add(new Word(30, "dösä", "bus"));
        words.add(new Word(31, "dumari", "referee"));
        words.add(new Word(32, "duunari", "mannual worker"));
        words.add(new Word(33, "einis", "food"));
        words.add(new Word(34, "enskari", "premiere or opening night"));
        words.add(new Word(35, "esa", "first"));
        words.add(new Word(36, "eskari", "preschool"));
        words.add(new Word(37, "faija", "father"));
        words.add(new Word(38, "festarit", "music festival"));
        words.add(new Word(39, "fillari", "bicycle"));
        words.add(new Word(40, "Fotari", "Photoshop"));
        words.add(new Word(41, "frari", "driver"));
        words.add(new Word(42, "frdi", "finish"));
        words.add(new Word(43, "frendi", "friend"));
        words.add(new Word(44, "futis", "football"));
        words.add(new Word(45, "gille", "man"));
        words.add(new Word(46, "gissa", "girl"));
        words.add(new Word(47, "hamppari", "hamburger"));
        words.add(new Word(48, "henkari", "coat hanger"));
        words.add(new Word(49, "henkkari", "ID"));
        words.add(new Word(50, "Hesari", "Helsingin Sanomat newspaper"));
        words.add(new Word(51, "hevari", "heavy metal musician/enthusiast"));
        words.add(new Word(52, "hiilari", "carbonhydrates"));
        words.add(new Word(53, "hima", "home"));
        words.add(new Word(54, "hodari", "hotdog"));
        words.add(new Word(55, "hokkarit", "hockey skates"));
        words.add(new Word(56, "homma", "job"));
        words.add(new Word(57, "hyyraa", "rent"));
        words.add(new Word(58, "inssi", "engineer"));
        words.add(new Word(59, "iskari", "shock absorber"));
        words.add(new Word(60, "jäde", "ice cream"));
        words.add(new Word(61, "jälkkäri", "dessert"));
        words.add(new Word(62, "jannu", "boy"));
        words.add(new Word(63, "järkkäri", "security guy"));
        words.add(new Word(64, "kaasari", "carburetor"));
        words.add(new Word(65, "kaks", "two"));
        words.add(new Word(66, "kalsari", "long johns"));
        words.add(new Word(67, "kammari", "police station"));
        words.add(new Word(68, "kännykä", "cellphone"));
        words.add(new Word(69, "kirppis", "flea market or thrift shop"));
        words.add(new Word(70, "klemmari", "paperclip"));
        words.add(new Word(71, "koile", "school"));
        words.add(new Word(72, "kokkarit", "cocktail party"));
        words.add(new Word(73, "kol", "three"));
        words.add(new Word(74, "kommari", "communist"));
        words.add(new Word(75, "Kraiskeri", "Chrysler"));
        words.add(new Word(76, "kumpparit", "rainsboots"));
        words.add(new Word(77, "kunnari", "home run in baseball"));
        words.add(new Word(78, "kuohari", "sparkling wine"));
        words.add(new Word(79, "kylppäri", "bathroom"));
        words.add(new Word(80, "lande", "countryside"));
        words.add(new Word(81, "läppäri", "laptop"));
        words.add(new Word(82, "lastari", "truck"));
        words.add(new Word(83, "leffa", "movie"));
        words.add(new Word(84, "leggarit", "leggings"));
        words.add(new Word(85, "leguri", "doctor"));
        words.add(new Word(86, "lenkkarit", "sneakers"));
        words.add(new Word(87, "liiva", "nice"));
        words.add(new Word(88, "limppari", "lemonade"));
        words.add(new Word(89, "limu", "soft drink"));
        words.add(new Word(90, "linkkari", "pocket knife"));
        words.add(new Word(91, "lokari", "mudguard"));
        words.add(new Word(92, "lukkari", "school timetable"));
        words.add(new Word(93, "luuseri", "loser"));
        words.add(new Word(94, "maiharit", "combat boots"));
        words.add(new Word(95, "Maikkari", "TV-station MTV3"));
        words.add(new Word(96, "makkari", "bedroom"));
        words.add(new Word(97, "Mäkkäri", "McDonald"));
        words.add(new Word(98, "merkkari", "pirate"));
        words.add(new Word(99, "molari", "goal keeper"));
        words.add(new Word(100, "motskari", "motorcycle"));
        words.add(new Word(101, "muija", "wife"));
        words.add(new Word(102, "muskari", "musical school"));
        words.add(new Word(103, "naamari", "mask"));
        words.add(new Word(104, "näkkäri", "cripsbread"));
        words.add(new Word(105, "nel", "four"));
        words.add(new Word(106, "neukkari", "meeting room"));
        words.add(new Word(107, "nilkkarit", "ankle boots"));
        words.add(new Word(108, "nimpparit", "name day"));
        words.add(new Word(109, "nyyttärit", "potluck gathering"));
        words.add(new Word(110, "olkkari", "living room"));
        words.add(new Word(111, "ostari", "shopping mall"));
        words.add(new Word(112, "päättäri", "the last stop of a railway or other transport route"));
        words.add(new Word(113, "paku", "van"));
        words.add(new Word(114, "pannari", "pancakes"));
        words.add(new Word(115, "penkkarit", "school graduation day"));
        words.add(new Word(116, "piikkarit", "soccer shoes"));
        words.add(new Word(117, "piilarit", "contact lenses"));
        words.add(new Word(118, "pikkari", "panties or briefs"));
        words.add(new Word(119, "Pleikkari", "PlayStation"));
        words.add(new Word(120, "pokkari", "paperback"));
        words.add(new Word(121, "polttarit", "bachelor party"));
        words.add(new Word(122, "portsari", "bouncer"));
        words.add(new Word(123, "pukkari", "changing room"));
        words.add(new Word(124, "rafla", "restaurant"));
        words.add(new Word(125, "rankkari", "penalty kick"));
        words.add(new Word(126, "rekkari", "license plate"));
        words.add(new Word(127, "rööki", "cigarette"));
        words.add(new Word(128, "roudari", "roadie"));
        words.add(new Word(129, "ruuvari", "screw driver"));
        words.add(new Word(130, "sivari", "police in civilian clothes"));
        words.add(new Word(131, "skeittari", "skater"));
        words.add(new Word(132, "snägäri", "(hot dog) stand or kiosk"));
        words.add(new Word(133, "spora", "tram"));
        words.add(new Word(134, "Stadi", "Helsinki"));
        words.add(new Word(135, "stoge", "train"));
        words.add(new Word(136, "systeri", "sister"));
        words.add(new Word(137, "sytkäri", "lighter"));
        words.add(new Word(138, "talkkari", "janitor"));
        words.add(new Word(139, "tekstari", "text message"));
        words.add(new Word(140, "telkkari", "television"));
        words.add(new Word(141, "tennarit", "tennis shoes"));
        words.add(new Word(142, "terkkari", "nurse"));
        words.add(new Word(143, "termari", "thermal bottle"));
        words.add(new Word(144, "tikkari", "lollipop"));
        words.add(new Word(145, "toppari", "midfield player in soccer"));
        words.add(new Word(146, "tuparit", "house-warming party"));
        words.add(new Word(147, "tyypi", "person"));
        words.add(new Word(148, "uikkarit", "swimming suit"));
        words.add(new Word(149, "välkkäri", "breaktime at school"));
        words.add(new Word(150, "vapari", "free kick in soccer"));
        words.add(new Word(151, "veke", "away"));
        words.add(new Word(152, "vekkari", "alarm clock"));
        words.add(new Word(153, "ventata", "to wait"));
        words.add(new Word(154, "verkkarit", "sweatpants"));
        words.add(new Word(155, "vetskari", "zipper"));
        words.add(new Word(156, "vinkkari", "car blinker"));
        words.add(new Word(157, "voikkari", "sandwich"));
        words.add(new Word(158, "Volkkari", "Volkswagen"));
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
