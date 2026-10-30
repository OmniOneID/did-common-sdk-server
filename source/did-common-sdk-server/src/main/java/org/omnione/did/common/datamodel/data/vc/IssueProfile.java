package org.omnione.did.common.datamodel.data.vc;

import com.google.gson.annotations.SerializedName;
import org.omnione.did.common.datamodel.enums.protocol.ProfileType;
import org.omnione.did.common.datamodel.enums.Encoding;
import org.omnione.did.common.datamodel.enums.Language;

public class IssueProfile {
    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private ProfileType type;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("encoding")
    private Encoding encoding;

    @SerializedName("language")
    private Language language;

    @SerializedName("profile")
    private VcProfile profile;

    public IssueProfile() {
    }

    public IssueProfile(String id, ProfileType type, String title, String description, Encoding encoding,
            Language language, VcProfile profile) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
        this.encoding = encoding;
        this.language = language;
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProfileType getType() {
        return type;
    }

    public void setType(ProfileType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Encoding getEncoding() {
        return encoding;
    }

    public void setEncoding(Encoding encoding) {
        this.encoding = encoding;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public VcProfile getProfile() {
        return profile;
    }

    public void setProfile(VcProfile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "IssueProfile{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", profile=" + profile +
                '}';
    }
}
