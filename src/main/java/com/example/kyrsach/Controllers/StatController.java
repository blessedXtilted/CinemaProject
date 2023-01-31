package com.example.kyrsach.Controllers;

import com.example.kyrsach.Models.Film;
import com.example.kyrsach.Models.Stat;
import com.example.kyrsach.Models.Zabronirovannoe;
import com.example.kyrsach.Repositories.FilmRepos;
import com.example.kyrsach.Repositories.ZabronirovannoeRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class StatController {
    @Autowired
    FilmRepos filmRepos;
    @Autowired
    ZabronirovannoeRepos zabronirovannoeRepos;

    @GetMapping("/stat")
    public String getStat(Model model){
        return("stat");
    }

    @GetMapping("/api")
    public ResponseEntity<ArrayList<Stat>> userallapi(){
        ArrayList<Stat> a = new ArrayList<>();
        Iterable<Zabronirovannoe> zabronirovannoes = zabronirovannoeRepos.findAll();

        for (Zabronirovannoe zabronirovannoe: zabronirovannoes
        ) {
            Stat stat = new Stat();
            stat.setAmount(zabronirovannoeRepos.countByfilm(zabronirovannoe.getFilm().getId()));
            stat.setName(zabronirovannoe.getFilm().getName_film());
            a.add(stat);
        }
        return ResponseEntity.ok().body(a);
    }


    @GetMapping("/BackupExport")
    public String backup()
            throws IOException, InterruptedException {
        String command = String.format("mysqldump -u%s --password=%s --add-drop-table --databases %s -r %s",
                "root", "", "kyrsach", "C:\\OSPanel\\bec.sql");
        Process process = Runtime.getRuntime().exec(command);
        int processComplete = process.waitFor();
        if(processComplete==0)
        {
            return("redirect:/admin");}
        else
        { return("stat");
        }
    }
    @GetMapping("/BackupImport")
    public String backupImport()
            throws IOException, InterruptedException {
        String[] command = new String[]{
                "mysql",
                "-u" + "root",
                "--password=" + "",
                "-e",
                " source " + "C:\\OSPanel\\bec.sql",
                "kyrsach"
        };
        Process runtimeProcess = Runtime.getRuntime().exec(command);
        int processComplete = runtimeProcess.waitFor();
        if(processComplete==0)
        {
            return("redirect:/admin");}
        else
        {
            return("stat");
        }
    }


}
