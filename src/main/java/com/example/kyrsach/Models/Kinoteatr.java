package com.example.kyrsach.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;


@Entity
@Table(name = "[Kinoteatr]")
public class Kinoteatr {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String adresKinoteatra;
    @NotNull
    private int kolichestvo_zalov;

    @ManyToOne(optional = true)
    private User user;

    @OneToMany(mappedBy = "kinoteatr", fetch = FetchType.EAGER)
    private java.util.Collection<Film> FilmCollection;



    public Kinoteatr() {
    }

    public Kinoteatr(@NotNull String adresKinoteatra, @NotNull int kolichestvo_zalov, User user, Collection<Film> filmCollection) {
        this.adresKinoteatra = adresKinoteatra;
        this.kolichestvo_zalov = kolichestvo_zalov;
        this.user = user;
        FilmCollection = filmCollection;
    }

    public String getAdres_kinoteatra() {
        return adresKinoteatra;
    }

    public void setAdres_kinoteatra(String adres_kinoteatra) {
        this.adresKinoteatra = adres_kinoteatra;
    }

    public int getKolichestvo_zalov() {
        return kolichestvo_zalov;
    }

    public void setKolichestvo_zalov(int kolichestvo_zalov) {
        this.kolichestvo_zalov = kolichestvo_zalov;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresKinoteatra() {
        return adresKinoteatra;
    }

    public void setAdresKinoteatra(String adresKinoteatra) {
        this.adresKinoteatra = adresKinoteatra;
    }

    public Collection<Film> getFilmCollection() {
        return FilmCollection;
    }

    public void setFilmCollection(Collection<Film> filmCollection) {
        FilmCollection = filmCollection;
    }
}
