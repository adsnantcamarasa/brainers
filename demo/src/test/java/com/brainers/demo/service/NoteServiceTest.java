package com.brainers.demo.service;

public class NoteServiceTest {
    /**
     *   getNotes(String search)
     *   A tester :
     *              -> Ternaire : search null ? findAll() | findByTitleContainingIgnoreCase
     *              -> Le repository me retourne une liste de note, je dois vérifier que je créer bien une liste de noteResponse.
     *                 Je reçois du repository une liste de notes, je créer une liste de NoteResponse. a tester j'imagine
     *
     *   Note note = repository.findById(id).orElseThrow(() -> new NoteNotFoundException(MessageException.NOTE_NOT_FOUND.getMessageException()));
     *
     *   update(Long id, NoteRequest request){
     *      1. Appel la methode findById(id) avec le bon id. si le retour est null alors on lève une exception.
     *      2. Si tout ce passe bien, je setTitle, je setContent
     *      3. j'appel repository.save(note)
     *      4. je retourne une note et je créer une NoteResponse et retourne cette note response.
     *   }
     */
}
