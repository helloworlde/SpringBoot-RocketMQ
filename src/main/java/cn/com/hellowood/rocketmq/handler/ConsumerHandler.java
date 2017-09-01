package cn.com.hellowood.rocketmq.handler;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author HelloWood
 * @create 2017-08-28 22:34
 * @email hellowoodes@outlook.com
 **/

@Component
public class ConsumerHandler implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerHandler.class);

    /**
     * Message consumer handler
     *
     * @param message
     * @param context
     * @return
     */
    @Override
    public Action consume(Message message, ConsumeContext context) {
        logger.info("Consumer get new normal message [{}]", message);
        try {
            // TODO do something for subscribed message
            logger.info("Consumer consumed the normal message, message body is [{}]", new String(message.getBody()));
            return Action.CommitMessage;
        } catch (Exception e) {
            logger.error("Consumer consumed the normal message failed, exception message is [{}]", e.getMessage());
            return Action.ReconsumeLater;
        }
    }

}
