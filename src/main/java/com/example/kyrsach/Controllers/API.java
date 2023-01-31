package com.example.kyrsach.Controllers;

import com.example.kyrsach.Models.Film;
import com.example.kyrsach.Models.User;
import com.example.kyrsach.Repositories.FilmRepos;
import com.example.kyrsach.Repositories.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@ComponentScan
@Controller
public class API {
    @Autowired
    FilmRepos filmRepos;
    @Autowired
    UserRepos userRepos;
    @PostMapping("/api/users")
    public ResponseEntity<User> CreateUserApi(@Valid @RequestBody User user)
    {
        return ResponseEntity.ok(userRepos.save(user));
    }

    @DeleteMapping("/api/users/del/{id}")
    public ResponseEntity<User> DeleteUserApi(@PathVariable long id) throws Exception
    {

        User user = userRepos.findById(id)
                .orElseThrow(() -> new Exception(""));
        userRepos.delete(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/users/view")
    public ResponseEntity<List<String>> getAllNotes() {
        List<String> users = new ArrayList<>();
        Iterable<User> iu=userRepos.findAll();
        for (User us:iu
        ) {
            users.add(us.toString());
        }
        return ResponseEntity.ok(users);
    }


    @PostMapping("/api/films")
    public ResponseEntity<Film> CreateFilmApi(@Valid @RequestBody Film film)
    {
        return ResponseEntity.ok(filmRepos.save(film));
    }
}
