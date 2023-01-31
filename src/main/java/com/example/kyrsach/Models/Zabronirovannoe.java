package com.example.kyrsach.Models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "[Zabronirovannoe]")
public class Zabronirovannoe {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String dataBronirovniya;
    @NotNull
    private String status;

    @ManyToOne(optional = true)
    private User user;

    @ManyToOne(optional = true)
    private Film Film;

    @ManyToOne(optional = true)
    private Mesto Mesto;

    @OneToMany(mappedBy = "zabronirovannoe", fetch = FetchType.LAZY)
    private java.util.Collection<Bilet> biletCollection;



    public Zabronirovannoe() {
    }

    public Zabronirovannoe(@NotNull String dataBronirovniya, @NotNull String status, com.example.kyrsach.Models.User user1, com.example.kyrsach.Models.Film film, Mesto mesto, Collection<Bilet> biletCollection) {
        this.dataBronirovniya = dataBronirovniya;
        this.status = status;
        user = user1;
        Film = film;
        this.Mesto = mesto;
        this.biletCollection = biletCollection;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Film getFilm() {
        return Film;
    }

    public void setFilm(Film film) {
        this.Film = film;
    }

    public Collection<Bilet> getBiletCollection() {
        return biletCollection;
    }

    public void setBiletCollection(Collection<Bilet> biletCollection) {
        this.biletCollection = biletCollection;
    }

    public Long getId() {
        return id;
    }



    public String getDataBronirovniya() {
        return dataBronirovniya;
    }

    public void setDataBronirovniya(String dataBronirovniya) {
        this.dataBronirovniya = dataBronirovniya;
    }

    public Mesto getMesto() {
        return Mesto;
    }

    public void setMesto(Mesto mesto) {
        this.Mesto = mesto;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
