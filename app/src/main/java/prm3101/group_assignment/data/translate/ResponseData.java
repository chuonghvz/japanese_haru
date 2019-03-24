package prm3101.group_assignment.data.translate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by chuonghv on 12/25/17.
 */

public class ResponseData implements Serializable {

    @SuppressWarnings("unused")
    @SerializedName("translatedText")
    @Expose
    private String translatedText;

    @SuppressWarnings("unused")
    @SerializedName("match")
    @Expose
    private Double match;

    public String getTranslatedText() {
        return translatedText;
    }

}

