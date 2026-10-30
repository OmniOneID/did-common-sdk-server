package org.omnione.did.common.datamodel.data.vc;

import org.omnione.did.core.data.rest.ClaimInfo;

import java.util.HashMap;

/**
 * CredentialSubject represents the entity described by a Verifiable Credential.
 *
 * <p>In the W3C VC spec, {@code credentialSubject.id} is the DID of the subject.
 * This class carries the holder's DID and the claim data to be encoded in the VC.</p>
 *
 * <p><b>Usage example (Protocol class building VC parameters):</b></p>
 * <pre>
 * {@code
 * CredentialSubject subject = new CredentialSubject();
 * subject.setHolderDid("did:omn:alice");
 * subject.setClaims(ClaimManager.build(
 *     "org.opendid.v1.subject", "o=Alice Inc",
 *     "org.opendid.v1.role",    "Issuer"
 * ));
 * }
 * </pre>
 */
public class CredentialSubject {

    /**
     * DID of the credential subject (holder).
     * Maps to {@code credentialSubject.id} in the W3C VC spec.
     */
    private String holderDid;

    /**
     * Claim data to include in the VC.
     * Key is the claim code (e.g., "org.opendid.v1.role").
     * Value is a {@link ClaimInfo} containing code and UTF-8 encoded value bytes.
     */
    private HashMap<String, ClaimInfo> claims;

    public CredentialSubject() {
    }

    public CredentialSubject(String holderDid, HashMap<String, ClaimInfo> claims) {
        this.holderDid = holderDid;
        this.claims = claims;
    }

    public String getHolderDid() {
        return holderDid;
    }

    public void setHolderDid(String holderDid) {
        this.holderDid = holderDid;
    }

    public HashMap<String, ClaimInfo> getClaims() {
        return claims;
    }

    public void setClaims(HashMap<String, ClaimInfo> claims) {
        this.claims = claims;
    }

    @Override
    public String toString() {
        return "CredentialSubject{" +
                "holderDid='" + holderDid + '\'' +
                '}';
    }
}
