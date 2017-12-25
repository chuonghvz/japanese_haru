package prm3101.group_assignment.data.translate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chuonghv on 12/25/17.
 */

public class TranslateResponse implements Serializable {

    @SerializedName("responseData")
    @Expose
    private ResponseData responseData;
    @SerializedName("quotaFinished")
    @Expose
    private Boolean quotaFinished;
    @SerializedName("responseDetails")
    @Expose
    private String responseDetails;
    @SerializedName("responseStatus")
    @Expose
    private Integer responseStatus;
    @SerializedName("responderId")
    @Expose
    private String responderId;
    @SerializedName("matches")
    @Expose
    private List<Match> matches = null;

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    public Boolean getQuotaFinished() {
        return quotaFinished;
    }

    public void setQuotaFinished(Boolean quotaFinished) {
        this.quotaFinished = quotaFinished;
    }

    public String getResponseDetails() {
        return responseDetails;
    }

    public void setResponseDetails(String responseDetails) {
        this.responseDetails = responseDetails;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponderId() {
        return responderId;
    }

    public void setResponderId(String responderId) {
        this.responderId = responderId;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "TranslateResponse{" +
                "responseData=" + responseData +
                ", quotaFinished=" + quotaFinished +
                ", responseDetails='" + responseDetails + '\'' +
                ", responseStatus=" + responseStatus +
                ", responderId='" + responderId + '\'' +
                ", matches=" + matches +
                '}';
    }
}
