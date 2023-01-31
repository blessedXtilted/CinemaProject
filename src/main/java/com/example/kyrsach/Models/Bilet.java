package com.example.kyrsach.Models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "[bilet]")
public class Bilet {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Integer nomerBileta;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Zabronirovannoe zabronirovannoe;

    public Bilet(@NotNull Integer nomer_bileta, Zabronirovannoe zabronirovannoe) {
        this.nomerBileta = nomer_bileta;
        this.zabronirovannoe = zabronirovannoe;
    }

    public Bilet() {
    }

    public Integer getNomer_bileta() {
        return nomerBileta;
    }

    public void setNomer_bileta(Integer nomer_bileta) {
        this.nomerBileta = nomer_bileta;
    }

    public Zabronirovannoe getZabronirovannoe() {
        return zabronirovannoe;
    }

    public void setZabronirovannoe(Zabronirovannoe zabronirovannoe) {
        this.zabronirovannoe = zabronirovannoe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
