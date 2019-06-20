
package com.nytsample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Media {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("subtype")
    @Expose
    private String subtype;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("approved_for_syndication")
    @Expose
    private Integer approved_for_syndication;
    @SerializedName("media-metadata")
    @Expose
    private List<Media_Metadata> mediaMetadata = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getApprovedForSyndication() {
        return approved_for_syndication;
    }

    public void setApprovedForSyndication(Integer approved_for_syndication) {
        this.approved_for_syndication = approved_for_syndication;
    }

    public List<Media_Metadata> getMediaMetadata() {
        return mediaMetadata;
    }

    public void setMediaMetadata(List<Media_Metadata> mediaMetadata) {
        this.mediaMetadata = mediaMetadata;
    }

}
