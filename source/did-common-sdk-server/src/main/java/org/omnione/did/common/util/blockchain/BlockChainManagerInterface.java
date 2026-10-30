package org.omnione.did.common.util.blockchain;

import org.omnione.did.common.datamodel.data.vc.VcMetadata;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.data.model.did.DidDocument;
import org.omnione.did.data.model.enums.did.DidDocStatus;
import org.omnione.did.data.model.enums.vc.VcStatus;

/**
 * Interface for Blockchain Manager.
 */
public interface BlockChainManagerInterface {
    void registerDidDocument(String did, DidDocument didDocument) throws CommonSdkException;

    DidDocument getDidDocument(String did) throws CommonSdkException;

    void registerVcMetadata(String vcId, VcMetadata vcMetadata) throws CommonSdkException;

    VcMetadata getVcMetadata(String vcId) throws CommonSdkException;

    DidDocument updateDidDocStatus(String did, DidDocStatus status) throws CommonSdkException;

    void updateVcStatus(String vcId, VcStatus status) throws CommonSdkException;

    void removeIndex(String index) throws CommonSdkException;

    void removeIndexAll() throws CommonSdkException;
}
