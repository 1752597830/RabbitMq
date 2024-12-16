package com.qf.rabbitmq.topic;

import com.qf.rabbitmq.RabbitMQConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

/**
 * @Author: sin
 * @Date: 2024/12/16 9:32
 * @Description:
 **/
public class Publisher {
    public static final String EXCHANGE_NAME = "topic";
    public static final String QUEUE_NAME1 = "topic-one";
    public static final String QUEUE_NAME2 = "topic-two";

    @Test
    public void publish() throws Exception {
        //1. 获取连接对象
        Connection connection = RabbitMQConnectionUtil.getConnection();

        //2. 构建Channel
        Channel channel = connection.createChannel();

        //3. 构建交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        //4. 构建队列
        channel.queueDeclare(QUEUE_NAME1,false,false,false,null);
        channel.queueDeclare(QUEUE_NAME2,false,false,false,null);

        //5. 绑定交换机和队列，使用的是TOPIC类型的交换机，绑定方式是通配符绑定
        channel.queueBind(QUEUE_NAME1,EXCHANGE_NAME,"*.orange.*");
        channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"*.*.rabbit");
        channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"lazy.#");

        // 6. 发消息到交换机
        channel.basicPublish(EXCHANGE_NAME,"quick.fox.rabbit",null,"小兔子".getBytes());
        channel.basicPublish(EXCHANGE_NAME,"quick.orange.fox",null,"小狐狸".getBytes());
        channel.basicPublish(EXCHANGE_NAME,"lazy.orange.elephant",null,"大懒虫".getBytes());
        channel.basicPublish(EXCHANGE_NAME,"lazy.brown.fox",null,"大狐狸".getBytes());

        System.out.println("消息发送成功！");
    }
}
