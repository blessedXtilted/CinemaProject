package com.example.kyrsach.Controllers;

import com.example.kyrsach.Models.*;
import com.example.kyrsach.Repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class HomeController {
    @Autowired
    FilmRepos filmRepos;
    @Autowired
    KinoteatrRepos kinoteatrRepos;
    @Autowired
    MestoRepos mestoRepos;
    @Autowired
    RyadRepos ryadRepos;
    @Autowired
    UserRepos userRepos;
    @Autowired
    ZabronirovannoeRepos zabronirovannoeRepos;
    @Autowired
    ZalRepos zalRepos;


    @GetMapping("/admin")
    public String admin(Model model) {
        Iterable<Film> films = filmRepos.findAll();
        model.addAttribute(("films"), films);
        return ("/index");
    }

    @GetMapping("/")
    public String index(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            User user = userRepos.findByUsername(auth.getName());
            if (user.getRoles().contains(Role.USER)) {
                return ("redirect:/Edit/zabronirovannoe");
            }
            if (user.getRoles().contains(Role.ADMIN)) {
                return ("redirect:/admin");
            }
        }

        return ("/login");
    }

    @GetMapping("/filter")
    public String filmsFilter(Model model,
                              @RequestParam(name = "search") String naim) {

        Film film = filmRepos.findByNameFilm(naim);
        model.addAttribute("searchResult", film);
        return ("filter");
    }

    @GetMapping("/Delete/film")
    public String DeleteProvider(Model model) {
        Iterable<Film> films = filmRepos.findAll();
        model.addAttribute(("film"), films);
        return ("/index");
    }

    @GetMapping("/Edit/film")
    public String Editfilm(Model model) {
        Iterable<Film> films = filmRepos.findAll();
        model.addAttribute(("films"), films);
        Iterable<Kinoteatr> kinoteatrs = kinoteatrRepos.findAll();
        model.addAttribute(("kinoteatrs"), kinoteatrs);
//        Iterable<Mesto> mestos = mestoRepos.findAll();
//        model.addAttribute(("mestos"), mestos);
        return ("Film/filmEdit");
    }

    @GetMapping("/Delete/user")
    public String DeleteUser(Model model) {
        Iterable<User> users = userRepos.findAll();
        model.addAttribute(("users"), users);
        return ("/User/User-edit");
    }

    @GetMapping("/Edit/user")
    public String Edituser(Model model) {
        Iterable<User> users = userRepos.findAll();
        model.addAttribute(("users"), users);
        return ("User/User-edit");
    }

    @GetMapping("/Edit/kinoteatr")
    public String Editkinoteatr(Model model) {
        Iterable<Kinoteatr> kinoteatrs = kinoteatrRepos.findAll();
        model.addAttribute(("kinoteatrs"), kinoteatrs);
        return ("Client/kinoteatrChoose");
    }

    @GetMapping("/Edit/clientFilm/{id}")
    public String EditclientFilm(Model model, @PathVariable Long id) {
        Iterable<Film> films = filmRepos.findAllByKinoteatrId(id);
        model.addAttribute(("films"), films);
        model.addAttribute("kinoteatr", kinoteatrRepos.findById(id).orElseThrow());
        return ("Client/MovieChoose");
    }

    @GetMapping("/Edit/Zal/{id}/{id1}")
    public String EditZal(Model model, @PathVariable Long id, @PathVariable Long id1) {
        Iterable<Zal> zals = zalRepos.findAll();
        model.addAttribute(("zals"), zals);
        model.addAttribute("kinoteatr", kinoteatrRepos.findById(id).orElseThrow());
        model.addAttribute("movie", filmRepos.findById(id1).orElseThrow());
        return ("Client/ZalChoose");
    }

    @GetMapping("/Edit/Ryad/{id}/{id1}/{id2}")
    public String EditZal(Model model, @PathVariable Long id, @PathVariable Long id1, @PathVariable Long id2) {
        Iterable<Ryad> ryads = ryadRepos.findAllByZalId(id2);
        model.addAttribute(("ryads"), ryads);
        model.addAttribute("kinoteatr", kinoteatrRepos.findById(id).orElseThrow());
        model.addAttribute("movie", filmRepos.findById(id1).orElseThrow());
        model.addAttribute("zal", zalRepos.findById(id2).orElseThrow());
        return ("Client/RyadChoose");
    }

    @GetMapping("/Edit/Mestos/{id}/{id1}/{id2}/{id3}")
    public String EditMestos(Model model, @PathVariable Long id, @PathVariable Long id1, @PathVariable Long id2, @PathVariable Long id3) {
        Iterable<Mesto> mestos = mestoRepos.findAllByRyadId(id3);
        model.addAttribute(("mestos"), mestos);
        model.addAttribute("kinoteatr", kinoteatrRepos.findById(id).orElseThrow());
        model.addAttribute("movie", filmRepos.findById(id1).orElseThrow());
        model.addAttribute("zal", zalRepos.findById(id2).orElseThrow());
        model.addAttribute("ryad", ryadRepos.findById(id3).orElseThrow());
        return ("Client/MestoChoose");
    }

    @GetMapping("/Edit/zabronirovannoe/{id}/{id1}/{id2}/{id3}/{id4}")
    public String Editzabronirovannoe(Model model, @PathVariable Long id, @PathVariable Long id1, @PathVariable Long id2, @PathVariable Long id3, @PathVariable Long id4) {
        model.addAttribute("kinoteatr", kinoteatrRepos.findById(id).orElseThrow());
        model.addAttribute("film", filmRepos.findById(id1).orElseThrow());
        model.addAttribute("zal", zalRepos.findById(id2).orElseThrow());
        model.addAttribute("ryad", ryadRepos.findById(id3).orElseThrow());
        model.addAttribute("mesto", mestoRepos.findById(id4).orElseThrow());
        return ("Client/Zabronnirovannoe");
    }

    @PostMapping("/Edit/zabronirovannoe/{id1}/{id4}")
    public String Editzabronirovannoes(Model model, Principal principal, @PathVariable Long id1, @PathVariable Long id4) {
        Zabronirovannoe zabronirovannoe = new Zabronirovannoe();
        zabronirovannoe.setFilm(filmRepos.findById(id1).orElseThrow());
        zabronirovannoe.setMesto(mestoRepos.findById(id4).orElseThrow());
        String data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        zabronirovannoe.setDataBronirovniya(data);
        zabronirovannoe.setStatus("Активен");
        zabronirovannoe.setUser(userRepos.findByUsername(principal.getName()));
        zabronirovannoeRepos.save(zabronirovannoe);



        return ("redirect:/");
    }

    @GetMapping("/Edit/zabronirovannoe")
    public String Editzabronirovannoe(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepos.findByUsername(auth.getName());
        Iterable<Zabronirovannoe> zabronirovannoes = zabronirovannoeRepos.findByUserId(user.getId());
        model.addAttribute("zabronirovannoes", zabronirovannoes);
        return ("Client/SpisokBroney");
    }

    @GetMapping("/Delete/broni/{id}")
    public String broniDelete(@PathVariable long id) {
        zabronirovannoeRepos.deleteById(id);
        return ("redirect:/Edit/zabronirovannoe");
    }
}
