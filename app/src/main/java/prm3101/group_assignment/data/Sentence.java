package prm3101.group_assignment.data;

/**
 * Created by ASUS on 21/09/2017.
 */

public class Sentence {
    private String vietnameseMeaning;
    private String hiragana;
    private String romaji;
    private String sound;
    private boolean like;

    public Sentence(String vietnameseMeaning, String hiragana, String romaji, String sound, boolean like) {
        this.vietnameseMeaning = vietnameseMeaning;
        this.hiragana = hiragana;
        this.romaji = romaji;
        this.sound = sound;
        this.like = like;
    }

    public String getVietnameseMeaning() {
        return vietnameseMeaning;
    }

    public void setVietnameseMeaning(String vietnameseMeaning) {
        this.vietnameseMeaning = vietnameseMeaning;
    }

    public String getHiragana() {
        return hiragana;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
