package prm3101.group_assignment.data;

import java.io.Serializable;

/**
 * Created by chuonghv on 12/23/17.
 */

public class KanjiExample implements Serializable{
    private String hira;
    private String meaning;
    private String audio;

    public KanjiExample(String hira, String meaning, String audio) {
        this.hira = hira;
        this.meaning = meaning;
        this.audio = audio;
    }

    public String getHira() {
        return hira;
    }

    public void setHira(String hira) {
        this.hira = hira;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    @Override
    public String toString() {
        return "KanjiExample{" +
                "hira='" + hira + '\'' +
                ", meaning='" + meaning + '\'' +
                ", audio='" + audio + '\'' +
                '}';
    }
}
