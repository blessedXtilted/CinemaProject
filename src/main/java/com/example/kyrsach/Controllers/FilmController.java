package com.example.kyrsach.Controllers;

import com.example.kyrsach.Models.Film;
import com.example.kyrsach.Models.Kinoteatr;
import com.example.kyrsach.Models.Mesto;
import com.example.kyrsach.Models.User;
import com.example.kyrsach.Repositories.FilmRepos;
import com.example.kyrsach.Repositories.KinoteatrRepos;
import com.example.kyrsach.Repositories.MestoRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class FilmController {

    @Autowired
    KinoteatrRepos kinoteatrRepos;
    @Autowired
    MestoRepos mestoRepos;
    @Autowired
    FilmRepos filmRepos;

    Film filmEdit;

    @GetMapping("/ADD/film")
    public String filmAddView(Film films, Model model)
    {
        Iterable<Kinoteatr> kinoteatrs = kinoteatrRepos.findAll();
//        Iterable<Mesto> mestos = mestoRepos.findAll();
        model.addAttribute("films", films);
        model.addAttribute("kinoteatr", kinoteatrs);
//        model.addAttribute("mesto", mestos);

        return ("Film/filmAdd");}

    @PostMapping("/ADD/film")
    public String EmployeeAdd(
            @Valid Film film,
            BindingResult bindingResult,
            Model model,
            @RequestParam String pname
    ) {
        if(bindingResult.hasErrors()){
            Iterable<Kinoteatr> kinoteatrs = kinoteatrRepos.findAll();
//            Iterable<Mesto> mestos = mestoRepos.findAll();
            model.addAttribute("films", film);
            model.addAttribute("kinoteatr", kinoteatrs);
//            model.addAttribute("mesto", mestos);
            return ("Film/filmAdd");}
        Kinoteatr kinoteatr = kinoteatrRepos.findByAdresKinoteatra(pname);
//        Mesto mesto = mestoRepos.findByNomerMesta(gname);
        film.setKinoteatr(kinoteatr);
//        film.setMesto(mesto);

        filmRepos.save(film);
        return ("redirect:/");
    }


    @GetMapping("/Delete/film/{id}")
    public String filmDelete(@PathVariable long id) {
        filmRepos.deleteById(id);
        return("redirect:/");
    }

    @GetMapping("Edit/film/{id}")
    public String filmEdit(Model model, @PathVariable long id) {

        filmEdit = filmRepos.findById(id).orElseThrow();
        model.addAttribute("film", filmEdit);
        Iterable<Kinoteatr> kinoteatrs = kinoteatrRepos.findAll();
//        Iterable<Mesto> mestos = mestoRepos.findAll();
        model.addAttribute("kinoteatr",kinoteatrs);
//        model.addAttribute("mesto", mestos);
        return("film/filmEdit");

    }
    @PostMapping("Edit/film/{id}")
    public String filmEdit(@Valid Film film,
                               BindingResult bindingResult,
                               Model model,
                               @RequestParam String pname
    ){
        if(bindingResult.hasErrors()){
            Iterable<Kinoteatr> kinoteatrs = kinoteatrRepos.findAll();
//            Iterable<Mesto> mestos = mestoRepos.findAll();
            model.addAttribute("kinoteatr",kinoteatrs);
//            model.addAttribute("mesto", mestos);
            return("film/film-edit");}
        Kinoteatr kinoteatr = kinoteatrRepos.findByAdresKinoteatra(pname);
//        Mesto mesto = mestoRepos.findByNomerMesta(gname);
        film.setKinoteatr(kinoteatr);
//        film.setMesto(mesto);
        filmRepos.save(film);

        return("redirect:/");

    }
}
