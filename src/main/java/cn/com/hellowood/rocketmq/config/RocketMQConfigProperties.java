package cn.com.hellowood.rocketmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author HelloWood
 * @create 2017-08-28 17:41
 * @email hellowoodes@outlook.com
 **/

@ConfigurationProperties("spring.rocketmq")
@Component
public class RocketMQConfigProperties {

    private String ONSAddresses;

    private String accessKey;

    private String secretKey;

    private String producerId;

    public String getONSAddresses() {
        return ONSAddresses;
    }

    public void setONSAddresses(String ONSAddresses) {
        this.ONSAddresses = ONSAddresses;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }
}
