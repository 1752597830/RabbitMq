package com.qf.rabbitmq.helloword;

import com.qf.rabbitmq.RabbitMQConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

/**
 * @Author: sin
 * @Date: 2024/12/15 20:53
 * @Description:
 **/
public class Publisher {

    public static final String QUEUE_NAME = "hello";

    @Test
    public void publish() throws Exception{
        // 1. 获取连接对象
        Connection connection = RabbitMQConnectionUtil.getConnection();

        // 2. 创建通道对象
        Channel channel = connection.createChannel();

        // 3. 构建队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 4. 发送消息
        String message = "hello world";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        // 让程序暂停
        System.in.read();
    }
}
