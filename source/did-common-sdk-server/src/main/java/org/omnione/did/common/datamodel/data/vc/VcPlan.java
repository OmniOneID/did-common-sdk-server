package org.omnione.did.common.datamodel.data.vc;

import com.google.gson.annotations.SerializedName;
import org.omnione.did.common.datamodel.data.Option;
import org.omnione.did.common.datamodel.data.zkp.CredentialDefinition;
import java.util.List;

public class VcPlan {
    @SerializedName("vcPlanId")
    private String vcPlanId;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("ref")
    private String ref;

    @SerializedName("logo")
    private LogoImage logo;

    @SerializedName("validFrom")
    private String validFrom;

    @SerializedName("validUntil")
    private String validUntil;

    @SerializedName("tags")
    private List<String> tags;

    @SerializedName("credentialSchema")
    private CredentialSchema credentialSchema;

    @SerializedName("credentialDefinition")
    private CredentialDefinition credentialDefinition;

    @SerializedName("option")
    private Option option;

    @SerializedName("delegator")
    private String delegator;

    @SerializedName("allowedIssuers")
    private List<String> allowedIssuers;

    @SerializedName("manager")
    private String manager;

    public VcPlan() {
    }

    public VcPlan(String vcPlanId, String name, String description, String ref, LogoImage logo, String validFrom,
            String validUntil, List<String> tags, CredentialSchema credentialSchema,
            CredentialDefinition credentialDefinition, Option option, String delegator, List<String> allowedIssuers,
            String manager) {
        this.vcPlanId = vcPlanId;
        this.name = name;
        this.description = description;
        this.ref = ref;
        this.logo = logo;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.tags = tags;
        this.credentialSchema = credentialSchema;
        this.credentialDefinition = credentialDefinition;
        this.option = option;
        this.delegator = delegator;
        this.allowedIssuers = allowedIssuers;
        this.manager = manager;
    }

    // Getters and Setters
    public String getVcPlanId() {
        return vcPlanId;
    }

    public void setVcPlanId(String vcPlanId) {
        this.vcPlanId = vcPlanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public LogoImage getLogo() {
        return logo;
    }

    public void setLogo(LogoImage logo) {
        this.logo = logo;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public CredentialSchema getCredentialSchema() {
        return credentialSchema;
    }

    public void setCredentialSchema(CredentialSchema credentialSchema) {
        this.credentialSchema = credentialSchema;
    }

    public CredentialDefinition getCredentialDefinition() {
        return credentialDefinition;
    }

    public void setCredentialDefinition(CredentialDefinition credentialDefinition) {
        this.credentialDefinition = credentialDefinition;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public String getDelegator() {
        return delegator;
    }

    public void setDelegator(String delegator) {
        this.delegator = delegator;
    }

    public List<String> getAllowedIssuers() {
        return allowedIssuers;
    }

    public void setAllowedIssuers(List<String> allowedIssuers) {
        this.allowedIssuers = allowedIssuers;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "VcPlan{" +
                "vcPlanId='" + vcPlanId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
