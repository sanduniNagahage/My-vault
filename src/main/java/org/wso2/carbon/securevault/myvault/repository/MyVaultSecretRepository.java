package org.wso2.carbon.securevault.myvault.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.wso2.securevault.keystore.IdentityKeyStoreWrapper;
import org.wso2.securevault.keystore.TrustKeyStoreWrapper;
import org.wso2.securevault.secret.SecretRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import static org.wso2.carbon.securevault.myvault.common.MyVaultConstants.TOKEN_PARAMETER;

public class MyVaultSecretRepository implements SecretRepository {
    private static final Log LOG = LogFactory.getLog(MyVaultSecretRepository.class);
    private static final String SLASH = "/";

    private SecretRepository parentRepository;
    private IdentityKeyStoreWrapper identityKeyStoreWrapper;
    private TrustKeyStoreWrapper trustKeyStoreWrapper;
    private String token;
    private  String secret;

    public MyVaultSecretRepository(IdentityKeyStoreWrapper identityKeyStoreWrapper,
                                     TrustKeyStoreWrapper trustKeyStoreWrapper) {

        this.identityKeyStoreWrapper = identityKeyStoreWrapper;
        this.trustKeyStoreWrapper = trustKeyStoreWrapper;
    }

    /**
     * Initializes the repository based on provided properties.
     *
     * @param properties Configuration properties
     * @param id         Identifier to identify properties related to the corresponding repository
     */
    @Override
    public void init(Properties properties, String id) {

        LOG.info("Initializing HashiCorp Secure Vault");

        token = System.getenv(TOKEN_PARAMETER);
        if (token == null) {
            LOG.error("VAULT_TOKEN environment variable is not set");
        }
    }

    /**
     * Get Secret from the Secret Repository
     *
     * @param alias Alias name for look up a secret
     * @return Secret if there is any, otherwise, alias itself
     * @see SecretRepository
     */
    @Override
    public String getSecret(String alias) {

        try {
            String url ="https://run.mocky.io/v3/ae971b1b-e391-4c93-b384-5fc28b1aa1f6";
            URL obj = new URL(url);
            HttpURLConnection con=(HttpURLConnection) obj.openConnection();


            BufferedReader reader  =new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            StringBuffer jsonString =new StringBuffer();
            while((inputLine = reader.readLine())!=null){
                jsonString.append(inputLine);
            }
            reader.close();

            JSONObject jsonValue = new JSONObject(jsonString.toString());
            secret = jsonValue.getString(alias);



        } catch (Exception e){
            System.out.println(e);
        }
        return secret;
    }

    /**
     * Get Encrypted data.
     *
     * @param alias Alias of the secret
     * @return
     */
    @Override
    public String getEncryptedData(String alias) {

        throw new UnsupportedOperationException();
    }

    /**
     * Set parent repository.
     *
     * @param parent Parent secret repository
     */
    @Override
    public void setParent(SecretRepository parent) {

        this.parentRepository = parent;
    }

    /**
     * Get parent repository.
     *
     * @return
     */
    @Override
    public SecretRepository getParent() {

        return this.parentRepository;
    }
}
