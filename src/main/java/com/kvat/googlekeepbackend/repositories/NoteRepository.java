package com.kvat.googlekeepbackend.repositories;

import com.kvat.googlekeepbackend.entities.Note;
import com.kvat.googlekeepbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer> {
    List<Note> findByUser(User user);
//    can't use below method as there is bug with this hibernate version so writing out own custom methods
//    List<Note> findByTitleContaining(String keyword);
    @Query("select p from Note p where p.title like :key")
    List<Note> searchByTitle(@Param("key") String keyword);
}
