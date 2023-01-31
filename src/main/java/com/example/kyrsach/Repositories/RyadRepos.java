package com.example.kyrsach.Repositories;

import com.example.kyrsach.Models.Film;
import com.example.kyrsach.Models.Kinoteatr;
import com.example.kyrsach.Models.Ryad;
import com.example.kyrsach.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RyadRepos extends CrudRepository<Ryad, Long>{
    public Ryad findBynomerRyada(int nomerRyada);
    List<Ryad> findAllByZalId(Long Id);
}
