package com.ss.oa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 通过EnableWebSocketMessageBroker 开启使用STOMP协议来传输基于代理(message broker)的消息,此时浏览器支持使用@MessageMapping 就像支持@RequestMapping一样。
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    //    /**
//     * endPoint 注册协议节点,并映射指定的URl
//     *
//     * @param registry
//     */
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        //注册一个Stomp 协议的endpoint,并指定 SockJS协议。
//        registry.addEndpoint("/endpointWisely").withSockJS();
//    }
//
//    /**
//     * 配置消息代理(message broker)
//     *
//     * @param registry
//     */
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        //广播式应配置一个/topic 消息代理
//        registry.enableSimpleBroker("/topic");
//    }

    /**
     * 这个方法的作用是添加一个服务端点，来接收客户端的连接。
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { //endPoint 注册协议节点,并映射指定的URl
        /**
         * addEndpoint() 表示添加了一个/endpointChat端点，客户端就可以通过这个端点来进行连接
         * withSockJS() 的作用是开启SockJS支持
         */

        //注册一个名字为"endpointChat" 的endpoint,并指定 SockJS协议。   点对点-用
        registry.addEndpoint("/endpointChat").withSockJS();
    }


    /**
     * 这个方法的作用是定义消息代理，通俗一点讲就是设置消息连接请求的各种规范信息。
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {//配置消息代理(message broker)
        /**
         * registry.enableSimpleBroker("/topic")表示客户端订阅地址的前缀信息，也就是客户端接收服务端消息的地址的前缀信息
         *
         * registry.setApplicationDestinationPrefixes("/app")指服务端接收地址的前缀，意思就是说客户端给服务端发消息的地址的前缀
         */



        //点对点式增加一个/topic 消息代理
        registry.enableSimpleBroker( "/queue","/topic");

        registry.setApplicationDestinationPrefixes("/app");

    }
}
