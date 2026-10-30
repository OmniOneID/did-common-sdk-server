package org.omnione.did.common.datamodel.data.vc;

import com.google.gson.annotations.SerializedName;
import org.omnione.did.common.datamodel.enums.LogoImageType;

public class LogoImage {
    @SerializedName("format")
    private LogoImageType format;

    @SerializedName("link")
    private String link;

    @SerializedName("value")
    private String value;

    public LogoImage() {
    }

    public LogoImage(LogoImageType format, String link, String value) {
        this.format = format;
        this.link = link;
        this.value = value;
    }

    public LogoImageType getFormat() {
        return format;
    }

    public void setFormat(LogoImageType format) {
        this.format = format;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LogoImage{" +
                "format=" + format +
                ", link='" + link + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
