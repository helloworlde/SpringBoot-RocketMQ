package cn.com.hellowood.rocketmq.model;

/**
 * @author HelloWood
 * @create 2017-08-28 17:04
 * @email hellowoodes@outlook.com
 **/

public class ProducerMessage {

    private String topic;

    private String tags;

    private String key;

    private Object body;

    public ProducerMessage() {
    }

    public ProducerMessage(String topic, String tags, String key, Object body) {
        this.topic = topic;
        this.tags = tags;
        this.key = key;
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

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
