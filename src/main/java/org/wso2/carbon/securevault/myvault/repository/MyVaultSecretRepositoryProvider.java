package org.wso2.carbon.securevault.myvault.repository;

import org.wso2.securevault.keystore.IdentityKeyStoreWrapper;
import org.wso2.securevault.keystore.TrustKeyStoreWrapper;
import org.wso2.securevault.secret.SecretRepository;
import org.wso2.securevault.secret.SecretRepositoryProvider;

public class MyVaultSecretRepositoryProvider implements SecretRepositoryProvider {
    /**
     * Get Secret Repository.
     *
     * @param identityKeyStoreWrapper Identity KeyStore Wrapper
     * @param trustKeyStoreWrapper Trust KeyStore Wrapper
     * @return
     */
    public SecretRepository getSecretRepository(IdentityKeyStoreWrapper identityKeyStoreWrapper,
                                                TrustKeyStoreWrapper trustKeyStoreWrapper) {
        return new MyVaultSecretRepository(identityKeyStoreWrapper, trustKeyStoreWrapper);
    }
}
