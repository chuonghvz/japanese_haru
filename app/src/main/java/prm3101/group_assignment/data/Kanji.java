package prm3101.group_assignment.data;

/**
 * Created by ASUS on 21/09/2017.
 */

public class Kanji {
    private String kanji;
    private String meaning;
    private String kanjiImage;
    private String kanjiSound;

    public Kanji(String kanji, String meaning, String kanjiImage, String kanjiSound) {
        this.kanji = kanji;
        this.meaning = meaning;
        this.kanjiImage = kanjiImage;
        this.kanjiSound = kanjiSound;
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

    public String getKanjiImage() {
        return kanjiImage;
    }

    public void setKanjiImage(String kanjiImage) {
        this.kanjiImage = kanjiImage;
    }

    public String getKanjiSound() {
        return kanjiSound;
    }

    public void setKanjiSound(String kanjiSound) {
        this.kanjiSound = kanjiSound;
    }
}
