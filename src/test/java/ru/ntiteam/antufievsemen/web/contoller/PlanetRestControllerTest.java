package ru.ntiteam.antufievsemen.web.contoller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.ntiteam.antufievsemen.entity.Lord;
import ru.ntiteam.antufievsemen.entity.Planet;
import ru.ntiteam.antufievsemen.repository.PlanetRepository;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class PlanetRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanetRepository planetRepository;

    @BeforeEach
    public void resetMocks() {
        Mockito.reset(planetRepository);
    }

    @Test
    public void getPlanetsApiTest() throws Exception {
        Lord lord1 = new Lord("Mix", 11L);
        lord1.setId(1L);
        Lord lord2 = new Lord("Kok", 12312L);
        lord2.setId(2L);
        Planet planet1 = new Planet("Mars", lord1);
        planet1.setId(1L);
        Planet planet2 = new Planet("Earth", lord2);
        planet2.setId(2L);
        List<Planet> planetList = new ArrayList<>();
        planetList.add(planet1);
        planetList.add(planet2);
        Mockito.when(planetRepository.findAll()).thenReturn(planetList);
        String jsonRequest = "[{\"id\":1,\"name\":\"Mars\",\"lord\":{\"id\":1,\"name\":\"Mix\",\"years\":11}}," +
                "{\"id\":2,\"name\":\"Earth\",\"lord\":{\"id\":2,\"name\":\"Kok\",\"years\":12312}}]";
        mockMvc.perform(get("/planet"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(jsonRequest));
    }

    @Test
    public void getPlanetsNullApiTest() throws Exception{
        Mockito.when(planetRepository.findAll()).thenReturn(null);
        mockMvc.perform(get("/planet"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void getPlanetApiTest() throws Exception {
        Lord lord = new Lord("Max", 11L);
        Planet planet = new Planet("Mars", lord);
        planet.setId(1L);
        lord.setId(1L);
        Optional<Planet> planetOptional = Optional.of(planet);
        String jsonResponse = "";
        Mockito.when(planetRepository.findById(Mockito.eq(planet.getId()))).thenReturn(planetOptional);
        mockMvc.perform(get(String.format("/planet/%s", planet.getId())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Mars")))
                .andExpect(jsonPath("$.lord.id", is(1)))
                .andExpect(jsonPath("$.lord.name", is("Max")))
                .andExpect(jsonPath("$.lord.years", is(11)));
    }

    @Test
    public void getPlanetNullApiTest() throws Exception {
        Long planetId = 1L;
        mockMvc.perform(get(String.format("/planet/%s", planetId)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void addPlanetApiTest() throws Exception {
        Lord lord = new Lord();
        lord.setId(1L);
        Planet planet = new Planet("Mars", lord);
        String jsonRequest = "{\"name\":\"Mars\",\"lord\":{\"id\":1}}";
        Mockito.when(planetRepository.saveAndFlush(Mockito.eq(planet))).thenReturn(planet);
        mockMvc.perform(post("/planet")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRequest).characterEncoding(StandardCharsets.UTF_8.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Mars")))
                .andExpect(jsonPath("$.lord.id", is(1)));
    }

    @Test
    public void deletePlanetApiTest() throws Exception {
        Lord lord = new Lord("Max", 11L);
        lord.setId(1L);
        Planet planet = new Planet("Mars", lord);
        planet.setId(1L);
        Optional<Planet> optional = Optional.of(planet);
        Mockito.when(planetRepository.findById(Mockito.eq(planet.getId()))).thenReturn(optional);
        mockMvc.perform(delete(String.format("/planet/%s", planet.getId())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

    }

    @Test
    public void deletePlanetNullApiTest() throws Exception {

        Mockito.when(planetRepository.findById(Mockito.eq(1L))).thenReturn(Optional.empty());
        mockMvc.perform(delete(String.format("/planet/%s", 1L)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void updatePlanetApiTest() throws Exception {
        Lord lord = new Lord("Mix", 11L);
        lord.setId(1L);
        Planet planet = new Planet("Mars", lord);
        planet.setId(1L);
        String jsonRequest = "{\"id\":1,\"name\":\"Mars\",\"lord\":{\"id\":1,\"name\":\"Mix\",\"years\":11}}";
        Mockito.when(planetRepository.saveAndFlush(Mockito.eq(planet))).thenReturn(planet);
        mockMvc.perform(put(String.format("/planet/%s", 1L))
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRequest).characterEncoding(StandardCharsets.UTF_8.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Mars")))
                .andExpect(jsonPath("$.lord.id", is(1)))
                .andExpect(jsonPath("$.lord.name", is("Mix")))
                .andExpect(jsonPath("$.lord.years", is(11)));
    }
}
