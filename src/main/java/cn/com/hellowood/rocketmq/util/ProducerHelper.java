package cn.com.hellowood.rocketmq.util;

import cn.com.hellowood.rocketmq.config.RocketMQConfigProperties;
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
     * Send SYNCHRONOUS message
     *
     * @param message
     * @return
     */
    public SendResult sendSynchronousMessage(Message message) {
        logger.info("MQ start send SYNCHRONOUS message ");

        long startTimestamp = System.currentTimeMillis();

        SendResult sendResult = producer.send(message);

        long costTime = System.currentTimeMillis() - startTimestamp;
        logger.info("MQ completed send SYNCHRONOUS message in " + costTime + " ms");

        return sendResult;
    }

    /**
     * 发送单向消息
     *
     * @param message
     * @return
     */
    public SendResult sendOneWayMessage(Message message) {
        logger.info("MQ start send ONE_WAY message ");

        long startTimestamp = System.currentTimeMillis();

        producer.sendOneway(message);
        long costTime = System.currentTimeMillis() - startTimestamp;
        logger.info("MQ completed send ONE_WAY message in " + costTime + " ms");

        return null;
    }

    /**
     * Send ASYNCHRONOUS message
     *
     * @param message
     * @return
     */
    public SendResult sendAsynchronousMessage(Message message) {
        logger.info("MQ start send ASYNCHRONOUS message ");

        long startTimestamp = System.currentTimeMillis();

        producer.sendAsync(message, new SendCallback() {
            @Override
            public void onSuccess(final SendResult sendResult) {
                logger.info("MQ send ASYNCHRONOUS message successed，response is " + JSON.toJSONString(sendResult));
            }

            @Override
            public void onException(OnExceptionContext onExceptionContext) {
                logger.info("MQ send ASYNCHRONOUS message failed, error is " + onExceptionContext.getException().getMessage());
            }
        });

        long costTime = System.currentTimeMillis() - startTimestamp;
        logger.info("MQ completed send ASYNCHRONOUS message in " + costTime + " ms");

        return null;
    }
}
