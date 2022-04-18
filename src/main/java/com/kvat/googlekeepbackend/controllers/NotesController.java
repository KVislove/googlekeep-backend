package com.kvat.googlekeepbackend.controllers;

import com.kvat.googlekeepbackend.dtos.ApiResponseDto;
import com.kvat.googlekeepbackend.dtos.NoteDto;
import com.kvat.googlekeepbackend.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/googlekeep")
@CrossOrigin("*")
public class NotesController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/notes")
    public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto noteDto){
        NoteDto noteDto1= noteService.createNote(noteDto);
        return new ResponseEntity<>(noteDto1, HttpStatus.CREATED);
    }

    @GetMapping("/notes/{noteId}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable Integer noteId){
        NoteDto noteDto=noteService.getNoteById(noteId);
        return ResponseEntity.ok(noteDto);
    }

    @GetMapping("/notes")
    public ResponseEntity<List<NoteDto>> getAllNotes(){
        List<NoteDto> noteDtoList= noteService.getAllNotes();
        return ResponseEntity.ok(noteDtoList);
    }

    @PutMapping("/notes/{noteId}")
    public ResponseEntity<NoteDto> updateNote(@RequestBody NoteDto noteDto,@PathVariable("noteId") Integer noteId){
        NoteDto noteDto1=noteService.updateNote(noteDto,noteId);
        return ResponseEntity.ok(noteDto1);
    }

    @DeleteMapping("/notes/{noteId}")
    @CrossOrigin("*")
    public ResponseEntity<ApiResponseDto> deleteNote(@PathVariable("noteId") Integer noteId){
        noteService.deleteNote(noteId);
        return new ResponseEntity<>(new ApiResponseDto("note deleted successfully",true),HttpStatus.OK);
    }
}
