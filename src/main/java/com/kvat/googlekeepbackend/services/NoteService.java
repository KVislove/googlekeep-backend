package com.kvat.googlekeepbackend.services;

import com.kvat.googlekeepbackend.dtos.NoteDto;
import com.kvat.googlekeepbackend.dtos.NoteResponseDto;

import java.util.List;

public interface NoteService {
    NoteDto createNote(NoteDto noteDto, Integer userId);
    List<NoteDto> getAllNotesByUser(Integer userId);
    NoteDto getNoteById(Integer noteId);
    NoteDto updateNote(NoteDto noteDto,Integer noteId);
    void deleteNote(Integer noteId);
    List<NoteDto> getNotesByUser(Integer userId);
    NoteResponseDto getAllNotes(Integer pageNumber, Integer pageSize,String sortBy, String sortDir);
    List<NoteDto> searchNotesByTitle(String keyword);
}
