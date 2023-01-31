package com.example.kyrsach.Repositories;


import com.example.kyrsach.Models.Kinoteatr;
import com.example.kyrsach.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface KinoteatrRepos extends CrudRepository<Kinoteatr, Long>{
    public Kinoteatr findByAdresKinoteatra(String adresKinoteatra);
}
