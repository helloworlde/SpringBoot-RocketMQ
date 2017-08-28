package cn.com.hellowood.rocketmq.listenerimpl;

import cn.com.hellowood.rocketmq.config.RocketMQConfigProperties;
import cn.com.hellowood.rocketmq.util.ConsumerHelper;
import cn.com.hellowood.rocketmq.util.ProducerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author HelloWood
 * @create 2017-08-28 23:02
 * @email hellowoodes@outlook.com
 **/

@Component
public class CommandLineRunnerListenerImpl implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CommandLineRunnerListenerImpl.class);

    @Autowired
    RocketMQConfigProperties configProperties;

    @Autowired
    ProducerHelper producerHelper;

    @Autowired
    ConsumerHelper consumerHelper;

    @Override
    public void run(String... args) throws Exception {
        long startTimestamp = System.currentTimeMillis();
        logger.info("CommandLine runner listener started");
        producerHelper.initializeProducer();
        consumerHelper.initializeConsumer();
        long costTime = System.currentTimeMillis() - startTimestamp;
        logger.info("CommandLine runner listener completed in " + costTime + " ms");
    }
}
