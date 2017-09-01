package cn.com.hellowood.rocketmq.controller;

import cn.com.hellowood.rocketmq.model.ProducerMessage;
import cn.com.hellowood.rocketmq.service.ProducerService;
import com.aliyun.openservices.ons.api.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HelloWood
 * @create 2017-08-28 17:00
 * @email hellowoodes@outlook.com
 **/

@RestController
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    /**
     * Send message
     *
     * @param producerMessage
     * @return
     */
    @PostMapping("/sendMessage")
    public SendResult sendMessage(@RequestBody ProducerMessage producerMessage) {
        return producerService.sendMessage(producerMessage);
    }
}
