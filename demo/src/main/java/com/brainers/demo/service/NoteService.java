package com.brainers.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.brainers.demo.exception.MessageException;
import com.brainers.demo.exception.NoteAlreadyExistsException;
import com.brainers.demo.exception.NoteNotFoundException;
import com.brainers.demo.model.domain.Note;
import com.brainers.demo.model.dto.NoteRequest;
import com.brainers.demo.model.dto.NoteResponse;
import com.brainers.demo.repository.NoteRepository;


@Service
public class NoteService{
    private NoteRepository repository;

    public NoteService(NoteRepository noteRepository){
        this.repository = noteRepository;
    }

    public List<NoteResponse> getNotes(String search){
        List<Note> notes = search == null ? repository.findAll() : repository.findByTitleContainingIgnoreCase(search);
        // MapStruct
        return notes.stream()
                .map(note -> new NoteResponse(note.getId(), note.getTitle(), note.getContent(), note.getCreatedAt(), note.getLastUpdateAt()))
                .toList();
    }
    
    public NoteResponse findById(Long id){
        Note note = repository.findById(id).orElseThrow(() -> new NoteNotFoundException(MessageException.NOTE_NOT_FOUND.getMessageException()));

        return new NoteResponse(note.getId(), note.getTitle(), note.getContent(), note.getCreatedAt(), note.getLastUpdateAt());
    }

    public NoteResponse create(NoteRequest request){
        if (repository.findNoteByTitle(request.title()).isPresent()) {
            throw new NoteAlreadyExistsException(MessageException.NOTE_ALREADY_EXIST.getMessageException());
        }       

        Note savedNote = repository.save(new Note(request.title(), request.content()));
        return new NoteResponse(savedNote.getId(), savedNote.getTitle(), savedNote.getContent(), savedNote.getCreatedAt(), savedNote.getLastUpdateAt());
    }

    public NoteResponse update(Long id, NoteRequest request){
        Note note = repository.findById(id).orElseThrow(() -> new NoteNotFoundException(MessageException.NOTE_NOT_FOUND.getMessageException()));
        
        note.setTitle(request.title());
        note.setContent(request.content());

        Note savedNote = repository.save(note);
        
        return new NoteResponse(
            savedNote.getId(), 
            savedNote.getTitle(), 
            savedNote.getContent(), 
            savedNote.getCreatedAt(), 
            savedNote.getLastUpdateAt()
        );
    }

    public void delete(Long id){
        Note note = this.repository.findById(id).orElseThrow(() -> new NoteNotFoundException(MessageException.NOTE_NOT_FOUND.getMessageException()));
        this.repository.delete(note);        
    }
}