package prm3101.group_assignment.data;

import java.io.Serializable;

/**
 * Created by ASUS on 21/09/2017.
 */

public class Kanji implements Serializable{
    private String kanji;
    private String meaning;
    private String kanjiData;

    public Kanji(String kanji, String meaning, String kanjiData) {
        this.kanji = kanji;
        this.meaning = meaning;
        this.kanjiData = kanjiData;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getKanjiData() {
        return kanjiData;
    }

    public void setKanjiData(String kanjiData) {
        this.kanjiData = kanjiData;
    }

}
