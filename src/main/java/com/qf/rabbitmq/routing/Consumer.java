package com.qf.rabbitmq.routing;

import com.qf.rabbitmq.RabbitMQConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
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
    public void read1() throws Exception{
        // 1. 获取连接
        Connection connection = RabbitMQConnectionUtil.getConnection();

        // 2. 创建通道对象
        Channel channel = connection.createChannel();

        // 3. 构建交换机
        channel.exchangeDeclare(Publisher.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        // 4. 构建队列
        channel.queueDeclare(Publisher.QUEUE_NAME1, false, false, false, null);

        // 4.5 设置消息的流控
        channel.basicQos(1);

        // 5. 监听消息 false 表示手动返回完成状态，true表示自动
        channel.basicConsume(Publisher.QUEUE_NAME1, true, (consumerTag, message) -> {
            System.out.println("消费者1号：接收到消息：" + new String(message.getBody()));
        }, consumerTag -> {});

        System.in.read();
    }

    @Test
    public void read2() throws Exception{
        // 1. 获取连接
        Connection connection = RabbitMQConnectionUtil.getConnection();

        // 2. 创建通道对象
        Channel channel = connection.createChannel();

        // 3. 构建交换机
        channel.exchangeDeclare(Publisher.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        // 4. 构建队列
        channel.queueDeclare(Publisher.QUEUE_NAME2, false, false, false, null);

        channel.basicQos(1);

        // 5. 监听消息 false 表示手动返回完成状态，true表示自动
        channel.basicConsume(Publisher.QUEUE_NAME2, true, (consumerTag, message) -> {
            System.out.println("消费者2号：接收到消息：" + new String(message.getBody()));
        }, consumerTag -> {});
        System.in.read();
    }
}