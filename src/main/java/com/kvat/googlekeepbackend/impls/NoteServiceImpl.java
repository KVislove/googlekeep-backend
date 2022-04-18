package com.kvat.googlekeepbackend.impls;

import com.kvat.googlekeepbackend.dtos.NoteDto;
import com.kvat.googlekeepbackend.entities.Note;
import com.kvat.googlekeepbackend.exceptions.ResourceNotFoundException;
import com.kvat.googlekeepbackend.repositories.NoteRepository;
import com.kvat.googlekeepbackend.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public NoteDto createNote(NoteDto noteDto) {
        Note note=noteDtoToNote(noteDto);
        Note savedNote= noteRepository.save(note);
        NoteDto noteDto1=noteToNoteDto(savedNote);
        return noteDto1;
    }

    @Override
    public List<NoteDto> getAllNotes() {
        List<Note> notes= noteRepository.findAll();
        List<NoteDto> noteDtos= notes.stream().map(note -> noteToNoteDto(note)).collect(Collectors.toList());
        return noteDtos;
    }

    @Override
    public NoteDto getNoteById(Integer noteId) {
        Note note=noteRepository.findById(noteId).orElseThrow(()->new ResourceNotFoundException("Note does not exist with id: "+noteId));
        NoteDto noteDto=noteToNoteDto(note);
        return noteDto;
    }

    @Override
    public NoteDto updateNote(NoteDto noteDto, Integer noteId) {
        return null;
    }

    @Override
    public void deleteNote(Integer noteId) {
        Note note=noteRepository.findById(noteId).orElseThrow(()-> new ResourceNotFoundException("Note does not exist with id: "+noteId));
        noteRepository.delete(note);
    }

    private NoteDto noteToNoteDto(Note note){
        NoteDto noteDto=new NoteDto();
        noteDto.setId(note.getId());
        noteDto.setTitle(note.getTitle());
        noteDto.setContent(note.getContent());
        return noteDto;
    }
    private Note noteDtoToNote(NoteDto noteDto){
        Note note=new Note();
        note.setId(noteDto.getId());
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        return note;
    }
}
