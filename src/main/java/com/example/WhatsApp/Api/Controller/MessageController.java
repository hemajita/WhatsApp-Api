package com.example.WhatsApp.Api.Controller;

import com.example.WhatsApp.Api.Model.Message;
import com.example.WhatsApp.Api.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    public Object sendMessage(@RequestParam("senderId") String senderId,
                              @RequestParam("recipientId") String recipientId,
                              @RequestParam("content") String content,
                              @RequestParam(value = "attachment", required = false) MultipartFile attachment,
                              @RequestParam(value = "emoji", required = false) String emoji) {
        if (senderId == null || recipientId == null || content == null) {
            return ResponseEntity.badRequest().build();
        }


        if (attachment != null && attachment.getSize() > 10 * 1024 * 1024) { // 10MB limit
            return ResponseEntity.badRequest().body("Attachment size exceeds the limit of 10MB.");
        }


        if (emoji != null && !isValidEmojiKind(emoji)) {
            return ResponseEntity.badRequest().body("Invalid emoji kind. Allowed values: thumbup, love, crying, surprised.");
        }


        Message message1 = messageService.sendMessage(senderId, recipientId, content, attachment, emoji);


        return ResponseEntity.ok();
    }

    private boolean isValidEmojiKind(String emoji) {
        return emoji.equals("thumbup") || emoji.equals("love") || emoji.equals("crying") || emoji.equals("surprised");
    }

    @GetMapping("recipientId")
    public ResponseEntity<List<Message>> getMessages(@RequestParam("recipientId") String recipientId) {
        List<Message> messages = messageService.getMessages(recipientId);
        return ResponseEntity.ok(messages);
    }
}
