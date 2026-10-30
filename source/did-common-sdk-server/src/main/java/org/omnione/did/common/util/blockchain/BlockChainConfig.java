package org.omnione.did.common.util.blockchain;

import org.omnione.did.data.model.enums.vc.RoleType;

/**
 * Configuration class for Blockchain connection.
 */
public class BlockChainConfig {
    private String propertiesFilePath;
    private RoleType roleType;
    private BlockChainType blockChainType;

    /**
     * Blockchain type enumeration.
     */
    public enum BlockChainType {
        FABRIC,  // Hyperledger Fabric
        EVM      // Ethereum/Besu
    }

    public BlockChainConfig() {
        this.roleType = RoleType.TAS;  // Default role type for backward compatibility
        this.blockChainType = BlockChainType.EVM;  // Default blockchain type for backward compatibility
    }

    public BlockChainConfig(String propertiesFilePath) {
        this.propertiesFilePath = propertiesFilePath;
        this.roleType = RoleType.TAS;  // Default role type for backward compatibility
        this.blockChainType = BlockChainType.EVM;  // Default blockchain type for backward compatibility
    }

    public BlockChainConfig(String propertiesFilePath, RoleType roleType) {
        this.propertiesFilePath = propertiesFilePath;
        this.roleType = roleType;
        this.blockChainType = BlockChainType.EVM;  // Default blockchain type for backward compatibility
    }

    /**
     * Full constructor with blockchain type.
     *
     * @param propertiesFilePath Path to blockchain configuration file
     * @param roleType Role type for blockchain operations
     * @param blockChainType Type of blockchain (FABRIC or EVM)
     */
    public BlockChainConfig(String propertiesFilePath, RoleType roleType, BlockChainType blockChainType) {
        this.propertiesFilePath = propertiesFilePath;
        this.roleType = roleType;
        this.blockChainType = blockChainType;
    }

    public String getPropertiesFilePath() {
        return propertiesFilePath;
    }

    public void setPropertiesFilePath(String propertiesFilePath) {
        this.propertiesFilePath = propertiesFilePath;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public BlockChainType getBlockChainType() {
        return blockChainType;
    }

    public void setBlockChainType(BlockChainType blockChainType) {
        this.blockChainType = blockChainType;
    }
}
