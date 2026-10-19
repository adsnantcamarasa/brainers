package com.brainers.demo.controller;
import com.brainers.demo.model.domain.Note;
import com.brainers.demo.model.dto.NoteResponse;
import com.brainers.demo.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private NoteService noteService;

    // ___________________ getAll ___________________
    /**
     * Le rôle du test controller avec mockMVC est de vérifier le contrat d'interface(API) entre notre app et les clients(front-end)
     * Détails de ce que fait le controller :
     *
     * 1. Le contrat http (le protocole)
     * • Le type de requête : Est-ce que la route accepte bien du GET ? Si quelqu'un envoie un POST sur /notes, est-ce que l'API renvoie bien une erreur (405 Method Not Allowed) ?
     *• Le code de statut : Quand tout se passe bien, est-ce que l'API répond bien avec un statut 200 OK ? (Parfois, un bug de configuration peut faire cracher une 500 alors que le service a bien travaillé).
     *
     * 2. La sérialisation JSON (Le format des données)
     * • La structure du JSON : Est-ce que Spring réussit à transformer cette liste Java en un tableau JSON [...] ?
     * • Les clés du JSON : Est-ce que les champs de ton objet (ex: id, title, content) sont correctement nommés dans le JSON final ?
     *
     * 3. La gestion du paramètre optionnel (?title=...)
     * Scénario A : Sans paramètre (GET /notes)
     * • Si l'URL est /notes, le contrôleur appelle service.getNotes(null).
     *
     * Scénario B : Avec paramètre (GET /notes?title=Java)
     *  • Si l'URL est /notes?title=Java, le contrôleur extrait bien "Java" et appelle service.getNotes("Java").
     */

    @Test
    public void getAllShouldReturn200() throws Exception {
        // GET "Notes" httpStatus.ok
        mockMvc.perform(get("/notes"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_call_service_with_title_when_title_param_is_present() throws Exception{
        String searchTerms = "Stream";
        when(noteService.getNotes(searchTerms)).thenReturn(List.of(
                new NoteResponse(1L, "Stream en java", "this is a test content", LocalDateTime.now(), LocalDateTime.now())
        ));

        mockMvc.perform(get("/notes")
                .param("title", searchTerms))
                .andExpect(status().isOk());

        verify(noteService).getNotes(searchTerms);
    }


    @Test
    public void should_call_service_with_null_when_title_param_is_absent()throws Exception{
        when(noteService.getNotes(null))
                .thenReturn(List.of(
                        new NoteResponse(1L, "Stream en java", "this is a test content", LocalDateTime.now(), LocalDateTime.now()),
                        new NoteResponse(2L, "DTO en spring",  "this is another test content", LocalDateTime.now(), LocalDateTime.now()),
                        new NoteResponse(2L, "Stream en java",  "this is again, another test content", LocalDateTime.now(), LocalDateTime.now())
                ));

        mockMvc.perform(get("/notes"))
                .andExpect(status().isOk());

        verify(noteService).getNotes(null);
    }
}
