package prm3101.group_assignment.data;

/**
 * Created by chuonghv on 12/27/17.
 */

public class KanjiLevel {
    private String character;
    private String sound;
    private String mean;
    private String onyomi;
    private String kunyomi;
    private String kanjiData;

    public KanjiLevel(String character, String sound, String mean, String onyomi, String kunyomi, String kanjiData) {
        this.character = character;
        this.sound = sound;
        this.mean = mean;
        this.onyomi = onyomi;
        this.kunyomi = kunyomi;
        this.kanjiData = kanjiData;
    }

    public String getCharacter() {
        return character;
    }

    public String getMean() {
        return mean;
    }

    public String getOnyomi() {
        return onyomi;
    }

    public String getKunyomi() {
        return kunyomi;
    }

    public String getKanjiData() {
        return kanjiData;
    }
}
