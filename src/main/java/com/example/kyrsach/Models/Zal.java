package com.example.kyrsach.Models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "[Zal]")
public class Zal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int Kolichestvo_ryadov;
    @NotNull
    private int nomerZala;


    @OneToMany(mappedBy = "zal", fetch = FetchType.EAGER)
    private java.util.Collection<Ryad> RyadCollection;



    public Zal() {
    }

    public Zal(@NotNull int kolichestvo_ryadov, @NotNull int nomer_zala, Collection<Ryad> ryadCollection) {
        Kolichestvo_ryadov = kolichestvo_ryadov;
        nomerZala = nomer_zala;
        RyadCollection = ryadCollection;
    }

    public int getKolichestvo_ryadov() {
        return Kolichestvo_ryadov;
    }

    public void setKolichestvo_ryadov(int kolichestvo_ryadov) {
        Kolichestvo_ryadov = kolichestvo_ryadov;
    }

    public Collection<Ryad> getRyadCollection() {
        return RyadCollection;
    }

    public void setRyadCollection(Collection<Ryad> ryadCollection) {
        RyadCollection = ryadCollection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id1) {
        this.id = id1;
    }

    public int getNomer_zala() {
        return nomerZala;
    }

    public void setNomer_zala(int nomer_zala) {
        nomerZala = nomer_zala;
    }
}
