package org.omnione.did.common.datamodel.data.ecdh;

import com.google.gson.annotations.SerializedName;
import org.omnione.did.common.datamodel.enums.crypto.SymmetricCipherType;
import java.util.List;

public class Candidate {
    @SerializedName("ciphers")
    private List<SymmetricCipherType> ciphers;

    public Candidate() {
    }

    public Candidate(List<SymmetricCipherType> ciphers) {
        this.ciphers = ciphers;
    }

    public List<SymmetricCipherType> getCiphers() {
        return ciphers;
    }

    public void setCiphers(List<SymmetricCipherType> ciphers) {
        this.ciphers = ciphers;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "ciphers=" + ciphers +
                '}';
    }
}
