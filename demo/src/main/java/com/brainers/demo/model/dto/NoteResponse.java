package com.brainers.demo.model.dto;
import java.time.LocalDateTime;

public class NoteResponse{
    private Long id;
    private String title;
    private String content;
    private LocalDateTime cratedAt;
    private LocalDateTime lastUpdateAt;

    public NoteResponse(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime lastUpDateTime){
        this.id           = id;
        this.title        = title;
        this.content      = content;
        this.cratedAt     = createdAt;
        this.lastUpdateAt = lastUpDateTime;
    }

    public Long getId(){return this.id;}
    public String getTitle(){return this.title;}
    public String getContent(){return this.content;}
    public LocalDateTime getCreatedAt(){return this.cratedAt;}
    public LocalDateTime getLastUpDateTime(){return this.lastUpdateAt;}
}