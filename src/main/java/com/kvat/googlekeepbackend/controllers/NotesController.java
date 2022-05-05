package com.kvat.googlekeepbackend.controllers;

import com.kvat.googlekeepbackend.dtos.ApiResponseDto;
import com.kvat.googlekeepbackend.dtos.NoteDto;
import com.kvat.googlekeepbackend.dtos.NoteResponseDto;
import com.kvat.googlekeepbackend.services.FileService;
import com.kvat.googlekeepbackend.services.NoteService;
import com.kvat.googlekeepbackend.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/users/{userId}/notes")
    public ResponseEntity<NoteDto> createNote(@Valid @RequestBody NoteDto noteDto,@PathVariable Integer userId){
        NoteDto noteDto1= noteService.createNote(noteDto, userId);
        return new ResponseEntity<>(noteDto1, HttpStatus.CREATED);
    }

    @GetMapping("/notes")
    public ResponseEntity<NoteResponseDto> getAllNotes(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false)Integer pageNumber, @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize, @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy, @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){
        NoteResponseDto noteResponseDto=noteService.getAllNotes(pageNumber,pageSize, sortBy, sortDir);
        return new ResponseEntity<>(noteResponseDto,HttpStatus.OK);
    }

    @GetMapping("/notes/{noteId}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable Integer noteId){
        NoteDto noteDto=noteService.getNoteById(noteId);
        return ResponseEntity.ok(noteDto);
    }

    @GetMapping("/users/{userId}/notes")
    public ResponseEntity<List<NoteDto>> getAllNotesByUser(@PathVariable Integer userId){
        List<NoteDto> noteDtoList= noteService.getAllNotesByUser(userId);
        return ResponseEntity.ok(noteDtoList);
    }


    @PutMapping("/notes/{noteId}")
    public ResponseEntity<NoteDto> updateNote(@Valid @RequestBody NoteDto noteDto,@PathVariable("noteId") Integer noteId){
        NoteDto noteDto1=noteService.updateNote(noteDto,noteId);
        return ResponseEntity.ok(noteDto1);
    }

    @DeleteMapping("/notes/{noteId}")
    @CrossOrigin("*")
    public ResponseEntity<ApiResponseDto> deleteNote(@PathVariable("noteId") Integer noteId){
        noteService.deleteNote(noteId);
        return new ResponseEntity<>(new ApiResponseDto("note deleted successfully",true),HttpStatus.OK);
    }
    @GetMapping("/notes/search/{keyword}")
    public ResponseEntity<List<NoteDto>> searchPostByTitle(@PathVariable String keyword){
        List<NoteDto>noteDtos=noteService.searchNotesByTitle(keyword);
        return new ResponseEntity<>(noteDtos,HttpStatus.OK);
    }
}
