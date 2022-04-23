package com.kvat.googlekeepbackend.repositories;

import com.kvat.googlekeepbackend.entities.Note;
import com.kvat.googlekeepbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer> {
    List<Note> findByUser(User user);
}
