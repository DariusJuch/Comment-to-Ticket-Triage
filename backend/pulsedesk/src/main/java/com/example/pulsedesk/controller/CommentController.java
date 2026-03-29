package com.example.pulsedesk.controller;


import com.example.pulsedesk.dto.CommentRequest;
import com.example.pulsedesk.entity.Comment;
import com.example.pulsedesk.entity.Ticket;
import com.example.pulsedesk.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping("/comments")
    public void create(@RequestBody CommentRequest request){
        service.createComment(request.getText());
    }

    @GetMapping("/comments")
    public List<Comment> comments() {
        return service.getComments();
    }

    @GetMapping("/tickets")
    public List<Ticket> tickets(){
        return service.getTickets();
    }

    @GetMapping("/tickets/{id}")
    public Ticket ticket(@PathVariable Long id) {
        return service.getTicket(id);
    }
}
