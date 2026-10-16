package com.brainers.demo.controller;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brainers.demo.model.dto.NoteRequest;
import com.brainers.demo.model.dto.NoteResponse;
import com.brainers.demo.service.NoteService;
    /**
       GET /notes — lister les notes                 ✓
       GET /notes/{id} — récupérer une note          ✓
       POST /notes — créer une note                  ✓
       PUT /notes/{id} — remplacer une note          ✓
       DELETE /notes/{id} — supprimer une note       ✓
     */
@RestController
public class NoteController{
    private NoteService service;
    
    public NoteController(NoteService noteService){
        this.service = noteService;
    }

    @GetMapping("/notes")
    public List<NoteResponse> getAll(@RequestParam(value = "title", required = false) String search){
        return this.service.getNotes(search);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        NoteResponse noteResponse = this.service.findById(id);
        return ResponseEntity.ok(noteResponse);
    }

    @PostMapping("/notes")
    public ResponseEntity<?> create(@Valid @RequestBody NoteRequest noteRequest){
        // Ce qui peut être intéressant et de gérer les retours via ResponseEntity -> typiqement resosurce créer je veux un 201.
        NoteResponse noteResponse = this.service.create(noteRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(noteResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(noteResponse);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody NoteRequest noteRequest){
        NoteResponse noteResponse = this.service.update(id, noteRequest);

        return ResponseEntity.ok(noteResponse);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}