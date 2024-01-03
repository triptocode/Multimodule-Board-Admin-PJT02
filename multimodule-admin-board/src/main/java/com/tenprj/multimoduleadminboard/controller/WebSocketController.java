package com.tenprj.multimoduleadminboard.controller;

import com.tenprj.multimoduleadminboard.dto.websocket.WebSocketMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/chat")
    public WebSocketMessage chat(WebSocketMessage message, Principal principal) throws Exception {
        Thread.sleep(1000); // 대화하는 느낌을 시뮬레이션

        return WebSocketMessage.of("안녕하세요 " + principal.getName() + "님! " + message.content() + "라고 하셨나요? \n남겨주시는 내용을 바탕으로 확인해 드리겠습니다. (시간이 다소 소요될수 있습니다).");
    }

}