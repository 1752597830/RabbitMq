package com.qf.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
/**
 * @Author: sin
 * @Date: 2024/12/15 20:48
 * @Description:
 **/
public class RabbitMQConnectionUtil {
    public static final String HOST = "192.168.1.110";
    public static final int PORT = 5672;
    public static final String USERNAME = "guest";
    public static final String PASSWORD = "guest";
    public static final String VIRTUAL_HOST = "/";

    public static Connection getConnection() throws Exception {
        // 1. 创建Connection工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 2. 设置RabbitMQ的连接信息
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(VIRTUAL_HOST);
        // 3. 返回连接对象
        return factory.newConnection();
    }
}
