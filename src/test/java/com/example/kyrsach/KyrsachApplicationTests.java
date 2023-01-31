package com.example.kyrsach;

import com.example.kyrsach.Controllers.API;
import com.example.kyrsach.Models.Film;
import com.example.kyrsach.Models.User;
import com.example.kyrsach.Repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@SpringBootTest
//@ExtendWith(SpringExtension.class)
@WebMvcTest(API.class)
@RunWith(SpringRunner.class)
class KyrsachApplicationTests {
    @MockBean
    UserRepos userRepos;
    @Autowired
    private MockMvc mocMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private DataSource dataSource;
    @MockBean
    FilmRepos filmRepos;
    @MockBean
    KinoteatrRepos kinoteatrRepos;
    @MockBean
    MestoRepos mestoRepos;
    @MockBean
    RyadRepos ryadRepos;
    @MockBean
    ZalRepos zalRepos;
    @MockBean
    ZabronirovannoeRepos zabronirovannoeRepos;


    @Test
    void T_Registartion() throws Exception {
        User us = new User();
        us.setActive(true);
        us.setPassword("Qwerty1234");
        us.setUsername("Qwerty1234");
        us.setName("Андрей");
        us.setSerial_passport(1234);
        us.setNumber_passport(123456);
        us.setSurname("Рулев");
        us.setMiddleName("Алексеевич");


        Mockito.when(userRepos.save(us)).thenReturn(Optional.of(us).orElseThrow());
        mocMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)
                .content(objectMapper.writeValueAsString(us)))
                .andExpect(status().isOk());
    }

    @Test
    void T_RegistartionFailed() throws Exception {
        User us = new User();
        us.setActive(true);
        us.setPassword("Qwerty1234");
        us.setUsername("");
        us.setName("Андрей");
        us.setSerial_passport(1234);
        us.setNumber_passport(123456);
        us.setSurname("Рулев");
        us.setMiddleName("Алексеевич");

        Mockito.when(userRepos.save(us)).thenReturn(Optional.of(us).orElseThrow());
        mocMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)
                .content(objectMapper.writeValueAsString(us)))
                .andExpect(status().isOk());
    }


    @Test
    void CreateFilm_whenCreateFilm_thenStatus200() throws Exception {
        Film us = new Film();
        us.setName_film("bob");
        us.setData_nachala("19.12.2022");
        us.setDlitelnost_filma("2 часа 41 минут");
        us.setUrl("https://mobimg.b-cdn.net/v3/fetch/d0/d0a69ce499f28ff0a592875aeb4607ed.jpeg");
        Mockito.when(filmRepos.save(us)).thenReturn(Optional.of(us).orElseThrow());
        mocMvc.perform(post("/api/films")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)
                .content(objectMapper.writeValueAsString(us)))
                .andExpect(status().isOk());
    }

}
