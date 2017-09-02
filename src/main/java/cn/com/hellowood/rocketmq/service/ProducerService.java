package cn.com.hellowood.rocketmq.service;

import cn.com.hellowood.rocketmq.common.RocketMQServiceConstant;
import cn.com.hellowood.rocketmq.model.ProducerMessage;
import cn.com.hellowood.rocketmq.util.ProducerHelper;
import cn.com.hellowood.rocketmq.util.RocketMQServiceUtil;
import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * @author HelloWood
 * @create 2017-08-28 17:07
 * @email hellowoodes@outlook.com
 **/

@Service
public class ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    @Autowired
    ProducerHelper producerHelper;

    /**
     * Send message
     *
     * @param producerMessage
     * @return
     */
    public SendResult sendMessage(ProducerMessage producerMessage) {
        SendResult sendResult = null;
        Message message = null;
        try {
            // Generate message object
            message = generateMessage(producerMessage);

            // Send message
            sendResult = sendMessage(message, producerMessage);
        } catch (ParseException e) {
            logger.error("Send message failed, parse Data error ：" + e.getMessage());
            e.printStackTrace();
        }

        return sendResult;
    }

    /**
     * Send message in different method
     *
     * @param message
     * @param producerMessage
     * @return
     */
    private SendResult sendMessage(Message message, ProducerMessage producerMessage) {
        SendResult sendResult = null;
        switch (producerMessage.getMethod()) {
            // Send message in ASYNCHRONOUS method
            case RocketMQServiceConstant.ASYNCHRONOUS_MESSAGE:
                sendResult = producerHelper.sendAsynchronousMessage(message);
                break;
            // Send message in ONE_WAY method
            case RocketMQServiceConstant.ONE_WAY_MESSAGE:
                sendResult = producerHelper.sendOneWayMessage(message);
                break;
            // Send message in SYNCHRONOUS method
            case RocketMQServiceConstant.SYNCHRONOUS_MESSAGE:
            default:
                sendResult = producerHelper.sendSynchronousMessage(message);
                break;
        }
        return sendResult;
    }


    /**
     * Generate message object
     *
     * @param producerMessage
     * @return
     */
    private Message generateMessage(ProducerMessage producerMessage) throws ParseException {
        Message msg = new Message();
        msg.setTag(producerMessage.getTags());
        msg.setKey(producerMessage.getKey());
        msg.setTopic(producerMessage.getTopic());
        msg.setBody(JSON.toJSONString(producerMessage.getBody()).getBytes());

        switch (producerMessage.getType()) {
            // Generate Delay message
            case RocketMQServiceConstant.DELAY_MESSAGE:
                msg.setStartDeliverTime(System.currentTimeMillis() + producerMessage.getDelayTime());
                break;
            // Generate Timing message
            case RocketMQServiceConstant.TIMING_MESSAGE:
                long startDeliverTime = RocketMQServiceUtil.getTimestampByDateString(producerMessage.getStartDeliveryTime());
                // if start deliver time early than now, send message right now
                if (startDeliverTime < System.currentTimeMillis()) {
                    startDeliverTime = System.currentTimeMillis();
                }
                msg.setStartDeliverTime(startDeliverTime);
                break;
            // Generate Order message
            case RocketMQServiceConstant.ORDER_MESSAGE:
            default:
                break;
        }

        return msg;
    }
}
