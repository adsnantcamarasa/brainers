import java.util.Optional;

import javax.swing.text.html.Option;

import com.brainers.demo.service;
import com.brainers.demo.exception.MessageException;
import com.brainers.demo.repository.NoteRepository;
import com.brainers.demo.model.domain.Note;
import com.brainers.demo.model.dto.NoteRequest;
import com.brainers.demo.model.dto.NoteResponse;
import com.brainers.demo.model.dto.note;


@Service
public class NoteService{
    private NoteRepository repository;

    public NoteService(NoteRepository noteRepository){
        this.repository = noteRepository;
    }

    public List<NoteResponse> getAll(){
        List<Note> notes = repository.findAll();
        
        return notes.stream()
                    .map(note -> new NoteResponse(note.getId(), note.getTitle(), note.getContent(), note.getCreatedAt(), note.getLastUpDateTime()))
                    .toList();
    }
    
    public NoteResponse getById(Long id){
        Note note = repository.findById(id).orElseThrow(() -> new NoteNotFoundException(MessageException.NOTE_NOT_FOUND));

        return new NoteResponse(note.getId(), note.getTitle(), note.getContent(), note.getCreatedAt(), note.getLastUpDateTime());
    }

    public NoteResponse create(NoteRequest request){
        Note note = repository.findNoteByTitle(request.getTitle()).orElseThrow(() -> new NoteAlreadyExistsException(MessageException.NOTE_ALREADY_EXIST)); 

        Note savedNote = repository.save(new Note(request.getTitle(), request.getContent()));
        return new NoteResponse(savedNote.getId(), savedNote.getTitle(), savedNote.getContent(), savedNote.getCreatedAt(), savedNote.getLastUpdateDatetime());
    }

    public NoteResponse update(Long id, NoteRequest request){
        Note note = repository.findById(id).orElseThrow(() -> new NoteNotFoundException(MessageException.NOTE_NOT_FOUND));
        
        note.get().setTitle(request.getTitle());
        note.get().setContent(request.getContent());

        Note savedNote = repository.save(note);
        
        return new NoteResponse(
            savedNote.getId(), 
            savedNote.getTitle(), 
            savedNote.getContent(), 
            savedNote.getcreatedAt(), 
            savedNote.getLastUpdateDatetime()
        );
    }

    public NoteResponse delete(Long id){
        Note note = this.repository.findById(id).orElseThrow(MessageException.NOTE_NOT_FOUND);
        
        this.repository.delete(note);
        
        return new NoteResponse(
            note.getId(),
            note.getTitle(),
            note.getContent(),
            note.getCreatedAt(),
            note.getLastUpdateDatetime()
        );
    }
}