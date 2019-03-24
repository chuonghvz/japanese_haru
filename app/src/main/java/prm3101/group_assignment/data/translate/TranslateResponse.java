package prm3101.group_assignment.data.translate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chuonghv on 12/25/17.
 */

public class TranslateResponse implements Serializable {

    @SuppressWarnings("unused")
    @SerializedName("responseData")
    @Expose
    private ResponseData responseData;

    @SuppressWarnings("unused")
    @SerializedName("quotaFinished")
    @Expose
    private Boolean quotaFinished;

    @SuppressWarnings("unused")
    @SerializedName("responseDetails")
    @Expose
    private String responseDetails;

    @SuppressWarnings("unused")
    @SerializedName("responseStatus")
    @Expose
    private Integer responseStatus;

    @SuppressWarnings("unused")
    @SerializedName("responderId")
    @Expose
    private String responderId;

    @SuppressWarnings("unused")
    @SerializedName("matches")
    @Expose
    private List<Match> matches = null;

    public ResponseData getResponseData() {
        return responseData;
    }

}
