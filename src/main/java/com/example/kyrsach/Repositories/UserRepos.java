package com.example.kyrsach.Repositories;

import com.example.kyrsach.Models.User;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface UserRepos extends CrudRepository<User, Long> {
    public User findByUsername(String username);
    public User findBySurname(String surname);
}