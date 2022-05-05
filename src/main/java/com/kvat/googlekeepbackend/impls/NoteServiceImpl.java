package com.kvat.googlekeepbackend.impls;

import com.kvat.googlekeepbackend.dtos.NoteDto;
import com.kvat.googlekeepbackend.dtos.NoteResponseDto;
import com.kvat.googlekeepbackend.entities.Note;
import com.kvat.googlekeepbackend.entities.User;
import com.kvat.googlekeepbackend.exceptions.ResourceNotFoundException;
import com.kvat.googlekeepbackend.repositories.NoteRepository;
import com.kvat.googlekeepbackend.repositories.UserRepository;
import com.kvat.googlekeepbackend.services.NoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<NoteDto> getAllNotesByUser(Integer userId) {
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
        Note note=noteRepository.findById(noteId).orElseThrow(()-> new ResourceNotFoundException("Note not found with id: "+noteId));
        note.setContent(noteDto.getContent());
        note.setTitle(noteDto.getTitle());
        Note savedNote=noteRepository.save(note);
        NoteDto noteDto1=noteToNoteDto(savedNote);
        return noteDto1;
    }

    @Override
    public void deleteNote(Integer noteId) {
        Note note=noteRepository.findById(noteId).orElseThrow(()-> new ResourceNotFoundException("Note does not exist with id: "+noteId));
        noteRepository.delete(note);
    }

    @Override
    public List<NoteDto> getNotesByUser(Integer userId) {
        return null;
    }

    @Override
    public NoteResponseDto getAllNotes(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase("ASC")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
         Pageable p=PageRequest.of(pageNumber,pageSize, sort);
         Page<Note> notePage=noteRepository.findAll(p);
         List<Note> notes=notePage.getContent();
         List<NoteDto> noteDtoList=notes.stream().map((note)-> noteToNoteDto(note)).collect(Collectors.toList());
         NoteResponseDto noteResponseDto=new NoteResponseDto();
         noteResponseDto.setNoteDtoList(noteDtoList);
         noteResponseDto.setPageNumber(notePage.getNumber());
         noteResponseDto.setPageSize(notePage.getSize());
         noteResponseDto.setTotalElements(notePage.getTotalElements());
         noteResponseDto.setTotalPages(notePage.getTotalPages());
         noteResponseDto.setLastPage(notePage.isLast());
        return noteResponseDto;
    }

    @Override
    public List<NoteDto> searchNotesByTitle(String keyword) {
        List<Note> notes=noteRepository.searchByTitle("%"+keyword+"%s");
        List<NoteDto> noteDtos= notes.stream().map(note -> noteToNoteDto(note)).collect(Collectors.toList());
        return noteDtos;
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
