package org.wso2.carbon.securevault.myvault.common;

import org.wso2.carbon.utils.CarbonUtils;

import java.io.File;

public class MyVaultConstants {
    private MyVaultConstants() {}

    public static final String CONFIG_FILE_PATH = CarbonUtils.getCarbonConfigDirPath() + File.separator +
            "security" + File.separator + "secret-conf.properties";

    public static final String ADDRESS_PARAMETER = "secretRepositories.vault.properties.address";
    public static final String NAMESPACE_PARAMETER = "secretRepositories.vault.properties.namespace";
    public static final String PATH_PARAMETER = "secretRepositories.vault.properties.path.prefix";
    public static final String ENGINE_VERSION_PARAMETER = "secretRepositories.vault.properties.engineVersion";

    public static final int DEFAULT_ENGINE_VERSION = 2;

    public static final String VALUE_PARAMETER = "value";

    public static final String TOKEN_PARAMETER = "VAULT_TOKEN";
}
