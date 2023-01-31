package com.example.kyrsach.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "id_user"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private java.util.Collection<Kinoteatr> kinoteatrCollection;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private java.util.Collection<Zabronirovannoe> zabronirovannoeCollection;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Обязательное поле")
    @Size(min = 1, max = 50, message = "Колл-во символов 1-50")
    private String surname;
    @NotBlank(message = "Обязательное поле")
    @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов")
    private String name;

    private String middleName;
    @NotNull(message = "Обязательное поле")
    @NotNull(message = "Обязательное поле")
        private int number_passport;
    @NotNull(message = "Обязательное поле")
    private int serial_passport;

    private String username;
    private String password;
    private Boolean active;


    public User() {
    }


    public User(Set<Role> roles, Collection<Kinoteatr> kinoteatrCollection, Collection<Zabronirovannoe> zabronirovannoeCollection, @NotBlank(message = "Обязательное поле") @Size(min = 1, max = 50, message = "Колл-во символов 1-50") String surname, @NotBlank(message = "Обязательное поле") @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов") String name, String middleName, @NotNull(message = "Обязательное поле") @NotNull(message = "Обязательное поле") int number_passport, @NotNull(message = "Обязательное поле") int serial_passport, String username, String password, Boolean active) {
        this.roles = roles;
        this.kinoteatrCollection = kinoteatrCollection;
        this.zabronirovannoeCollection = zabronirovannoeCollection;
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.number_passport = number_passport;
        this.serial_passport = serial_passport;
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Collection<Kinoteatr> getKinoteatrCollection() {
        return kinoteatrCollection;
    }

    public void setKinoteatrCollection(Collection<Kinoteatr> kinoteatrCollection) {
        this.kinoteatrCollection = kinoteatrCollection;
    }

    public Collection<Zabronirovannoe> getZabronirovannoeCollection() {
        return zabronirovannoeCollection;
    }

    public void setZabronirovannoeCollection(Collection<Zabronirovannoe> zabronirovannoeCollection) {
        this.zabronirovannoeCollection = zabronirovannoeCollection;
    }



    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getNumber_passport() {
        return number_passport;
    }

    public void setNumber_passport(int number_passport) {
        this.number_passport = number_passport;
    }

    public int getSerial_passport() {
        return serial_passport;
    }

    public void setSerial_passport(int serial_passport) {
        this.serial_passport = serial_passport;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "roles=" + roles +
                ", kinoteatrCollection=" + kinoteatrCollection +
                ", zabronirovannoeCollection=" + zabronirovannoeCollection +
                ", id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", number_passport=" + number_passport +
                ", serial_passport=" + serial_passport +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                '}';
    }
}




