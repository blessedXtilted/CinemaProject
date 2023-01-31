package com.example.kyrsach.Repositories;

import com.example.kyrsach.Models.Kinoteatr;
import com.example.kyrsach.Models.User;
import com.example.kyrsach.Models.Zabronirovannoe;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ZabronirovannoeRepos extends CrudRepository<Zabronirovannoe, Long>{
    public Zabronirovannoe findByDataBronirovniya(String dataBronirovaniya);

    @Transactional
    @Query("SELECT COUNT(film_id) FROM Zabronirovannoe WHERE film_id=:id")
    public Long countByfilm(@Param("id") long id);

    List<Zabronirovannoe> findByUserId(Long Id);
}
