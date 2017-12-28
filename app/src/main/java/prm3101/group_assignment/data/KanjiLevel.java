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

    public KanjiLevel(String character, String sound, String mean, String onyomi, String kunyomi) {
        this.character = character;
        this.sound = sound;
        this.mean = mean;
        this.onyomi = onyomi;
        this.kunyomi = kunyomi;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getOnyomi() {
        return onyomi;
    }

    public void setOnyomi(String onyomi) {
        this.onyomi = onyomi;
    }

    public String getKunyomi() {
        return kunyomi;
    }

    public void setKunyomi(String kunyomi) {
        this.kunyomi = kunyomi;
    }

    @Override
    public String toString() {
        return "KanjiLevel{" +
                "character='" + character + '\'' +
                ", sound='" + sound + '\'' +
                ", mean='" + mean + '\'' +
                ", onyomi='" + onyomi + '\'' +
                ", kunyomi='" + kunyomi + '\'' +
                '}';
    }
}
