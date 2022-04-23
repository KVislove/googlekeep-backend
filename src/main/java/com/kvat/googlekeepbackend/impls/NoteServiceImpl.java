package com.kvat.googlekeepbackend.impls;

import com.kvat.googlekeepbackend.dtos.NoteDto;
import com.kvat.googlekeepbackend.entities.Note;
import com.kvat.googlekeepbackend.entities.User;
import com.kvat.googlekeepbackend.exceptions.ResourceNotFoundException;
import com.kvat.googlekeepbackend.repositories.NoteRepository;
import com.kvat.googlekeepbackend.repositories.UserRepository;
import com.kvat.googlekeepbackend.services.NoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public NoteDto createNote(NoteDto noteDto, Integer userId) {
        User user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found with id: "+userId));
        Note note=noteDtoToNote(noteDto);
        note.setCreatedDate(new Date());
        note.setUser(user);
        Note savedNote= noteRepository.save(note);
        NoteDto noteDto1=noteToNoteDto(savedNote);
        return noteDto1;
    }

    @Override
    public List<NoteDto> getAllNotes(Integer userId) {
        User user=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user does not exist with id: "+userId));
        List<Note> notes= noteRepository.findByUser(user);
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
    public void deleteNote(Integer userId,Integer noteId) {
        Note note=noteRepository.findById(noteId).orElseThrow(()-> new ResourceNotFoundException("Note does not exist with id: "+noteId));
        noteRepository.delete(note);
    }

    @Override
    public List<NoteDto> getNotesByUser(Integer userId) {
        return null;
    }

    private NoteDto noteToNoteDto(Note note){
        NoteDto noteDto=modelMapper.map(note,NoteDto.class);
//        NoteDto noteDto=new NoteDto();
//        noteDto.setId(note.getId());
//        noteDto.setTitle(note.getTitle());
//        noteDto.setContent(note.getContent());
        return noteDto;
    }
    private Note noteDtoToNote(NoteDto noteDto){
        Note note=modelMapper.map(noteDto,Note.class);
        return note;
    }
}
