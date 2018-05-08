package com.redhat.workshop.questions.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WebSocketResponse {
	private Integer code;
	private String message;
	
	public WebSocketResponse(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
