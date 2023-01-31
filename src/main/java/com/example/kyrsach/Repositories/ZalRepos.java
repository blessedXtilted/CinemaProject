package com.example.kyrsach.Repositories;

import com.example.kyrsach.Models.Film;
import com.example.kyrsach.Models.Kinoteatr;
import com.example.kyrsach.Models.User;
import com.example.kyrsach.Models.Zal;
import org.springframework.data.repository.CrudRepository;

public interface ZalRepos extends CrudRepository<Zal, Long>{
    public Zal findByNomerZala(String nomerZala);
}
