package com.qf.rabbitmq.workqueue;

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
    public void read1() throws Exception{
        // 1. 获取连接
        Connection connection = RabbitMQConnectionUtil.getConnection();

        // 2. 创建通道对象
        Channel channel = connection.createChannel();

        // 3. 构建队列
        channel.queueDeclare(Publisher.QUEUE_NAME, false, false, false, null);

        //3.5 设置消息的流控
        channel.basicQos(1);

        // 4. 监听消息 false 表示手动返回完成状态，true表示自动
        channel.basicConsume(Publisher.QUEUE_NAME, false, (consumerTag, message) -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费者1号：接收到消息：" + new String(message.getBody()));
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        }, consumerTag -> {});

        System.in.read();
    }

    @Test
    public void read2() throws Exception{
        // 1. 获取连接
        Connection connection = RabbitMQConnectionUtil.getConnection();

        // 2. 创建通道对象
        Channel channel = connection.createChannel();

        // 3. 构建队列
        channel.queueDeclare(Publisher.QUEUE_NAME, false, false, false, null);

        channel.basicQos(1);

        // 4. 监听消息 false 表示手动返回完成状态，true表示自动
        channel.basicConsume(Publisher.QUEUE_NAME, false, (consumerTag, message) -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费者2号：接收到消息：" + new String(message.getBody()));
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        }, consumerTag -> {});

        System.in.read();
    }
}
