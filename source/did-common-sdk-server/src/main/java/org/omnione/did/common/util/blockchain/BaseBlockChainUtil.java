package org.omnione.did.common.util.blockchain;

import com.google.gson.Gson;
import org.omnione.did.ContractApi;
import org.omnione.did.ContractFactory;
import org.omnione.did.common.datamodel.data.vc.VcMetadata;
import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.data.model.did.DidDocument;
import org.omnione.did.data.model.did.DidDocAndStatus;
import org.omnione.did.data.model.did.InvokedDidDoc;
import org.omnione.did.data.model.enums.did.DidDocStatus;
import org.omnione.did.data.model.enums.vc.RoleType;
import org.omnione.did.data.model.enums.vc.VcStatus;
import org.omnione.did.fabric.FabricContractApi;
import org.omnione.exception.BlockChainException;

public class BaseBlockChainUtil {

    private static final Gson gson = new Gson();

    /**
     * Connect to the blockchain network.
     *
     * @param config BlockChainConfig
     * @return BlockChainManagerInterface
     * @throws CommonSdkException if connection fails
     */
    public static BlockChainManagerInterface connect(BlockChainConfig config) throws CommonSdkException {
        try {
            ContractApi contractApi;

            // Create ContractApi based on blockchain type from config
            if (config.getBlockChainType() == BlockChainConfig.BlockChainType.FABRIC) {
                contractApi = ContractFactory.FABRIC.create(config.getPropertiesFilePath());
            } else {
                contractApi = ContractFactory.EVM.create(config.getPropertiesFilePath());
            }

            final RoleType roleType = config.getRoleType(); // Get role type from config

            return new BlockChainManagerInterface() {
                @Override
                public void registerDidDocument(String did, DidDocument didDocument) throws CommonSdkException {
                    try {
                        // Convert to InvokedDidDoc required by ContractApi
                        InvokedDidDoc invokedDidDoc = gson.fromJson(gson.toJson(didDocument), InvokedDidDoc.class);
                        contractApi.registDidDoc(invokedDidDoc, roleType); // Use dynamic role type
                    } catch (BlockChainException e) {
                        throw new CommonSdkException(ErrorCode.BLOCKCHAIN_REGISTER_DID_DOCUMENT_FAILED,
                                "Blockchain error: " + e.getMessage());
                    } catch (Exception e) {
                        throw new CommonSdkException(ErrorCode.BLOCKCHAIN_REGISTER_DID_DOCUMENT_FAILED,
                                "Unexpected error: " + e.getMessage());
                    }
                }

                @Override
                public DidDocument getDidDocument(String did) throws CommonSdkException {
                    try {
                        return ((DidDocAndStatus) contractApi.getDidDoc(did)).getDocument();
                    } catch (BlockChainException e) {
                        throw new CommonSdkException(ErrorCode.BLOCKCHAIN_GET_DID_DOCUMENT_FAILED,
                                "Blockchain error: " + e.getMessage());
                    } catch (Exception e) {
                        throw new CommonSdkException(ErrorCode.BLOCKCHAIN_GET_DID_DOCUMENT_FAILED,
                                "Unexpected error: " + e.getMessage());
                    }
                }

                @Override
                public void registerVcMetadata(String vcId, VcMetadata vcMetadata) throws CommonSdkException {
                    try {
                        contractApi.registVcMetadata(vcMetadata);
                    } catch (BlockChainException e) {
                        throw new CommonSdkException(ErrorCode.BLOCKCHAIN_REGISTER_VC_METADATA_FAILED,
                                "Blockchain error: " + e.getMessage());
                    } catch (Exception e) {
                        throw new CommonSdkException(ErrorCode.BLOCKCHAIN_REGISTER_VC_METADATA_FAILED,
                                "Unexpected error: " + e.getMessage());
                    }
                }

                @Override
                public VcMetadata getVcMetadata(String vcId) throws CommonSdkException {
                    try {
                        Object result = contractApi.getVcMetadata(vcId);
                        return gson.fromJson(gson.toJson(result), VcMetadata.class);
                    } catch (BlockChainException e) {
                        throw new CommonSdkException(ErrorCode.BLOCKCHAIN_GET_VC_METADATA_FAILED,
                                "Blockchain error: " + e.getMessage());
                    } catch (Exception e) {
                        throw new CommonSdkException(ErrorCode.BLOCKCHAIN_GET_VC_METADATA_FAILED,
                                "Unexpected error: " + e.getMessage());
                    }
                }

                @Override
                public DidDocument updateDidDocStatus(String did, DidDocStatus status) throws CommonSdkException {
                    try {
                        return (DidDocument) contractApi.updateDidDocStatus(did, status);
                    } catch (BlockChainException e) {
                        throw new CommonSdkException(ErrorCode.BLOCKCHAIN_UPDATE_DID_DOCUMENT_FAILED,
                                "Blockchain error: " + e.getMessage());
                    } catch (Exception e) {
                        throw new CommonSdkException(ErrorCode.BLOCKCHAIN_UPDATE_DID_DOCUMENT_FAILED,
                                "Unexpected error: " + e.getMessage());
                    }
                }

                @Override
                public void updateVcStatus(String vcId, VcStatus status) throws CommonSdkException {
                    try {
                        contractApi.updateVcStatus(vcId, status);
                    } catch (BlockChainException e) {
                        throw new CommonSdkException(ErrorCode.BLOCKCHAIN_UPDATE_VC_STATUS_FAILED,
                                "Blockchain error: " + e.getMessage());
                    } catch (Exception e) {
                        throw new CommonSdkException(ErrorCode.BLOCKCHAIN_UPDATE_VC_STATUS_FAILED,
                                "Unexpected error: " + e.getMessage());
                    }
                }

                @Override
                public void removeIndex(String index) throws CommonSdkException {
                    try {
                        if (contractApi instanceof FabricContractApi) {
                            ((FabricContractApi) contractApi).removeIndex(index);
                        } else {
                            throw new CommonSdkException(ErrorCode.BLOCKCHAIN_REMOVE_INDEX_FAILED,
                                    "Not a Fabric contract information");
                        }
                    } catch (BlockChainException e) {
                        throw new CommonSdkException(ErrorCode.BLOCKCHAIN_REMOVE_INDEX_FAILED,
                                "Blockchain error: " + e.getMessage());
                    } catch (Exception e) {
                        throw new CommonSdkException(ErrorCode.BLOCKCHAIN_REMOVE_INDEX_FAILED,
                                "Unexpected error: " + e.getMessage());
                    }
                }

                @Override
                public void removeIndexAll() throws CommonSdkException {
                    try {
                        if (contractApi instanceof FabricContractApi) {
                            ((FabricContractApi) contractApi).removeAll();
                        } else {
                            throw new CommonSdkException(ErrorCode.BLOCKCHAIN_REMOVE_INDEX_FAILED,
                                    "Not a Fabric contract information");
                        }
                    } catch (BlockChainException e) {
                        throw new CommonSdkException(ErrorCode.BLOCKCHAIN_REMOVE_INDEX_FAILED,
                                "Blockchain error: " + e.getMessage());
                    } catch (Exception e) {
                        throw new CommonSdkException(ErrorCode.BLOCKCHAIN_REMOVE_INDEX_FAILED,
                                "Unexpected error: " + e.getMessage());
                    }
                }
            };
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.BLOCKCHAIN_CONNECT_FAILED,
                    "Failed to connect to blockchain: " + e.getMessage());
        }
    }

    public static void registerDidDocument(BlockChainManagerInterface blockChain, String did, DidDocument didDocument)
            throws CommonSdkException {
        blockChain.registerDidDocument(did, didDocument);
    }

    public static DidDocument getDidDocument(BlockChainManagerInterface blockChain, String did)
            throws CommonSdkException {
        return blockChain.getDidDocument(did);
    }

    public static void registerVcMetadata(BlockChainManagerInterface blockChain, String vcId, VcMetadata vcMetadata)
            throws CommonSdkException {
        blockChain.registerVcMetadata(vcId, vcMetadata);
    }

    public static VcMetadata getVcMetadata(BlockChainManagerInterface blockChain, String vcId) throws CommonSdkException {
        return blockChain.getVcMetadata(vcId);
    }

    public static DidDocument updateDidDocStatus(BlockChainManagerInterface blockChain, String did, DidDocStatus status)
            throws CommonSdkException {
        return blockChain.updateDidDocStatus(did, status);
    }

    public static void updateVcStatus(BlockChainManagerInterface blockChain, String vcId, VcStatus status)
            throws CommonSdkException {
        blockChain.updateVcStatus(vcId, status);
    }

    public static void removeIndex(BlockChainManagerInterface blockChain, String index) throws CommonSdkException {
        blockChain.removeIndex(index);
    }

    public static void removeIndexAll(BlockChainManagerInterface blockChain) throws CommonSdkException {
        blockChain.removeIndexAll();
    }

    /**
     * Retrieve a DID document and its status using an existing ContractApi instance.
     * This method mirrors the server-side findDidDocument pattern.
     *
     * @param contractApi ContractApi instance (caller manages lifecycle/singleton)
     * @param did         DID key URL to query
     * @return DidDocAndStatus containing the DID document and its status
     * @throws CommonSdkException if retrieval fails
     */
    public static DidDocAndStatus findDidDocument(ContractApi contractApi, String did) throws CommonSdkException {
        try {
            return (DidDocAndStatus) contractApi.getDidDoc(did);
        } catch (BlockChainException e) {
            throw new CommonSdkException(ErrorCode.BLOCKCHAIN_GET_DID_DOCUMENT_FAILED,
                    "Failed to get DID Document: " + e.getMessage());
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.BLOCKCHAIN_GET_DID_DOCUMENT_FAILED,
                    "Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Create ContractApi based on blockchain type.
     *
     * @param config BlockChainConfig with blockchain type
     * @return ContractApi (Fabric or EVM)
     * @throws CommonSdkException if creation fails
     */
    public static ContractApi createContractApi(BlockChainConfig config) throws CommonSdkException {
        try {
            if (config.getBlockChainType() == BlockChainConfig.BlockChainType.FABRIC) {
                return ContractFactory.FABRIC.create(config.getPropertiesFilePath());
            } else {
                return ContractFactory.EVM.create(config.getPropertiesFilePath());
            }
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.BLOCKCHAIN_CONNECT_FAILED,
                    "Failed to create ContractApi: " + e.getMessage());
        }
    }
}
