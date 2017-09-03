## Rocket MQ 发送消息
> 使用阿里巴巴的Rocket MQ发送消息的SpringBoot项目

- 使用之前请现在[阿里云](https://ons.console.aliyun.com/?spm=5176.2020520001.1002.d10ons.61d991b3GbNJPR#/home/topic)申请消息队列并创建消息TOPIC、Producer和Consumer

## 启动
- 下载： `https://github.com/helloworlde/SpringBoot-RocketMQ.git` 
- 修改配置文件：修改`application.properties` ，填入相应的信息
```
spring.rocketmq.ONSAddresses=消息队列地址
spring.rocketmq.accessKey=AccessKey
spring.rocketmq.secretKey=SercetKey
spring.rocketmq.topic=TOPIC
spring.rocketmq.producerId生产者
spring.rocketmq.consumerId=订阅者
```

- 编译：`gradle build` 
- 启动：`gradle bootrun`


-------------------------
    
## API

**请求URL：** 
- `http://localhost:8080/sendMessage`
  
**请求方式：**
- `POST `

**参数：** 

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|topic |是  |String | 消息Topic   |
|body |是  |String | 消息所携带的数据，可以是对象的JSON串   |
|key |是  |String | 32位key    |
|tags     |否  |String | Message Tag，用于过滤订阅者过滤消息    |
|method     |是  |String | 消息发送方式，共有三种：同步发送：`SYNCHRONOUS`，异步发送：`ASYNCHRONOUS`, 单向发送：`ONE_WAY` ,默认使用同步发送方式发送消息   |
|type     |是  |String |消息类型，共有三种：顺序消息：`ORDER`，延时消息：`DELAY`，定时消息：`TIMING`，默认发送顺序消息|
|delayTime     |否  |long | 消息发送延时时间，单位是毫秒    |
|startDeliveryTime     |否  |String | 定时消息发送时间，必须是`yyyy-MM-dd HH:mm:ss`格式    |

**顺序消息 Body**
```
{  
    "body":"{\"name\":\"MQ\",\"type\":\"Rocket\"}",
    "delayTime":30000,
    "key":"key",
    "method":"SYNCHRONOUS",
    "tags":"TAG1,TAG2",
    "topic":"TOPIC",
    "type":"ORDER"
}
```

**延时消息 Body**
```
{  
    "body":"{\"name\":\"MQ\",\"type\":\"Rocket\"}",
    "delayTime":30000,
    "key":"key",
    "method":"ONE_WAY",
    "tags":"TAG1,TAG2",
    "topic":"TOPIC",
    "type":"DELAY"
}
```

**定时消息 Body**
```
{  
    "body":"{\"name\":\"MQ\",\"type\":\"Rocket\"}",
    "key":"key",
    "method":"ASYNCHRONOUS",
    "startDeliveryTime":"2017-09-01 17:40:00",
    "tags":"TAG1,TAG2",
    "topic":"TOPIC",
    "type":"TIMING"
}
```

 **返回示例**
- 同步方式发送时返回消息信息，异步和单向方式返回值为空
``` 
{
    "messageId": "0A00004541843624AEC09C820EAD0000",
    "topic": "Topic"
}
```

 **返回参数说明** 

|参数名|说明|
|:----- |-----                           |
|messageId |所发送消息ID  |
|topic | 所发送消息 Topic  |

 


