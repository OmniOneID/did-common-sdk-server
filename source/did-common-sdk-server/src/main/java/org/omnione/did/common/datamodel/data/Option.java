package org.omnione.did.common.datamodel.data;

import com.google.gson.annotations.SerializedName;

public class Option {
    @SerializedName("allowUserInit")
    private Boolean allowUserInit;

    @SerializedName("allowIssuerInit")
    private Boolean allowIssuerInit;

    @SerializedName("delegatedIssuance")
    private Boolean delegatedIssuance;

    public Option() {
    }

    public Option(Boolean allowUserInit, Boolean allowIssuerInit, Boolean delegatedIssuance) {
        this.allowUserInit = allowUserInit;
        this.allowIssuerInit = allowIssuerInit;
        this.delegatedIssuance = delegatedIssuance;
    }

    public Boolean getAllowUserInit() {
        return allowUserInit;
    }

    public void setAllowUserInit(Boolean allowUserInit) {
        this.allowUserInit = allowUserInit;
    }

    public Boolean getAllowIssuerInit() {
        return allowIssuerInit;
    }

    public void setAllowIssuerInit(Boolean allowIssuerInit) {
        this.allowIssuerInit = allowIssuerInit;
    }

    public Boolean getDelegatedIssuance() {
        return delegatedIssuance;
    }

    public void setDelegatedIssuance(Boolean delegatedIssuance) {
        this.delegatedIssuance = delegatedIssuance;
    }

    @Override
    public String toString() {
        return "Option{" +
                "allowUserInit=" + allowUserInit +
                ", allowIssuerInit=" + allowIssuerInit +
                ", delegatedIssuance=" + delegatedIssuance +
                '}';
    }
}
