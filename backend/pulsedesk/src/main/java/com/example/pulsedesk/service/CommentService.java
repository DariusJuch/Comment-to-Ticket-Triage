package com.example.pulsedesk.service;

import com.example.pulsedesk.client.HuggingFaceClient;
import com.example.pulsedesk.dto.AIResponse;
import com.example.pulsedesk.entity.Category;
import com.example.pulsedesk.entity.Comment;
import com.example.pulsedesk.entity.Priority;
import com.example.pulsedesk.entity.Ticket;
import com.example.pulsedesk.repository.CommentRepository;
import com.example.pulsedesk.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;
    private final HuggingFaceClient huggingFaceClient;
    private final AIParser aiParser;

    public void createComment(String text){
        Comment comment = new Comment(null, text, LocalDateTime.now());
        commentRepository.save(comment);

        String raw = huggingFaceClient.classify(text);
        AIResponse ai = aiParser.parse(raw);

        if(ai == null || !ai.isTicket()){
            return;
        }

        Category category;
        try {
            category = Category.valueOf(
                    ai.getCategory() != null ? ai.getCategory().toUpperCase() : "OTHER"
            );
        } catch (Exception e){
            category = Category.OTHER;
        }

        Priority priority;
        try{
            priority = Priority.valueOf(
                    ai.getPriority() != null ? ai.getPriority().toUpperCase() : "MEDIUM"
            );
        } catch (Exception e){
            priority = Priority.MEDIUM;
        }

        Ticket ticket = new Ticket(
                null,
                ai.getTitle() != null ? ai.getTitle() : "Auto-generated ticket",
                category,
                priority,
                ai.getSummary() != null ? ai.getSummary() : "No summary"
        );

        ticketRepository.save(ticket);
    }

    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    public List<Ticket> getTickets(){
        return ticketRepository.findAll();
    }

    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }
}
