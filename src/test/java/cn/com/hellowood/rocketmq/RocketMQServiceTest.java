package cn.com.hellowood.rocketmq;

import cn.com.hellowood.rocketmq.config.RocketMQConfigProperties;
import cn.com.hellowood.rocketmq.model.ProducerMessage;
import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author HelloWood
 * @create 2017-09-02 12:23
 * @email hellowoodes@outlook.com
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketMQServiceApplication.class)
@WebAppConfiguration
public class RocketMQServiceTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private RocketMQConfigProperties properties;

    @Before
    public void before() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void sendMessage() throws Exception {
        RequestBuilder request = null;

        ProducerMessage message = new ProducerMessage();
        message.setKey("key");
        message.setTags("TAG");
        message.setBody("Hello");
        message.setType("ORDER");
        message.setMethod("SYNCHRONOUS");
        message.setTopic("TOPIC");

        request = post("/sendMessage/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(message));

        ResultActions resultActions = mvc.perform(request);
        String result = resultActions.andReturn().getResponse().getContentAsString();
        resultActions.andExpect(status().isOk());

        assertTrue(result.contains("topic") && result.contains("messageId"));
    }
}


