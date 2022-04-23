package com.kvat.googlekeepbackend.repositories;

import com.kvat.googlekeepbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByUsernameAndPassword(String userName,String password);
}
