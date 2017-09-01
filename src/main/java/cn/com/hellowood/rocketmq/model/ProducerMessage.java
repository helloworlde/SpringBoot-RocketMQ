package cn.com.hellowood.rocketmq.model;

import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;

/**
 * @author HelloWood
 * @create 2017-08-28 17:04
 * @email hellowoodes@outlook.com
 **/

public class ProducerMessage<T> {

    private String topic;

    private String tags;

    private String key;

    private String method; // Send method ：SYNCHRONOUS ASYNCHRONOUS ONE_WAY

    private String type; // Message type：Timing Order Delay

    private long delayTime;

    private String startDeliveryTime;

    private T body;

    public ProducerMessage() {
    }

    public ProducerMessage(String topic, String tags, String key, String method, String type, long delayTime, String startDeliveryTime, T body) {
        this.topic = topic;
        this.tags = tags;
        this.key = key;
        this.method = method;
        this.type = type;
        this.delayTime = delayTime;
        this.startDeliveryTime = startDeliveryTime;
        this.body = body;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public String getStartDeliveryTime() {
        return startDeliveryTime;
    }

    public void setStartDeliveryTime(String startDeliveryTime) {
        this.startDeliveryTime = startDeliveryTime;
    }

    @Override
    public String toString() {
        return "ProducerMessage{" +
                "topic='" + topic + '\'' +
                ", tags='" + tags + '\'' +
                ", key='" + key + '\'' +
                ", method='" + method + '\'' +
                ", type='" + type + '\'' +
                ", delayTime=" + delayTime +
                ", startDeliveryTime='" + startDeliveryTime + '\'' +
                ", body=" + JSON.toJSONString(body) +
                '}';
    }
}
