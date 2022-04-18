package com.kvat.googlekeepbackend.repositories;

import com.kvat.googlekeepbackend.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer> {
}
