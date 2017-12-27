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
    private String ex_1_hira;
    private String ex_1_mean;
    private String ex_2_hira;
    private String ex_2_mean;

    public KanjiLevel(String character, String sound, String mean, String onyomi, String kunyomi,
                      String ex_1_hira, String ex_1_mean, String ex_2_hira, String ex_2_mean) {
        this.character = character;
        this.sound = sound;
        this.mean = mean;
        this.onyomi = onyomi;
        this.kunyomi = kunyomi;
        this.ex_1_hira = ex_1_hira;
        this.ex_1_mean = ex_1_mean;
        this.ex_2_hira = ex_2_hira;
        this.ex_2_mean = ex_2_mean;
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

    public String getEx_1_hira() {
        return ex_1_hira;
    }

    public void setEx_1_hira(String ex_1_hira) {
        this.ex_1_hira = ex_1_hira;
    }

    public String getEx_1_mean() {
        return ex_1_mean;
    }

    public void setEx_1_mean(String ex_1_mean) {
        this.ex_1_mean = ex_1_mean;
    }

    public String getEx_2_hira() {
        return ex_2_hira;
    }

    public void setEx_2_hira(String ex_2_hira) {
        this.ex_2_hira = ex_2_hira;
    }

    public String getEx_2_mean() {
        return ex_2_mean;
    }

    public void setEx_2_mean(String ex_2_mean) {
        this.ex_2_mean = ex_2_mean;
    }

    @Override
    public String toString() {
        return "KanjiLevel{" +
                "character='" + character + '\'' +
                ", sound='" + sound + '\'' +
                ", mean='" + mean + '\'' +
                ", onyomi='" + onyomi + '\'' +
                ", kunyomi='" + kunyomi + '\'' +
                ", ex_1_hira='" + ex_1_hira + '\'' +
                ", ex_1_mean='" + ex_1_mean + '\'' +
                ", ex_2_hira='" + ex_2_hira + '\'' +
                ", ex_2_mean='" + ex_2_mean + '\'' +
                '}';
    }
}
