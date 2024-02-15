package com.nighthawk.spring_portfolio.mvc.chat;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/chat")
public class ChatApiController {
    @Autowired
    private ChatJpaRepository chatJpaRepository; 
    
    @PostMapping("create")
    public String sendMessage(@RequestParam String email, @RequestParam String message, @RequestParam String fromEmail, @RequestParam long personId, @RequestParam long fromPersonId){
        Chat chat = new Chat(email, message, fromEmail, personId, fromPersonId);
        Chat savedChat = chatJpaRepository.save(chat);

        // store this message somewhere and deliver to user
        return "Message Sent Successfully. ID: " + savedChat.getId();
    }
    
    @GetMapping("read")
    public String readUnreadMessages(@RequestParam String email, @RequestParam String fromEmail) throws JsonProcessingException{
        List<Chat> unreadMessages = chatJpaRepository.findByEmailAndFromEmailAndReadFlagOrderByDateSent(email, fromEmail, false);

        for (Chat c : unreadMessages) {
        	c.setReadFlag(true);
        	chatJpaRepository.save(c);
        }
        
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(unreadMessages);
        System.out.println(json);
        // store this message somewhere and deliver to user
        return json;
    }
    
    @GetMapping("history")
    public String readAllMessages(@RequestParam String email, @RequestParam String fromEmail) throws JsonProcessingException{
    	System.out.println("Email: " + email);
    	System.out.println("From Email: " + fromEmail);
        List<Chat> histMessages = chatJpaRepository.findByEmailAndFromEmailOrFromEmailAndEmailOrderByDateSent(email, fromEmail);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(histMessages);
        System.out.println(json);
        // store this message somewhere and deliver to user
        return json;
    }
    
    @DeleteMapping("history/clear")
    public void deleteMessages(@RequestParam String email, @RequestParam String fromEmail) throws JsonProcessingException{
    	System.out.println("Email: " + email);
    	System.out.println("From Email: " + fromEmail);
    	if (fromEmail.equals("All")) {
    		chatJpaRepository.deleteAllByEmail(email);
    	} else {
    		chatJpaRepository.deleteAllByEmailAndFromEmail(email, fromEmail);
    	}
    }
}
