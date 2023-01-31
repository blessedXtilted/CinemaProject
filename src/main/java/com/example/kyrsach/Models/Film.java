package com.example.kyrsach.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;


@Entity
@Table(name = "[film]")
public class Film {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nameFilm;
    @NotNull
    private String data_nachala;
    @NotNull
    private String dlitelnost_filma;
    @NotNull
    private String url;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Kinoteatr kinoteatr;

    @OneToMany(mappedBy = "Film", fetch = FetchType.LAZY)
    private Collection<Zabronirovannoe> zabronirovannoeCollection;




    public Film() {
    }

    public Film(@NotNull String nameFilm, @NotNull String data_nachala, @NotNull String dlitelnost_filma, @NotNull String url, Kinoteatr kinoteatr, Collection<Zabronirovannoe> zabronirovannoeCollection) {
        this.nameFilm = nameFilm;
        this.data_nachala = data_nachala;
        this.dlitelnost_filma = dlitelnost_filma;
        this.url = url;
        this.kinoteatr = kinoteatr;
        this.zabronirovannoeCollection = zabronirovannoeCollection;
    }

    public String getName_film() {
        return nameFilm;
    }

    public void setName_film(String name_film) {
        this.nameFilm = name_film;
    }

    public String getData_nachala() {
        return data_nachala;
    }

    public void setData_nachala(String data_nachala) {
        this.data_nachala = data_nachala;
    }

    public String getDlitelnost_filma() {
        return dlitelnost_filma;
    }

    public void setDlitelnost_filma(String dlitelnost_filma) {
        this.dlitelnost_filma = dlitelnost_filma;
    }

    public Kinoteatr getKinoteatr() {
        return kinoteatr;
    }

    public void setKinoteatr(Kinoteatr kinoteatr) {
        this.kinoteatr = kinoteatr;
    }

    public Collection<Zabronirovannoe> getZabronirovannoeCollection() {
        return zabronirovannoeCollection;
    }

    public void setZabronirovannoeCollection(Collection<Zabronirovannoe> zabronirovannoeCollection) {
        this.zabronirovannoeCollection = zabronirovannoeCollection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameFilm() {
        return nameFilm;
    }

    public void setNameFilm(String nameFilm) {
        this.nameFilm = nameFilm;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
