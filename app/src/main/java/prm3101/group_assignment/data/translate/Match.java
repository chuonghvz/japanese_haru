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
    @SerializedName("segment")
    @Expose
    private String segment;
    @SerializedName("translation")
    @Expose
    private String translation;
    @SerializedName("quality")
    @Expose
    private String quality;
    @SerializedName("reference")
    @Expose
    private Object reference;
    @SerializedName("usage-count")
    @Expose
    private Integer usageCount;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("created-by")
    @Expose
    private String createdBy;
    @SerializedName("last-updated-by")
    @Expose
    private String lastUpdatedBy;
    @SerializedName("create-date")
    @Expose
    private String createDate;
    @SerializedName("last-update-date")
    @Expose
    private String lastUpdateDate;
    @SerializedName("match")
    @Expose
    private Double match;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Object getReference() {
        return reference;
    }

    public void setReference(Object reference) {
        this.reference = reference;
    }

    public Integer getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(Integer usageCount) {
        this.usageCount = usageCount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Double getMatch() {
        return match;
    }

    public void setMatch(Double match) {
        this.match = match;
    }

}
