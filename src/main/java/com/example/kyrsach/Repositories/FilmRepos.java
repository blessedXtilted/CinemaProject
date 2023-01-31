package com.example.kyrsach.Repositories;

import com.example.kyrsach.Models.Bilet;
import com.example.kyrsach.Models.Film;
import com.example.kyrsach.Models.Mesto;
import com.example.kyrsach.Models.User;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmRepos extends CrudRepository<Film, Long>{
    public Film findByNameFilm(String nameFilm);
    List<Film> findAllByKinoteatrId(Long id);
}
