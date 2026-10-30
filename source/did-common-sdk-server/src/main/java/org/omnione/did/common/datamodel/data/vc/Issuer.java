package org.omnione.did.common.datamodel.data.vc;

import com.google.gson.annotations.SerializedName;

public class Issuer {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    @SerializedName("certVcRef")
    private String certVcRef;

    public Issuer() {
    }

    public Issuer(String id, String name, String url, String certVcRef) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.certVcRef = certVcRef;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCertVcRef() {
        return certVcRef;
    }

    public void setCertVcRef(String certVcRef) {
        this.certVcRef = certVcRef;
    }

    @Override
    public String toString() {
        return "Issuer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", certVcRef='" + certVcRef + '\'' +
                '}';
    }
}
