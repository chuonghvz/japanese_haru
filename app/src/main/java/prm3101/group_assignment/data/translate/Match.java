package prm3101.group_assignment.data.translate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by chuonghv on 12/25/17.
 */

public class Match implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;

    @SuppressWarnings("unused")
    @SerializedName("segment")
    @Expose
    private String segment;

    @SuppressWarnings("unused")
    @SerializedName("translation")
    @Expose
    private String translation;

    @SuppressWarnings("unused")
    @SerializedName("quality")
    @Expose
    private String quality;

    @SuppressWarnings("unused")
    @SerializedName("reference")
    @Expose
    private Object reference;

    @SuppressWarnings("unused")
    @SerializedName("usage-count")
    @Expose
    private Integer usageCount;

    @SuppressWarnings("unused")
    @SerializedName("subject")
    @Expose
    private String subject;

    @SuppressWarnings("unused")
    @SerializedName("created-by")
    @Expose
    private String createdBy;

    @SuppressWarnings("unused")
    @SerializedName("last-updated-by")
    @Expose
    private String lastUpdatedBy;

    @SuppressWarnings("unused")
    @SerializedName("create-date")
    @Expose
    private String createDate;

    @SuppressWarnings("unused")
    @SerializedName("last-update-date")
    @Expose
    private String lastUpdateDate;

    @SuppressWarnings("unused")
    @SerializedName("match")
    @Expose
    private Double match;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
