package com.kvat.googlekeepbackend.controllers;

import com.kvat.googlekeepbackend.dtos.ApiResponseDto;
import com.kvat.googlekeepbackend.dtos.NoteDto;
import com.kvat.googlekeepbackend.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/googlekeep/notes-api")
@CrossOrigin("*")
public class NotesController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/user/{userId}/notes")
    public ResponseEntity<NoteDto> createNote(@Valid @RequestBody NoteDto noteDto,@PathVariable Integer userId){
        NoteDto noteDto1= noteService.createNote(noteDto, userId);
        return new ResponseEntity<>(noteDto1, HttpStatus.CREATED);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable Integer noteId){
        NoteDto noteDto=noteService.getNoteById(noteId);
        return ResponseEntity.ok(noteDto);
    }

    @GetMapping("/user/{userId}/notes")
    public ResponseEntity<List<NoteDto>> getAllNotes(@PathVariable Integer userId){
        List<NoteDto> noteDtoList= noteService.getAllNotes(userId);
        return ResponseEntity.ok(noteDtoList);
    }


    @PutMapping("/{noteId}")
    public ResponseEntity<NoteDto> updateNote(@Valid @RequestBody NoteDto noteDto,@PathVariable("noteId") Integer noteId){
        NoteDto noteDto1=noteService.updateNote(noteDto,noteId);
        return ResponseEntity.ok(noteDto1);
    }

    @DeleteMapping("/user/{userId}/notes/{noteId}")
    @CrossOrigin("*")
    public ResponseEntity<ApiResponseDto> deleteNote(@PathVariable Integer userId,@PathVariable("noteId") Integer noteId){
        noteService.deleteNote(userId,noteId);
        return new ResponseEntity<>(new ApiResponseDto("note deleted successfully",true),HttpStatus.OK);
    }
}
