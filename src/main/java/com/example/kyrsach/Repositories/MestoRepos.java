package com.example.kyrsach.Repositories;

import com.example.kyrsach.Models.Kinoteatr;
import com.example.kyrsach.Models.Mesto;
import com.example.kyrsach.Models.Ryad;
import com.example.kyrsach.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MestoRepos extends CrudRepository<Mesto, Long>{
    public Mesto findByNomerMesta(Integer nomerMesta);
    List<Mesto> findAllByRyadId(Long id);
}
