package cn.com.hellowood.rocketmq.util;

import cn.com.hellowood.rocketmq.config.RocketMQConfigProperties;
import cn.com.hellowood.rocketmq.handler.ConsumerHandler;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * @author HelloWood
 * @create 2017-08-28 23:06
 * @email hellowoodes@outlook.com
 **/

@Service
public class ConsumerHelper {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerHelper.class);

    @Autowired
    RocketMQConfigProperties configProperties;

    @Autowired
    ConsumerHandler normalMessageListener;

    private Consumer consumer;

    /**
     * Initialize consumer
     */
    public void initializeConsumer() {
        logger.info("Consumer initialize started");
        long startTimestamp = System.currentTimeMillis();

        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ConsumerId, configProperties.getConsumerId());
        properties.put(PropertyKeyConst.AccessKey, configProperties.getAccessKey());
        properties.put(PropertyKeyConst.SecretKey, configProperties.getSecretKey());

        consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe(configProperties.getTopic(), "*", normalMessageListener);
        consumer.start();

        long costTime = System.currentTimeMillis() - startTimestamp;
        logger.info("Consumer initialize completed in " + costTime + " ms");
    }

    /**
     * To shut down consumer
     */
    public void shutDown() {
        consumer.shutdown();
    }

}
