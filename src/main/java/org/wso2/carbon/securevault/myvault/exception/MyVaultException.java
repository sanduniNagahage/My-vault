package org.wso2.carbon.securevault.myvault.exception;

public class MyVaultException extends Exception {

    public MyVaultException(String message) {

        super(message);
    }

    public MyVaultException(String message, Throwable cause) {

        super(message, cause);
    }
}
