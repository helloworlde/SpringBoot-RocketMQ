package cn.com.hellowood.rocketmq.util;

import cn.com.hellowood.rocketmq.config.RocketMQConfigProperties;
import cn.com.hellowood.rocketmq.model.ProducerMessage;
import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author HelloWood
 * @create 2017-08-28 18:47
 * @email hellowoodes@outlook.com
 **/

@Component
public class ProducerHelper {

    private static final Logger logger = LoggerFactory.getLogger(ProducerHelper.class);

    @Autowired
    RocketMQConfigProperties configProperties;

    private Producer producer;

    /**
     * Initialize producer for multiple message send
     */
    public void initializeProducer() {
        long startTimestamp = System.currentTimeMillis();
        logger.info("Producer initialize started");

        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ProducerId, configProperties.getProducerId());
        properties.put(PropertyKeyConst.AccessKey, configProperties.getAccessKey());
        properties.put(PropertyKeyConst.SecretKey, configProperties.getSecretKey());
        properties.put(PropertyKeyConst.ONSAddr, configProperties.getONSAddresses());

        producer = ONSFactory.createProducer(properties);
        producer.start();

        long costTime = System.currentTimeMillis() - startTimestamp;
        logger.info("Producer initialize completed in " + costTime + " ms");
    }

    /**
     * To shut down producer
     */
    public void shutDownProducer() {
        producer.shutdown();
    }

    /**
     * Send normal message
     *
     * @param producerMessage
     * @return
     */
    public SendResult sendNormalMessage(ProducerMessage producerMessage) {
        long startTimestamp = System.currentTimeMillis();

        Message msg = new Message();
        msg.setTopic(producerMessage.getTopic());
        msg.setTag(producerMessage.getTags());
        msg.setKey(producerMessage.getKey());
        msg.setBody(JSON.toJSONString(producerMessage.getBody()).getBytes());

        SendResult sendResult = producer.send(msg);

        long costTime = System.currentTimeMillis() - startTimestamp;
        logger.info("Producer send normal message completed in " + costTime + " ms");

        return sendResult;
    }

}
