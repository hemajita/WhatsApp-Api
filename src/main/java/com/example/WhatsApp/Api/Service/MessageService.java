package com.example.WhatsApp.Api.Service;



import com.example.WhatsApp.Api.Model.Message;
import com.example.WhatsApp.Api.Repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
//public Message sendMessage(Message message){
//Message m=new Message();
//m.setSenderId(message.getSenderId());
//m.setRecipientId(message.getRecipientId());
//m.setContent(message.getContent());
//    return messageRepository.save(m);
//}
    public Message sendMessage(String senderId, String recipientId, String content, MultipartFile attachment, String emoji) {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setRecipientId(recipientId);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        message.setEmoji(emoji);


        if (attachment != null && !attachment.isEmpty()) {
            try {

                if (attachment.getSize() > 10 * 1024 * 1024) {
                    throw new IllegalArgumentException("Attachment size exceeds the limit of 10MB.");
                }
                message.setAttachment(attachment.getBytes());
                message.setAttachmentName(attachment.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException("Failed to process attachment.", e);
            }
        }

        return messageRepository.save(message);
    }
    public List<Message> getMessages(String recipientId) {

    return messageRepository.findByRecipientId(recipientId);
    }
}
