package com.redhat.workshop.questions.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
	
	@MessageMapping("/hello")
	@SendTo("/question/message")
	public String teste(String message) {
		System.out.println("Recebi " + message);
		return "{ \"from\": \"oi\"}";
	}
}
