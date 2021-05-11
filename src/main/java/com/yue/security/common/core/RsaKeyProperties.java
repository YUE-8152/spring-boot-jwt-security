package com.yue.security.common.core;

import com.yue.security.common.utils.RsaUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @ProjectName: spring-boot-jwt-security
 * @Package: com.yue.security.common.core
 * @ClassName: RsaKeyProperties
 * @Author: YUE
 * @Description:
 * @Date: 2021/5/10 17:01
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "rsa.key")
public class RsaKeyProperties {

    private String secret;

    private String pubKeyFile;

    private String priKeyFile;

    private PublicKey publicKey;

    private PrivateKey privateKey;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPubKeyFile() {
        return pubKeyFile;
    }

    public void setPubKeyFile(String pubKeyFile) {
        this.pubKeyFile = pubKeyFile;
    }

    public String getPriKeyFile() {
        return priKeyFile;
    }

    public void setPriKeyFile(String priKeyFile) {
        this.priKeyFile = priKeyFile;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    @PostConstruct
    public void createKey() throws Exception {
        File pubKey = new File(pubKeyFile);
        File priKey = new File(priKeyFile);
        if (!pubKey.exists() || !priKey.exists()) {
            RsaUtils.generateKey(pubKeyFile,priKeyFile,secret,256);
        }
        this.publicKey = RsaUtils.getPublicKey(pubKeyFile);
        this.privateKey = RsaUtils.getPrivateKey(priKeyFile);
    }
}
