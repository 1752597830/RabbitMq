package com.qf.rabbitmq.helloword;

import com.qf.rabbitmq.RabbitMQConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

/**
 * @Author: sin
 * @Date: 2024/12/15 21:06
 * @Description:
 **/
public class Consumer {


    @Test
    public void read() throws Exception{
        // 1. 获取连接
        Connection connection = RabbitMQConnectionUtil.getConnection();

        // 2. 创建通道对象
        Channel channel = connection.createChannel();

        // 3. 构建队列
        channel.queueDeclare(Publisher.QUEUE_NAME, false, false, false, null);

        // 4. 监听消息
        channel.basicConsume(Publisher.QUEUE_NAME, true, (consumerTag, message) -> {
            System.out.println("接收到消息：" + new String(message.getBody()));
        }, consumerTag -> {});

        System.in.read();
    }
}
