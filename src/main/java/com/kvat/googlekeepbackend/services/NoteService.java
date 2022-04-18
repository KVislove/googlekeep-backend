package com.kvat.googlekeepbackend.services;

import com.kvat.googlekeepbackend.dtos.NoteDto;

import java.util.List;

public interface NoteService {
    NoteDto createNote(NoteDto noteDto);
    List<NoteDto> getAllNotes();
    NoteDto getNoteById(Integer noteId);
    NoteDto updateNote(NoteDto noteDto,Integer noteId);
    void deleteNote(Integer noteId);
}
