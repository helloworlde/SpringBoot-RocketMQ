package cn.com.hellowood.rocketmq.service;

import cn.com.hellowood.rocketmq.model.ProducerMessage;
import cn.com.hellowood.rocketmq.util.ProducerHelper;
import com.aliyun.openservices.ons.api.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HelloWood
 * @create 2017-08-28 17:07
 * @email hellowoodes@outlook.com
 **/

@Service
public class ProducerService {

    @Autowired
    ProducerHelper producerHelper;

    /**
     * Send normal message
     *
     * @param producerMessage
     * @return
     */
    public SendResult sendNormalMessage(ProducerMessage producerMessage) {
        return producerHelper.sendNormalMessage(producerMessage);
    }
}
