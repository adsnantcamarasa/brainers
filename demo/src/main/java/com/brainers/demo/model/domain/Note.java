package com.brainers.demo.model.domain; 

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp; 
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String content;
    
    // Champs qui viennent de la DB
    @CreationTimestamp
    @Column(nullable = false,  updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime lastUpdateAt;

    protected Note(){}
    public Note(String title, String content){
        this.title   = title;
        this.content = content;
    }


    public Long getId(){return this.id;}
    public String getTitle(){return this.title;}
    public String getContent(){return this.content;}
    public LocalDateTime getCreatedAt(){return this.createdAt;}
    public LocalDateTime getLastUpdateDatetime(){return this.lastUpdateAt;}


    public void setTitle(String newTitle){this.title = newTitle;}
    public void setContent(String newContent){this.content = newContent;}
    public void setLastUpdate(LocalDateTime lastUpdateAt){this.lastUpdateAt = lastUpdateAt;}
}