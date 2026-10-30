package org.omnione.did.common.service.ecdh;

import org.omnione.did.common.datamodel.data.ecdh.AccEcdh;
import org.omnione.did.common.datamodel.data.ecdh.EcdhReqData;
import org.omnione.did.common.datamodel.enums.crypto.SymmetricCipherType;
import org.omnione.did.common.datamodel.enums.crypto.SymmetricPaddingType;
import org.omnione.did.common.exception.CommonSdkException;

/**
 * ECDH Service Interface.
 * Definition based on SoT 5.2.1.
 */
public interface EcdhService {
        /**
         * Processing ECDH Request
         *
         * @param ecdhReqData       ECDH Request Data
         * @param serverCipherType  Server Symmetric Cipher Type
         * @param serverPaddingType Server Symmetric Padding Type
         * @return AccEcdh ECDH Accept Response
         * @throws CommonSdkException If processing fails
         */
        AccEcdh processEcdhRequest(
                        EcdhReqData ecdhReqData,
                        SymmetricCipherType serverCipherType,
                        SymmetricPaddingType serverPaddingType) throws CommonSdkException;

        /**
         * Verifying ECDH Request
         *
         * @param ecdhReqData       ECDH Request Data
         * @param expectedClientDid Expected Client DID
         * @throws CommonSdkException If verification fails
         */
        void verifyEcdhRequest(
                        EcdhReqData ecdhReqData,
                        String expectedClientDid) throws CommonSdkException;

        /**
         * Generating Session Key
         *
         * @param ecdhReqData      ECDH Request Data
         * @param serverPrivateKey Server Private Key
         * @param mergedNonce      Merged Nonce (Client Nonce + Server Nonce)
         * @return byte[] Session Key
         * @throws CommonSdkException If generation fails
         */
        byte[] generateSessionKey(
                        EcdhReqData ecdhReqData,
                        byte[] serverPrivateKey,
                        byte[] mergedNonce,
                        SymmetricCipherType serverCipherType) throws CommonSdkException;
}
