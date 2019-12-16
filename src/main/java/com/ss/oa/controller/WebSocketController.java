package com.ss.oa.controller;

import com.ss.system.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {

	/**
	 * 是Spring-WebSocket内置的一个消息发送工具，可以将消息发送到指定的客户端。
	 */
	@Autowired
	SimpMessagingTemplate template;

	@Autowired
    SessionService sessionService;

	@RequestMapping("/helloSocket")
	public String index(){
		return "/hello/index";
	}

	/**
	 * Spring对于WebSocket封装的特别简单，提供了一个@MessageMapping注解，功能类似@RequestMapping
	 *
	 * SendTo定义了消息的目的地
	 *
	 *
	 * 接收/app/change-notice发来的value，然后将value转发到/topic/notice客户端
	 *
	 *
	 * @param value
	 */
	@MessageMapping("/change-notice")
	@SendTo("/topic/notice")
	public String greeting(String value) {
		System.out.println("服务器接收到信息:["+value +"] 将转发到订阅地址:/topic/notice");
		return value;
	}
}