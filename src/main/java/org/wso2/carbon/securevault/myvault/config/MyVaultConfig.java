package org.wso2.carbon.securevault.myvault.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.securevault.myvault.exception.MyVaultException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.wso2.carbon.securevault.myvault.common.MyVaultConstants.*;
import static org.wso2.carbon.securevault.myvault.common.MyVaultConstants.DEFAULT_ENGINE_VERSION;

public class MyVaultConfig {
    private static final Log log = LogFactory.getLog(MyVaultConfig.class);

    private static String address;

    private static String namespace;

    private static String path;

    private static int engineVersion;

    static {
        load();
    }

    /**
     * Load configurations.
     */
    public static void load() {

        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("Error while loading configurations from " + CONFIG_FILE_PATH, e);
        }

        try {
            address = getProperty(ADDRESS_PARAMETER, properties, true);
            namespace = getProperty(NAMESPACE_PARAMETER, properties, false);
            path = getProperty(PATH_PARAMETER, properties, true);

            String version = getProperty(ENGINE_VERSION_PARAMETER, properties, false);
            engineVersion = version != null ? Integer.parseInt(version) : DEFAULT_ENGINE_VERSION;
        } catch (MyVaultException e) {
            log.error(e.getMessage(), e);
        }

    }

    /**
     * Get vault addresss.
     *
     * @return
     */
    public static String getAddress() {

        return address;
    }

    /**
     * Get namespace.
     *
     * @return
     */
    public static String getNamespace() {

        return namespace;
    }

    /**
     * Get engine path.
     *
     * @return
     */
    public static String getPath() {

        return path;
    }

    /**
     * Get vault engine version.
     *
     * @return
     */
    public static int getEngineVersion() {

        return engineVersion;
    }

    /**
     * Check existence and get property
     *
     * @param key Key
     * @param properties Properties
     * @param required Property is required
     * @return
     * @throws MyVaultException
     */
    private static String getProperty(String key, Properties properties, boolean required) throws MyVaultException {

        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        } else {
            if (!required) {
                return null;
            } else {
                throw new MyVaultException("Required configuration is not found: " + key);
            }
        }
    }
}
