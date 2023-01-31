package com.example.kyrsach.Models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "[Ryad]")
public class Ryad {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int Kolichestvo_mest;
    @NotNull
    private int nomerRyada;

    @OneToMany(mappedBy = "ryad", fetch = FetchType.EAGER)
    private java.util.Collection<Mesto> MestoCollection;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Zal zal;



    public Ryad() {
    }

    public Ryad(@NotNull int kolichestvo_mest, @NotNull int nomerRyada, Collection<Mesto> mestoCollection, Zal zal) {
        Kolichestvo_mest = kolichestvo_mest;
        this.nomerRyada = nomerRyada;
        MestoCollection = mestoCollection;
        this.zal = zal;
    }

    public int getKolichestvo_mest() {
        return Kolichestvo_mest;
    }

    public void setKolichestvo_mest(int kolichestvo_mest) {
        Kolichestvo_mest = kolichestvo_mest;
    }

    public Collection<Mesto> getMestoCollection() {
        return MestoCollection;
    }

    public void setMestoCollection(Collection<Mesto> mestoCollection) {
        MestoCollection = mestoCollection;
    }

    public Zal getZal() {
        return zal;
    }

    public void setZal(Zal zal) {
        this.zal = zal;
    }

    public Long getId() {
        return id;
    }



    public int getNomerRyada() {
        return nomerRyada;
    }

    public void setNomerRyada(int nomerRyada) {
        this.nomerRyada = nomerRyada;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
