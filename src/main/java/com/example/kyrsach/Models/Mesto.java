package com.example.kyrsach.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "[Mesto]")
public class Mesto {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int nomerMesta;

    @OneToMany(mappedBy = "Mesto", fetch = FetchType.EAGER)
    private java.util.Collection<Zabronirovannoe> zabronirovannoeCollection;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Ryad ryad;



    public Mesto() {
    }

    public Mesto(@NotNull int nomerMesta, Collection<Zabronirovannoe> zabronirovannoeCollection, Ryad ryad) {
        this.nomerMesta = nomerMesta;
        this.zabronirovannoeCollection = zabronirovannoeCollection;
        this.ryad = ryad;
    }


    public Ryad getRyad() {
        return ryad;
    }

    public void setRyad(Ryad ryad) {
        this.ryad = ryad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNomerMesta() {
        return nomerMesta;
    }

    public void setNomerMesta(int nomerMesta) {
        this.nomerMesta = nomerMesta;
    }

    public Collection<Zabronirovannoe> getZabronirovannoeCollection() {
        return zabronirovannoeCollection;
    }

    public void setZabronirovannoeCollection(Collection<Zabronirovannoe> zabronirovannoeCollection) {
        this.zabronirovannoeCollection = zabronirovannoeCollection;
    }
}
