package com.brainers.demo.controller;
import com.brainers.demo.exception.MessageException;
import com.brainers.demo.exception.NoteNotFoundException;
import com.brainers.demo.model.dto.NoteResponse;
import com.brainers.demo.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private NoteService noteService;

    // _______________________________________________ getAll __________________________________________________________
    @Test
    public void get_all_empty_should_return_empty_list_with_200() throws Exception{
        when(noteService.getNotes(null)).thenReturn(List.of());

        mockMvc.perform(get("/notes"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

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
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(status().isOk());

        verify(noteService).getNotes(null);
    }

    @Test
    public void should_contain_correct_name_field()throws Exception{
        NoteResponse noteResponse =  new NoteResponse(
                1L,
                "Stream en java",
                "this is a test content",
                LocalDateTime.of(2026, 12, 10, 10, 40, 10),
                LocalDateTime.of(2026, 12, 10, 10, 40, 10));

        when(noteService.getNotes(null))
                .thenReturn(List.of(
                        noteResponse
                ));

        mockMvc.perform(get("/notes"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.length()").value(1),
                        jsonPath("$[0].id").value(1),
                        jsonPath("$[0].title").value("Stream en java"),
                        jsonPath("$[0].content").value("this is a test content"),
                        jsonPath("[0].createdAt").value("2026-12-10T10:40:10"),
                        jsonPath("[0].lastUpdateAt").value("2026-12-10T10:40:10")
                );
    }

    // ______________________________________________ getById __________________________________________________________
    @Test
    public void should_return_correct_id() throws Exception{
        when(noteService.findById(42L))
                        .thenReturn(new NoteResponse(7L, "Stream in java", "This is a content", LocalDateTime.now(), LocalDateTime.now()));

        mockMvc.perform(get("/notes/42"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7));

        verify(noteService).findById(42L);
    }


    @Test
    public void should_return_note_not_foud_exception_in_body_when_id_does_not_exist() throws Exception{
        when(noteService.findById(99L))
                .thenThrow(new NoteNotFoundException(MessageException.NOTE_NOT_FOUND.getMessageException()));

        mockMvc.perform(get("/notes/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value(MessageException.NOTE_NOT_FOUND.getMessageException()));
    }
}
