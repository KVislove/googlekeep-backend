package com.kvat.googlekeepbackend.services;

import com.kvat.googlekeepbackend.dtos.NoteDto;

import java.util.List;

public interface NoteService {
    NoteDto createNote(NoteDto noteDto, Integer userId);
    List<NoteDto> getAllNotes(Integer userId);
    NoteDto getNoteById(Integer noteId);
    NoteDto updateNote(NoteDto noteDto,Integer noteId);
    void deleteNote(Integer userId,Integer noteId);
    List<NoteDto> getNotesByUser(Integer userId);
}
