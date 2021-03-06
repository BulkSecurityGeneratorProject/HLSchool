package com.hl.web.websocket;


import com.hl.web.websocket.dto.ChatMessage;
import com.hl.web.websocket.dto.ChatMessageRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public ChatMessage sendMessage(@Payload ChatMessageRequestDTO chatMessageRequestDTO,
                                   SimpMessageHeaderAccessor headerAccessor) {
        org.springframework.security.authentication.UsernamePasswordAuthenticationToken pricipleFromHeader = (UsernamePasswordAuthenticationToken) headerAccessor.getUser();
        org.springframework.security.core.userdetails.User userFromHeader = (org.springframework.security.core.userdetails.User) pricipleFromHeader.getPrincipal();

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(chatMessageRequestDTO.getMessage());
        chatMessage.setType(ChatMessage.MessageType.CHAT);
        chatMessage.setSender(userFromHeader.getUsername());

        messagingTemplate.convertAndSend("/topic/"+chatMessageRequestDTO.getRoomId(), chatMessage);
        return chatMessage;
    }
}
